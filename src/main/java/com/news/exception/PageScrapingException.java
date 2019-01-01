package com.news.exception;
/**
 * provides exception handling
 *
 */
public class PageScrapingException extends Exception{
	private String code;

	public PageScrapingException(String code, String msg) {
		super(msg);
		this.code = code;
	}

	public PageScrapingException(String msg) {
		super(msg);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
