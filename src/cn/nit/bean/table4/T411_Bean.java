package cn.nit.bean.table4;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T411TeaBasicInfoPer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T411_TeaBasicInfo_Per$", schema = "dbo", catalog = "TDMS")
public class T411_Bean implements java.io.Serializable {

	// Fields

	private String teaId;
	private String teaName;
	private String gender;
	private Date birthday;
	private Date admisTime;
	private String teaState;
	private Date beginWorkTime;
	private String idcode;
	private String fromOffice;
	private String officeID;
	private String fromUnit;
	private String unitId;
	private String fromTeaResOffice;
	private String teaResOfficeID;
	private String education;
	private String topDegree;
	private String graSch;
	private String major;
	private String source;
	private String adminLevel;
	private String majTechTitle;
	private String teaTitle;
	private String notTeaTitle;
	private String subjectClass;
	private Boolean doubleTea;
	private Boolean industry;
	private Boolean engineer;
	private Boolean teaBase;
	private String teaFlag; //老师的标志，表是外聘还是正式
	private String note;

	// Property accessors
	@Id
	@Column(name = "TeaID", unique = true, nullable = false)
	public String getTeaId() {
		return this.teaId;
	}

	public void setTeaId(String teaId) {
		this.teaId = teaId;
	}

	@Column(name = "TeaName", nullable = false)
	public String getTeaName() {
		return this.teaName;
	}

	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}

	@Column(name = "gender")
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
	@Column(name = "AdmisTime", length = 10)
	public Date getAdmisTime() {
		return this.admisTime;
	}

	public void setAdmisTime(Date admisTime) {
		this.admisTime = admisTime;
	}

	@Column(name = "TeaState")
	public String getTeaState() {
		return this.teaState;
	}

	public void setTeaState(String teaState) {
		this.teaState = teaState;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BeginWorkTime", length = 10)
	public Date getBeginWorkTime() {
		return this.beginWorkTime;
	}

	public void setBeginWorkTime(Date beginWorkTime) {
		this.beginWorkTime = beginWorkTime;
	}

	@Column(name = "IDCode")
	public String getIdcode() {
		return this.idcode;
	}

	public void setIdcode(String idcode) {
		this.idcode = idcode;
	}

	@Column(name = "FromOffice")
	public String getFromOffice() {
		return this.fromOffice;
	}

	public void setFromOffice(String fromOffice) {
		this.fromOffice = fromOffice;
	}

	@Column(name = "FromUnit")
	public String getFromUnit() {
		return this.fromUnit;
	}

	public void setFromUnit(String fromUnit) {
		this.fromUnit = fromUnit;
	}

	@Column(name = "FromTeaResOffice")
	public String getFromTeaResOffice() {
		return this.fromTeaResOffice;
	}

	public void setFromTeaResOffice(String fromTeaResOffice) {
		this.fromTeaResOffice = fromTeaResOffice;
	}

	@Column(name = "GraSch")
	public String getGraSch() {
		return this.graSch;
	}

	public void setGraSch(String graSch) {
		this.graSch = graSch;
	}

	@Column(name = "Major")
	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Column(name = "AdminLevel")
	public String getAdminLevel() {
		return this.adminLevel;
	}

	public void setAdminLevel(String adminLevel) {
		this.adminLevel = adminLevel;
	}

	@Column(name = "NotTeaTitle")
	public String getNotTeaTitle() {
		return this.notTeaTitle;
	}

	public void setNotTeaTitle(String notTeaTitle) {
		this.notTeaTitle = notTeaTitle;
	}

	@Column(name = "SubjectClass")
	public String getSubjectClass() {
		return this.subjectClass;
	}

	public void setSubjectClass(String subjectClass) {
		this.subjectClass = subjectClass;
	}

	@Column(name = "DoubleTea")
	public Boolean getDoubleTea() {
		return this.doubleTea;
	}

	public void setDoubleTea(Boolean doubleTea) {
		this.doubleTea = doubleTea;
	}

	@Column(name = "Industry")
	public Boolean getIndustry() {
		return this.industry;
	}

	public void setIndustry(Boolean industry) {
		this.industry = industry;
	}

	@Column(name = "Engineer")
	public Boolean getEngineer() {
		return this.engineer;
	}

	public void setEngineer(Boolean engineer) {
		this.engineer = engineer;
	}

	@Column(name = "TeaBase")
	public Boolean getTeaBase() {
		return this.teaBase;
	}

	public void setTeaBase(Boolean teaBase) {
		this.teaBase = teaBase;
	}

	@Column(name = "Note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public String getOfficeID() {
		return officeID;
	}

	public void setOfficeID(String officeID) {
		this.officeID = officeID;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getTopDegree() {
		return topDegree;
	}

	public void setTopDegree(String topDegree) {
		this.topDegree = topDegree;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMajTechTitle() {
		return majTechTitle;
	}

	public void setMajTechTitle(String majTechTitle) {
		this.majTechTitle = majTechTitle;
	}

	public String getTeaTitle() {
		return teaTitle;
	}

	public void setTeaTitle(String teaTitle) {
		this.teaTitle = teaTitle;
	}

	public void setTeaResOfficeID(String teaResOfficeID) {
		this.teaResOfficeID = teaResOfficeID;
	}

	public String getTeaResOfficeID() {
		return teaResOfficeID;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setTeaFlag(String teaFlag) {
		this.teaFlag = teaFlag;
	}

	public String getTeaFlag() {
		return teaFlag;
	}
}