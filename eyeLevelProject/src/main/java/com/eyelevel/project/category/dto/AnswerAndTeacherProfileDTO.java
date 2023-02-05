package com.eyelevel.project.category.dto;

import java.util.Date;
import java.util.List;

import com.eyelevel.project.category.entity.TeacherProfile;

public class AnswerAndTeacherProfileDTO {
	
	private Long answerNo;
	private Long questionNo;
	private String teacherNo;
	private Date answerDate;
	private String answerContent;
	private TeacherProfile teacherList;
	
	public AnswerAndTeacherProfileDTO() {
	}
	
	public AnswerAndTeacherProfileDTO(Long answerNo, Long questionNo, String teacherNo, Date answerDate,
			String answerContent, TeacherProfile teacherList) {
		this.answerNo = answerNo;
		this.questionNo = questionNo;
		this.teacherNo = teacherNo;
		this.answerDate = answerDate;
		this.answerContent = answerContent;
		this.teacherList = teacherList;
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
	
	public Date getAnswerDate() {
		return answerDate;
	}
	
	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}
	
	public String getAnswerContent() {
		return answerContent;
	}
	
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	
	public TeacherProfile getTeacherList() {
		return teacherList;
	}
	
	public void setTeacherList(TeacherProfile teacherList) {
		this.teacherList = teacherList;
	}
	
	@Override
	public String toString() {
		return "AnswerAndTeacherProfileDTO [answerNo=" + answerNo + ", questionNo=" + questionNo + ", teacherNo="
				+ teacherNo + ", answerDate=" + answerDate + ", answerContent=" + answerContent + ", teacherList="
				+ teacherList + "]";
	}
}