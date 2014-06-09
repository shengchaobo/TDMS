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
	private Date time;
	private String note;

	// Constructors

	/** default constructor */
	public T621_Bean() {
	}


	/** full constructor */
	public T621_Bean(String fromTeaUnit, String unitId, String majorName, String majorId, Integer amisPlanNum,
			Integer actulEnrollNum, Integer actulRegisterNum,
			Integer autoEnrollNum, Integer specialtyEnrollNum,
			Integer inProviEnrollNum, Integer newMajEnrollNum, Date time,
			String note) {
		this.majorId = majorId;
		this.unitId = unitId;
		this.fromTeaUnit = fromTeaUnit;
		this.majorName = majorName;
		this.amisPlanNum = amisPlanNum;
		this.actulEnrollNum = actulEnrollNum;
		this.actulRegisterNum = actulRegisterNum;
		this.autoEnrollNum = autoEnrollNum;
		this.specialtyEnrollNum = specialtyEnrollNum;
		this.inProviEnrollNum = inProviEnrollNum;
		this.newMajEnrollNum = newMajEnrollNum;
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

	@Column(name = "FromTeaUnit")
	public String getFromTeaUnit() {
		return this.fromTeaUnit;
	}

	public void setFromTeaUnit(String fromTeaUnit) {
		this.fromTeaUnit = fromTeaUnit;
	}

	@Column(name = "MajorName")
	public String getMajorName() {
		return this.majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	@Column(name = "AmisPlanNum")
	public Integer getAmisPlanNum() {
		return this.amisPlanNum;
	}

	public void setAmisPlanNum(Integer amisPlanNum) {
		this.amisPlanNum = amisPlanNum;
	}

	@Column(name = "ActulEnrollNum")
	public Integer getActulEnrollNum() {
		return this.actulEnrollNum;
	}

	public void setActulEnrollNum(Integer actulEnrollNum) {
		this.actulEnrollNum = actulEnrollNum;
	}

	@Column(name = "ActulRegisterNum")
	public Integer getActulRegisterNum() {
		return this.actulRegisterNum;
	}

	public void setActulRegisterNum(Integer actulRegisterNum) {
		this.actulRegisterNum = actulRegisterNum;
	}

	@Column(name = "AutoEnrollNum")
	public Integer getAutoEnrollNum() {
		return this.autoEnrollNum;
	}

	public void setAutoEnrollNum(Integer autoEnrollNum) {
		this.autoEnrollNum = autoEnrollNum;
	}

	@Column(name = "SpecialtyEnrollNum")
	public Integer getSpecialtyEnrollNum() {
		return this.specialtyEnrollNum;
	}

	public void setSpecialtyEnrollNum(Integer specialtyEnrollNum) {
		this.specialtyEnrollNum = specialtyEnrollNum;
	}

	@Column(name = "InProviEnrollNum")
	public Integer getInProviEnrollNum() {
		return this.inProviEnrollNum;
	}

	public void setInProviEnrollNum(Integer inProviEnrollNum) {
		this.inProviEnrollNum = inProviEnrollNum;
	}

	@Column(name = "NewMajEnrollNum")
	public Integer getNewMajEnrollNum() {
		return this.newMajEnrollNum;
	}

	public void setNewMajEnrollNum(Integer newMajEnrollNum) {
		this.newMajEnrollNum = newMajEnrollNum;
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