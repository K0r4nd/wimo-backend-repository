package com.k0r4nd.wimo.ext.api.model;

import com.k0r4nd.wimo.api.model.DeliveryStatus;

public class DhlResponse {

	private String trackingId;

	private String shipperName;

	private String destinationAddress;

	private Long shippingDate;

	private Long deliveryDate;

	private DeliveryStatus deliveryState;

	private String statusText;

	private Long lastStatusUpdate;

	private Boolean sentByUser;

	public DhlResponse(String trackingId, String shipperName, String destinationAddress, Long shippingDate,
			Long deliveryDate, DeliveryStatus status, String statusText, Long lastStatusUpdate, Boolean sentByUser) {
		this.trackingId = trackingId;
		this.shipperName = shipperName;
		this.destinationAddress = destinationAddress;
		this.shippingDate = shippingDate;
		this.deliveryDate = deliveryDate;
		this.deliveryState = status;
		this.statusText = statusText;
		this.lastStatusUpdate = lastStatusUpdate;
		this.sentByUser = sentByUser;
	}

	public DhlResponse() {

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

	public DeliveryStatus getDeliveryState() {
		return deliveryState;
	}

	public void setDeliveryState(DeliveryStatus deliveryState) {
		this.deliveryState = deliveryState;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public Long getLastStatusUpdate() {
		return lastStatusUpdate;
	}

	public void setLastStatusUpdate(Long lastStatusUpdate) {
		this.lastStatusUpdate = lastStatusUpdate;
	}

	public Boolean getSentByUser() {
		return sentByUser;
	}

	public void setSentByUser(Boolean sentByUser) {
		this.sentByUser = sentByUser;
	}

	public static class DhlResponseBuilder {
		private String trackingId;

		private String shipperName;

		private String destinationAddress;

		private Long shippingDate;

		private Long deliveryDate;

		private DeliveryStatus deliveryState;

		private String statusText;

		private Long lastStatusUpdate;

		private Boolean sentByUser;

		public DhlResponseBuilder trackingId(String trackingId) {
			this.trackingId = trackingId;
			return this;
		}

		public DhlResponseBuilder shipperName(String shipperName) {
			this.shipperName = shipperName;
			return this;
		}

		public DhlResponseBuilder destinationAddress(String destinationAddress) {
			this.destinationAddress = destinationAddress;
			return this;
		}

		public DhlResponseBuilder shippingDate(Long shippingDate) {
			this.shippingDate = shippingDate;
			return this;
		}

		public DhlResponseBuilder deliveryDate(Long deliveryDate) {
			this.deliveryDate = deliveryDate;
			return this;
		}

		public DhlResponseBuilder deliveryState(DeliveryStatus deliveryState) {
			this.deliveryState = deliveryState;
			return this;
		}

		public DhlResponseBuilder statusText(String statusText) {
			this.statusText = statusText;
			return this;
		}

		public DhlResponseBuilder lastStatusUpdate(Long lastStatusUpdate) {
			this.lastStatusUpdate = lastStatusUpdate;
			return this;
		}

		public DhlResponseBuilder sentByUser(Boolean sentByUser) {
			this.sentByUser = sentByUser;
			return this;
		}

		public DhlResponse build() {
			return new DhlResponse(trackingId, shipperName, destinationAddress, shippingDate, deliveryDate,
					deliveryState, statusText, lastStatusUpdate, sentByUser);
		}

	}

}
