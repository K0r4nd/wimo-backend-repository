package com.k0r4nd.wimo.service;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import javax.annotation.PostConstruct;

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

import com.k0r4nd.wimo.api.model.Order;
import com.k0r4nd.wimo.config.ApplicationConfig;
import com.k0r4nd.wimo.ext.api.ShipperServiceFacade;

@Component
public class ScheduleService {

	private Scheduler scheduler;

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
		jobData.put("serviceFacade", facade);
		jobData.put("orderService", orderService);
		jobData.put("pushService", pushService);
		jobData.put("order", order);
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

	@PostConstruct
	void init() {
		SchedulerFactory factory = new StdSchedulerFactory();
		try {
			Scheduler scheduler = factory.getScheduler();
			scheduler.start();
			this.scheduler = scheduler;
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		interval = config.getScheduleInterval();
	}

}
