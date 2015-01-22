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
 * T658InInterConferenceTeaInter entity. @author Yuan
 */

@Table(name = "T658_InInterConference_TeaInter$", schema = "dbo", catalog = "TDMS")
public class T658_Bean implements java.io.Serializable {

	// Fields
	private int seqNumber;
	private String conferenceLevel;
	private String unitId;
	private String teaUnit;
	private String conferenceName;
	private String paperTitle;
	private Date holdTime;
	private String holdPlace;
	private String holdUnit;
	private String awardStuName;
	private Integer awardStuNum;
	private String guideTeaName;
	private Integer guideTeaNum;
	private Date time;
	private String note;
	private String fillUnitID;
	private int CheckState;
	
	public int getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}
	public String getConferenceLevel() {
		return conferenceLevel;
	}
	public void setConferenceLevel(String conferenceLevel) {
		this.conferenceLevel = conferenceLevel;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getTeaUnit() {
		return teaUnit;
	}
	public void setTeaUnit(String teaUnit) {
		this.teaUnit = teaUnit;
	}
	public String getConferenceName() {
		return conferenceName;
	}
	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}
	public String getPaperTitle() {
		return paperTitle;
	}
	public void setPaperTitle(String paperTitle) {
		this.paperTitle = paperTitle;
	}
	public Date getHoldTime() {
		return holdTime;
	}
	public void setHoldTime(Date holdTime) {
		this.holdTime = holdTime;
	}
	public String getHoldPlace() {
		return holdPlace;
	}
	public void setHoldPlace(String holdPlace) {
		this.holdPlace = holdPlace;
	}
	public String getHoldUnit() {
		return holdUnit;
	}
	public void setHoldUnit(String holdUnit) {
		this.holdUnit = holdUnit;
	}
	public String getAwardStuName() {
		return awardStuName;
	}
	public void setAwardStuName(String awardStuName) {
		this.awardStuName = awardStuName;
	}
	public Integer getAwardStuNum() {
		return awardStuNum;
	}
	public void setAwardStuNum(Integer awardStuNum) {
		this.awardStuNum = awardStuNum;
	}
	public String getGuideTeaName() {
		return guideTeaName;
	}
	public void setGuideTeaName(String guideTeaName) {
		this.guideTeaName = guideTeaName;
	}
	public Integer getGuideTeaNum() {
		return guideTeaNum;
	}
	public void setGuideTeaNum(Integer guideTeaNum) {
		this.guideTeaNum = guideTeaNum;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getCheckState() {
		return CheckState;
	}
	public void setCheckState(int checkState) {
		CheckState = checkState;
	}
	public String getFillUnitID() {
		return fillUnitID;
	}
	public void setFillUnitID(String fillUnitID) {
		this.fillUnitID = fillUnitID;
	}

	
}