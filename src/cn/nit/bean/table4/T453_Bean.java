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
 * T453TeaCommunInfoTeaInter entity. @author MyEclipse Persistence Tools
 */

@Table(name = "T453_TeaCommunInfo_TeaInter$", schema = "dbo", catalog = "TDMS")
public class T453_Bean implements
		java.io.Serializable {

	// Fields
	private int SeqNumber;
	private String teaUnitName;
	private String unitId;
	private String name;
	private String teaId;
	private String communType;
	private Date beginTime;
	private Date endTime;
	private String inOrOut;
	private String communUnit;
	private String communContent;
	private String time;
	private String note;

	@Column(name = "TeaUnitName")
	public String getTeaUnitName() {
		return this.teaUnitName;
	}

	public void setTeaUnitName(String teaUnitName) {
		this.teaUnitName = teaUnitName;
	}

	@Column(name = "Name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CommunType")
	public String getCommunType() {
		return this.communType;
	}

	public void setCommunType(String communType) {
		this.communType = communType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BeginTime", length = 10)
	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EndTime", length = 10)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "InOrOut")
	public String getInOrOut() {
		return this.inOrOut;
	}

	public void setInOrOut(String inOrOut) {
		this.inOrOut = inOrOut;
	}

	@Column(name = "CommunUnit")
	public String getCommunUnit() {
		return this.communUnit;
	}

	public void setCommunUnit(String communUnit) {
		this.communUnit = communUnit;
	}

	@Column(name = "CommunContent")
	public String getCommunContent() {
		return this.communContent;
	}

	public void setCommunContent(String communContent) {
		this.communContent = communContent;
	}

	@Column(name = "Time")
	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
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

	public void setSeqNumber(int seqNumber) {
		SeqNumber = seqNumber;
	}

	public int getSeqNumber() {
		return SeqNumber;
	}

}