package com.eyelevel.project.category.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name="Calendar")
@Table(name = "CALENDAR")
@SequenceGenerator(
		name = "CAL_NO_SEQ",
		sequenceName = "SEQ_CALENDAR_NO",
		initialValue = 1,
		allocationSize = 1
)
public class Calendar {

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "CAL_NO_SEQ"
	)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "GROUPID")
	private Long number;

	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "WRITER")
	private String writer;
	
	@Column(name = "CONTENT")
	private String content;
	
	@Column(name = "START1")
	private LocalDateTime start;
	
	@Column(name = "END1")
	private LocalDateTime end;
	
	@Column(name = "ALLDAY")
	private Long allDay;
	
	@Column(name = "TEXTCOLOR")
	private String textColor;
	
	@Column(name = "BACKGROUNDCOLOR")
	private String backgroundColor;
	
	@Column(name = "BORDERCOLOR")
	private String borderColor;

	@Column(name = "TEACHER_NAME")
	private String teacherName;

	public Calendar() {
	}

	public Calendar(Long id, Long number, String title, String writer, String content, LocalDateTime start,
			LocalDateTime end, Long allDay, String textColor, String backgroundColor, String borderColor,
			String teacherName) {
		this.id = id;
		this.number = number;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.start = start;
		this.end = end;
		this.allDay = allDay;
		this.textColor = textColor;
		this.backgroundColor = backgroundColor;
		this.borderColor = borderColor;
		this.teacherName = teacherName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public Long getAllDay() {
		return allDay;
	}

	public void setAllDay(Long allDay) {
		this.allDay = allDay;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	@Override
	public String toString() {
		return "Calendar [id=" + id + ", number=" + number + ", title=" + title + ", writer=" + writer + ", content="
				+ content + ", start=" + start + ", end=" + end + ", allDay=" + allDay + ", textColor=" + textColor
				+ ", backgroundColor=" + backgroundColor + ", borderColor=" + borderColor + ", teacherName="
				+ teacherName + "]";
	}
	

}
	


