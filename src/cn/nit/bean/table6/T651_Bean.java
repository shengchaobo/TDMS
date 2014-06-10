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
 * T651StuCompetiAwardInfoTeaYlc entity. @author Yuan
 */

@Table(name = "T651_StuCompetiAwardInfo_TeaYLC$", schema = "dbo", catalog = "TDMS")
public class T651_Bean implements java.io.Serializable {

	// Fields
	private String competiType;
	private String awardLevel;
	private String unitId;
	private String teaUnit;
	private String competiName;
	private String awardItem;
	private String awardGrade;
	private String awardFromUnit;
	private Date awardTime;
	private String awardStuName;
	private Integer awardStuNum;
	private String guideTeaName;
	private Integer guideTeaNum;
	private Date time;
	private String note;

	// Constructors

	/** default constructor */
	public T651_Bean() {
	}


	/** full constructor */
	public T651_Bean(
			String teaUnit, String unitId, String competiType, String competiName,
			String awardItem, String awardLevel, String awardGrade, String awardFromUnit,
			Date awardTime, String awardStuName, Integer awardStuNum,
			String guideTeaName, Integer guideTeaNum, Date time, String note) {
		this.competiType = competiType;
		this.awardLevel = awardLevel;
		this.unitId = unitId;
		this.teaUnit = teaUnit;
		this.competiName = competiName;
		this.awardItem = awardItem;
		this.awardGrade = awardGrade;
		this.awardFromUnit = awardFromUnit;
		this.awardTime = awardTime;
		this.awardStuName = awardStuName;
		this.awardStuNum = awardStuNum;
		this.guideTeaName = guideTeaName;
		this.guideTeaNum = guideTeaNum;
		this.time = time;
		this.note = note;
	}

	// Property accessors

	@JoinColumn(name = "CompetiType")
	public String getCompetiType() {
		return this.competiType;
	}

	public void setCompetiType(String competiType) {
		this.competiType = competiType;
	}

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

	@Column(name = "CompetiName")
	public String getCompetiName() {
		return this.competiName;
	}

	public void setCompetiName(String competiName) {
		this.competiName = competiName;
	}

	@Column(name = "AwardItem")
	public String getAwardItem() {
		return this.awardItem;
	}

	public void setAwardItem(String awardItem) {
		this.awardItem = awardItem;
	}

	@Column(name = "AwardGrade")
	public String getAwardGrade() {
		return this.awardGrade;
	}

	public void setAwardGrade(String awardGrade) {
		this.awardGrade = awardGrade;
	}

	@Column(name = "AwardFromUnit")
	public String getAwardFromUnit() {
		return this.awardFromUnit;
	}

	public void setAwardFromUnit(String awardFromUnit) {
		this.awardFromUnit = awardFromUnit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "AwardTime", length = 10)
	public Date getAwardTime() {
		return this.awardTime;
	}

	public void setAwardTime(Date awardTime) {
		this.awardTime = awardTime;
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