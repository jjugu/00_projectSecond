package com.eyelevel.project.category.entity;

import javax.persistence.*;

import com.eyelevel.project.category.dto.CounselingCategoryDTO;

@Entity
@Table(name="COUNSELING")
@SequenceGenerator(
		name = "COUNSELING_SEQ_GENERATOR",
		sequenceName = "SEQ_COUNSELING_NO",
		initialValue = 1,
		allocationSize = 1
		)
public class Counseling {

	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "COUNSELING_SEQ_GENERATOR"
	)
	@Column(name = "COUNSELING_NO")
	private int counselingNo;
	
	@Column(name = "COUNSELING_PHONE")
	private String counselingPhone;
	
	@Column(name = "COUNSELING_ST")
	private String counselingSt;
	
	@Column(name = "COUNSELING_COM")
	private String counselingCom;
	
	@ManyToOne
	@JoinColumn(name = "CATEGORY_NO")
	private CounselingCategory categoryNo;

	public Counseling() {
		super();
	}

	public Counseling(int counselingNo, String counselingPhone, String counselingSt, String counselingCom,
			CounselingCategory categoryNo) {
		super();
		this.counselingNo = counselingNo;
		this.counselingPhone = counselingPhone;
		this.counselingSt = counselingSt;
		this.counselingCom = counselingCom;
		this.categoryNo = categoryNo;
	}

	public int getCounselingNo() {
		return counselingNo;
	}

	public void setCounselingNo(int counselingNo) {
		this.counselingNo = counselingNo;
	}

	public String getCounselingPhone() {
		return counselingPhone;
	}

	public void setCounselingPhone(String counselingPhone) {
		this.counselingPhone = counselingPhone;
	}

	public String getCounselingSt() {
		return counselingSt;
	}

	public void setCounselingSt(String counselingSt) {
		this.counselingSt = counselingSt;
	}

	public String getCounselingCom() {
		return counselingCom;
	}

	public void setCounselingCom(String counselingCom) {
		this.counselingCom = counselingCom;
	}

	public CounselingCategory getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(CounselingCategory categoryNo) {
		this.categoryNo = categoryNo;
	}

	@Override
	public String toString() {
		return "Counseling [counselingNo=" + counselingNo + ", counselingPhone=" + counselingPhone + ", counselingSt="
				+ counselingSt + ", counselingCom=" + counselingCom + ", categoryNo=" + categoryNo.getCategoryName() + "]";
	}
	
}
