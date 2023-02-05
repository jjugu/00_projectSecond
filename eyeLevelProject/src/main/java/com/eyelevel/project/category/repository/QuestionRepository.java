package com.eyelevel.project.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyelevel.project.category.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
