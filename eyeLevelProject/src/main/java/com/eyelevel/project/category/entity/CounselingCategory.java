package com.eyelevel.project.category.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "COUNSELING_CATEGORY")
public class CounselingCategory {
	
	@Id
	@Column(name = "CATEGORY_NO")
	private int categoryNo;
	
	@Column(name = "CATEGORY_NAME")
	private String categoryName;


	public CounselingCategory() {}


	public CounselingCategory(int categoryNo, String categoryName) {
		super();
		this.categoryNo = categoryNo;
		this.categoryName = categoryName;
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


	@Override
	public String toString() {
		return "CounselingCategory [categoryNo=" + categoryNo + ", categoryName=" + categoryName + "]";
	}

	
}