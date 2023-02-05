package com.eyelevel.project.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyelevel.project.category.entity.TeacherProfile;

public interface TeacherProfileRepository extends JpaRepository<TeacherProfile, String> {

	TeacherProfile findByTeacherId(String teacherId);
}