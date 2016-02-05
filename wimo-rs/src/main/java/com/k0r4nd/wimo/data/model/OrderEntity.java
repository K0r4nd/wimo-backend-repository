package com.k0r4nd.wimo.data.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.k0r4nd.wimo.api.model.DeliveryStatus;
import com.k0r4nd.wimo.api.model.Order;
import com.k0r4nd.wimo.api.model.Shipper;

@Entity
@Table(name = "orders")
public class OrderEntity {

	@Id
	private String id;

	@ManyToOne
	private UserEntity user;

	private String trackingId;

	@Enumerated(EnumType.STRING)
	private Shipper shipperName;

	private String destinationAddress;

	private Long shippingDate;

	private Long deliveryDate;

	@Enumerated(EnumType.STRING)
	private DeliveryStatus status;

	private String statusText;

	private Long lastStatusUpdate;

	private Boolean sentByUser;

	public OrderEntity(Order order) {
		this.id = order.getId()==null? UUID.randomUUID().toString():order.getId();
		this.trackingId = order.getTrackingId();
		this.shipperName = order.getShipperName();
		this.destinationAddress = order.getDestinationAddress();
		this.shippingDate = order.getShippingDate();
		this.deliveryDate = order.getDeliveryDate();
		this.status = order.getDeliveryState();
		this.statusText = order.getDeliveryStateText();
		this.lastStatusUpdate = order.getLastStatusUpdate();
		this.sentByUser = order.isSentByUser();
	}

	public OrderEntity() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
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

	public DeliveryStatus getStatus() {
		return status;
	}

	public void setStatus(DeliveryStatus status) {
		this.status = status;
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

}
