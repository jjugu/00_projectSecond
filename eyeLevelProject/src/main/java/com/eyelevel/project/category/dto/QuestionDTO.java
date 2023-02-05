package com.eyelevel.project.category.dto;

import java.util.Date;

public class QuestionDTO {
	
	private Long questionNo;
	private Long studentNo;
	private Date questionDate;
	private String questionContent;
	private String questionFile;
	private String questionCom;
	private String questionName;
	
	public QuestionDTO() {
	}

	public QuestionDTO(Long questionNo, Long studentNo, Date questionDate, String questionContent, String questionFile,
			String questionCom, String questionName) {
		this.questionNo = questionNo;
		this.studentNo = studentNo;
		this.questionDate = questionDate;
		this.questionContent = questionContent;
		this.questionFile = questionFile;
		this.questionCom = questionCom;
		this.questionName = questionName;
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

	@Override
	public String toString() {
		return "QuestionDTO [questionNo=" + questionNo + ", studentNo=" + studentNo + ", questionDate=" + questionDate
				+ ", questionContent=" + questionContent + ", questionFile=" + questionFile + ", questionCom="
				+ questionCom + ", questionName=" + questionName + "]";
	}
}