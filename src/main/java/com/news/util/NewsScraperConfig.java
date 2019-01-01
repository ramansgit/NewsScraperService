package com.news.util;

public class NewsScraperConfig {
// code
	public static final String GENERIC_EXCEPTION = "GENERIC_EXCEPTION";
	public static final String PAGE_SCRAPING_EXCEPTION = "PAGE_SCRAPING_EXCEPTION";
	public static final String DOCUMENT_INDEXING_EXCEPTION = "DOCUMENT_INDEXING_EXCEPTION";
	public static final String DOCUMENT_SEARCHING_EXCEPTION = "DOCUMENT_SEARCHING_EXCEPTION";
	public static final String SUCCESS = "SUCCESS";
	public static final String SOLR_ENDPOINT = "http://localhost:8983/solr/articles_core";
	
	//page to process
	public static final String PAGE_TO_PROCESS="https://www.thehindu.com/archive/web/2018/12/30/";
	public static final boolean PAGEONLY_MODE=true;
}
