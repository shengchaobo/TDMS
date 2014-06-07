package cn.nit.bean.di;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * DiDepartment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DiDepartment", schema = "dbo", catalog = "TDMS")
public class DiDepartmentBean implements java.io.Serializable {

	// Fields

	private String unitId;
	private String unitName;
	private String class1;
	private String class2;
	private String functions;
	private String leader;
	private String teaId;
	private String note;

	// Constructors

	/** default constructor */
	public DiDepartmentBean() {
	}

	/** minimal constructor */
	public DiDepartmentBean(String unitId, String unitName) {
		this.unitId = unitId;
		this.unitName = unitName;
	}

	// Property accessors
	@Id
	@Column(name = "UnitID", unique = true, nullable = false)
	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	@Column(name = "UnitName", nullable = false)
	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	@Column(name = "Class1")
	public String getClass1() {
		return this.class1;
	}

	public void setClass1(String class1) {
		this.class1 = class1;
	}

	@Column(name = "Class2")
	public String getClass2() {
		return this.class2;
	}

	public void setClass2(String class2) {
		this.class2 = class2;
	}

	@Column(name = "Functions")
	public String getFunctions() {
		return this.functions;
	}

	public void setFunctions(String functions) {
		this.functions = functions;
	}

	@Column(name = "Leader")
	public String getLeader() {
		return this.leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	@Column(name = "TeaID")
	public String getTeaId() {
		return this.teaId;
	}

	public void setTeaId(String teaId) {
		this.teaId = teaId;
	}

	@Column(name = "Note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}