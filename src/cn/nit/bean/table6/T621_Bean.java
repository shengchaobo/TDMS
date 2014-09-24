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
 * T621UndergraAdmiInfoAdmission entity. @author Yuan
 */

@Table(name = "T621_UndergraAdmiInfo_Admission$", schema = "dbo", catalog = "TDMS")
public class T621_Bean implements java.io.Serializable {

	// Fields
	private Integer seqNumber;
	private String fromTeaUnit;
	private String unitId;
	private String majorName;
	private String majorId;
	private Integer amisPlanNum;
	private Integer actulEnrollNum;
	private Integer actulRegisterNum;
	private Integer autoEnrollNum;
	private Integer specialtyEnrollNum;
	private Integer inProviEnrollNum;
	private Integer newMajEnrollNum;
	private double avgScore;
	private Date time;
	private String note;
	
	
	public Integer getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}
	public String getFromTeaUnit() {
		return fromTeaUnit;
	}
	public void setFromTeaUnit(String fromTeaUnit) {
		this.fromTeaUnit = fromTeaUnit;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public String getMajorId() {
		return majorId;
	}
	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}

	public Integer getAmisPlanNum() {
		return amisPlanNum;
	}
	public void setAmisPlanNum(Integer amisPlanNum) {
		this.amisPlanNum = amisPlanNum;
	}
	public Integer getActulEnrollNum() {
		return actulEnrollNum;
	}
	public void setActulEnrollNum(Integer actulEnrollNum) {
		this.actulEnrollNum = actulEnrollNum;
	}
	public Integer getActulRegisterNum() {
		return actulRegisterNum;
	}
	public void setActulRegisterNum(Integer actulRegisterNum) {
		this.actulRegisterNum = actulRegisterNum;
	}
	public Integer getAutoEnrollNum() {
		return autoEnrollNum;
	}
	public void setAutoEnrollNum(Integer autoEnrollNum) {
		this.autoEnrollNum = autoEnrollNum;
	}
	public Integer getSpecialtyEnrollNum() {
		return specialtyEnrollNum;
	}
	public void setSpecialtyEnrollNum(Integer specialtyEnrollNum) {
		this.specialtyEnrollNum = specialtyEnrollNum;
	}
	public Integer getInProviEnrollNum() {
		return inProviEnrollNum;
	}
	public void setInProviEnrollNum(Integer inProviEnrollNum) {
		this.inProviEnrollNum = inProviEnrollNum;
	}
	public Integer getNewMajEnrollNum() {
		return newMajEnrollNum;
	}
	public void setNewMajEnrollNum(Integer newMajEnrollNum) {
		this.newMajEnrollNum = newMajEnrollNum;
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
	
	
	public double getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(double avgScore) {
		this.avgScore = avgScore;
	}



}