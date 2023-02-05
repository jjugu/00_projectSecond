package com.eyelevel.project.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyelevel.project.category.entity.CategoryAndBoardInfo;

public interface CategoryAndBoardInfoRepository extends JpaRepository<CategoryAndBoardInfo, String> {
}