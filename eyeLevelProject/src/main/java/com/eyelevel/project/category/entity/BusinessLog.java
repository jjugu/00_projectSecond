package com.eyelevel.project.category.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name="Business")
@Table(name = "BUSINESS_LOG")
@SequenceGenerator(
		name = "BUSINESS_SEQ_GENERATOR",
		sequenceName = "SEQ_BUSINESS_DATE",
		initialValue = 1,
		allocationSize = 1
)
public class BusinessLog {
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "BUSINESS_SEQ_GENERATOR"
	)
	@Column(name = "BUSINESS_DATE")
	private Date businessDate;

	@Column(name = "STUDENT_NO")
	private int studentNo;
	
	@Column(name = "BUSINESS_MEMO")
	private String businessMemo;
	
	@Column(name = "TEACHER_NO")
	private String teacherNo;
	
	@Column(name = "PAPER_NO")
	private int paperNo;
	
	
	
	
	
	public BusinessLog() {}

	public BusinessLog(Date businessDate, int studentNo, String businessMemo, String teacherNo, int paperNo) {
		super();
		this.businessDate = businessDate;
		this.studentNo = studentNo;
		this.businessMemo = businessMemo;
		this.teacherNo = teacherNo;
		this.paperNo = paperNo;
		
	}

	public Date getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(Date BusinessDate) {
		this.businessDate = BusinessDate;
	}

	public int getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(int StudentNo) {
		this.studentNo = StudentNo;
	}

	public String getBusinessMemo() {
		return businessMemo;
	}

	public void setBusinessMemo(String BusinessMemo) {
		this.businessMemo = BusinessMemo;
	}

	public String getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(String TeacherNo) {
		this.teacherNo = TeacherNo;
	}
	public int getPaperNo() {
		return paperNo;
	}

	public void setPaperNo(int PaperNo) {
		this.paperNo = PaperNo;
	}

	
	@Override
	public String toString() {
		return "Business [businessDate=" + businessDate + ", studentNo=" + studentNo + ", businessMemo=" + businessMemo
				+ ", teacherNo=" + teacherNo + ", paperNo=" + paperNo + "]";
	}
	
}