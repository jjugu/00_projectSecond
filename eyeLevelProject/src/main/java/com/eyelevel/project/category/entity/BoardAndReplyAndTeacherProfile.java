package com.eyelevel.project.category.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity(name = "BoardAndReplyAndTeacherProfile")
@Table(name = "BOARD")
@SequenceGenerator(
	      name = "BOARD_SEQ_GENERATOR",
	      sequenceName = "SEQ_BOARD_NO",
	      initialValue = 1,
	      allocationSize = 1
	)
public class BoardAndReplyAndTeacherProfile implements java.io.Serializable {

	private static final long serialVersionUID = -8525501329495115234L;

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "BOARD_SEQ_GENERATOR"
	)
	@Column(name = "BOARD_NO")
	private Long boardNo;
	
	@CreationTimestamp
	@Column(name = "BOARD_DATE")
	private java.util.Date boardDate;
	
	@Column(name = "BOARD_CONTENT")
	private String boardContent;
	
	@Column(name = "BOARD_NAME")
	private String boardName;
	
	@Column(name = "CATEGORY_CODE")
	private String categoryCode;
	
	@Column(name = "TEACHER_NO")
	private String teacherNo;
	
	/* 매핑을 위해 컬럼에 없는 값 추가 */
	@OneToOne
	@JoinColumn(name = "TEACHER_NO", insertable = false, updatable = false)
	private TeacherProfile teacherInfo;
	
	/* 매핑을 위해 컬럼에 없는 값 추가 */
	@OneToMany(mappedBy = "boardNo")
	private List<ReplyAndTeacherProfile> replyList = new ArrayList<>();

	public BoardAndReplyAndTeacherProfile() {
	}

	public BoardAndReplyAndTeacherProfile(Long boardNo, Date boardDate, String boardContent, String boardName,
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BoardAndReplyAndTeacherProfile [boardNo=" + boardNo + ", boardDate=" + boardDate + ", boardContent="
				+ boardContent + ", boardName=" + boardName + ", categoryCode=" + categoryCode + ", teacherNo="
				+ teacherNo + ", teacherInfo=" + teacherInfo + ", replyList=" + replyList + "]";
	}
}