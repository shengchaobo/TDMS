package cn.nit.bean.table4;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T411TeaServInfoTea entity. @author MyEclipse Persistence Tools
 */
@Table(name = "T411_TeaServInfo_Tea", schema = "dbo", catalog = "TDMS")
public class T4_11_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private String unitName;
	private String unitId;
	private Integer patentNum;
	private Integer achieNum;
	private Integer consNum;
	private Integer partJobNum;
	private Integer judgeNum;
	private String note;
	private String fillUnitID;
	private Date time;

	// Property accessors
	@Id
	@Column(name = "SeqNumber", unique = true, nullable = false)
	public Integer getSeqNumber() {
		return this.seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	@Column(name = "UnitName")
	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	@Column(name = "UnitID")
	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	@Column(name = "PatentNum")
	public Integer getPatentNum() {
		return this.patentNum;
	}

	public void setPatentNum(Integer patentNum) {
		this.patentNum = patentNum;
	}

	@Column(name = "AchieNum")
	public Integer getAchieNum() {
		return this.achieNum;
	}

	public void setAchieNum(Integer achieNum) {
		this.achieNum = achieNum;
	}

	@Column(name = "ConsNum")
	public Integer getConsNum() {
		return this.consNum;
	}

	public void setConsNum(Integer consNum) {
		this.consNum = consNum;
	}

	@Column(name = "PartJobNum")
	public Integer getPartJobNum() {
		return this.partJobNum;
	}

	public void setPartJobNum(Integer partJobNum) {
		this.partJobNum = partJobNum;
	}

	@Column(name = "JudgeNum")
	public Integer getJudgeNum() {
		return this.judgeNum;
	}

	public void setJudgeNum(Integer judgeNum) {
		this.judgeNum = judgeNum;
	}

	@Column(name = "Note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Time", nullable = false, length = 10)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setFillUnitID(String fillUnitID) {
		this.fillUnitID = fillUnitID;
	}

	public String getFillUnitID() {
		return fillUnitID;
	}

}