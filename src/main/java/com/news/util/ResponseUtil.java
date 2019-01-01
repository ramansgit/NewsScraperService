package com.news.util;


public class ResponseUtil {

	/**
	 * handle all type of exceptions
	 * 
	 * @param e
	 * @return
	 */
	public static ApiResponse handleException(Exception e) {
		if (e instanceof PageScrapingException) {
			return new ApiResponse(NewsScraperConfig.PAGE_SCRAPING_EXCEPTION, Status.INTERNAL_SERVER_ERROR.getStatusCode(),
					StatusResponse.ERROR, e.getMessage());

		}
		if (e instanceof DocumentSearchException) {
			return new ApiResponse(NewsScraperConfig.DOCUMENT_SEARCHING_EXCEPTION, Status.INTERNAL_SERVER_ERROR.getStatusCode(),
					StatusResponse.ERROR, e.getMessage());

		}
		if (e instanceof DocumentIndexingException) {
			return new ApiResponse(NewsScraperConfig.DOCUMENT_INDEXING_EXCEPTION, Status.INTERNAL_SERVER_ERROR.getStatusCode(),
					StatusResponse.ERROR, e.getMessage());

		}
		return new ApiResponse(NewsScraperConfig.GENERIC_EXCEPTION, Status.INTERNAL_SERVER_ERROR.getStatusCode(),
				StatusResponse.ERROR, e.getMessage());

	}

	/**
	 * returns success response
	 * 
	 * @param data
	 * @return
	 */
	public static ApiResponse handleSuccessResp(Object data) {
		return new ApiResponse(NewsScraperConfig.SUCCESS, StatusResponse.SUCCESS, Status.OK.getStatusCode(), data);

	}
}
