package com.eyelevel.project.category.dto;

import java.util.List;

import com.eyelevel.project.category.entity.BoardAndReplyAndTeacherProfile;

public class CategoryAndBoardInfoDTO {

	private String categoryCode;
	private String categoryName;
	private List<BoardAndReplyAndTeacherProfile> boardInfo;
	
	public CategoryAndBoardInfoDTO() {
	}

	public CategoryAndBoardInfoDTO(String categoryCode, String categoryName,
			List<BoardAndReplyAndTeacherProfile> boardInfo) {
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.boardInfo = boardInfo;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<BoardAndReplyAndTeacherProfile> getBoardInfo() {
		return boardInfo;
	}

	public void setBoardInfo(List<BoardAndReplyAndTeacherProfile> boardInfo) {
		this.boardInfo = boardInfo;
	}

	@Override
	public String toString() {
		return "CategoryAndBoardInfoDTO [categoryCode=" + categoryCode + ", categoryName=" + categoryName + "]";
	}
}