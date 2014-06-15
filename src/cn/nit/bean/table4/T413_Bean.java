package cn.nit.bean.table4;

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
 * T413HireTeaInfoTeaPer entity. @author MyEclipse Persistence Tools
 */

@Table(name = "T413_HireTeaInfo_TeaPer$", schema = "dbo", catalog = "TDMS")
public class T413_Bean implements java.io.Serializable {

	// Fields
	private String name;
	private String teaId;
	private String gender;
	private Date birthday;
	private Date hireBeginTime;
	private String teaState;
	private Integer hireTimeLen;
	private String unitId;
	private String unitName;
	private String topDegree;
	private String education;
	private String techTitle;
	private String subjectClass;	
	private String workUnitType;
	private String tutorType;
	private String region;
	private String note;

	@Column(name = "Name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Gender")
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Birthday", length = 10)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "HireBeginTime", length = 10)
	public Date getHireBeginTime() {
		return this.hireBeginTime;
	}

	public void setHireBeginTime(Date hireBeginTime) {
		this.hireBeginTime = hireBeginTime;
	}

	@Column(name = "TeaState")
	public String getTeaState() {
		return this.teaState;
	}

	public void setTeaState(String teaState) {
		this.teaState = teaState;
	}

	@Column(name = "HireTimeLen", nullable = false)
	public Integer getHireTimeLen() {
		return this.hireTimeLen;
	}

	public void setHireTimeLen(Integer hireTimeLen) {
		this.hireTimeLen = hireTimeLen;
	}

	@Column(name = "UnitID")
	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	@Column(name = "UnitName")
	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	@Column(name = "SubjectClass")
	public String getSubjectClass() {
		return this.subjectClass;
	}

	public String getTeaId() {
		return teaId;
	}

	public void setTeaId(String teaId) {
		this.teaId = teaId;
	}

	public String getTopDegree() {
		return topDegree;
	}

	public void setTopDegree(String topDegree) {
		this.topDegree = topDegree;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getTechTitle() {
		return techTitle;
	}

	public void setTechTitle(String techTitle) {
		this.techTitle = techTitle;
	}

	public String getTutorType() {
		return tutorType;
	}

	public void setTutorType(String tutorType) {
		this.tutorType = tutorType;
	}

	public void setSubjectClass(String subjectClass) {
		this.subjectClass = subjectClass;
	}

	@Column(name = "WorkUnitType")
	public String getWorkUnitType() {
		return this.workUnitType;
	}

	public void setWorkUnitType(String workUnitType) {
		this.workUnitType = workUnitType;
	}

	@Column(name = "Region")
	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Column(name = "Note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}