package com.hotnews.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotnews.repository.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
	CategoryEntity findOneByCode(String code);
}
