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
 * T671MinorInfoTea entity. @author Yuan
 */

@Table(name = "T671_MinorInfo_Tea$", schema = "dbo", catalog = "TDMS")
public class T671_Bean implements java.io.Serializable {

	// Fields

	private String minorUnitID;
	private String majorId;
	private String minorId;
	private String unitId;
	private String stuName;
	private String stuId;
	private String fromTeaUnit;
	private String fromMaj;
	private String fromClass;
	private String minorFromTeaUnit;
	private String minorMaj;
	private Date beginTime;
	private Date graduateTime;
	private Date time;
	private String note;

	// Constructors

	/** default constructor */
	public T671_Bean() {
	}



	/** full constructor */
	public T671_Bean(	
			String stuName, String stuId,
			String fromTeaUnit,String unitId,  String fromMaj, String majorId, String fromClass,
			String minorFromTeaUnit, String minorUnitID, String minorMaj, String minorId, Date beginTime,
			Date graduateTime, Date time, String note) {
		
		this.minorUnitID = minorUnitID;
		this.majorId = majorId;
		this.minorId = minorId;
		this.unitId = unitId;
		this.stuName = stuName;
		this.stuId = stuId;
		this.fromTeaUnit = fromTeaUnit;
		this.fromMaj = fromMaj;
		this.fromClass = fromClass;
		this.minorFromTeaUnit = minorFromTeaUnit;
		this.minorMaj = minorMaj;
		this.beginTime = beginTime;
		this.graduateTime = graduateTime;
		this.time = time;
		this.note = note;
	}

	// Property accessors


	@JoinColumn(name = "MinorUnitID")
	public String getMinorUnitID() {
		return this.minorUnitID;
	}

	public void setMinorUnitID(
			String minorUnitID) {
		this.minorUnitID = minorUnitID;
	}


	@JoinColumn(name = "MajID")
	public String getMajID() {
		return this.majorId;
	}

	public void setMajID(String majorId) {
		this.majorId = majorId;
	}


	@JoinColumn(name = "MinorID")
	public String getMinorID() {
		return this.minorId;
	}

	public void setMinorID(String minorId) {
		this.minorId = minorId;
	}


	@JoinColumn(name = "UnitID")
	public String getUnitID() {
		return this.unitId;
	}

	public void setUnitID(String unitId) {
		this.unitId = unitId;
	}

	@Column(name = "StuName")
	public String getStuName() {
		return this.stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	@Column(name = "StuID")
	public String getStuId() {
		return this.stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	@Column(name = "FromTeaUnit")
	public String getFromTeaUnit() {
		return this.fromTeaUnit;
	}

	public void setFromTeaUnit(String fromTeaUnit) {
		this.fromTeaUnit = fromTeaUnit;
	}

	@Column(name = "FromMaj")
	public String getFromMaj() {
		return this.fromMaj;
	}

	public void setFromMaj(String fromMaj) {
		this.fromMaj = fromMaj;
	}

	@Column(name = "FromClass")
	public String getFromClass() {
		return this.fromClass;
	}

	public void setFromClass(String fromClass) {
		this.fromClass = fromClass;
	}

	@Column(name = "MinorFromTeaUnit")
	public String getMinorFromTeaUnit() {
		return this.minorFromTeaUnit;
	}

	public void setMinorFromTeaUnit(String minorFromTeaUnit) {
		this.minorFromTeaUnit = minorFromTeaUnit;
	}

	@Column(name = "MinorMaj")
	public String getMinorMaj() {
		return this.minorMaj;
	}

	public void setMinorMaj(String minorMaj) {
		this.minorMaj = minorMaj;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BeginTime", length = 10)
	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "GraduateTime", length = 10)
	public Date getGraduateTime() {
		return this.graduateTime;
	}

	public void setGraduateTime(Date graduateTime) {
		this.graduateTime = graduateTime;
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