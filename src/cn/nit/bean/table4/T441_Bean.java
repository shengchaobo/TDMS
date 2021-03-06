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
 * T441MajLeaderTeaTea entity. @author MyEclipse Persistence Tools
 */
@Table(name = "T441_MajLeader_TeaTea$", schema = "dbo", catalog = "TDMS")
public class T441_Bean implements java.io.Serializable {

	// Fields
	private int seqNumber;
	private String fromTeaUnit;
	private String teaUnitID;
	private String majorName;
	private String majorId;
	private String majorLeaderName;
	private String teaId;
	private Date time;
	private String note;
	private String fillUnitID;
	private int checkState;

	public String getTeaUnitID() {
		return teaUnitID;
	}

	public void setTeaUnitID(String teaUnitID) {
		this.teaUnitID = teaUnitID;
	}

	public String getMajorId() {
		return majorId;
	}

	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}

	public String getTeaId() {
		return teaId;
	}

	public void setTeaId(String teaId) {
		this.teaId = teaId;
	}

	@Column(name = "FromTeaUnit")
	public String getFromTeaUnit() {
		return this.fromTeaUnit;
	}

	public void setFromTeaUnit(String fromTeaUnit) {
		this.fromTeaUnit = fromTeaUnit;
	}

	@Column(name = "MajorName")
	public String getMajorName() {
		return this.majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	@Column(name = "MajorLeaderName")
	public String getMajorLeaderName() {
		return this.majorLeaderName;
	}

	public void setMajorLeaderName(String majorLeaderName) {
		this.majorLeaderName = majorLeaderName;
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

	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}

	public int getSeqNumber() {
		return seqNumber;
	}

	public void setFillUnitID(String fillUnitID) {
		this.fillUnitID = fillUnitID;
	}

	public String getFillUnitID() {
		return fillUnitID;
	}

	public void setCheckState(int checkState) {
		this.checkState = checkState;
	}

	public int getCheckState() {
		return checkState;
	}

}