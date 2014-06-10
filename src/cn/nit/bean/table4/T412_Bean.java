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
 * T412AllMajTeaInfoTeaPer entity. @author MyEclipse Persistence Tools
 */

@Table(name = "T412_AllMajTeaInfo_TeaPer$", schema = "dbo", catalog = "TDMS")
public class T412_Bean implements java.io.Serializable {

	// Fields
	private String fromTeaUnit;
	private String teaUnitID;
	private String majorName;
	private String majorID;
	private String teaName;
	private String teaId;
	private Date time;
	private String note;


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

	@Column(name = "TeaName", nullable = false)
	public String getTeaName() {
		return this.teaName;
	}

	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}

	@Column(name = "TeaID")
	public String getTeaId() {
		return this.teaId;
	}

	public void setTeaId(String teaId) {
		this.teaId = teaId;
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

	public void setTeaUnitID(String teaUnitID) {
		this.teaUnitID = teaUnitID;
	}

	public String getTeaUnitID() {
		return teaUnitID;
	}

	public void setMajorID(String majorID) {
		this.majorID = majorID;
	}

	public String getMajorID() {
		return majorID;
	}

}