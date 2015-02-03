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
 * T617JuniorInSchStuInfoTea entity. @author Yuan
 */

@Table(name = "T617_JuniorInSchStuInfo_Tea$", schema = "dbo", catalog = "TDMS")
public class T617_Bean implements java.io.Serializable {

	// Fields
	private int seqNumber;
	private String teaUnit;
	private String unitId;
	private String majorName;
	private String majorId;	
	private String majorFieldName;
	private Integer juniorStuSumNum;
	private Integer juniorOneStuNum;
	private Integer juniorTwoStuNum;
	private Integer juniorThreeStuNum;
	private Date time;
	private String note;
	private int CheckState;
	
	public int getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}
	public String getTeaUnit() {
		return teaUnit;
	}
	public void setTeaUnit(String teaUnit) {
		this.teaUnit = teaUnit;
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
	public String getMajorFieldName() {
		return majorFieldName;
	}
	public void setMajorFieldName(String majorFieldName) {
		this.majorFieldName = majorFieldName;
	}
	public Integer getJuniorStuSumNum() {
		return juniorStuSumNum;
	}
	public void setJuniorStuSumNum(Integer juniorStuSumNum) {
		this.juniorStuSumNum = juniorStuSumNum;
	}
	public Integer getJuniorOneStuNum() {
		return juniorOneStuNum;
	}
	public void setJuniorOneStuNum(Integer juniorOneStuNum) {
		this.juniorOneStuNum = juniorOneStuNum;
	}
	public Integer getJuniorTwoStuNum() {
		return juniorTwoStuNum;
	}
	public void setJuniorTwoStuNum(Integer juniorTwoStuNum) {
		this.juniorTwoStuNum = juniorTwoStuNum;
	}
	public Integer getJuniorThreeStuNum() {
		return juniorThreeStuNum;
	}
	public void setJuniorThreeStuNum(Integer juniorThreeStuNum) {
		this.juniorThreeStuNum = juniorThreeStuNum;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getCheckState() {
		return CheckState;
	}
	public void setCheckState(int checkState) {
		CheckState = checkState;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	
}