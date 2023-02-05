package com.eyelevel.project.category.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eyelevel.project.category.entity.Counseling;

public interface CounselingRepository extends JpaRepository<Counseling, Integer> {

	int countByCounselingNoContaining(String searchValue);
	int countByCategoryNoCategoryNameContaining(String searchValue);
	
	List<Counseling> findByCounselingNoContaining(String searchValue, Pageable paging);
	List<Counseling> findByCategoryNoCategoryNameContaining(String searchValue, Pageable paging);
	
}