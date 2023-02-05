package com.eyelevel.project.category.dto;

import java.util.Date;

import com.eyelevel.project.category.entity.StudentProfile;

public class QuestionAndStudentProfileDTO {
	
	private Long questionNo;
	private Long studentNo;
	private Date questionDate;
	private String questionContent;
	private String questionFile;
	private String questionCom;
	private String questionName;
	private StudentProfile student;
	
	public QuestionAndStudentProfileDTO() {
	}
	
	public QuestionAndStudentProfileDTO(Long questionNo, Long studentNo, Date questionDate, String questionContent,
			String questionFile, String questionCom, String questionName, StudentProfile student) {
		this.questionNo = questionNo;
		this.studentNo = studentNo;
		this.questionDate = questionDate;
		this.questionContent = questionContent;
		this.questionFile = questionFile;
		this.questionCom = questionCom;
		this.questionName = questionName;
		this.student = student;
	}
	
	public Long getQuestionNo() {
		return questionNo;
	}
	
	public void setQuestionNo(Long questionNo) {
		this.questionNo = questionNo;
	}
	
	public Long getStudentNo() {
		return studentNo;
	}
	
	public void setStudentNo(Long studentNo) {
		this.studentNo = studentNo;
	}
	
	public Date getQuestionDate() {
		return questionDate;
	}
	
	public void setQuestionDate(Date questionDate) {
		this.questionDate = questionDate;
	}
	
	public String getQuestionContent() {
		return questionContent;
	}
	
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	
	public String getQuestionFile() {
		return questionFile;
	}
	
	public void setQuestionFile(String questionFile) {
		this.questionFile = questionFile;
	}
	
	public String getQuestionCom() {
		return questionCom;
	}
	
	public void setQuestionCom(String questionCom) {
		this.questionCom = questionCom;
	}
	
	public String getQuestionName() {
		return questionName;
	}
	
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	
	public StudentProfile getStudent() {
		return student;
	}
	
	public void setStudent(StudentProfile student) {
		this.student = student;
	}
	
	@Override
	public String toString() {
		return "QuestionAndStudentProfileDTO [questionNo=" + questionNo + ", studentNo=" + studentNo + ", questionDate="
				+ questionDate + ", questionContent=" + questionContent + ", questionFile=" + questionFile
				+ ", questionCom=" + questionCom + ", questionName=" + questionName + ", student=" + student + "]";
	}
}