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

	// Constructors

	/** default constructor */
	public T624_Bean() {
	}

	/** full constructor */
	public T624_Bean(String teaUnit,
			String unitId, 
			String majorName, String majorId, String majorFieldName,
			Boolean isCurrentYearAdmis, Integer planAdmisNum,
			Integer actualAdmisNum, Integer actualRegisterNum,
			Integer genHignSchNum, Integer secondVocationNum, Integer otherNum,
			Date time, String note) {
		this.majorId = majorId;
		this.unitId = unitId;
		this.teaUnit = teaUnit;
		this.majorName = majorName;
		this.majorFieldName = majorFieldName;
		this.isCurrentYearAdmis = isCurrentYearAdmis;
		this.planAdmisNum = planAdmisNum;
		this.actualAdmisNum = actualAdmisNum;
		this.actualRegisterNum = actualRegisterNum;
		this.genHignSchNum = genHignSchNum;
		this.secondVocationNum = secondVocationNum;
		this.otherNum = otherNum;
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

	@Column(name = "MajorFieldName")
	public String getMajorFieldName() {
		return this.majorFieldName;
	}

	public void setMajorFieldName(String majorFieldName) {
		this.majorFieldName = majorFieldName;
	}

	@Column(name = "IsCurrentYearAdmis")
	public Boolean getIsCurrentYearAdmis() {
		return this.isCurrentYearAdmis;
	}

	public void setIsCurrentYearAdmis(Boolean isCurrentYearAdmis) {
		this.isCurrentYearAdmis = isCurrentYearAdmis;
	}

	@Column(name = "PlanAdmisNum")
	public Integer getPlanAdmisNum() {
		return this.planAdmisNum;
	}

	public void setPlanAdmisNum(Integer planAdmisNum) {
		this.planAdmisNum = planAdmisNum;
	}

	@Column(name = "ActualAdmisNum")
	public Integer getActualAdmisNum() {
		return this.actualAdmisNum;
	}

	public void setActualAdmisNum(Integer actualAdmisNum) {
		this.actualAdmisNum = actualAdmisNum;
	}

	@Column(name = "ActualRegisterNum")
	public Integer getActualRegisterNum() {
		return this.actualRegisterNum;
	}

	public void setActualRegisterNum(Integer actualRegisterNum) {
		this.actualRegisterNum = actualRegisterNum;
	}

	@Column(name = "GenHignSchNum")
	public Integer getGenHignSchNum() {
		return this.genHignSchNum;
	}

	public void setGenHignSchNum(Integer genHignSchNum) {
		this.genHignSchNum = genHignSchNum;
	}

	@Column(name = "SecondVocationNum")
	public Integer getSecondVocationNum() {
		return this.secondVocationNum;
	}

	public void setSecondVocationNum(Integer secondVocationNum) {
		this.secondVocationNum = secondVocationNum;
	}

	@Column(name = "OtherNum")
	public Integer getOtherNum() {
		return this.otherNum;
	}

	public void setOtherNum(Integer otherNum) {
		this.otherNum = otherNum;
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