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
 * T631UndergarGraduateInfoTea entity. @author Yuan
 */

@Table(name = "T631_UndergarGraduateInfo_Tea$", schema = "dbo", catalog = "TDMS")
public class T631_Bean implements java.io.Serializable {

	// Fields
	private int seqNumber;
	private String teaUnit;
	private String unitId;
	private String majorName;
	private String majorId;
	private Integer thisYearGraduNum;
	private Integer thisYearNotGraduNum;
	private Integer awardDegreeNum;
	private Date time;
	private Integer CheckState;
	private String note;
	
	
	
	public Integer getCheckState() {
		return CheckState;
	}
	public void setCheckState(Integer checkState) {
		CheckState = checkState;
	}
	public int getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}
	public String getMajorId() {
		return majorId;
	}
	public void setMajorId(String majorId) {
		this.majorId = majorId;
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
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public Integer getThisYearGraduNum() {
		return thisYearGraduNum;
	}
	public void setThisYearGraduNum(Integer thisYearGraduNum) {
		this.thisYearGraduNum = thisYearGraduNum;
	}
	public Integer getThisYearNotGraduNum() {
		return thisYearNotGraduNum;
	}
	public void setThisYearNotGraduNum(Integer thisYearNotGraduNum) {
		this.thisYearNotGraduNum = thisYearNotGraduNum;
	}
	public Integer getAwardDegreeNum() {
		return awardDegreeNum;
	}
	public void setAwardDegreeNum(Integer awardDegreeNum) {
		this.awardDegreeNum = awardDegreeNum;
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

	
}