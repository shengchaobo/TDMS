package cn.nit.bean.table6;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T652StuPublishPaperTeaYlc entity. @author Yuan
 */

@Table(name = "T652_StuPublishPaper_TeaYLC$", schema = "dbo", catalog = "TDMS")
public class T652_Bean implements java.io.Serializable {

	// Fields

	private String awardLevel;
	private String unitId;
	private String teaUnit;
	private String paperTitle;
	private String jonalName;
	private String jonalId;
	private Date jonalDate;
	private String awardStuName;
	private Integer awardStuNum;
	private String guideTeaName;
	private Integer guideTeaNum;
	private Boolean isAward;
	private String awardName;
	private String awardFromUnit;
	private Date time;
	private String note;

	// Constructors

	/** default constructor */
	public T652_Bean() {
	}

	/** full constructor */
	public T652_Bean(String teaUnit, String unitId,
			 String paperTitle, String jonalName,
			String jonalId, Date jonalDate, String awardStuName,
			Integer awardStuNum, String guideTeaName, Integer guideTeaNum,
			Boolean isAward, String awardLevel, String awardName, String awardFromUnit, Date time,
			String note) {
		this.awardLevel = awardLevel;
		this.unitId = unitId;
		this.teaUnit = teaUnit;
		this.paperTitle = paperTitle;
		this.jonalName = jonalName;
		this.jonalId = jonalId;
		this.jonalDate = jonalDate;
		this.awardStuName = awardStuName;
		this.awardStuNum = awardStuNum;
		this.guideTeaName = guideTeaName;
		this.guideTeaNum = guideTeaNum;
		this.isAward = isAward;
		this.awardName = awardName;
		this.awardFromUnit = awardFromUnit;
		this.time = time;
		this.note = note;
	}

	// Property accessors

	@JoinColumn(name = "AwardLevel")
	public String getAwardLevel() {
		return this.awardLevel;
	}

	public void setAwardLevel(String awardLevel) {
		this.awardLevel = awardLevel;
	}


	@JoinColumn(name = "UnitID")
	public String getUnitID() {
		return this.unitId;
	}

	public void setUnitID(String unitId) {
		this.unitId = unitId;
	}

	@Column(name = "TeaUnit")
	public String getTeaUnit() {
		return this.teaUnit;
	}

	public void setTeaUnit(String teaUnit) {
		this.teaUnit = teaUnit;
	}

	@Column(name = "PaperTitle")
	public String getPaperTitle() {
		return this.paperTitle;
	}

	public void setPaperTitle(String paperTitle) {
		this.paperTitle = paperTitle;
	}

	@Column(name = "JonalName")
	public String getJonalName() {
		return this.jonalName;
	}

	public void setJonalName(String jonalName) {
		this.jonalName = jonalName;
	}

	@Column(name = "JonalID")
	public String getJonalId() {
		return this.jonalId;
	}

	public void setJonalId(String jonalId) {
		this.jonalId = jonalId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "JonalDate", length = 10)
	public Date getJonalDate() {
		return this.jonalDate;
	}

	public void setJonalDate(Date jonalDate) {
		this.jonalDate = jonalDate;
	}

	@Column(name = "AwardStuName")
	public String getAwardStuName() {
		return this.awardStuName;
	}

	public void setAwardStuName(String awardStuName) {
		this.awardStuName = awardStuName;
	}

	@Column(name = "AwardStuNum")
	public Integer getAwardStuNum() {
		return this.awardStuNum;
	}

	public void setAwardStuNum(Integer awardStuNum) {
		this.awardStuNum = awardStuNum;
	}

	@Column(name = "GuideTeaName")
	public String getGuideTeaName() {
		return this.guideTeaName;
	}

	public void setGuideTeaName(String guideTeaName) {
		this.guideTeaName = guideTeaName;
	}

	@Column(name = "GuideTeaNum")
	public Integer getGuideTeaNum() {
		return this.guideTeaNum;
	}

	public void setGuideTeaNum(Integer guideTeaNum) {
		this.guideTeaNum = guideTeaNum;
	}

	@Column(name = "IsAward")
	public Boolean getIsAward() {
		return this.isAward;
	}

	public void setIsAward(Boolean isAward) {
		this.isAward = isAward;
	}

	@Column(name = "AwardName")
	public String getAwardName() {
		return this.awardName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}

	@Column(name = "AwardFromUnit")
	public String getAwardFromUnit() {
		return this.awardFromUnit;
	}

	public void setAwardFromUnit(String awardFromUnit) {
		this.awardFromUnit = awardFromUnit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Time", length = 10)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "Note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}