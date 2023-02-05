package com.eyelevel.project.category.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity(name="AnswerAndTeacherProfile")
@Table(name = "ANSWER")
@SequenceGenerator(
	      name = "ANSWER_SEQ_GENERATOR",
	      sequenceName = "SEQ_ANSWER_NO",
	      initialValue = 1,
	      allocationSize = 1
	)
public class AnswerAndTeacherProfile implements Serializable {
	private static final long serialVersionUID = 4554271580393265345L;

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

	@OneToOne
	@JoinColumn(name = "TEACHER_NO", insertable = false, updatable = false)
	private TeacherProfile teacherList;

	public AnswerAndTeacherProfile() {
	}

	public AnswerAndTeacherProfile(Long answerNo, Long questionNo, String teacherNo, Date answerDate,
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
		return "AnswerAndTeacherProfile [answerNo=" + answerNo + ", questionNo=" + questionNo + ", teacherNo="
				+ teacherNo + ", answerDate=" + answerDate + ", answerContent=" + answerContent + ", teacherList="
				+ teacherList + "]";
	} 
	
	
}
