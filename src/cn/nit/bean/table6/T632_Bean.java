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
 * T632GraStuEmployInfoAdmission entity. @author Yuan
 */

@Table(name = "T632_GraStuEmployInfo_Admission$", schema = "dbo", catalog = "TDMS")
public class T632_Bean implements java.io.Serializable {

	// Fields

	private String majorId;
	private String unitId;
	private String teaUnit;
	private String majorName;
	private Integer sumEmployNum;
	private Integer govermentNum;
	private Integer pubInstiNum;
	private Integer enterpriseNum;
	private Integer forceNum;
	private Integer flexibleEmploy;
	private Integer goOnHighStudy;
	private Integer nationItemEmploy;
	private Integer otherEmploy;
	private Integer sumGoOnHighStudyNum;
	private Integer recommendGraNum;
	private Integer examGraApplyNum;
	private Integer examGraEnrollNum;
	private Integer examGraInSch;
	private Integer examGraOutSch;
	private Integer abroadNum;
	private Date time;
	private String note;

	// Constructors

	/** default constructor */
	public T632_Bean() {
	}

	/** full constructor */
	public T632_Bean(
			String teaUnit, String unitId, 
			String majorName, String majorId, Integer sumEmployNum, Integer govermentNum,
			Integer pubInstiNum, Integer enterpriseNum, Integer forceNum,
			Integer flexibleEmploy, Integer goOnHighStudy,
			Integer nationItemEmploy, Integer otherEmploy,
			Integer sumGoOnHighStudyNum, Integer recommendGraNum,
			Integer examGraApplyNum, Integer examGraEnrollNum,
			Integer examGraInSch, Integer examGraOutSch, Integer abroadNum,
			Date time, String note) {
		this.majorId = majorId;
		this.unitId = unitId;
		this.teaUnit = teaUnit;
		this.majorName = majorName;
		this.sumEmployNum = sumEmployNum;
		this.govermentNum = govermentNum;
		this.pubInstiNum = pubInstiNum;
		this.enterpriseNum = enterpriseNum;
		this.forceNum = forceNum;
		this.flexibleEmploy = flexibleEmploy;
		this.goOnHighStudy = goOnHighStudy;
		this.nationItemEmploy = nationItemEmploy;
		this.otherEmploy = otherEmploy;
		this.sumGoOnHighStudyNum = sumGoOnHighStudyNum;
		this.recommendGraNum = recommendGraNum;
		this.examGraApplyNum = examGraApplyNum;
		this.examGraEnrollNum = examGraEnrollNum;
		this.examGraInSch = examGraInSch;
		this.examGraOutSch = examGraOutSch;
		this.abroadNum = abroadNum;
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

	@JoinColumn(name = "UintID")
	public String getUintID() {
		return this.unitId;
	}

	public void setUintID(String unitId) {
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

	@Column(name = "SumEmployNum")
	public Integer getSumEmployNum() {
		return this.sumEmployNum;
	}

	public void setSumEmployNum(Integer sumEmployNum) {
		this.sumEmployNum = sumEmployNum;
	}

	@Column(name = "GovermentNum")
	public Integer getGovermentNum() {
		return this.govermentNum;
	}

	public void setGovermentNum(Integer govermentNum) {
		this.govermentNum = govermentNum;
	}

	@Column(name = "PubInstiNum")
	public Integer getPubInstiNum() {
		return this.pubInstiNum;
	}

	public void setPubInstiNum(Integer pubInstiNum) {
		this.pubInstiNum = pubInstiNum;
	}

	@Column(name = "EnterpriseNum")
	public Integer getEnterpriseNum() {
		return this.enterpriseNum;
	}

	public void setEnterpriseNum(Integer enterpriseNum) {
		this.enterpriseNum = enterpriseNum;
	}

	@Column(name = "ForceNum")
	public Integer getForceNum() {
		return this.forceNum;
	}

	public void setForceNum(Integer forceNum) {
		this.forceNum = forceNum;
	}

	@Column(name = "FlexibleEmploy")
	public Integer getFlexibleEmploy() {
		return this.flexibleEmploy;
	}

	public void setFlexibleEmploy(Integer flexibleEmploy) {
		this.flexibleEmploy = flexibleEmploy;
	}

	@Column(name = "GoOnHighStudy")
	public Integer getGoOnHighStudy() {
		return this.goOnHighStudy;
	}

	public void setGoOnHighStudy(Integer goOnHighStudy) {
		this.goOnHighStudy = goOnHighStudy;
	}

	@Column(name = "NationItemEmploy")
	public Integer getNationItemEmploy() {
		return this.nationItemEmploy;
	}

	public void setNationItemEmploy(Integer nationItemEmploy) {
		this.nationItemEmploy = nationItemEmploy;
	}

	@Column(name = "OtherEmploy")
	public Integer getOtherEmploy() {
		return this.otherEmploy;
	}

	public void setOtherEmploy(Integer otherEmploy) {
		this.otherEmploy = otherEmploy;
	}

	@Column(name = "SumGoOnHighStudyNum")
	public Integer getSumGoOnHighStudyNum() {
		return this.sumGoOnHighStudyNum;
	}

	public void setSumGoOnHighStudyNum(Integer sumGoOnHighStudyNum) {
		this.sumGoOnHighStudyNum = sumGoOnHighStudyNum;
	}

	@Column(name = "RecommendGraNum")
	public Integer getRecommendGraNum() {
		return this.recommendGraNum;
	}

	public void setRecommendGraNum(Integer recommendGraNum) {
		this.recommendGraNum = recommendGraNum;
	}

	@Column(name = "ExamGraApplyNum")
	public Integer getExamGraApplyNum() {
		return this.examGraApplyNum;
	}

	public void setExamGraApplyNum(Integer examGraApplyNum) {
		this.examGraApplyNum = examGraApplyNum;
	}

	@Column(name = "ExamGraEnrollNum")
	public Integer getExamGraEnrollNum() {
		return this.examGraEnrollNum;
	}

	public void setExamGraEnrollNum(Integer examGraEnrollNum) {
		this.examGraEnrollNum = examGraEnrollNum;
	}

	@Column(name = "ExamGraInSch")
	public Integer getExamGraInSch() {
		return this.examGraInSch;
	}

	public void setExamGraInSch(Integer examGraInSch) {
		this.examGraInSch = examGraInSch;
	}

	@Column(name = "ExamGraOutSch")
	public Integer getExamGraOutSch() {
		return this.examGraOutSch;
	}

	public void setExamGraOutSch(Integer examGraOutSch) {
		this.examGraOutSch = examGraOutSch;
	}

	@Column(name = "AbroadNum")
	public Integer getAbroadNum() {
		return this.abroadNum;
	}

	public void setAbroadNum(Integer abroadNum) {
		this.abroadNum = abroadNum;
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