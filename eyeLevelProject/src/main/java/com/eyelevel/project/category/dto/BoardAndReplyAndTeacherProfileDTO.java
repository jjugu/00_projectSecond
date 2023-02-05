package com.eyelevel.project.category.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.eyelevel.project.category.entity.ReplyAndTeacherProfile;
import com.eyelevel.project.category.entity.TeacherProfile;

public class BoardAndReplyAndTeacherProfileDTO {

	private Long boardNo;
	private java.util.Date boardDate;
	private String boardContent;
	private String boardName;
	private String categoryCode;
	private String teacherNo;
	private TeacherProfile teacherInfo;
	private List<ReplyAndTeacherProfile> replyList = new ArrayList<>();
	
	public BoardAndReplyAndTeacherProfileDTO() {
	}

	public BoardAndReplyAndTeacherProfileDTO(Long boardNo, Date boardDate, String boardContent, String boardName,
			String categoryCode, String teacherNo, TeacherProfile teacherInfo, List<ReplyAndTeacherProfile> replyList) {
		this.boardNo = boardNo;
		this.boardDate = boardDate;
		this.boardContent = boardContent;
		this.boardName = boardName;
		this.categoryCode = categoryCode;
		this.teacherNo = teacherNo;
		this.teacherInfo = teacherInfo;
		this.replyList = replyList;
	}

	public Long getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(Long boardNo) {
		this.boardNo = boardNo;
	}

	public java.util.Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(java.util.Date boardDate) {
		this.boardDate = boardDate;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
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

	public List<ReplyAndTeacherProfile> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<ReplyAndTeacherProfile> replyList) {
		this.replyList = replyList;
	}

	@Override
	public String toString() {
		return "BoardAndReplyAndTeacherProfileDTO [boardNo=" + boardNo + ", boardDate=" + boardDate + ", boardContent="
				+ boardContent + ", boardName=" + boardName + ", categoryCode=" + categoryCode + ", teacherNo="
				+ teacherNo + ", teacherInfo=" + teacherInfo + ", replyList=" + replyList + "]";
	}
}