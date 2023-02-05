package com.eyelevel.project.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyelevel.project.category.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

	void deleteByQuestionNo(Long questionNo);
}