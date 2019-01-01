package com.news.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.news.exception.DocumentIndexingException;
import com.news.exception.DocumentSearchException;
import com.news.model.Article;
import com.news.util.NewsScraperConfig;

public class SearchHandler {
	private static HttpSolrClient solrClient = null;
	private static SearchHandler instance =  null;

	private SearchHandler(){	
		
		solrClient = new HttpSolrClient.Builder(NewsScraperConfig.SOLR_ENDPOINT).build();
		solrClient.setParser(new XMLResponseParser());
	}
	
	/**
	 * singleton pattern for getting SearchHandler instance
	 * @return
	 */
	public  static SearchHandler getInstance(){
		if(instance == null) {
			synchronized (SearchHandler.class) {
				if(instance == null) {
					instance = new SearchHandler();
				}
			}
		}
		return instance;
	}
	
	/**
	 * saves articles in the search system
	 * @param article
	 * @throws DocumentIndexingException
	 */
	public void indexArticles(Article article) throws DocumentIndexingException{
		try {
			System.out.println("Indexing done for document =>" +article);
			solrClient.addBean(article);
			solrClient.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw  new DocumentIndexingException(e.getMessage());
		} 
		
	}
	
	/**
	 * filters by author 
	 * @param author
	 * @return
	 * @throws DocumentSearchException
	 */
	public List<Article> filterByAuthor(String author) throws DocumentSearchException{
		List<Article> articleList = new ArrayList<>();
		try {
		SolrQuery query = new SolrQuery();
		query.set("q", "author:"+author);
		QueryResponse response = solrClient.query(query);
		SolrDocumentList docList = response.getResults();
		contructArticle(articleList, docList);
		}catch(Exception e) {
			throw  new DocumentSearchException(e.getMessage());
		}
		return articleList;
	}

	/**
	 * construct article from response
	 * @param articleList
	 * @param docList
	 */
	private void contructArticle(List<Article> articleList, SolrDocumentList docList) {
		for (SolrDocument doc : docList) {
			articleList.add(new Article(doc.getFieldValue("articleId").toString(),
					doc.getFieldValue("author")!= null ? doc.getFieldValue("author").toString() : "",
					doc.getFieldValue("title")!= null ? doc.getFieldValue("title").toString() : "",
					doc.getFieldValue("description")!= null ? doc.getFieldValue("description").toString() : ""));
		}
	}
	
	/**
	 * filters by tile and description
	 * @param title
	 * @param description
	 * @return
	 * @throws DocumentSearchException
	 */
	public List<Article> filterByTitleAndDescription(String title, String description) throws DocumentSearchException{
		List<Article> articleList = new ArrayList<>();
		try {
		SolrQuery query = new SolrQuery("title:"+title +" AND " + "description:" +description);
		QueryResponse response = solrClient.query(query);
		SolrDocumentList docList = response.getResults();
		contructArticle(articleList, docList);
		}catch(Exception e) {
			e.printStackTrace();
			throw  new DocumentSearchException(e.getMessage());
		}
		return articleList;
	}
	
	/**
	 * filters by article which has author
	 * @param start
	 * @param pageSize
	 * @return
	 * @throws DocumentSearchException
	 */
	public List<Article> getPaginatedAuthorList(int start, int pageSize) throws DocumentSearchException{
		List<Article> articleList = new ArrayList<>();
		try {
		SolrQuery query = new SolrQuery("author:['' TO *]");
        query.setStart(start);
        query.setRows(pageSize);
		QueryResponse response = solrClient.query(query);
		SolrDocumentList docList = response.getResults();
		contructArticle(articleList, docList);
		}catch(Exception e) {
			e.printStackTrace(); 
			throw  new DocumentSearchException(e.getMessage());
		}
		return articleList;
	}
}
