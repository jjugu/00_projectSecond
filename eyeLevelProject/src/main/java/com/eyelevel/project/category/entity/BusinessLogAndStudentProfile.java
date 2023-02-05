package com.eyelevel.project.category.entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "BusinessAndStudent")
@Table(name = "BUSINESS_LOG")
public class BusinessLogAndStudentProfile {

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "BUSINESS_SEQ_GENERATOR"
	)
	@Column(name = "BUSINESS_DATE")
	private Date businessDate;

	@ManyToOne
	@JoinColumn(name = "STUDENT_NO")
	private StudentProfileAndBusinessLog student;
	
	@Column(name = "BUSINESS_MEMO")
	private String businessMemo;
	
	@Column(name = "TEACHER_NO")
	private String teacherNo;
	
	@Column(name = "PAPER_NO")
	private int paperNo;

	@Column(name = "ORDERABLE_STATUS")
	private String orderableStatus;
	
	public BusinessLogAndStudentProfile() {
	}

	public BusinessLogAndStudentProfile(Date businessDate, StudentProfileAndBusinessLog student, String businessMemo, String teacherNo,
			int paperNo, String orderableStatus) {
		super();
		this.businessDate = businessDate;
		this.student = student;
		this.businessMemo = businessMemo;
		this.teacherNo = teacherNo;
		this.paperNo = paperNo;
		this.orderableStatus = orderableStatus;
	}

	public Date getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public StudentProfileAndBusinessLog getStudent() {
		return student;
	}

	public void setStudent(StudentProfileAndBusinessLog student) {
		this.student = student;
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

	public String getOrderableStatus() {
		return orderableStatus;
	}

	public void setOrderableStatus(String orderableStatus) {
		this.orderableStatus = orderableStatus;
	}

	@Override
	public String toString() {
		return "BusinessAndStudent [businessDate=" + businessDate + ", student=" + student + ", businessMemo="
				+ businessMemo + ", teacherNo=" + teacherNo + ", paperNo=" + paperNo + ", orderableStatus="
				+ orderableStatus + "]";
	}
	
}
