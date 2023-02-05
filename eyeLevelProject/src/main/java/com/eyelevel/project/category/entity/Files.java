package com.eyelevel.project.category.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name="Files")
@Table(name = "FILES")
@SequenceGenerator(
		name = "FILES_SEQ_GENERATOR",
		sequenceName = "SEQ_FILES_NO",
		initialValue = 1,
		allocationSize = 1
)
public class Files {

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "FILES_SEQ_GENERATOR"
	)
	@Column(name = "FILE_NO")
	private Long fileNo;
	
	@Column(name = "TEACHER_NO")
	private String teacherNo;
	
	@Column(name = "FILE_TYPE")
	private String fileType;
	
	@Column(name = "FILE_ORIGIN_NAME")
	private String fileOriginName;
	
	@Column(name = "FILE_SAVE_NAME")
	private String fileSaveName;
	
	@Column(name = "FILE_PATH")
	private String filePath;
	
	@Column(name = "THUMBNAIL_PATH")
	private String thumbnailPath;

	public Files() {
	}

	public Files(Long fileNo, String teacherNo, String fileType, String fileOriginName, String fileSaveName,
			String filePath, String thumbnailPath) {
		this.fileNo = fileNo;
		this.teacherNo = teacherNo;
		this.fileType = fileType;
		this.fileOriginName = fileOriginName;
		this.fileSaveName = fileSaveName;
		this.filePath = filePath;
		this.thumbnailPath = thumbnailPath;
	}

	public Long getFileNo() {
		return fileNo;
	}

	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}

	public String getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileOriginName() {
		return fileOriginName;
	}

	public void setFileOriginName(String fileOriginName) {
		this.fileOriginName = fileOriginName;
	}

	public String getFileSaveName() {
		return fileSaveName;
	}

	public void setFileSaveName(String fileSaveName) {
		this.fileSaveName = fileSaveName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	@Override
	public String toString() {
		return "File [fileNo=" + fileNo + ", teacherNo=" + teacherNo + ", fileType=" + fileType + ", fileOriginName="
				+ fileOriginName + ", fileSaveName=" + fileSaveName + ", filePath=" + filePath + ", thumbnailPath="
				+ thumbnailPath + "]";
	}
}