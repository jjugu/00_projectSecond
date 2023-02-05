package com.eyelevel.project.category.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyelevel.project.category.entity.AnswerAndTeacherProfile;

public interface AnswerAndTeacherProfileRepository extends JpaRepository<AnswerAndTeacherProfile, Long> {

	List<AnswerAndTeacherProfile> findByQuestionNoLike(Long questionNo);
}