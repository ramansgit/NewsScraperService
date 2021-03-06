package com.news.model;

import org.apache.solr.client.solrj.beans.Field;

public class Article {

	public Article() {

	}

	public Article(String articleId, String author, String title, String description) {
		this.articleId = articleId;
		this.author = author;
		this.title = title;
		this.description = description;
	}
	@Field("articleId")
	private String articleId;
	@Field("author")
	private String author;
	@Field("title")
	private String title;
	@Field("description")
	private String description;

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", author=" + author + ", title=" + title + ", description="
				+ description + "]";
	}

}
