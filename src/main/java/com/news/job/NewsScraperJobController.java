package com.news.job;

import com.news.exception.PageScrapingException;
import com.news.scraper.ArticleScrapingHandler;
import com.news.util.NewsScraperConfig;

public  class NewsScraperJobController implements NewsScraperJob {

	/**
	 * batch process for fetching articles from given pages
	 */
	@Override
	public void batchProcessor() {
		ArticleScrapingHandler scrapingHandler = new ArticleScrapingHandler();
		
		try {
			if(NewsScraperConfig.PAGEONLY_MODE) {
				scrapingHandler.processPages(NewsScraperConfig.PAGE_TO_PROCESS);
			}else {
				scrapingHandler.getArchivedNews();
			}
			 
		} catch (PageScrapingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
