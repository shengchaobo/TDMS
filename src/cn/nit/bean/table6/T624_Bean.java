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
 * T624JuniorAdmisInfoAdmission entity. @author Yuan
 */

@Table(name = "T624_JuniorAdmisInfo_Admission$", schema = "dbo", catalog = "TDMS")
public class T624_Bean implements java.io.Serializable {

	// Fields
	private int seqNumber;
	private String majorId;
	private String unitId;
	private String teaUnit;
	private String majorName;
	private String majorFieldName;
	private Boolean isCurrentYearAdmis;
	private Integer planAdmisNum;
	private Integer actualAdmisNum;
	private Integer actualRegisterNum;
	private Integer genHignSchNum;
	private Integer secondVocationNum;
	private Integer otherNum;
	private Date time;
	private String note;
	
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
	public String getMajorFieldName() {
		return majorFieldName;
	}
	public void setMajorFieldName(String majorFieldName) {
		this.majorFieldName = majorFieldName;
	}
	public Boolean getIsCurrentYearAdmis() {
		return isCurrentYearAdmis;
	}
	public void setIsCurrentYearAdmis(Boolean isCurrentYearAdmis) {
		this.isCurrentYearAdmis = isCurrentYearAdmis;
	}
	public Integer getPlanAdmisNum() {
		return planAdmisNum;
	}
	public void setPlanAdmisNum(Integer planAdmisNum) {
		this.planAdmisNum = planAdmisNum;
	}
	public Integer getActualAdmisNum() {
		return actualAdmisNum;
	}
	public void setActualAdmisNum(Integer actualAdmisNum) {
		this.actualAdmisNum = actualAdmisNum;
	}
	public Integer getActualRegisterNum() {
		return actualRegisterNum;
	}
	public void setActualRegisterNum(Integer actualRegisterNum) {
		this.actualRegisterNum = actualRegisterNum;
	}
	public Integer getGenHignSchNum() {
		return genHignSchNum;
	}
	public void setGenHignSchNum(Integer genHignSchNum) {
		this.genHignSchNum = genHignSchNum;
	}
	public Integer getSecondVocationNum() {
		return secondVocationNum;
	}
	public void setSecondVocationNum(Integer secondVocationNum) {
		this.secondVocationNum = secondVocationNum;
	}
	public Integer getOtherNum() {
		return otherNum;
	}
	public void setOtherNum(Integer otherNum) {
		this.otherNum = otherNum;
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