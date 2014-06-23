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
 * T653StuPublishWordTeaYlc entity. @author Yuan
 */

@Table(name = "T653_StuPublishWord_TeaYLC$", schema = "dbo", catalog = "TDMS")
public class T653_Bean implements java.io.Serializable {

	// Fields
	private int seqNumber;
	private String awardLevel;
	private String unitId;
	private String teaUnit;
	private String workName;
	private String jonalName;
	private String jonalId;
	private Date jonalDate;
	private String awardStuName;
	private Integer awardStuNum;
	private String guideTeaName;
	private String guideTeaNum;
	private Date isAward;
	private String awardName;
	private String awardFromUnit;
	private Date time;
	private String note;
	private String fillUnitID;
	
	public int getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}
	public String getAwardLevel() {
		return awardLevel;
	}
	public void setAwardLevel(String awardLevel) {
		this.awardLevel = awardLevel;
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
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
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
	public Date getJonalDate() {
		return jonalDate;
	}
	public void setJonalDate(Date jonalDate) {
		this.jonalDate = jonalDate;
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
	public String getGuideTeaNum() {
		return guideTeaNum;
	}
	public void setGuideTeaNum(String guideTeaNum) {
		this.guideTeaNum = guideTeaNum;
	}
	public Date getIsAward() {
		return isAward;
	}
	public void setIsAward(Date isAward) {
		this.isAward = isAward;
	}
	public String getAwardName() {
		return awardName;
	}
	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}
	public String getAwardFromUnit() {
		return awardFromUnit;
	}
	public void setAwardFromUnit(String awardFromUnit) {
		this.awardFromUnit = awardFromUnit;
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

}