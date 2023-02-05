package com.eyelevel.project.category.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eyelevel.project.category.entity.PaperInformation;

import java.util.List;


public interface PaperInformationRepository extends JpaRepository<PaperInformation, Integer> {
	
	int countByPaperNameContaining(String searchValue);
	
	List<PaperInformation> findByPaperNameContaining(String searchValue, Pageable paging);

	
	
}