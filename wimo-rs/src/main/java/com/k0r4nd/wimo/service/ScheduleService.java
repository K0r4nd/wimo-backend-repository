package com.k0r4nd.wimo.service;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.JobKey.jobKey;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.k0r4nd.wimo.api.model.DeliveryStatus;
import com.k0r4nd.wimo.api.model.Order;
import com.k0r4nd.wimo.config.ApplicationConfig;
import com.k0r4nd.wimo.ext.api.ShipperServiceFacade;

@Component
public class ScheduleService {

	private Scheduler scheduler;

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private ApplicationConfig config;

	@Autowired
	private ShipperServiceFacade dhlServiceFacade;

	@Autowired
	private ShipperServiceFacade hermesServiceFacade;

	@Autowired
	private PushService pushService;

	@Autowired
	private OrderService orderService;

	private Integer interval;

	public void startJob(Order order) {
		ShipperServiceFacade facade;
		switch (order.getShipperName()) {
		case DHL:
			facade = dhlServiceFacade;
			break;
		case HERMES:
			facade = hermesServiceFacade;
		default:
			facade = dhlServiceFacade;
		}
		JobDataMap jobData = new JobDataMap();
		jobData.put("orderId", order.getId());
		jobData.put("trackingId", order.getTrackingId());
		jobData.put("shipper", order.getShipperName());
		JobDetail job = newJob(FetchOrderInfoJob.class).withIdentity(order.getId(), "orderInfoPush")
				.usingJobData(jobData).build();

		Trigger trigger = newTrigger().withIdentity(order.getId(), "orderInfoPushTrigger").startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(interval).repeatForever())
				.build();

		try {
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void checkAndStopJob(Order order) {
		Long now = new Date().getTime();
		Long timeElapsedSinceLastUpdate = now - order.getLastStatusUpdate();
		if (order.getDeliveryState() == DeliveryStatus.DELIVERED || order.getDeliveryState() == DeliveryStatus.CANCELLED
				|| timeElapsedSinceLastUpdate > config.getMaximumJobDurationSinceLastUpdate()) {
			try {
				scheduler.deleteJob(jobKey(order.getId()));
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@PostConstruct
	void init() {
		SchedulerFactory factory = new StdSchedulerFactory();
		try {
			Scheduler scheduler = factory.getScheduler();
			scheduler.getContext().put("servletContext", servletContext);
			scheduler.start();
			this.scheduler = scheduler;
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		interval = config.getScheduleInterval();
	}

}
