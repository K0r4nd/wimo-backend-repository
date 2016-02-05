package com.k0r4nd.wimo.service;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.k0r4nd.wimo.api.model.Order;
import com.k0r4nd.wimo.ext.api.ShipperServiceFacade;

public class FetchOrderInfoJob implements Job {

	public FetchOrderInfoJob() {
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobInfos = context.getMergedJobDataMap();
		ShipperServiceFacade service = (ShipperServiceFacade) jobInfos.get("serviceFacade");

		OrderService orderService = (OrderService) jobInfos.get("orderService");

		PushService pushService = (PushService) jobInfos.get("pushService");

		Order order = (Order) jobInfos.get("order");

		Order newOrder = service.findByTrackingId(order.getTrackingId());
		Long lastExecution = context.getPreviousFireTime().getTime();
		if (order.getLastStatusUpdate() > lastExecution) {
			System.out.println("pushing Order " +order.getId() );
			newOrder.setId(order.getId());
			orderService.save(newOrder);
			pushService.pushOrderUpdate(newOrder);
		}

	}

}
