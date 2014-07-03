package cn.nit.bean.table2;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T294SocialDonaInfoFinance entity. @author MyEclipse Persistence Tools
 */

@Table(name = "T294_SocialDonaInfo_Finance$", schema = "dbo", catalog = "TDMS")
public class T294_Bean implements
		java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private String donaName;
	private String type;
	private Double donaMoney;
	private Date time;
	private String note;

	// Property accessors
	@Id
	@Column(name = "SeqNumber", unique = true, nullable = false)
	public Integer getSeqNumber() {
		return this.seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	@Column(name = "DonaName", nullable = false)
	public String getDonaName() {
		return this.donaName;
	}

	public void setDonaName(String donaName) {
		this.donaName = donaName;
	}

	@Column(name = "Type")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "DonaMoney", precision = 18)
	public Double getDonaMoney() {
		return this.donaMoney;
	}

	public void setDonaMoney(Double donaMoney) {
		this.donaMoney = donaMoney;
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