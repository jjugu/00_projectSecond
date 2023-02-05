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

@Entity(name="Question")
@Table(name = "QUESTION")
@SequenceGenerator(
		name = "QUESTION_SEQ_GENERATOR",
		sequenceName = "SEQ_QUESTION_NO",
		initialValue = 1,
		allocationSize = 1
)

public class Question {

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "QUESTION_SEQ_GENERATOR"
	)
	@Column(name = "QUESTION_NO")
	private Long questionNo;
	
	@Column(name = "STUDENT_NO")
	private String studentNo;
	
	@CreationTimestamp
	@Column(name = "QUESTION_DATE")
	private Date questionDate;
	
	@Column(name = "QUESTION_CONTENT")
	private String questionContent;
	
	@Column(name = "QUESTION_FILE")
	private String questionFile;
	
	@Column(name = "QUESTION_COM")
	private String questionCom;
	
	@Column(name = "QUESTION_NAME")
	private String questionName;

	public Question() {
	}

	public Question(Long questionNo, String studentNo, Date questionDate, String questionContent, String questionFile,
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

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
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
		return "Question [questionNo=" + questionNo + ", studentNo=" + studentNo + ", questionDate=" + questionDate
				+ ", questionContent=" + questionContent + ", questionFile=" + questionFile + ", questionCom="
				+ questionCom + ", questionName=" + questionName + "]";
	}
}