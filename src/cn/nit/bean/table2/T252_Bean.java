package cn.nit.bean.table2;

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
 * T252PractisePlaceInfoTea entity. @author MyEclipse Persistence Tools
 */
@Table(name = "T252_PractisePlaceInfo_Tea$", schema = "dbo", catalog = "TDMS")
public class T252_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private String teaUnitID;
	private String expCenterName;
	private String teaUnit;
	private String labName;
	private Integer expClassHour;
	private Integer stuNum;
	private Integer expHour;
	private Integer expTimes;
	private Integer practiseItemNum;
	private Date time;
	private String note;
	private String fillUnitID;
	private int CheckState;


	// Property accessors
	@Id
	@Column(name = "SeqNumber", unique = true, nullable = false)
	public Integer getSeqNumber() {
		return this.seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	@Column(name = "ExpCenterName", nullable = false)
	public String getExpCenterName() {
		return this.expCenterName;
	}

	public void setExpCenterName(String expCenterName) {
		this.expCenterName = expCenterName;
	}

	@Column(name = "TeaUnit")
	public String getTeaUnit() {
		return this.teaUnit;
	}

	public void setTeaUnit(String teaUnit) {
		this.teaUnit = teaUnit;
	}

	@Column(name = "LabName")
	public String getLabName() {
		return this.labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

	@Column(name = "ExpClassHour")
	public Integer getExpClassHour() {
		return this.expClassHour;
	}

	public void setExpClassHour(Integer expClassHour) {
		this.expClassHour = expClassHour;
	}

	@Column(name = "StuNum")
	public Integer getStuNum() {
		return this.stuNum;
	}

	public void setStuNum(Integer stuNum) {
		this.stuNum = stuNum;
	}

	@Column(name = "ExpHour")
	public Integer getExpHour() {
		return this.expHour;
	}

	public void setExpHour(Integer expHour) {
		this.expHour = expHour;
	}

	@Column(name = "ExpTimes")
	public Integer getExpTimes() {
		return this.expTimes;
	}

	public void setExpTimes(Integer expTimes) {
		this.expTimes = expTimes;
	}

	@Column(name = "PractiseItemNum")
	public Integer getPractiseItemNum() {
		return this.practiseItemNum;
	}

	public void setPractiseItemNum(Integer practiseItemNum) {
		this.practiseItemNum = practiseItemNum;
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

	@Column(name = "FillUnitID", nullable = false)
	public String getFillUnitID() {
		return this.fillUnitID;
	}

	public void setFillUnitID(String fillUnitID) {
		this.fillUnitID = fillUnitID;
	}

	public void setTeaUnitID(String teaUnitID) {
		this.teaUnitID = teaUnitID;
	}

	public String getTeaUnitID() {
		return teaUnitID;
	}

	public void setCheckState(int checkState) {
		CheckState = checkState;
	}

	public int getCheckState() {
		return CheckState;
	}

}