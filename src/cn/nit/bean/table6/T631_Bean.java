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

	private String majorId;
	private String unitId;
	private String teaUnit;
	private String majorName;
	private Integer thisYearGraduNum;
	private Integer thisYearNotGraduNum;
	private Integer awardDegreeNum;
	private Date time;
	private String note;

	// Constructors

	/** default constructor */
	public T631_Bean() {
	}

	/** full constructor */
	public T631_Bean(String teaUnit,
			 String unitId, 
			String majorName, String majorId, Integer thisYearGraduNum,
			Integer thisYearNotGraduNum, Integer awardDegreeNum, Date time,
			String note) {
		this.majorId = majorId;
		this.unitId = unitId;
		this.teaUnit = teaUnit;
		this.majorName = majorName;
		this.thisYearGraduNum = thisYearGraduNum;
		this.thisYearNotGraduNum = thisYearNotGraduNum;
		this.awardDegreeNum = awardDegreeNum;
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

	@Column(name = "ThisYearGraduNum")
	public Integer getThisYearGraduNum() {
		return this.thisYearGraduNum;
	}

	public void setThisYearGraduNum(Integer thisYearGraduNum) {
		this.thisYearGraduNum = thisYearGraduNum;
	}

	@Column(name = "ThisYearNotGraduNum")
	public Integer getThisYearNotGraduNum() {
		return this.thisYearNotGraduNum;
	}

	public void setThisYearNotGraduNum(Integer thisYearNotGraduNum) {
		this.thisYearNotGraduNum = thisYearNotGraduNum;
	}

	@Column(name = "AwardDegreeNum")
	public Integer getAwardDegreeNum() {
		return this.awardDegreeNum;
	}

	public void setAwardDegreeNum(Integer awardDegreeNum) {
		this.awardDegreeNum = awardDegreeNum;
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