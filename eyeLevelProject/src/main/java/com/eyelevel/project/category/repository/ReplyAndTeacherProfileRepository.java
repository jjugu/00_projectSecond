package com.eyelevel.project.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyelevel.project.category.entity.ReplyAndTeacherProfile;

public interface ReplyAndTeacherProfileRepository extends JpaRepository<ReplyAndTeacherProfile, Long> {
	
}