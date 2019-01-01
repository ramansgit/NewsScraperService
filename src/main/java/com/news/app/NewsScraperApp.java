package com.news.app;

import com.news.api.NewsScraperSearchApi;
import com.news.api.NewsScraperSearchController;
import com.news.job.NewsScraperJobController;

import spark.Spark;

/**
 * NewsScraperApp class for routing api request to controller.
 */
public class NewsScraperApp {

	public static void main(String[] args) {

		System.out.println("started");	
		Spark.port(4560); // runs on port 4567
		enableCORS("*", "GET,PUT,DELETE,POST,OPTIONS", "Access-Control-Allow-Origin,"
				+ "Content-Type,Access-Control-Allow-Headers,Authorization,X-Requested-With,jwt-access-token");
		NewsScraperSearchApi searchApi = new NewsScraperSearchController();
		String API_CONTEXT = "/api/v1/newsscraper/search/articles";
		spark.Spark.get(API_CONTEXT, (request, response) -> {
			response.type("application/json");
			String title = request.queryParams("title");
			String description = request.queryParams("description");
			String author = request.queryParams("author");
			if(author !=null && !author.isEmpty()) {
			return searchApi.getArticlesForAuthor(author);
			}else if(title !=null && description != null && !description.isEmpty() && !title.isEmpty()) {
				return searchApi.getArticlesByTitleAndDescription(title, 
						description);
			}else {
				return searchApi.getAuthorList(Integer.parseInt(request.queryParams("start")), 
						Integer.parseInt(request.queryParams("page")));
			}
		});
		try {
			System.out.println("Indexing started");	
			new NewsScraperJobController().batchProcessor();
			
			}catch(Exception e) {
				e.printStackTrace();
			}
		System.out.println("Indexing Finished");	
	}

	/**
	 * allows CORS for calling api from different domain.
	 * @param origin
	 * @param methods
	 * @param headers
	 */
	private static void enableCORS(final String origin, final String methods, final String headers) {

		spark.Spark.options("/*", (request, response) -> {

			String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
			if (accessControlRequestHeaders != null) {
				response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
			}

			String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
			if (accessControlRequestMethod != null) {
				response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
			}

			return "OK";
		});

		spark.Spark.before((request, response) -> {

			response.header("Access-Control-Allow-Origin", origin);
			response.header("Access-Control-Request-Method", methods);
			response.header("Access-Control-Allow-Headers", headers);
			// Note: this may or may not be necessary in your particular application
			response.type("application/json");
		});
	}

}
