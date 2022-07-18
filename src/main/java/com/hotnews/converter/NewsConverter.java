package com.hotnews.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hotnews.model.NewsModel;
import com.hotnews.repository.CategoryRepository;
import com.hotnews.repository.entity.NewsEntity;

@Component
public class NewsConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public NewsEntity convertModelToEntity(NewsModel newsModel) {
		NewsEntity newsEntity = new NewsEntity();
		newsEntity = modelMapper.map(newsModel, NewsEntity.class);
		newsEntity.setCategoryEntity(categoryRepository.findOneByCode(newsModel.getCategoryCode()));
		return newsEntity;
	}
	
	public NewsModel convertEntityToModel(NewsEntity newsEntity) {
		NewsModel newsModel = new NewsModel();
		newsModel = modelMapper.map(newsEntity, NewsModel.class);
		newsModel.setCategoryCode(newsEntity.getCategoryEntity().getCode());
		return newsModel;
	}
	
	public NewsEntity convertEntityToEntity(NewsEntity newsEntity, NewsEntity newsEntity2) {
		newsEntity.setCreatedBy(newsEntity2.getCreatedBy());
		newsEntity.setCreatedDate(newsEntity2.getCreatedDate());
		return newsEntity;
	}
}
