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
 * S25PractisePlace entity. @author MyEclipse Persistence Tools
 */
@Table(name = "S25_PractisePlace$", schema = "dbo", catalog = "TDMS")
public class S25_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private String labCenterName;
	private String teaUnit;
	private String UnitID;
	private Integer machNum;
	private Double money;
	private Double area;
	private Double newArea;
	private Integer labHour;
	private String labStuNum;
	private Integer yearHour;
	private Integer yearTimes;
	private Integer itemNum;
	private Date time;
	private String note;

	// Constructors
	// Property accessors
	@Id
	@Column(name = "SeqNumber", unique = true, nullable = false)
	public Integer getSeqNumber() {
		return this.seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	@Column(name = "LabCenterName", nullable = false)
	public String getLabCenterName() {
		return this.labCenterName;
	}

	public void setLabCenterName(String labCenterName) {
		this.labCenterName = labCenterName;
	}

	@Column(name = "TeaUnit")
	public String getTeaUnit() {
		return this.teaUnit;
	}

	public void setTeaUnit(String teaUnit) {
		this.teaUnit = teaUnit;
	}

	@Column(name = "MachNum")
	public Integer getMachNum() {
		return this.machNum;
	}

	public void setMachNum(Integer machNum) {
		this.machNum = machNum;
	}

	@Column(name = "Money", precision = 18)
	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@Column(name = "Area", precision = 18)
	public Double getArea() {
		return this.area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	@Column(name = "NewArea", precision = 18)
	public Double getNewArea() {
		return this.newArea;
	}

	public void setNewArea(Double newArea) {
		this.newArea = newArea;
	}

	@Column(name = "LabHour")
	public Integer getLabHour() {
		return this.labHour;
	}

	public void setLabHour(Integer labHour) {
		this.labHour = labHour;
	}

	@Column(name = "LabStuNum")
	public String getLabStuNum() {
		return this.labStuNum;
	}

	public void setLabStuNum(String labStuNum) {
		this.labStuNum = labStuNum;
	}

	@Column(name = "YearHour")
	public Integer getYearHour() {
		return this.yearHour;
	}

	public void setYearHour(Integer yearHour) {
		this.yearHour = yearHour;
	}

	@Column(name = "YearTimes")
	public Integer getYearTimes() {
		return this.yearTimes;
	}

	public void setYearTimes(Integer yearTimes) {
		this.yearTimes = yearTimes;
	}

	@Column(name = "ItemNum")
	public Integer getItemNum() {
		return this.itemNum;
	}

	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
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

	public void setUnitID(String unitID) {
		UnitID = unitID;
	}

	public String getUnitID() {
		return UnitID;
	}

}