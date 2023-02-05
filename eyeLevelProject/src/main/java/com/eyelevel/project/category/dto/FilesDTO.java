package com.eyelevel.project.category.dto;

public class FilesDTO {

	private Long fileNo;
	private String teacherNo;
	private String fileType;
	private String fileOriginName;
	private String fileSaveName;
	private String filePath;
	private String thumbnailPath;
	
	public FilesDTO() {
	}

	public FilesDTO(Long fileNo, String teacherNo, String fileType, String fileOriginName, String fileSaveName,
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
		return "FileDTO [fileNo=" + fileNo + ", teacherNo=" + teacherNo + ", fileType=" + fileType + ", fileOriginName="
				+ fileOriginName + ", fileSaveName=" + fileSaveName + ", filePath=" + filePath + ", thumbnailPath="
				+ thumbnailPath + "]";
	}
}