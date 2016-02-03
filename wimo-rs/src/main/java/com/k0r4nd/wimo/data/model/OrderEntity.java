package com.k0r4nd.wimo.data.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.k0r4nd.wimo.api.model.Order;

@Entity
@Table(name = "orders")
public class OrderEntity {

	@javax.persistence.Id
	private String id;

	@ManyToOne
	private UserEntity user;

	private String name;

	private String trackingId;

	private String shipperName;

	private String destinationAddress;

	private Long shippingDate;

	private Long deliveryDate;

	private String status;

	private String statusText;

	private Long lastStatusUpdate;

	private Boolean sentByUser;

	public OrderEntity(Order order) {
		this.id = UUID.randomUUID().toString();
		this.name = order.getName();
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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
