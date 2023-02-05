package com.eyelevel.project.category.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eyelevel.project.category.entity.BoardAndReplyAndTeacherProfile;

public interface BoardAndReplyAndTeacherProfileRepository extends JpaRepository<BoardAndReplyAndTeacherProfile, Long> {

	int countByBoardNameAndCategoryCodeContaining(String searchValue, String categoryCode);

	int countByBoardContentAndCategoryCodeContaining(String searchValue, String categoryCode);
	
	int countByCategoryCodeContaining(String categoryCode);

	List<BoardAndReplyAndTeacherProfile> findByBoardNameAndCategoryCodeContaining(String searchValue, Pageable paging,
			String categoryCode);

	List<BoardAndReplyAndTeacherProfile> findByBoardContentAndCategoryCodeContaining(String searchValue,
			Pageable paging, String categoryCode);

	BoardAndReplyAndTeacherProfile findBoardAndReplyAndTeacherProfileByCategoryCodeAndBoardNoLike(String categoryCode,
			Long boardNo);
}