package com.eyelevel.project.category.dto;

import com.eyelevel.project.category.entity.CounselingCategory;

public class CounselingDTO {
	private int counselingNo;
	private String counselingPhone;
	private String counselingSt;
	private String counselingCom;
	private CounselingCategory categoryNo;
	
	
	public CounselingDTO() {}


	public CounselingDTO(int counselingNo, String counselingPhone, String counselingSt, String counselingCom,
			CounselingCategory categoryNo) {
		super();
		this.counselingNo = counselingNo;
		this.counselingPhone = counselingPhone;
		this.counselingSt = counselingSt;
		this.counselingCom = counselingCom;
		this.categoryNo = categoryNo;
	}


	public int getCounselingNo() {
		return counselingNo;
	}


	public void setCounselingNo(int counselingNo) {
		this.counselingNo = counselingNo;
	}


	public String getCounselingPhone() {
		return counselingPhone;
	}


	public void setCounselingPhone(String counselingPhone) {
		this.counselingPhone = counselingPhone;
	}


	public String getCounselingSt() {
		return counselingSt;
	}


	public void setCounselingSt(String counselingSt) {
		this.counselingSt = counselingSt;
	}


	public String getCounselingCom() {
		return counselingCom;
	}


	public void setCounselingCom(String counselingCom) {
		this.counselingCom = counselingCom;
	}


	public CounselingCategory getCategoryNo() {
		return categoryNo;
	}


	public void setCategoryNo(CounselingCategory categoryNo) {
		this.categoryNo = categoryNo;
	}


	@Override
	public String toString() {
		return "CounselingDTO [counselingNo=" + counselingNo + ", counselingPhone=" + counselingPhone
				+ ", counselingSt=" + counselingSt + ", counselingCom=" + counselingCom + ", categoryNo=" + categoryNo
				+ "]";
	}


}