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
 * DiMajorOne entity. @author MyEclipse Persistence Tools
 */

public class DiMajorOneBean implements java.io.Serializable {

	// Fields

	private String majorNum;
	private String majorName;
	private String direction;
	private String duration;
	private String unitId;

	// Constructors

	/** default constructor */
	public DiMajorOneBean() {
	}

	/** minimal constructor */
	public DiMajorOneBean(String majorNum, String majorName, String unitId) {
		this.majorNum = majorNum;
		this.majorName = majorName;
		this.unitId = unitId;
	}

	// Property accessors
	@Id
	@Column(name = "MajorNum", unique = true, nullable = false)
	public String getMajorNum() {
		return this.majorNum;
	}

	public void setMajorNum(String majorNum) {
		this.majorNum = majorNum;
	}

	@Column(name = "MajorName", nullable = false)
	public String getMajorName() {
		return this.majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	@Column(name = "Direction")
	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Column(name = "Duration")
	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Column(name = "UnitID", nullable = false)
	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

}