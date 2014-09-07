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
 * T42SchLeaderInfoPartyOffice entity. @author MyEclipse Persistence Tools
 */

@Table(name = "T42_SchLeaderInfo_PartyOffice$", schema = "dbo", catalog = "TDMS")
public class T42_Bean implements
		java.io.Serializable {

	// Fields
	private Integer seqNumber;
	private String name;
	private String teaId;
	private String duty;
	private String gender;
	private Date birthday;
	private Date joinSchTime;
	private String education;
	private String topDegree;
	private String majTechTitle;
	private String forCharge;
	private String resume;
	private Date time;
	private String note;
	
	
	@Column(name = "Name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Duty")
	public String getDuty() {
		return this.duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	@Column(name = "Gender")
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Birthday", length = 10)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "JoinSchTime", length = 10)
	public Date getJoinSchTime() {
		return this.joinSchTime;
	}

	public void setJoinSchTime(Date joinSchTime) {
		this.joinSchTime = joinSchTime;
	}

	@Column(name = "MajTechTitle")
	public String getMajTechTitle() {
		return this.majTechTitle;
	}

	public void setMajTechTitle(String majTechTitle) {
		this.majTechTitle = majTechTitle;
	}

	@Column(name = "ForCharge")
	public String getForCharge() {
		return this.forCharge;
	}

	public void setForCharge(String forCharge) {
		this.forCharge = forCharge;
	}

	@Column(name = "Resume")
	public String getResume() {
		return this.resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
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

	public String getTeaId() {
		return teaId;
	}

	public void setTeaId(String teaId) {
		this.teaId = teaId;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getTopDegree() {
		return topDegree;
	}

	public void setTopDegree(String topDegree) {
		this.topDegree = topDegree;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	public Integer getSeqNumber() {
		return seqNumber;
	}

}