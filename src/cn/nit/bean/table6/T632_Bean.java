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
	private int seqNumber;
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
	
	/**
	 * 		GraStuEmployInfo.setSumGoOnHighStudyNum(GraStuEmployInfo.getRecommendGraNum()+GraStuEmployInfo.getExamGraInSch()+GraStuEmployInfo.getExamGraOutSch()+GraStuEmployInfo.getAbroadNum());//统计生成-应届升学总人数
		GraStuEmployInfo.setExamGraEnrollNum(GraStuEmployInfo.getExamGraInSch()+GraStuEmployInfo.getExamGraOutSch());//统计生成-考研录取总人数
		GraStuEmployInfo.setGoOnHighStudy(GraStuEmployInfo.getSumGoOnHighStudyNum());//引用生成--升学
		
		GraStuEmployInfo.setSumEmployNum(GraStuEmployInfo.getGovermentNum()+GraStuEmployInfo.getPubInstiNum()+GraStuEmployInfo.getEnterpriseNum()+GraStuEmployInfo.getForceNum()
				+GraStuEmployInfo.getFlexibleEmploy()+GraStuEmployInfo.getNationItemEmploy()+GraStuEmployInfo.getOtherEmploy()+GraStuEmployInfo.getGoOnHighStudy());//统计生成-应届就业总人数
	 * */
	
	public int getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
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
	public String getTeaUnit() {
		return teaUnit;
	}
	public void setTeaUnit(String teaUnit) {
		this.teaUnit = teaUnit;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public Integer getSumEmployNum() {
		return sumEmployNum;
	}
	//统计生成-应届就业总人数
	public void setSumEmployNum(Integer sumEmployNum) {
		sumEmployNum = this.getGovermentNum()+this.getPubInstiNum()+this.getEnterpriseNum()+this.getForceNum()
		+this.getFlexibleEmploy()+this.getNationItemEmploy()+this.getOtherEmploy()+this.getGoOnHighStudy();
		this.sumEmployNum = sumEmployNum;
	}
	public Integer getGovermentNum() {
		return govermentNum;
	}
	public void setGovermentNum(Integer govermentNum) {
		this.govermentNum = govermentNum;
	}
	public Integer getPubInstiNum() {
		return pubInstiNum;
	}
	public void setPubInstiNum(Integer pubInstiNum) {
		this.pubInstiNum = pubInstiNum;
	}
	public Integer getEnterpriseNum() {
		return enterpriseNum;
	}
	public void setEnterpriseNum(Integer enterpriseNum) {
		this.enterpriseNum = enterpriseNum;
	}
	public Integer getForceNum() {
		return forceNum;
	}
	public void setForceNum(Integer forceNum) {
		this.forceNum = forceNum;
	}
	public Integer getFlexibleEmploy() {
		return flexibleEmploy;
	}
	public void setFlexibleEmploy(Integer flexibleEmploy) {
		this.flexibleEmploy = flexibleEmploy;
	}
	public Integer getGoOnHighStudy() {
		return goOnHighStudy;
	}
	
	////引用生成--升学
	public void setGoOnHighStudy(Integer goOnHighStudy) {
		goOnHighStudy = this.getSumGoOnHighStudyNum();
		this.goOnHighStudy = goOnHighStudy;
	}
	public Integer getNationItemEmploy() {
		return nationItemEmploy;
	}
	public void setNationItemEmploy(Integer nationItemEmploy) {
		this.nationItemEmploy = nationItemEmploy;
	}
	public Integer getOtherEmploy() {
		return otherEmploy;
	}
	public void setOtherEmploy(Integer otherEmploy) {
		this.otherEmploy = otherEmploy;
	}
	public Integer getSumGoOnHighStudyNum() {
		return sumGoOnHighStudyNum;
	}
	////统计生成-应届升学总人数
	public void setSumGoOnHighStudyNum(Integer sumGoOnHighStudyNum) {
		
		sumGoOnHighStudyNum = this.getRecommendGraNum()+this.getExamGraInSch()+this.getExamGraOutSch()+this.getAbroadNum();
		this.sumGoOnHighStudyNum = sumGoOnHighStudyNum;
	}
	public Integer getRecommendGraNum() {
		return recommendGraNum;
	}
	public void setRecommendGraNum(Integer recommendGraNum) {
		this.recommendGraNum = recommendGraNum;
	}
	public Integer getExamGraApplyNum() {
		return examGraApplyNum;
	}
	public void setExamGraApplyNum(Integer examGraApplyNum) {
		this.examGraApplyNum = examGraApplyNum;
	}
	public Integer getExamGraEnrollNum() {
		return examGraEnrollNum;
	}
	//统计生成-考研录取总人数
	public void setExamGraEnrollNum(Integer examGraEnrollNum) {
		examGraEnrollNum = this.getExamGraInSch()+this.getExamGraOutSch();
		this.examGraEnrollNum = examGraEnrollNum;
	}
	public Integer getExamGraInSch() {
		return examGraInSch;
	}
	public void setExamGraInSch(Integer examGraInSch) {
		this.examGraInSch = examGraInSch;
	}
	public Integer getExamGraOutSch() {
		return examGraOutSch;
	}
	public void setExamGraOutSch(Integer examGraOutSch) {
		this.examGraOutSch = examGraOutSch;
	}
	public Integer getAbroadNum() {
		return abroadNum;
	}
	public void setAbroadNum(Integer abroadNum) {
		this.abroadNum = abroadNum;
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