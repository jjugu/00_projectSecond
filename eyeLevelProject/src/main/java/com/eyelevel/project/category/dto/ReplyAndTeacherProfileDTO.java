package com.eyelevel.project.category.dto;

import java.util.Date;

import com.eyelevel.project.category.entity.TeacherProfile;

public class ReplyAndTeacherProfileDTO {

	private Long replyNo;
	private Long boardNo;
	private java.util.Date replyDate;
	private String content;
	private String teacherNo;
	private TeacherProfile teacherInfo;
	
	public ReplyAndTeacherProfileDTO() {
	}
	
	public ReplyAndTeacherProfileDTO(Long replyNo, Long boardNo, Date replyDate, String content, String teacherNo,
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

	@Override
	public String toString() {
		return "ReplyAndTeacherProfileDTO [replyNo=" + replyNo + ", boardNo=" + boardNo + ", replyDate=" + replyDate
				+ ", content=" + content + ", teacherNo=" + teacherNo + ", teacherInfo=" + teacherInfo + "]";
	}
}