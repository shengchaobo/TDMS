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
 * DiMajorTwo entity. @author MyEclipse Persistence Tools
 */


public class DiMajorTwoBean implements java.io.Serializable {

	// Fields

	private String majorNum;
	private String majorName;
	private String version;
	private String duration;
	private String direction;
	private String unitId;
	private String schMajorName;
	private String schMajorID;
	
	
	

	// Constructors



	/** default constructor */
	public DiMajorTwoBean() {
	}

	public String getSchMajorName() {
		return schMajorName;
	}

	public void setSchMajorName(String schMajorName) {
		this.schMajorName = schMajorName;
	}

	public String getSchMajorID() {
		return schMajorID;
	}

	public void setSchMajorID(String schMajorID) {
		this.schMajorID = schMajorID;
	}

	/** minimal constructor */
	public DiMajorTwoBean(String majorNum, String majorName, String unitId) {
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

	@Column(name = "Version")
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "Duration")
	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Column(name = "Direction")
	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Column(name = "UnitID", nullable = false)
	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
}