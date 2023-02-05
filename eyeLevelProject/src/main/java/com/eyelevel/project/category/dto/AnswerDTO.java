package com.eyelevel.project.category.dto;

public class AnswerDTO {
	
	private Long answerNo;
	private Long questionNo;
	private String teacherNo;
	private String answerDate;
	private String answerContent;
	
	public AnswerDTO() {
	}
	
	public AnswerDTO(Long answerNo, Long questionNo, String teacherNo, String answerDate, String answerContent) {
		this.answerNo = answerNo;
		this.questionNo = questionNo;
		this.teacherNo = teacherNo;
		this.answerDate = answerDate;
		this.answerContent = answerContent;
	}
	
	public Long getAnswerNo() {
		return answerNo;
	}
	
	public void setAnswerNo(Long answerNo) {
		this.answerNo = answerNo;
	}
	
	public Long getQuestionNo() {
		return questionNo;
	}
	
	public void setQuestionNo(Long questionNo) {
		this.questionNo = questionNo;
	}
	
	public String getTeacherNo() {
		return teacherNo;
	}
	
	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}
	
	public String getAnswerDate() {
		return answerDate;
	}
	
	public void setAnswerDate(String answerDate) {
		this.answerDate = answerDate;
	}
	
	public String getAnswerContent() {
		return answerContent;
	}
	
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	
	@Override
	public String toString() {
		return "AnswerDTO [answerNo=" + answerNo + ", questionNo=" + questionNo + ", teacherNo=" + teacherNo
				+ ", answerDate=" + answerDate + ", answerContent=" + answerContent + "]";
	}
}