package com.eyelevel.project.category.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "ReplyAndTeacherProfile")
@Table(name = "REPLY")
@SequenceGenerator(
	      name = "REPLY_SEQ_GENERATOR",
	      sequenceName = "SEQ_REPLY_NO",
	      initialValue = 1,
	      allocationSize = 1
	)
public class ReplyAndTeacherProfile implements java.io.Serializable {

	private static final long serialVersionUID = 1820987879403816369L;
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "REPLY_SEQ_GENERATOR"
	)
	
	@Column(name = "REPLY_NO")
	private Long replyNo;
	
	@Column(name = "BOARD_NO")
	private Long boardNo;
	
	@Column(name = "REPLY_DATE")
	private java.util.Date replyDate;
	
	@Column(name = "REPLY_CONTENT")
	private String content;
	
	@Column(name = "TEACHER_NO")
	private String teacherNo;
	
	@OneToOne
	@JoinColumn(name = "TEACHER_NO", insertable = false, updatable = false)
	private TeacherProfile teacherInfo;

	public ReplyAndTeacherProfile() {
	}

	public ReplyAndTeacherProfile(Long replyNo, Long boardNo, Date replyDate, String content, String teacherNo,
			TeacherProfile teacherInfo) {
		this.replyNo = replyNo;
		this.boardNo = boardNo;
		this.replyDate = replyDate;
		this.content = content;
		this.teacherNo = teacherNo;
		this.teacherInfo = teacherInfo;
	}

	public Long getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(Long replyNo) {
		this.replyNo = replyNo;
	}

	public Long getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(Long boardNo) {
		this.boardNo = boardNo;
	}

	public java.util.Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(java.util.Date replyDate) {
		this.replyDate = replyDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}

	public TeacherProfile getTeacherInfo() {
		return teacherInfo;
	}

	public void setTeacherInfo(TeacherProfile teacherInfo) {
		this.teacherInfo = teacherInfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ReplyAndTeacherProfile [replyNo=" + replyNo + ", boardNo=" + boardNo + ", replyDate=" + replyDate
				+ ", content=" + content + ", teacherNo=" + teacherNo + ", teacherInfo=" + teacherInfo + "]";
	}
}