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
 * T442GraTutorInfoGra entity. @author MyEclipse Persistence Tools
 */

@Table(name = "T442_GraTutorInfo_Gra$", schema = "dbo", catalog = "TDMS")
public class T442_Bean implements java.io.Serializable {

	// Fields
	private String tutorName;
	private String teaId;
	private String tutorType;
	private String subjectClass;
	private String majorName;
	private String majorId;
	private String resField;
	private String fromUnit;
	private String unitId;
	private Date time;
	private String note;

	public String getTeaId() {
		return teaId;
	}

	public void setTeaId(String teaId) {
		this.teaId = teaId;
	}

	public String getTutorType() {
		return tutorType;
	}

	public void setTutorType(String tutorType) {
		this.tutorType = tutorType;
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

	@Column(name = "TutorName")
	public String getTutorName() {
		return this.tutorName;
	}

	public void setTutorName(String tutorName) {
		this.tutorName = tutorName;
	}

	@Column(name = "SubjectClass")
	public String getSubjectClass() {
		return this.subjectClass;
	}

	public void setSubjectClass(String subjectClass) {
		this.subjectClass = subjectClass;
	}

	@Column(name = "MajorName")
	public String getMajorName() {
		return this.majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	@Column(name = "ResField")
	public String getResField() {
		return this.resField;
	}

	public void setResField(String resField) {
		this.resField = resField;
	}

	@Column(name = "FromUnit")
	public String getFromUnit() {
		return this.fromUnit;
	}

	public void setFromUnit(String fromUnit) {
		this.fromUnit = fromUnit;
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