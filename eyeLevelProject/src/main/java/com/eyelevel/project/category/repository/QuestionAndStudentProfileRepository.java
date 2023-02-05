package com.eyelevel.project.category.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eyelevel.project.category.entity.QuestionAndStudentProfile;

public interface QuestionAndStudentProfileRepository extends JpaRepository<QuestionAndStudentProfile, Long> {
	int countByquestionNameContaining(String searchValue);
	
	int countByquestionContentContaining(String searchValue);

	List<QuestionAndStudentProfile> findByquestionNameContaining(String searchValue, Pageable paging);

	List<QuestionAndStudentProfile> findByquestionContentContaining(String searchValue, Pageable paging);
}
