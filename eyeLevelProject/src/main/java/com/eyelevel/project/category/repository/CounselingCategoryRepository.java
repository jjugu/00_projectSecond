package com.eyelevel.project.category.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eyelevel.project.category.entity.CounselingCategory;

public interface CounselingCategoryRepository extends JpaRepository<CounselingCategory, Integer>{
	
	int countByCategoryNameContaining(String searchValue);
	
	List<CounselingCategory> findByCategoryNameContaining(String searchValue, Pageable paging);
	
	
	
    @Query(value = "SELECT c FROM CounselingCategory c ORDER BY c.categoryNo")
    public List<CounselingCategory> findAllCategory();

    
}