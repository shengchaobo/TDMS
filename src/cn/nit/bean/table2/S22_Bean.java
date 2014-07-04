package cn.nit.bean.table2;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * S22TeaAdminArea entity. @author MyEclipse Persistence Tools
 */
@Table(name = "S22_TeaAdminArea$", schema = "dbo", catalog = "TDMS")
public class S22_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private Double sumTeaArea;
	private Double classrmArea;
	private Double libArea;
	private Double labArea;
	private Double resArea;
	private Double phyArea;
	private Double hallArea;
	private Double sumAdminArea;
	private String note;
	private Date time;

	// Constructors

	// Property accessors
	public Integer getSeqNumber() {
		return this.seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	@Column(name = "SumTeaArea", precision = 18)
	public Double getSumTeaArea() {
		return this.sumTeaArea;
	}

	public void setSumTeaArea(Double sumTeaArea) {
		this.sumTeaArea = sumTeaArea;
	}

	@Column(name = "ClassrmArea", precision = 18)
	public Double getClassrmArea() {
		return this.classrmArea;
	}

	public void setClassrmArea(Double classrmArea) {
		this.classrmArea = classrmArea;
	}

	@Column(name = "LibArea", precision = 18)
	public Double getLibArea() {
		return this.libArea;
	}

	public void setLibArea(Double libArea) {
		this.libArea = libArea;
	}

	@Column(name = "LabArea", precision = 18)
	public Double getLabArea() {
		return this.labArea;
	}

	public void setLabArea(Double labArea) {
		this.labArea = labArea;
	}

	@Column(name = "ResArea", precision = 18)
	public Double getResArea() {
		return this.resArea;
	}

	public void setResArea(Double resArea) {
		this.resArea = resArea;
	}

	@Column(name = "PhyArea", precision = 18)
	public Double getPhyArea() {
		return this.phyArea;
	}

	public void setPhyArea(Double phyArea) {
		this.phyArea = phyArea;
	}

	@Column(name = "HallArea", precision = 18)
	public Double getHallArea() {
		return this.hallArea;
	}

	public void setHallArea(Double hallArea) {
		this.hallArea = hallArea;
	}

	@Column(name = "SumAdminArea", precision = 18)
	public Double getSumAdminArea() {
		return this.sumAdminArea;
	}

	public void setSumAdminArea(Double sumAdminArea) {
		this.sumAdminArea = sumAdminArea;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Time", length = 10)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNote() {
		return note;
	}

}