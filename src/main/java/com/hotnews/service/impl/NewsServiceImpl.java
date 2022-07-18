package com.hotnews.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hotnews.converter.NewsConverter;
import com.hotnews.model.NewsModel;
import com.hotnews.repository.NewsRepository;
import com.hotnews.repository.entity.NewsEntity;
import com.hotnews.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {
	
	@Autowired
	private NewsRepository newsRepository;
	
	@Autowired
	private NewsConverter newsConverter;

	@Override
	public NewsModel save(NewsModel newsModel) {
		NewsEntity newsEntity = new NewsEntity();
		if (newsModel.getId() != null) {
			NewsEntity newsEntityOld = newsRepository.findById(newsModel.getId()).orElse(new NewsEntity());
			newsEntity = newsConverter.convertModelToEntity(newsModel); 
			newsEntity = newsConverter.convertEntityToEntity(newsEntity, newsEntityOld);
		} else {
			newsEntity = newsConverter.convertModelToEntity(newsModel);
		}
		newsEntity = newsRepository.save(newsEntity);
		return newsConverter.convertEntityToModel(newsEntity);
	}

	@Override
	public NewsModel update(NewsModel newsModel) {
		NewsEntity newsEntityOld = newsRepository.findById(newsModel.getId()).orElse(new NewsEntity());
		NewsEntity newsEntity = newsConverter.convertModelToEntity(newsModel); 
		newsEntity = newsConverter.convertEntityToEntity(newsEntity, newsEntityOld);
		newsEntity = newsRepository.save(newsEntity);
		return newsConverter.convertEntityToModel(newsEntity);
	}

	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			newsRepository.deleteById(id);
		}
	}

	@Override
	public List<NewsModel> findAll(Pageable pageable) {
		List<NewsModel> newsModels = new ArrayList<>();
		List<NewsEntity> newsEntities = newsRepository.findAll(pageable).getContent();
		for (NewsEntity newsEntity : newsEntities) {
			NewsModel newsModel = newsConverter.convertEntityToModel(newsEntity);
			newsModels.add(newsModel);
		}
		return newsModels;
	}

	@Override
	public int totalItem() {
		return (int) newsRepository.count();
	}

	@Override
	public List<NewsModel> findAll() {
		List<NewsModel> newsModels = new ArrayList<>();
		List<NewsEntity> newsEntities = newsRepository.findAll();
		for (NewsEntity newsEntity : newsEntities) {
			NewsModel newsModel = newsConverter.convertEntityToModel(newsEntity);
			newsModels.add(newsModel);
		}
		return newsModels;
	}
}
