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
 * T26OutSchPractiseBaseTea entity. @author MyEclipse Persistence Tools
 */
@Table(name = "T26_OutSchPractiseBase_Tea$", schema = "dbo", catalog = "TDMS")
public class T26_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private String signLevel;
	private String teaUnitID;
	private String baseLevel;
	private String practiseBase;
	private String teaUnit;
	private String address;
	private String forMajor;
	private Integer stuNumEachTime;
	private Integer stuNumEachYear;
	private Date time;
	private String note;
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

	@Column(name = "PractiseBase", nullable = false)
	public String getPractiseBase() {
		return this.practiseBase;
	}

	public void setPractiseBase(String practiseBase) {
		this.practiseBase = practiseBase;
	}

	@Column(name = "TeaUnit")
	public String getTeaUnit() {
		return this.teaUnit;
	}

	public void setTeaUnit(String teaUnit) {
		this.teaUnit = teaUnit;
	}

	@Column(name = "Address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "ForMajor")
	public String getForMajor() {
		return this.forMajor;
	}

	public void setForMajor(String forMajor) {
		this.forMajor = forMajor;
	}

	@Column(name = "StuNumEachTime")
	public Integer getStuNumEachTime() {
		return this.stuNumEachTime;
	}

	public void setStuNumEachTime(Integer stuNumEachTime) {
		this.stuNumEachTime = stuNumEachTime;
	}

	@Column(name = "StuNumEachYear")
	public Integer getStuNumEachYear() {
		return this.stuNumEachYear;
	}

	public void setStuNumEachYear(Integer stuNumEachYear) {
		this.stuNumEachYear = stuNumEachYear;
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

	public void setSignLevel(String signLevel) {
		this.signLevel = signLevel;
	}

	public String getSignLevel() {
		return signLevel;
	}

	public void setTeaUnitID(String teaUnitID) {
		this.teaUnitID = teaUnitID;
	}

	public String getTeaUnitID() {
		return teaUnitID;
	}

	public void setBaseLevel(String baseLevel) {
		this.baseLevel = baseLevel;
	}

	public String getBaseLevel() {
		return baseLevel;
	}

	public void setCheckState(int checkState) {
		CheckState = checkState;
	}

	public int getCheckState() {
		return CheckState;
	}

}