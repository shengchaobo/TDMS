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
	private int seqNumber;
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
	private String fillUnitID;
	
	public int getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}
	public String getCompetiType() {
		return competiType;
	}
	public void setCompetiType(String competiType) {
		this.competiType = competiType;
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
	public String getCompetiName() {
		return competiName;
	}
	public void setCompetiName(String competiName) {
		this.competiName = competiName;
	}
	public String getAwardItem() {
		return awardItem;
	}
	public void setAwardItem(String awardItem) {
		this.awardItem = awardItem;
	}
	public String getAwardGrade() {
		return awardGrade;
	}
	public void setAwardGrade(String awardGrade) {
		this.awardGrade = awardGrade;
	}
	public String getAwardFromUnit() {
		return awardFromUnit;
	}
	public void setAwardFromUnit(String awardFromUnit) {
		this.awardFromUnit = awardFromUnit;
	}
	public Date getAwardTime() {
		return awardTime;
	}
	public void setAwardTime(Date awardTime) {
		this.awardTime = awardTime;
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

	
}