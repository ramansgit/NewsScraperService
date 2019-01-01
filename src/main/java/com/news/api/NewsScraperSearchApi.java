package com.news.api;

public interface NewsScraperSearchApi {
	/**
	 * This api returns all available authors in the system. This will be paginated
	 * by start and pageSize
	 * 
	 * @param start
	 * @param pageSize
	 * @return
	 */
	public abstract String getAuthorList(int start, int pageSize);

	/**
	 * This api returns list of articles for the given author
	 * 
	 * @param authorName
	 * @return
	 */
	public abstract String getArticlesForAuthor(String authorName);

	/**
	 * This api returns list of authors matches filter condition
	 * 
	 * @param title
	 * @param description
	 * @return
	 */
	public abstract String getArticlesByTitleAndDescription(String title, String description);
}
