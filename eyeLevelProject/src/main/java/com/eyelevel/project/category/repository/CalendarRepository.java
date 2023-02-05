package com.eyelevel.project.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyelevel.project.category.entity.Calendar;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
	
	// 수정 기능을 위한 메소드
	Calendar findBytitle(String title);

	// 삭제 기능을 위한 메소드
	Calendar deleteBytitle(String title);
}
