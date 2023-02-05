package com.eyelevel.project.category.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eyelevel.project.category.entity.StudentProfileAndStudentFamily;

public interface StudentProfileAndStudentFamilyRepository extends JpaRepository<StudentProfileAndStudentFamily, Long> {

	
	int countByStudentNameContaining(String searchValue);
	int countByStudentIdContaining(String searchValue);
	int countByStudentAddressContaining(String searchValue);
	int countByStudentNoContaining(Long searchValue);
	
	List<StudentProfileAndStudentFamily> findByStudentNameContaining(String searchValue, Pageable paging);
	List<StudentProfileAndStudentFamily> findByStudentIdContaining(String searchValue, Pageable paging);
	List<StudentProfileAndStudentFamily> findByStudentAddressContaining(String searchValue, Pageable paging);
	List<StudentProfileAndStudentFamily> findByStudentNoContaining(Long searchValue, Pageable paging);
	
	

}