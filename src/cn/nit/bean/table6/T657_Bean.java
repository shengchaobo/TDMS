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

	private String unitId;
	private String teaUnit;
	private Double habitusQualifiedRate;
	private Double habitusTestReachRate;
	private Date time;
	private String note;

	// Constructors

	/** default constructor */
	public T657_Bean() {
	}

	/** full constructor */
	public T657_Bean(String teaUnit,String unitId,
			Double habitusQualifiedRate, Double habitusTestReachRate,
			Date time, String note) {
		this.unitId = unitId;
		this.teaUnit = teaUnit;
		this.habitusQualifiedRate = habitusQualifiedRate;
		this.habitusTestReachRate = habitusTestReachRate;
		this.time = time;
		this.note = note;
	}

	// Property accessors

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

	@Column(name = "HabitusQualifiedRate", precision = 18)
	public Double getHabitusQualifiedRate() {
		return this.habitusQualifiedRate;
	}

	public void setHabitusQualifiedRate(Double habitusQualifiedRate) {
		this.habitusQualifiedRate = habitusQualifiedRate;
	}

	@Column(name = "HabitusTestReachRate", precision = 18)
	public Double getHabitusTestReachRate() {
		return this.habitusTestReachRate;
	}

	public void setHabitusTestReachRate(Double habitusTestReachRate) {
		this.habitusTestReachRate = habitusTestReachRate;
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