package com.news.exception;

public class DocumentSearchException extends Exception{
	private String code;

	public DocumentSearchException(String code, String msg) {
		super(msg);
		this.code = code;
	}

	public DocumentSearchException(String msg) {
		super(msg);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
