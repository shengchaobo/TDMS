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

	// Constructors

	/** default constructor */
	public T615_Bean() {
	}


	/** full constructor */
	public T615_Bean(String majorName, String majorId,
			String fromUnitId, String unitId, Integer schLen, Integer schStuSumNum,
			Integer freshmanNum, Integer sophomoreNum, Integer juniorNum,
			Integer seniorNum, Integer otherGradeNum, Integer minorNum,
			Integer dualDegreeNum, Integer changeInNum, Integer changeOutNum,
			Date time, String note) {

		this.majorName = majorName;
		this.majorId = majorId;
		this.fromUnitId = fromUnitId;
		this.unitId = unitId;
		this.schLen = schLen;
		this.schStuSumNum = schStuSumNum;
		this.freshmanNum = freshmanNum;
		this.sophomoreNum = sophomoreNum;
		this.juniorNum = juniorNum;
		this.seniorNum = seniorNum;
		this.otherGradeNum = otherGradeNum;
		this.minorNum = minorNum;
		this.dualDegreeNum = dualDegreeNum;
		this.changeInNum = changeInNum;
		this.changeOutNum = changeOutNum;
		this.time = time;
		this.note = note;
	}

	// Property accessors

	@JoinColumn(name = "MajorID")
	public String getMajorID() {
		return this.majorId;
	}

	public void setMajorID(String majorId) {
		this.majorId = majorId;
	}


	@JoinColumn(name = "UnitID")
	public String getUnitID() {
		return this.unitId;
	}

	public void setUnitID(String unitId) {
		this.unitId = unitId;
	}

	@Column(name = "MajorName")
	public String getMajorName() {
		return this.majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	@Column(name = "FromUnitID")
	public String getFromUnitId() {
		return this.fromUnitId;
	}

	public void setFromUnitId(String fromUnitId) {
		this.fromUnitId = fromUnitId;
	}

	@Column(name = "SchLen")
	public Integer getSchLen() {
		return this.schLen;
	}

	public void setSchLen(Integer schLen) {
		this.schLen = schLen;
	}

	@Column(name = "SchStuSumNum")
	public Integer getSchStuSumNum() {
		return this.schStuSumNum;
	}

	public void setSchStuSumNum(Integer schStuSumNum) {
		this.schStuSumNum = schStuSumNum;
	}

	@Column(name = "FreshmanNum")
	public Integer getFreshmanNum() {
		return this.freshmanNum;
	}

	public void setFreshmanNum(Integer freshmanNum) {
		this.freshmanNum = freshmanNum;
	}

	@Column(name = "SophomoreNum")
	public Integer getSophomoreNum() {
		return this.sophomoreNum;
	}

	public void setSophomoreNum(Integer sophomoreNum) {
		this.sophomoreNum = sophomoreNum;
	}

	@Column(name = "JuniorNum")
	public Integer getJuniorNum() {
		return this.juniorNum;
	}

	public void setJuniorNum(Integer juniorNum) {
		this.juniorNum = juniorNum;
	}

	@Column(name = "SeniorNum")
	public Integer getSeniorNum() {
		return this.seniorNum;
	}

	public void setSeniorNum(Integer seniorNum) {
		this.seniorNum = seniorNum;
	}

	@Column(name = "OtherGradeNum")
	public Integer getOtherGradeNum() {
		return this.otherGradeNum;
	}

	public void setOtherGradeNum(Integer otherGradeNum) {
		this.otherGradeNum = otherGradeNum;
	}

	@Column(name = "MinorNum")
	public Integer getMinorNum() {
		return this.minorNum;
	}

	public void setMinorNum(Integer minorNum) {
		this.minorNum = minorNum;
	}

	@Column(name = "DualDegreeNum")
	public Integer getDualDegreeNum() {
		return this.dualDegreeNum;
	}

	public void setDualDegreeNum(Integer dualDegreeNum) {
		this.dualDegreeNum = dualDegreeNum;
	}

	@Column(name = "ChangeInNum")
	public Integer getChangeInNum() {
		return this.changeInNum;
	}

	public void setChangeInNum(Integer changeInNum) {
		this.changeInNum = changeInNum;
	}

	@Column(name = "ChangeOutNum")
	public Integer getChangeOutNum() {
		return this.changeOutNum;
	}

	public void setChangeOutNum(Integer changeOutNum) {
		this.changeOutNum = changeOutNum;
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

}