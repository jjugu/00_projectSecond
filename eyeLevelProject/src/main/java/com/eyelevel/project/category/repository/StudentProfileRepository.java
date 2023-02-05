package com.eyelevel.project.category.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eyelevel.project.category.entity.StudentProfile;
import com.eyelevel.project.category.entity.StudentProfileAndStudentFamily;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
	StudentProfile findByStudentId(String studentId);
	
	int countByStudentNameContaining(String searchValue);
	
	List<StudentProfile> findByStudentNameContaining(String searchValue, Pageable paging);

	void save(StudentProfileAndStudentFamily studentProfile);
	
}