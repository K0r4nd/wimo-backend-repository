package com.k0r4nd.wimo.service;

import java.util.Date;

import javax.servlet.ServletContext;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.k0r4nd.wimo.api.model.Order;
import com.k0r4nd.wimo.api.model.Shipper;
import com.k0r4nd.wimo.data.model.OrderEntity;
import com.k0r4nd.wimo.ext.api.DhlServiceFacade;
import com.k0r4nd.wimo.ext.api.HermesServiceFacade;
import com.k0r4nd.wimo.ext.api.ShipperServiceFacade;

public class FetchOrderInfoJob implements Job {

	@Autowired
	private OrderService orderService;
	@Autowired
	private PushService pushService;
	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private DhlServiceFacade dhlServiceFacade;

	@Autowired
	private HermesServiceFacade hermesServiceFacade;

	public FetchOrderInfoJob() {
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ServletContext servletContext;
		try {
			servletContext = (ServletContext) context.getScheduler().getContext().get("servletContext");
		} catch (SchedulerException e) {
			e.printStackTrace();
			return;
		}
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, servletContext);
		JobDataMap jobInfos = context.getMergedJobDataMap();

		String trackingId = jobInfos.getString("trackingId");
		String orderId = jobInfos.getString("orderId");
		Shipper shipper = (Shipper) jobInfos.get("shipper");
		ShipperServiceFacade shipperService;
		switch (shipper) {
		case DHL:
			shipperService = dhlServiceFacade;
			break;
		case HERMES:
			shipperService = hermesServiceFacade;
			break;
		default:
			System.out.println("SHIPPERTYPE NOT FOUND");
			return;
		}

		Order newOrder = shipperService.findByTrackingId(trackingId);
		// for first schedule use current time
		Long lastExecution = new Date().getTime();
		// if job was fired before use previous job fireTime
		if (context.getPreviousFireTime() != null) {
			lastExecution = context.getPreviousFireTime().getTime();
		}
		if (newOrder.getLastStatusUpdate() > lastExecution) {
			System.out.println("pushing Order " + orderId);
			newOrder.setId(orderId);
			OrderEntity update = orderService.update(newOrder);
			pushService.pushOrderUpdate(update);
		}
		scheduleService.checkAndStopJob(newOrder);
	}
}
