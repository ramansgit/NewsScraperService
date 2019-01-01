package com.news.scraper;

import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.news.exception.PageScrapingException;
import com.news.model.Article;
import com.news.search.SearchHandler;

/**
 * ArticleScrapingHandler for fetching articles information from pages 
 *
 */
public class ArticleScrapingHandler {

	private static SearchHandler searchHandler = null;

	public ArticleScrapingHandler() {
		searchHandler = SearchHandler.getInstance();
	}

	/**
	 * 
	 * This method takes pageUrl as Parameter and fetches list of articles from the
	 * given pageUrl.
	 * 
	 * @throws PageScrapingException
	 */
	public void processPages(String pageUrl) throws PageScrapingException {
		try {
			Document document = Jsoup.connect(pageUrl).get();
			Elements archiveList = document.select("ul[class^=\"archive-list\"]");
			try {
				for (Element archive : archiveList) {
					Elements articles = archive.select("a[href]");
					try {
						for (Element article : articles) {
							processArticles(article.attr("abs:href"));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new PageScrapingException(e.getMessage());
		}
	}

	/**
	 * 
	 * for the given articleUrl, fetches article information like author, title,
	 * description.
	 * 
	 * @throws PageScrapingException
	 */
	public void processArticles(String articleUrl) throws PageScrapingException {
		Document document;
		try {
			document = Jsoup.connect(articleUrl).get();
			// System.out.println(document);
			Elements articleTitle = document.select("h1[class^=\"title\"]");
			System.out.println("title =>" + articleTitle.text());
			Elements articleDesc = document.select("h2[class^=\"intro\"]");
			System.out.println("desc =>" + articleDesc.text());
			Elements author = document.select("a[class^=\"auth-nm lnk\"]");
			System.out.println("author =>" + author.text());
			searchHandler.indexArticles(
					new Article(UUID.randomUUID().toString(), author.text(), articleTitle.text(), articleDesc.text()));

		} catch (Exception e) {
			e.printStackTrace();
			throw new PageScrapingException(e.getMessage());
		}
	}

	/**
	 * 
	 * from archved news link fetches articles information.
	 * description.
	 * 
	 * @throws PageScrapingException
	 */
	public void getArchivedNews() throws PageScrapingException {
		Document document;
		try {
			document = Jsoup.connect("https://www.thehindu.com/archive/").get();
			Elements pages = document.select("div[id^=\"archiveWebContainer\"]");
			Elements monthList = pages.select("ul[class^=\"archiveMonthList\"]");
			try {
				for (Element monthElement : monthList) {
					Elements pageUrlElement = monthElement.select("a[href]");
					try {
						for (Element pageUrl : pageUrlElement) {
							getDaysForMonth(pageUrl.attr("abs:href"));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new PageScrapingException(e.getMessage());
		}
	}

	/**
	 * 
	 * for the given monthurl, fetches days in the month
	 * description.
	 * 
	 * @throws PageScrapingException
	 */
	public void getDaysForMonth(String url) throws PageScrapingException {
		Document document;
		try {
			document = Jsoup.connect(url).get();
			Elements table = document.select("table[class^=\"archiveTable\"]");
			Elements activeDays = table.select("td a[class^=\"ui-state-default\"]");
			try {
				for (Element activeDay : activeDays) {
					Elements pageUrlElement = activeDay.select("a[href]");
					try {
						for (Element pageUrl : pageUrlElement) {
							processPages(pageUrl.attr("abs:href"));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new PageScrapingException(e.getMessage());
		}
	}
}
