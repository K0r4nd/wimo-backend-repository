package com.k0r4nd.wimo.api.model;

import com.k0r4nd.wimo.data.model.OrderEntity;

public class Order {

	private String id;

	private String trackingId;

	private Shipper shipperName;

	private String destinationAddress;

	private Long shippingDate;

	private Long deliveryDate;

	private DeliveryStatus deliveryState;

	private String deliveryStateText;

	private Long lastStatusUpdate;

	private boolean sentByUser;

	public Order(OrderEntity entity) {
		this.id = entity.getId();
		this.trackingId = entity.getTrackingId();
		this.shipperName = entity.getShipperName();
		this.destinationAddress = entity.getDestinationAddress();
		this.shippingDate = entity.getShippingDate();
		this.deliveryDate = entity.getDeliveryDate();
		this.deliveryState = entity.getStatus();
		this.deliveryStateText = entity.getStatusText();
		this.lastStatusUpdate = entity.getLastStatusUpdate();
		this.sentByUser = entity.getSentByUser();
	}

	public Order() {

	}

	public Order(String trackingId) {
		this.trackingId = trackingId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public Shipper getShipperName() {
		return shipperName;
	}

	public void setShipperName(Shipper shipperName) {
		this.shipperName = shipperName;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public Long getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Long shippingDate) {
		this.shippingDate = shippingDate;
	}

	public Long getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Long deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public DeliveryStatus getDeliveryState() {
		return deliveryState;
	}

	public void setDeliveryState(DeliveryStatus deliveryState) {
		this.deliveryState = deliveryState;
	}

	public String getDeliveryStateText() {
		return deliveryStateText;
	}

	public void setDeliveryStateText(String deliveryStateText) {
		this.deliveryStateText = deliveryStateText;
	}

	public Long getLastStatusUpdate() {
		return lastStatusUpdate;
	}

	public void setLastStatusUpdate(Long lastStatusUpdate) {
		this.lastStatusUpdate = lastStatusUpdate;
	}

	public boolean isSentByUser() {
		return sentByUser;
	}

	public void setSentByUser(boolean sendByUser) {
		this.sentByUser = sendByUser;
	}
}
