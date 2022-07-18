package com.hotnews.model.response;

import java.util.ArrayList;
import java.util.List;

import com.hotnews.model.NewsModel;

public class NewsModelResponse {
	private int currentPage;
	private int totalPage;
	private List<NewsModel> newsModel = new ArrayList<>();
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<NewsModel> getNewsModel() {
		return newsModel;
	}
	public void setNewsModel(List<NewsModel> newsModel) {
		this.newsModel = newsModel;
	}
}
