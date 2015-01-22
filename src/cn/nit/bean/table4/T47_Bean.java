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
 * T47TeaUnitAwardTeaData entity. @author MyEclipse Persistence Tools
 */

@Table(name = "T47_TeaUnitAward_TeaData$", schema = "dbo", catalog = "TDMS")
public class T47_Bean implements java.io.Serializable {

	// Fields
	private int SeqNumber;
	private String teaUnit;
	private String unitId;
	private String awardName;
	private String awardLevel;
	private String awardFromUnit;
	private Date gainAwardTime;
	private String appvlId;
	private Date time;
	private String note;
	private String fillUnitID;
	private int checkState;

	@Column(name = "TeaUnit")
	public String getTeaUnit() {
		return this.teaUnit;
	}

	public void setTeaUnit(String teaUnit) {
		this.teaUnit = teaUnit;
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

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setAwardLevel(String awardLevel) {
		this.awardLevel = awardLevel;
	}

	public String getAwardLevel() {
		return awardLevel;
	}

	public void setSeqNumber(int seqNumber) {
		SeqNumber = seqNumber;
	}

	public int getSeqNumber() {
		return SeqNumber;
	}

	public void setFillUnitID(String fillUnitID) {
		this.fillUnitID = fillUnitID;
	}

	public String getFillUnitID() {
		return fillUnitID;
	}

	public void setCheckState(int checkState) {
		this.checkState = checkState;
	}

	public int getCheckState() {
		return checkState;
	}

}