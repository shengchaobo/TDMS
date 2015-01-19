package cn.nit.bean.table2;

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
 * T251PractisePlaceInfoEqu entity. @author MyEclipse Persistence Tools
 */
@Table(name = "T251_PractisePlaceInfo_EQU$", schema = "dbo", catalog = "TDMS")
public class T251_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private String teaUnitID;
	private String expCenterName;
	private String teaUnit;
	private String labName;
	private Date buildTime;
	private String place;
	private Integer machNum;
	private double money;
	private double area;
	private double newAddArea;
	private String nature;
	private String forMajor;
	private Date time;
	private String note;
	private int CheckState;



	// Property accessors
	@Id
	@Column(name = "SeqNumber", unique = true, nullable = false)
	public Integer getSeqNumber() {
		return this.seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	@Column(name = "ExpCenterName", nullable = false)
	public String getExpCenterName() {
		return this.expCenterName;
	}

	public void setExpCenterName(String expCenterName) {
		this.expCenterName = expCenterName;
	}

	@Column(name = "TeaUnit")
	public String getTeaUnit() {
		return this.teaUnit;
	}

	public void setTeaUnit(String teaUnit) {
		this.teaUnit = teaUnit;
	}

	@Column(name = "LabName")
	public String getLabName() {
		return this.labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BuildTime", length = 10)
	public Date getBuildTime() {
		return this.buildTime;
	}

	public void setBuildTime(Date buildTime) {
		this.buildTime = buildTime;
	}

	@Column(name = "Place")
	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Column(name = "MachNum")
	public Integer getMachNum() {
		return this.machNum;
	}

	public void setMachNum(Integer machNum) {
		this.machNum = machNum;
	}

	@Column(name = "Nature")
	public String getNature() {
		return this.nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	@Column(name = "ForMajor")
	public String getForMajor() {
		return this.forMajor;
	}

	public void setForMajor(String forMajor) {
		this.forMajor = forMajor;
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

	public void setMoney(double money) {
		this.money = money;
	}

	public double getMoney() {
		return money;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public double getArea() {
		return area;
	}

	public void setNewAddArea(double newAddArea) {
		this.newAddArea = newAddArea;
	}

	public double getNewAddArea() {
		return newAddArea;
	}

	public void setCheckState(int checkState) {
		CheckState = checkState;
	}

	public int getCheckState() {
		return CheckState;
	}

}