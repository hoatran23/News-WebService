package com.hotnews.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hotnews.model.NewsModel;

public interface NewsService {
	NewsModel save(NewsModel newsModel);
	NewsModel update(NewsModel newsModel);
	void delete(Long[] ids);
	List<NewsModel> findAll(Pageable pageable);
	List<NewsModel> findAll();
	int totalItem();
}
