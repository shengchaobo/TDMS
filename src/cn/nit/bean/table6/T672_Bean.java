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
 * T672DualDegreeInfoTea entity. @author Yuan
 */

@Table(name = "T672_DualDegreeInfo_Tea$", schema = "dbo", catalog = "TDMS")
public class T672_Bean implements java.io.Serializable {

	// Fields
	private int seqNumber;
	private String dualDegreeUnitId;
	private String majId;

	private String dualDegreeId;
	private String unitId;
	private String stuName;
	private String stuId;
	private String fromTeaUnit;
	private String fromMaj;
	private String fromClass;
	private String dualDegreeFromTeaUnit;
	private String dualDegreeMaj;
	private Date beginTime;
	private Date graduateTime;
	private Date time;
	private String note;
	
	
	public int getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}
	public String getDualDegreeUnitId() {
		return dualDegreeUnitId;
	}
	public void setDualDegreeUnitId(String dualDegreeUnitId) {
		this.dualDegreeUnitId = dualDegreeUnitId;
	}
	public String getMajId() {
		return majId;
	}
	public void setMajId(String majId) {
		this.majId = majId;
	}
	public String getDualDegreeId() {
		return dualDegreeId;
	}
	public void setDualDegreeId(String dualDegreeId) {
		this.dualDegreeId = dualDegreeId;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getFromTeaUnit() {
		return fromTeaUnit;
	}
	public void setFromTeaUnit(String fromTeaUnit) {
		this.fromTeaUnit = fromTeaUnit;
	}
	public String getFromMaj() {
		return fromMaj;
	}
	public void setFromMaj(String fromMaj) {
		this.fromMaj = fromMaj;
	}
	public String getFromClass() {
		return fromClass;
	}
	public void setFromClass(String fromClass) {
		this.fromClass = fromClass;
	}
	public String getDualDegreeFromTeaUnit() {
		return dualDegreeFromTeaUnit;
	}
	public void setDualDegreeFromTeaUnit(String dualDegreeFromTeaUnit) {
		this.dualDegreeFromTeaUnit = dualDegreeFromTeaUnit;
	}
	public String getDualDegreeMaj() {
		return dualDegreeMaj;
	}
	public void setDualDegreeMaj(String dualDegreeMaj) {
		this.dualDegreeMaj = dualDegreeMaj;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getGraduateTime() {
		return graduateTime;
	}
	public void setGraduateTime(Date graduateTime) {
		this.graduateTime = graduateTime;
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