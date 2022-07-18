package com.hotnews.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotnews.repository.entity.NewsEntity;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
	
}
