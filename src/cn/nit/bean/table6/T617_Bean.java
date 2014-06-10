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

	// Constructors

	/** default constructor */
	public T617_Bean() {
	}

	/** full constructor */
	public T617_Bean(String teaUnit, String unitId, String majorName, String majorId,	
			String majorFieldName, Integer juniorStuSumNum,
			Integer juniorOneStuNum, Integer juniorTwoStuNum,
			Integer juniorThreeStuNum, Date time, String note) {
		this.unitId = unitId;
		this.majorId = majorId;
		this.teaUnit = teaUnit;
		this.majorName = majorName;
		this.majorFieldName = majorFieldName;
		this.juniorStuSumNum = juniorStuSumNum;
		this.juniorOneStuNum = juniorOneStuNum;
		this.juniorTwoStuNum = juniorTwoStuNum;
		this.juniorThreeStuNum = juniorThreeStuNum;
		this.time = time;
		this.note = note;
	}

	// Property accessors
	
	@JoinColumn(name = "UnitID")
	public String getUnitID() {
		return this.unitId;
	}

	public void setUnitID(String unitId) {
		this.unitId = unitId;
	}

	@JoinColumn(name = "MajorID")
	
	public String getMajorID() {
		return this.majorId;
	}

	public void setMajorID(String majorId) {
		this.majorId = majorId;
	}

	@Column(name = "TeaUnit")
	public String getTeaUnit() {
		return this.teaUnit;
	}

	public void setTeaUnit(String teaUnit) {
		this.teaUnit = teaUnit;
	}

	@Column(name = "MajorName")
	public String getMajorName() {
		return this.majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	@Column(name = "MajorFieldName")
	public String getMajorFieldName() {
		return this.majorFieldName;
	}

	public void setMajorFieldName(String majorFieldName) {
		this.majorFieldName = majorFieldName;
	}

	@Column(name = "JuniorStuSumNum")
	public Integer getJuniorStuSumNum() {
		return this.juniorStuSumNum;
	}

	public void setJuniorStuSumNum(Integer juniorStuSumNum) {
		this.juniorStuSumNum = juniorStuSumNum;
	}

	@Column(name = "JuniorOneStuNum")
	public Integer getJuniorOneStuNum() {
		return this.juniorOneStuNum;
	}

	public void setJuniorOneStuNum(Integer juniorOneStuNum) {
		this.juniorOneStuNum = juniorOneStuNum;
	}

	@Column(name = "JuniorTwoStuNum")
	public Integer getJuniorTwoStuNum() {
		return this.juniorTwoStuNum;
	}

	public void setJuniorTwoStuNum(Integer juniorTwoStuNum) {
		this.juniorTwoStuNum = juniorTwoStuNum;
	}

	@Column(name = "JuniorThreeStuNum")
	public Integer getJuniorThreeStuNum() {
		return this.juniorThreeStuNum;
	}

	public void setJuniorThreeStuNum(Integer juniorThreeStuNum) {
		this.juniorThreeStuNum = juniorThreeStuNum;
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