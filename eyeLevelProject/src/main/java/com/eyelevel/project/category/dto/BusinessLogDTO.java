package com.eyelevel.project.category.dto;

import java.util.Date;

public class BusinessLogDTO {

	private Date businessDate;
	private int studentNo;
	private String businessMemo;
	private String teacherNo;
	private int paperNo;
	
	
	public BusinessLogDTO() {}


	@Override
	public String toString() {
		return "BusinessDTO [businessDate=" + businessDate + ", studentNo=" + studentNo + ", businessMemo="
				+ businessMemo + ", teacherNo=" + teacherNo + ", paperNo=" + paperNo + "]";
	}


	public Date getBusinessDate() {
		return businessDate;
	}


	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}


	public int getStudentNo() {
		return studentNo;
	}


	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}


	public String getBusinessMemo() {
		return businessMemo;
	}


	public void setBusinessMemo(String businessMemo) {
		this.businessMemo = businessMemo;
	}


	public String getTeacherNo() {
		return teacherNo;
	}


	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}


	public int getPaperNo() {
		return paperNo;
	}


	public void setPaperNo(int paperNo) {
		this.paperNo = paperNo;
	}


	public BusinessLogDTO(Date businessDate, int studentNo, String businessMemo, String teacherNo, int paperNo) {
		super();
		this.businessDate = businessDate;
		this.studentNo = studentNo;
		this.businessMemo = businessMemo;
		this.teacherNo = teacherNo;
		this.paperNo = paperNo;
	}

	
	
	
	
	
	
}