package com.news.api;

import com.google.gson.Gson;
import com.news.model.ApiResponse;
import com.news.search.SearchHandler;
import com.news.util.ResponseUtil;

/**
 * 
 * NewsScraperSearchController for getting search results
 *
 */
public class NewsScraperSearchController implements NewsScraperSearchApi {
	private static SearchHandler instance = null;

	public NewsScraperSearchController() {
		instance = SearchHandler.getInstance();
	}

	/**
	 * fetches list of authors
	 */
	@Override
	public String getAuthorList(int start, int pageSize) {
		ApiResponse resp = null;
		try {
			resp = ResponseUtil.handleSuccessResp(instance.getPaginatedAuthorList(start, pageSize));
		} catch (Exception e) {
			e.printStackTrace();
			return new Gson().toJson(ResponseUtil.handleException(e));
		}
		return new Gson().toJson(resp);
	}

	/**
	 * fetches articles by title and description
	 */
	@Override
	public String getArticlesByTitleAndDescription(String title, String description) {
		ApiResponse resp = null;
		try {
			resp = ResponseUtil.handleSuccessResp(instance.filterByTitleAndDescription(title, description));
		} catch (Exception e) {
			e.printStackTrace();
			return new Gson().toJson(ResponseUtil.handleException(e));
		}
		return new Gson().toJson(resp);
	}

	/**
	 * fetches articles for given author
	 */
	@Override
	public String getArticlesForAuthor(String authorName) {
		ApiResponse resp = null;
		try {
			resp = ResponseUtil.handleSuccessResp(instance.filterByAuthor(authorName));
		} catch (Exception e) {
			e.printStackTrace();
			return new Gson().toJson(ResponseUtil.handleException(e));
		}
		return new Gson().toJson(resp);
	}

}
