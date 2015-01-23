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
	private int seqNumber;
	private String teaUnit;
	private String unitId;	
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
	private String fillUnitID;
	private int CheckState;
	
	public int getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
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
	public String getJonalName() {
		return jonalName;
	}
	public void setJonalName(String jonalName) {
		this.jonalName = jonalName;
	}
	public String getJonalId() {
		return jonalId;
	}
	public void setJonalId(String jonalId) {
		this.jonalId = jonalId;
	}
	public String getPatentType() {
		return patentType;
	}
	public void setPatentType(String patentType) {
		this.patentType = patentType;
	}
	public Date getAppvlTime() {
		return appvlTime;
	}
	public void setAppvlTime(Date appvlTime) {
		this.appvlTime = appvlTime;
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
	public String getFillUnitID() {
		return fillUnitID;
	}
	public void setFillUnitID(String fillUnitID) {
		this.fillUnitID = fillUnitID;
	}
	public int getCheckState() {
		return CheckState;
	}
	public void setCheckState(int checkState) {
		CheckState = checkState;
	}

	
}