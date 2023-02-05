package com.eyelevel.project.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyelevel.project.category.entity.Files;

public interface FilesRepository extends JpaRepository<Files, Long> {

	Files findByTeacherNo(String teacherNo);
}