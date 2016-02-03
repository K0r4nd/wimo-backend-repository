package com.k0r4nd.wimo.api.model;

public class Order {
	
	private Long id;
	
	private String name;
	
	private String trackingId;
	
	private String shipperName;
	
	private String destinationAddress;
	
	private Long shippingDate;
	
	private Long deliveryDate;
	
	private String deliveryState;
	
	private String deliveryStateText;
	
	private Long lastStatusUpdate;
	
	private boolean sendByUser;
	
	public Order(String trackingId) {
		this.trackingId=trackingId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public String getShipperName() {
		return shipperName;
	}

	public void setShipperName(String shipperName) {
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

	public String getDeliveryState() {
		return deliveryState;
	}

	public void setDeliveryState(String deliveryState) {
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

	public boolean isSendByUser() {
		return sendByUser;
	}

	public void setSendByUser(boolean sendByUser) {
		this.sendByUser = sendByUser;
	}
	
	
	

}
