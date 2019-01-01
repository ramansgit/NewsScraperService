package com.news.model;
/**
 * returns Api response to the client app.
 */
public class ApiResponse {

	private String code;
	private int statusCode;
	private StatusResponse status;
	private String message;
	private Object data;
	
	public ApiResponse() {
		
	}
	public ApiResponse(String code, int statusCode, StatusResponse status, String message) {
		this.code = code;
		this.statusCode = statusCode;
		this.status = status;
		this.message = message;
	}
	public ApiResponse(String code,StatusResponse status, int statusCode, Object data) {
		this.code = code;
		this.statusCode = statusCode;
		this.status = status;
		this.data = data;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public StatusResponse getStatus() {
		return status;
	}
	public void setStatus(StatusResponse status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ApiResponse [code=" + code + ", statusCode=" + statusCode + ", status=" + status + ", message="
				+ message + ", data=" + data + "]";
	}
}
