package com.hotnews.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotnews.model.NewsModel;
import com.hotnews.model.response.NewsModelResponse;
import com.hotnews.service.NewsService;

@RestController("/api/news")
public class NewsAPI {
	
	@Autowired
	private NewsService newsService;
	
	@GetMapping
	public NewsModelResponse getNews(@RequestParam(value = "page", required = false) Integer currentPage, 
									@RequestParam(value = "limit", required = false) Integer limit) {
		NewsModelResponse newsModelResponse = new NewsModelResponse();
		if (currentPage != null && limit != null) {
			newsModelResponse.setCurrentPage(currentPage);
			newsModelResponse.setTotalPage((int) Math.ceil(newsService.totalItem() / limit));
			Pageable pageable = new PageRequest(currentPage - 1, limit);
			newsModelResponse.setNewsModel(newsService.findAll(pageable));
		} else {
			newsModelResponse.setNewsModel(newsService.findAll());
		}
		return newsModelResponse;
	}
	
	@PostMapping
	public NewsModel createNews(@RequestBody NewsModel newsModel) {
		return newsService.save(newsModel);
	}
	
	@PutMapping(value = "/{id}")
	public NewsModel updateNews(@RequestBody NewsModel newsModel, @PathVariable("id") Long id) {
		newsModel.setId(id);
		return newsService.save(newsModel);
	}
	
	@DeleteMapping
	public void deleteNew(@RequestBody Long[] ids) {
		newsService.delete(ids);
	}
}