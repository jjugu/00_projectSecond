package com.eyelevel.project.category.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name="Paper")
@Table(name = "PAPER_INFOMATION")
@SequenceGenerator(
		name = "PAPER_SEQ_GENERATOR",
		sequenceName = "SEQ_PAPER_NO",
		initialValue = 1,
		allocationSize = 1
)
public class PaperInformation {
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "PAPER_SEQ_GENERATOR"
	)
	@Column(name = "PAPER_NO")
	private int paperNo;

	@Column(name = "PAPER_NAME")
	private String paperName;
	
	@Column(name = "PAPER_DIFF")
	private String paperDiff;
	
	@Column(name = "PAPER_SUB")
	private String paperSub;
	
	
	
	
	
	public PaperInformation() {}

	public PaperInformation(int paperNo, String paperName, String paperDiff, String paperSub) {
		super();
		this.paperNo = paperNo;
		this.paperName = paperName;
		this.paperDiff = paperDiff;
		this.paperSub = paperSub;
		
	}

	public int getPaperNo() {
		return paperNo;
	}

	public void setPaperNo(int paperNo) {
		this.paperNo = paperNo;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public String getPaperDiff() {
		return paperDiff;
	}

	public void setPaperDiff(String paperDiff) {
		this.paperDiff = paperDiff;
	}

	public String getPaperSub() {
		return paperSub;
	}

	public void setPaperSub(String paperSub) {
		this.paperSub = paperSub;
	}

	
	@Override
	public String toString() {
		return "Paper [paperNo=" + paperNo + ", paperName=" + paperName + ", paperDiff=" + paperDiff
				+ ", paperSub=" + paperSub + "]";
	}
	
}