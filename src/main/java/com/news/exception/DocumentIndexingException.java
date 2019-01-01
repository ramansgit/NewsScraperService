package com.news.exception;

/**
 * provides exception handling
 *
 */
public class DocumentIndexingException extends Exception {

	private String code;

	public DocumentIndexingException(String code, String msg) {
		super(msg);
		this.code = code;
	}

	public DocumentIndexingException(String msg) {
		super(msg);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}