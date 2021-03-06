package cn.nit.bean.table4;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * T451TeaTeachDepOrgPer entity. @author MyEclipse Persistence Tools
 */
@Table(name = "T451_TeaTeachDepOrg_Per$", schema = "dbo", catalog = "TDMS")
public class T451_Bean implements java.io.Serializable {

	// Fields
	private int SeqNumber;
	private String orgName;
	private String unitId;
	private String orgType;
	private Integer trainTimes;
	private Integer trainPerTimes;
	private Date time;
	private String note;
	private int CheckState;

	@Column(name = "OrgName")
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "UnitID")
	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	@Column(name = "OrgType")
	public String getOrgType() {
		return this.orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	@Column(name = "TrainTimes")
	public Integer getTrainTimes() {
		return this.trainTimes;
	}

	public void setTrainTimes(Integer trainTimes) {
		this.trainTimes = trainTimes;
	}

	@Column(name = "TrainPerTimes")
	public Integer getTrainPerTimes() {
		return this.trainPerTimes;
	}

	public void setTrainPerTimes(Integer trainPerTimes) {
		this.trainPerTimes = trainPerTimes;
	}

	@Column(name = "Note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setSeqNumber(int seqNumber) {
		SeqNumber = seqNumber;
	}

	public int getSeqNumber() {
		return SeqNumber;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}

	public void setCheckState(int checkState) {
		CheckState = checkState;
	}

	public int getCheckState() {
		return CheckState;
	}

}