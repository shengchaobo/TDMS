package cn.nit.bean.table4;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * S444HighLevelResTeam entity. @author MyEclipse Persistence Tools
 */

@Table(name = "S444_HighLevelResTeam", schema = "dbo", catalog = "TDMS")
public class S444_Bean implements java.io.Serializable {

	// Fields
	private Integer sumTeam;
	private Integer teachTeam;
	private Integer nationReaTeam;
	private Integer proviResTeam;
	private Integer otherTeam;
	private Date time;
	private String note;

	@Column(name = "SumTeam")
	public Integer getSumTeam() {
		return this.sumTeam;
	}

	public void setSumTeam(Integer sumTeam) {
		this.sumTeam = sumTeam;
	}

	@Column(name = "TeachTeam")
	public Integer getTeachTeam() {
		return this.teachTeam;
	}

	public void setTeachTeam(Integer teachTeam) {
		this.teachTeam = teachTeam;
	}

	@Column(name = "NationReaTeam")
	public Integer getNationReaTeam() {
		return this.nationReaTeam;
	}

	public void setNationReaTeam(Integer nationReaTeam) {
		this.nationReaTeam = nationReaTeam;
	}

	@Column(name = "ProviResTeam")
	public Integer getProviResTeam() {
		return this.proviResTeam;
	}

	public void setProviResTeam(Integer proviResTeam) {
		this.proviResTeam = proviResTeam;
	}

	@Column(name = "OtherTeam")
	public Integer getOtherTeam() {
		return this.otherTeam;
	}

	public void setOtherTeam(Integer otherTeam) {
		this.otherTeam = otherTeam;
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

}