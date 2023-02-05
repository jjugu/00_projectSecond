package com.eyelevel.project.category.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eyelevel.project.category.entity.BusinessLog;

import java.util.Date;
import java.util.List;


public interface BusinessLogRepository extends JpaRepository<BusinessLog, Date> {
	
	int countBybusinessDateContaining(String searchValue);
	
	 List<BusinessLog> findBybusinessDateContaining(String searchValue, Pageable paging);
	
}