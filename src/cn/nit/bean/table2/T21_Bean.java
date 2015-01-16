package cn.nit.bean.table2;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T21OccupyAndCoverAreaLog entity. @author MyEclipse Persistence Tools
 */
@Table(name = "T21_OccupyAndCoverArea_Log$", schema = "dbo", catalog = "TDMS")
public class T21_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private Double sumArea;
	private Double schProArea;
	private Double greenArea;
	private Double notSchProArea;
	private Double greenAreaNotInSch;
	private Double onlyUseArea;
	private Double coUseArea;
	private Double sumCoverArea;
	private Double schProCovArea;
	private Double notSchProCovArea;
	private Double onlyUseCovArea;
	private Double coUseCovArea;
	private Date time;
	private String note;
	private int checkState;
	
	// Property accessors
	@Id
	@Column(name = "SeqNumber", unique = true, nullable = false)
	public Integer getSeqNumber() {
		return this.seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	@Column(name = "SumArea", precision = 18)
	public Double getSumArea() {
		return this.sumArea;
	}

	public void setSumArea(Double sumArea) {
		this.sumArea = sumArea;
	}

	@Column(name = "SchProArea", precision = 18)
	public Double getSchProArea() {
		return this.schProArea;
	}

	public void setSchProArea(Double schProArea) {
		this.schProArea = schProArea;
	}

	@Column(name = "GreenArea", precision = 18)
	public Double getGreenArea() {
		return this.greenArea;
	}

	public void setGreenArea(Double greenArea) {
		this.greenArea = greenArea;
	}

	@Column(name = "NotSchProArea", precision = 18)
	public Double getNotSchProArea() {
		return this.notSchProArea;
	}

	public void setNotSchProArea(Double notSchProArea) {
		this.notSchProArea = notSchProArea;
	}

	@Column(name = "GreenAreaNotInSch", precision = 18)
	public Double getGreenAreaNotInSch() {
		return this.greenAreaNotInSch;
	}

	public void setGreenAreaNotInSch(Double greenAreaNotInSch) {
		this.greenAreaNotInSch = greenAreaNotInSch;
	}

	@Column(name = "OnlyUseArea", precision = 18)
	public Double getOnlyUseArea() {
		return this.onlyUseArea;
	}

	public void setOnlyUseArea(Double onlyUseArea) {
		this.onlyUseArea = onlyUseArea;
	}

	@Column(name = "CoUseArea", precision = 18)
	public Double getCoUseArea() {
		return this.coUseArea;
	}

	public void setCoUseArea(Double coUseArea) {
		this.coUseArea = coUseArea;
	}

	@Column(name = "SumCoverArea", precision = 18)
	public Double getSumCoverArea() {
		return this.sumCoverArea;
	}

	public void setSumCoverArea(Double sumCoverArea) {
		this.sumCoverArea = sumCoverArea;
	}

	@Column(name = "SchProCovArea", precision = 18)
	public Double getSchProCovArea() {
		return this.schProCovArea;
	}

	public void setSchProCovArea(Double schProCovArea) {
		this.schProCovArea = schProCovArea;
	}

	@Column(name = "NotSchProCovArea", precision = 18)
	public Double getNotSchProCovArea() {
		return this.notSchProCovArea;
	}

	public void setNotSchProCovArea(Double notSchProCovArea) {
		this.notSchProCovArea = notSchProCovArea;
	}

	@Column(name = "OnlyUseCovArea", precision = 18)
	public Double getOnlyUseCovArea() {
		return this.onlyUseCovArea;
	}

	public void setOnlyUseCovArea(Double onlyUseCovArea) {
		this.onlyUseCovArea = onlyUseCovArea;
	}

	@Column(name = "CoUseCovArea", precision = 18)
	public Double getCoUseCovArea() {
		return this.coUseCovArea;
	}

	public void setCoUseCovArea(Double coUseCovArea) {
		this.coUseCovArea = coUseCovArea;
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

	public void setCheckState(int checkState) {
		this.checkState = checkState;
	}

	public int getCheckState() {
		return checkState;
	}

}