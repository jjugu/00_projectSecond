package com.eyelevel.project.category.dto;

import java.util.ArrayList;
import java.util.List;


public class CounselingCategoryDTO {
	private int categoryNo;
	private String categoryName;
	private List<CounselingDTO> counselingList = new ArrayList<>();
	
	public CounselingCategoryDTO() {}

	public CounselingCategoryDTO(int categoryNo, String categoryName, List<CounselingDTO> counselingList) {
		super();
		this.categoryNo = categoryNo;
		this.categoryName = categoryName;
		this.counselingList = counselingList;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<CounselingDTO> getCounselingList() {
		return counselingList;
	}

	public void setCounselingList(List<CounselingDTO> counselingList) {
		this.counselingList = counselingList;
	}

	@Override
	public String toString() {
		return "CounselingCategoryDTO [categoryNo=" + categoryNo + ", categoryName=" + categoryName
				+ ", counselingList=" + counselingList + "]";
	}
	
}