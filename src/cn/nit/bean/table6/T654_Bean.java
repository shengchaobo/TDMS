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
 * T654StuAwardPatentTeaYlc entity. @author Yuan
 */
@Table(name = "T654_StuAwardPatent_TeaYLC$", schema = "dbo", catalog = "TDMS")
public class T654_Bean implements java.io.Serializable {

	// Fields

	private String unitId;
	private String teaUnit;
	private String jonalName;
	private String jonalId;
	private String patentType;
	private Date appvlTime;
	private String awardStuName;
	private Integer awardStuNum;
	private String guideTeaName;
	private Integer guideTeaNum;
	private Date time;
	private String note;

	// Constructors

	/** default constructor */
	public T654_Bean() {
	}

	/** full constructor */
	public T654_Bean(
			String teaUnit, String unitId,  String jonalName,
			String jonalId, String patentType, Date appvlTime,
			String awardStuName, Integer awardStuNum, String guideTeaName,
			Integer guideTeaNum, Date time, String note) {
		this.unitId = unitId;
		this.teaUnit = teaUnit;
		this.jonalName = jonalName;
		this.jonalId = jonalId;
		this.patentType = patentType;
		this.appvlTime = appvlTime;
		this.awardStuName = awardStuName;
		this.awardStuNum = awardStuNum;
		this.guideTeaName = guideTeaName;
		this.guideTeaNum = guideTeaNum;
		this.time = time;
		this.note = note;
	}

	// Property accessors

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

	@Column(name = "PatentType")
	public String getPatentType() {
		return this.patentType;
	}

	public void setPatentType(String patentType) {
		this.patentType = patentType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "AppvlTime", length = 10)
	public Date getAppvlTime() {
		return this.appvlTime;
	}

	public void setAppvlTime(Date appvlTime) {
		this.appvlTime = appvlTime;
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