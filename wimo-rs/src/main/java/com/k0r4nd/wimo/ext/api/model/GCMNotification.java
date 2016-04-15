package com.k0r4nd.wimo.ext.api.model;

public class GCMNotification {

	private String title;

	private String body;

	private String icon;

	public GCMNotification(String title, String body, String icon) {
		this.title = title;
		this.body = body;
		this.icon = icon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
