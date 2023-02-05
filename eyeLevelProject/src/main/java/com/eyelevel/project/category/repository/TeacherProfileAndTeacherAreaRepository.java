package com.eyelevel.project.category.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eyelevel.project.category.entity.TeacherProfileAndTeacherArea;

public interface TeacherProfileAndTeacherAreaRepository extends JpaRepository<TeacherProfileAndTeacherArea, String> {

	int countByTeacherNoContaining(String searchValue);

	int countByTeacherNameContaining(String searchValue);

	List<TeacherProfileAndTeacherArea> findByTeacherNoContaining(String searchValue, Pageable paging);

	List<TeacherProfileAndTeacherArea> findByTeacherNameContaining(String searchValue, Pageable paging);
}