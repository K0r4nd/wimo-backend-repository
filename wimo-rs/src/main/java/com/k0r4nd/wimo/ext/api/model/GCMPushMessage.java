package com.k0r4nd.wimo.ext.api.model;

public class GCMPushMessage {

	private String to;

	private Object data;

	private GCMNotification notification;

	public GCMPushMessage(String to, Object data) {
		this.to = to;
		this.data = data;
	}

	public GCMPushMessage(String to, Object data, GCMNotification notification) {
		this(to, data);
		this.notification = notification;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public GCMNotification getNotification() {
		return notification;
	}

	public void setNotification(GCMNotification notification) {
		this.notification = notification;
	}

}
