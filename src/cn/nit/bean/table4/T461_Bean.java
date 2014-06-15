package cn.nit.bean.table4;

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
 * T461FameTeaAwardPer entity. @author MyEclipse Persistence Tools
 */

@Table(name = "T461_FameTeaAward_Per$", schema = "dbo", catalog = "TDMS")
public class T461_Bean implements java.io.Serializable {

	// Fields
	private String name;
	private String teaId;
	private String fromTeaUnit;
	private String awardFromUnit;
	private String unitId;
	private Date gainAwardTime;
	private String awardType;
	private String awardLevel;
	private String appvlId;
	private Integer otherTeaNum;
	private String otherTeaInfo;
	private Date time;
	private String note;

	@Column(name = "Name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "FromTeaUnit")
	public String getFromTeaUnit() {
		return this.fromTeaUnit;
	}

	public void setFromTeaUnit(String fromTeaUnit) {
		this.fromTeaUnit = fromTeaUnit;
	}

	@Column(name = "AwardFromUnit")
	public String getAwardFromUnit() {
		return this.awardFromUnit;
	}

	public void setAwardFromUnit(String awardFromUnit) {
		this.awardFromUnit = awardFromUnit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "GainAwardTime", length = 10)
	public Date getGainAwardTime() {
		return this.gainAwardTime;
	}

	public void setGainAwardTime(Date gainAwardTime) {
		this.gainAwardTime = gainAwardTime;
	}

	@Column(name = "AppvlID")
	public String getAppvlId() {
		return this.appvlId;
	}

	public void setAppvlId(String appvlId) {
		this.appvlId = appvlId;
	}

	@Column(name = "OtherTeaNum")
	public Integer getOtherTeaNum() {
		return this.otherTeaNum;
	}

	public void setOtherTeaNum(Integer otherTeaNum) {
		this.otherTeaNum = otherTeaNum;
	}

	@Column(name = "OtherTeaInfo")
	public String getOtherTeaInfo() {
		return this.otherTeaInfo;
	}

	public void setOtherTeaInfo(String otherTeaInfo) {
		this.otherTeaInfo = otherTeaInfo;
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

	public String getTeaId() {
		return teaId;
	}

	public void setTeaId(String teaId) {
		this.teaId = teaId;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getAwardType() {
		return awardType;
	}

	public void setAwardType(String awardType) {
		this.awardType = awardType;
	}

	public String getAwardLevel() {
		return awardLevel;
	}

	public void setAwardLevel(String awardLevel) {
		this.awardLevel = awardLevel;
	}

}