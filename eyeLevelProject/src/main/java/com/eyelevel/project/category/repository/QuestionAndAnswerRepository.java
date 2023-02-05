package com.eyelevel.project.category.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eyelevel.project.category.entity.QuestionAndAnswer;

public interface QuestionAndAnswerRepository extends JpaRepository<QuestionAndAnswer, Long> {
	
	int countByquestionNameContaining(String searchValue);
	
	int countByquestionContentContaining(String searchValue);

	List<QuestionAndAnswer> findByquestionNameContaining(String searchValue, Pageable paging);

	List<QuestionAndAnswer> findByquestionContentContaining(String searchValue, Pageable paging);
}