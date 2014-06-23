package cn.nit.bean.table6;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T615GenUndergraMajStuNumTea entity. @author Yuan
 */

@Table(name = "T615_GenUndergraMajStuNum_Tea$", schema = "dbo", catalog = "TDMS")
public class T615_Bean implements java.io.Serializable {

	// Fields

	private int seqNumber;
	private String majorName;
	private String majorId;
	private String fromUnitId;
	private String unitId;
	private Integer schLen;
	private Integer schStuSumNum;
	private Integer freshmanNum;
	private Integer sophomoreNum;
	private Integer juniorNum;
	private Integer seniorNum;
	private Integer otherGradeNum;
	private Integer minorNum;
	private Integer dualDegreeNum;
	private Integer changeInNum;
	private Integer changeOutNum;
	private Date time;
	private String note;
	
	public int getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
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
	public String getFromUnitId() {
		return fromUnitId;
	}
	public void setFromUnitId(String fromUnitId) {
		this.fromUnitId = fromUnitId;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public Integer getSchLen() {
		return schLen;
	}
	public void setSchLen(Integer schLen) {
		this.schLen = schLen;
	}
	public Integer getSchStuSumNum() {
		return schStuSumNum;
	}
	public void setSchStuSumNum(Integer schStuSumNum) {
		this.schStuSumNum = schStuSumNum;
	}
	public Integer getFreshmanNum() {
		return freshmanNum;
	}
	public void setFreshmanNum(Integer freshmanNum) {
		this.freshmanNum = freshmanNum;
	}
	public Integer getSophomoreNum() {
		return sophomoreNum;
	}
	public void setSophomoreNum(Integer sophomoreNum) {
		this.sophomoreNum = sophomoreNum;
	}
	public Integer getJuniorNum() {
		return juniorNum;
	}
	public void setJuniorNum(Integer juniorNum) {
		this.juniorNum = juniorNum;
	}
	public Integer getSeniorNum() {
		return seniorNum;
	}
	public void setSeniorNum(Integer seniorNum) {
		this.seniorNum = seniorNum;
	}
	public Integer getOtherGradeNum() {
		return otherGradeNum;
	}
	public void setOtherGradeNum(Integer otherGradeNum) {
		this.otherGradeNum = otherGradeNum;
	}
	public Integer getMinorNum() {
		return minorNum;
	}
	public void setMinorNum(Integer minorNum) {
		this.minorNum = minorNum;
	}
	public Integer getDualDegreeNum() {
		return dualDegreeNum;
	}
	public void setDualDegreeNum(Integer dualDegreeNum) {
		this.dualDegreeNum = dualDegreeNum;
	}
	public Integer getChangeInNum() {
		return changeInNum;
	}
	public void setChangeInNum(Integer changeInNum) {
		this.changeInNum = changeInNum;
	}
	public Integer getChangeOutNum() {
		return changeOutNum;
	}
	public void setChangeOutNum(Integer changeOutNum) {
		this.changeOutNum = changeOutNum;
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