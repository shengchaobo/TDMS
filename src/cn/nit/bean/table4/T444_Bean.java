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
 * T444HighLevelResTeamRes entity. @author MyEclipse Persistence Tools
 */
@Table(name = "T444_HighLevelResTeam_Res$", schema = "dbo", catalog = "TDMS")
public class T444_Bean implements java.io.Serializable {

	// Fields
	private int SeqNumber;
	private String resField;
	private String type;
	private Date gainTime;
	private String leader;
	private String teaId;
	private Integer otherTeamNum;
	private String otherTeamPer;
	private Date time;
	private String note;
	private int checkState;

	@Column(name = "ResField")
	public String getResField() {
		return this.resField;
	}

	public void setResField(String resField) {
		this.resField = resField;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "GainTime", length = 10)
	public Date getGainTime() {
		return this.gainTime;
	}

	public void setGainTime(Date gainTime) {
		this.gainTime = gainTime;
	}

	@Column(name = "Leader")
	public String getLeader() {
		return this.leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	@Column(name = "OtherTeamNum")
	public Integer getOtherTeamNum() {
		return this.otherTeamNum;
	}

	public void setOtherTeamNum(Integer otherTeamNum) {
		this.otherTeamNum = otherTeamNum;
	}

	@Column(name = "OtherTeamPer")
	public String getOtherTeamPer() {
		return this.otherTeamPer;
	}

	public void setOtherTeamPer(String otherTeamPer) {
		this.otherTeamPer = otherTeamPer;
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

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setTeaId(String teaId) {
		this.teaId = teaId;
	}

	public String getTeaId() {
		return teaId;
	}

	public void setSeqNumber(int seqNumber) {
		SeqNumber = seqNumber;
	}

	public int getSeqNumber() {
		return SeqNumber;
	}

	public void setCheckState(int checkState) {
		this.checkState = checkState;
	}

	public int getCheckState() {
		return checkState;
	}

}