package com.news.model;
/**
 * enum for maintaining status
 */
public enum StatusResponse {
	/**
	 * Success or Error enum
	 */
	SUCCESS("Success"),
	ERROR("Error");
	final private String status;
	StatusResponse(String status){
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
}
