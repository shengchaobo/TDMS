package cn.nit.bean.table4;

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
 * T48TeachTeamTeaTea entity. @author MyEclipse Persistence Tools
 */

@Table(name = "T48_TeachTeam_TeaTea$", schema = "dbo", catalog = "TDMS")
public class T48_Bean implements java.io.Serializable {

	// Fields
	private String SeqNumber;
	private String teaUnit;
	private String unitId;
	private String teamName;
	private String teamLevel;
	private String leader;
	private String teaId;
	private Integer groupNum;
	private String groupInfo;
	private Date gainTime;
	private String appvlId;
	private Date time;
	private String note;
	
	@Column(name = "TeaUnit")
	public String getTeaUnit() {
		return this.teaUnit;
	}

	public void setTeaUnit(String teaUnit) {
		this.teaUnit = teaUnit;
	}

	@Column(name = "TeamName")
	public String getTeamName() {
		return this.teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Column(name = "Leader")
	public String getLeader() {
		return this.leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	@Column(name = "GroupNum")
	public Integer getGroupNum() {
		return this.groupNum;
	}

	public void setGroupNum(Integer groupNum) {
		this.groupNum = groupNum;
	}

	@Column(name = "GroupInfo")
	public String getGroupInfo() {
		return this.groupInfo;
	}

	public void setGroupInfo(String groupInfo) {
		this.groupInfo = groupInfo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "GainTime", length = 10)
	public Date getGainTime() {
		return this.gainTime;
	}

	public void setGainTime(Date gainTime) {
		this.gainTime = gainTime;
	}

	@Column(name = "AppvlID")
	public String getAppvlId() {
		return this.appvlId;
	}

	public void setAppvlId(String appvlId) {
		this.appvlId = appvlId;
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

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setTeaId(String teaId) {
		this.teaId = teaId;
	}

	public String getTeaId() {
		return teaId;
	}

	public void setTeamLevel(String teamLevel) {
		this.teamLevel = teamLevel;
	}

	public String getTeamLevel() {
		return teamLevel;
	}

	public void setSeqNumber(String seqNumber) {
		SeqNumber = seqNumber;
	}

	public String getSeqNumber() {
		return SeqNumber;
	}

}