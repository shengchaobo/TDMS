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
 * T657HabitusQualifiedSport entity. @author Yuan
 */

@Table(name = "T657_HabitusQualified_Sport$", schema = "dbo", catalog = "TDMS")
public class T657_Bean implements java.io.Serializable {

	// Fields
	private int seqNumber;
	private String unitId;
	private String teaUnit;
	private Double habitusQualifiedRate;
	private Double habitusTestReachRate;
	private Date time;
	private String note;
	
	public int getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
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
	public Double getHabitusQualifiedRate() {
		return habitusQualifiedRate;
	}
	public void setHabitusQualifiedRate(Double habitusQualifiedRate) {
		this.habitusQualifiedRate = habitusQualifiedRate;
	}
	public Double getHabitusTestReachRate() {
		return habitusTestReachRate;
	}
	public void setHabitusTestReachRate(Double habitusTestReachRate) {
		this.habitusTestReachRate = habitusTestReachRate;
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