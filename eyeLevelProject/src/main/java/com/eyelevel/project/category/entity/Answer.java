package com.eyelevel.project.category.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity(name="Answer")
@Table(name = "ANSWER")
@SequenceGenerator(
	      name = "ANSWER_SEQ_GENERATOR",
	      sequenceName = "SEQ_ANSWER_NO",
	      initialValue = 1,
	      allocationSize = 1
	)
public class Answer {

	@Id
    @GeneratedValue(
	          strategy = GenerationType.SEQUENCE,
	          generator = "ANSWER_SEQ_GENERATOR"
	          )
	@Column(name = "ANSWER_NO")
	private Long answerNo;
	
	@Column(name = "QUESTION_NO")
	private Long questionNo;
	
	@Column(name = "TEACHER_NO")
	private String teacherNo;
	
	@CreationTimestamp
	@Column(name = "ANSWER_DATE")
	private Date answerDate;

	@Column(name = "ANSWER_CONTENT")
	private String answerContent;

	public Answer() {
	}

	public Answer(Long answerNo, Long questionNo, String teacherNo, Date answerDate, String answerContent) {
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

	@Override
	public String toString() {
		return "Answer [answerNo=" + answerNo + ", questionNo=" + questionNo + ", teacherNo=" + teacherNo
				+ ", answerDate=" + answerDate + ", answerContent=" + answerContent + "]";
	}


}
