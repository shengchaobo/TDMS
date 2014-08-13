package cn.nit.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */
@Table(name = "Users", schema = "dbo", catalog = "TDMS")
public class UsersBean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private String teaID;
	private String teaName;
	private String teaPasswd;
	private String fromOffice;
	private String teaEmail;
	private String userNote;
	private String unitID;


	// Property accessors
	@Id
	@Column(name = "SeqNumber", unique = true, nullable = false)
	public Integer getSeqNumber() {
		return this.seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}
	@Column(name = "TeaName", nullable = false)
	public String getTeaName() {
		return this.teaName;
	}

	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}

	@Column(name = "TeaPasswd")
	public String getTeaPasswd() {
		return this.teaPasswd;
	}

	public void setTeaPasswd(String teaPasswd) {
		this.teaPasswd = teaPasswd;
	}

	@Column(name = "FromOffice", nullable = false)
	public String getFromOffice() {
		return this.fromOffice;
	}

	public void setFromOffice(String fromOffice) {
		this.fromOffice = fromOffice;
	}

	@Column(name = "TeaEmail", nullable = false)
	public String getTeaEmail() {
		return this.teaEmail;
	}

	public void setTeaEmail(String teaEmail) {
		this.teaEmail = teaEmail;
	}

	@Column(name = "UserNote")
	public String getUserNote() {
		return this.userNote;
	}

	public void setUserNote(String userNote) {
		this.userNote = userNote;
	}

	public void setUnitID(String unitID) {
		this.unitID = unitID;
	}

	public String getUnitID() {
		return unitID;
	}

	public void setTeaID(String teaID) {
		this.teaID = teaID;
	}

	public String getTeaID() {
		return teaID;
	}

}