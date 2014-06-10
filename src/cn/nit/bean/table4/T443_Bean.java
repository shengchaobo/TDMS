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
 * T443HighLevelTalentPer entity. @author MyEclipse Persistence Tools
 */

@Table(name = "T443_HighLevelTalent_Per$", schema = "dbo", catalog = "TDMS")
public class T443_Bean implements java.io.Serializable {

	// Fields
	private String name;
	private String teaId;
	private String type;
	private String resField;
	private Date gainTime;
	private Date time;
	private String note;

	@Column(name = "Name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

}