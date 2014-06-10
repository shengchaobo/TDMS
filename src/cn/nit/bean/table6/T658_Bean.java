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
 * T658InInterConferenceTeaInter entity. @author Yuan
 */

@Table(name = "T658_InInterConference_TeaInter$", schema = "dbo", catalog = "TDMS")
public class T658_Bean implements java.io.Serializable {

	// Fields

	private String conferenceLevel;
	private String unitId;
	private String teaUnit;
	private String conferenceName;
	private String paperTitle;
	private Date holdTime;
	private String holdPlace;
	private String holdUnit;
	private String awardStuName;
	private Integer awardStuNum;
	private String guideTeaName;
	private Integer guideTeaNum;
	private Date time;
	private String note;

	// Constructors

	/** default constructor */
	public T658_Bean() {
	}

	/** full constructor */
	public T658_Bean(String teaUnit,String unitId,
			 String conferenceName, String paperTitle,
			Date holdTime, String holdPlace, String holdUnit, String conferenceLevel, 
			String awardStuName, Integer awardStuNum, String guideTeaName,
			Integer guideTeaNum, Date time, String note) {
		this.conferenceLevel = conferenceLevel;
		this.unitId = unitId;
		this.teaUnit = teaUnit;
		this.conferenceName = conferenceName;
		this.paperTitle = paperTitle;
		this.holdTime = holdTime;
		this.holdPlace = holdPlace;
		this.holdUnit = holdUnit;
		this.awardStuName = awardStuName;
		this.awardStuNum = awardStuNum;
		this.guideTeaName = guideTeaName;
		this.guideTeaNum = guideTeaNum;
		this.time = time;
		this.note = note;
	}

	// Property accessors


	@JoinColumn(name = "ConferencLevel")
	public String getConferencLevel() {
		return this.conferenceLevel;
	}

	public void setConferencLevel(String conferenceLevel) {
		this.conferenceLevel = conferenceLevel;
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

	@Column(name = "ConferenceName")
	public String getConferenceName() {
		return this.conferenceName;
	}

	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}

	@Column(name = "PaperTitle")
	public String getPaperTitle() {
		return this.paperTitle;
	}

	public void setPaperTitle(String paperTitle) {
		this.paperTitle = paperTitle;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "HoldTime", length = 10)
	public Date getHoldTime() {
		return this.holdTime;
	}

	public void setHoldTime(Date holdTime) {
		this.holdTime = holdTime;
	}

	@Column(name = "HoldPlace")
	public String getHoldPlace() {
		return this.holdPlace;
	}

	public void setHoldPlace(String holdPlace) {
		this.holdPlace = holdPlace;
	}

	@Column(name = "HoldUnit")
	public String getHoldUnit() {
		return this.holdUnit;
	}

	public void setHoldUnit(String holdUnit) {
		this.holdUnit = holdUnit;
	}

	@Column(name = "AwardStuName")
	public String getAwardStuName() {
		return this.awardStuName;
	}

	public void setAwardStuName(String awardStuName) {
		this.awardStuName = awardStuName;
	}

	@Column(name = "AwardStuNum")
	public Integer getAwardStuNum() {
		return this.awardStuNum;
	}

	public void setAwardStuNum(Integer awardStuNum) {
		this.awardStuNum = awardStuNum;
	}

	@Column(name = "GuideTeaName")
	public String getGuideTeaName() {
		return this.guideTeaName;
	}

	public void setGuideTeaName(String guideTeaName) {
		this.guideTeaName = guideTeaName;
	}

	@Column(name = "GuideTeaNum")
	public Integer getGuideTeaNum() {
		return this.guideTeaNum;
	}

	public void setGuideTeaNum(Integer guideTeaNum) {
		this.guideTeaNum = guideTeaNum;
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