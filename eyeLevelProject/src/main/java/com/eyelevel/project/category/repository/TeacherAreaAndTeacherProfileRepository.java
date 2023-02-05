package com.eyelevel.project.category.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eyelevel.project.category.entity.TeacherAreaAndTeacherProfile;

public interface TeacherAreaAndTeacherProfileRepository extends JpaRepository<TeacherAreaAndTeacherProfile, Long> {

	List<TeacherAreaAndTeacherProfile> findByTeacherNoContaining(String searchValue, Pageable paging);
}