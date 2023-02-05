package com.eyelevel.project.category.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "CategoryAndBoardInfo")
@Table(name = "CATEGORY")
@SequenceGenerator(
	      name = "CATEGORY_SEQ_GENERATOR",
	      sequenceName = "SEQ_CATEGORY_CODE",
	      initialValue = 1,
	      allocationSize = 1
	)
public class CategoryAndBoardInfo {

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "CATEGORY_SEQ_GENERATOR"
	)
	@Column(name = "CATEGORY_CODE")
	private String categoryCode;
	
	@Column(name = "CATEGORY_NAME")
	private String categoryName;
	
	/* 매핑을 위해 컬럼에 없는 값 추가 */
	@OneToMany(mappedBy = "categoryCode")
	private List<BoardAndReplyAndTeacherProfile> boardInfo;

	public CategoryAndBoardInfo() {
	}

	public CategoryAndBoardInfo(String categoryCode, String categoryName,
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
		return "CategoryAndBoardInfo [categoryCode=" + categoryCode + ", categoryName=" + categoryName + ", boardInfo="
				+ boardInfo + "]";
	}
}