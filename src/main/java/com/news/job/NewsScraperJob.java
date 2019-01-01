package com.news.job;

public interface NewsScraperJob {

	/**
	 * This will be cron job to process news sources. 
	 */
	public abstract void batchProcessor();
}
