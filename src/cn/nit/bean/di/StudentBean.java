package cn.nit.bean.di;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Student", schema = "dbo", catalog = "TDMS")
public class StudentBean implements java.io.Serializable {

	// Fields

	private String studentNum;
	private String studentName;
	private String gender;
	private String institution;
	private String major;
	private String class_;
	private String enrollYear;
	private String idcardNum;
	private String remarks;

	// Constructors

	/** default constructor */
	public StudentBean() {
	}

	/** minimal constructor */
	public StudentBean(String studentNum, String studentName,
			String institution, String major, String class_) {
		this.studentNum = studentNum;
		this.studentName = studentName;
		this.institution = institution;
		this.major = major;
		this.class_ = class_;
	}

	/** full constructor */
	public StudentBean(String studentNum, String studentName,
			String gender, String institution, String major,
			String class_, String enrollYear, String idcardNum,
			String remarks) {
		this.studentNum = studentNum;
		this.studentName = studentName;
		this.gender = gender;
		this.institution = institution;
		this.major = major;
		this.class_ = class_;
		this.enrollYear = enrollYear;
		this.idcardNum = idcardNum;
		this.remarks = remarks;
	}

	// Property accessors
	@Id
	@Column(name = "StudentNum", unique = true, nullable = false)
	public String getStudentNum() {
		return this.studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	@Column(name = "studentName", nullable = false)
	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	@Column(name = "Gender")
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "Institution", nullable = false)
	public String getInstitution() {
		return this.institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	@Column(name = "Major", nullable = false)
	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Column(name = "Class", nullable = false)
	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	@Column(name = "EnrollYear")
	public String getEnrollYear() {
		return this.enrollYear;
	}

	public void setEnrollYear(String enrollYear) {
		this.enrollYear = enrollYear;
	}

	@Column(name = "IDCardNum")
	public String getIdcardNum() {
		return this.idcardNum;
	}

	public void setIdcardNum(String idcardNum) {
		this.idcardNum = idcardNum;
	}

	@Column(name = "Remarks")
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}