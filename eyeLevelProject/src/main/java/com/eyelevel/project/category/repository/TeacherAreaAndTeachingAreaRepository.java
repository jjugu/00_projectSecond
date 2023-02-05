package com.eyelevel.project.category.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyelevel.project.category.entity.TeacherAreaAndTeachingArea;

public interface TeacherAreaAndTeachingAreaRepository extends JpaRepository<TeacherAreaAndTeachingArea, Long> {

	List<TeacherAreaAndTeachingArea> findByTeacherNoContaining(String teacherNo);

}