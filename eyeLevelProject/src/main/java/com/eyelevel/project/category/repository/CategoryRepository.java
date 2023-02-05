package com.eyelevel.project.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyelevel.project.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {

}