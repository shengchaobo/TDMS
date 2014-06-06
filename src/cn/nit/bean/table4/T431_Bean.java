package cn.nit.bean.table4;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * A431ManagerInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "A431_ManagerInfo$", schema = "dbo", catalog = "TDMS")
public class T431_Bean implements java.io.Serializable {

	// Fields
	private String name;
	private String teaId;
	private String managerType;
	private String fromDept;
	private String unitId;
	private String staffType;
	private String note;

	@Column(name = "Name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TeaID", nullable = false)
	public String getTeaId() {
		return this.teaId;
	}

	public void setTeaId(String teaId) {
		this.teaId = teaId;
	}

	@Column(name = "ManagerType")
	public String getManagerType() {
		return this.managerType;
	}

	public void setManagerType(String managerType) {
		this.managerType = managerType;
	}

	@Column(name = "FromDept")
	public String getFromDept() {
		return this.fromDept;
	}

	public void setFromDept(String fromDept) {
		this.fromDept = fromDept;
	}

	@Column(name = "UnitID")
	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	@Column(name = "StaffType")
	public String getStaffType() {
		return this.staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	@Column(name = "Note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}