package cn.nit.bean.table4;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * S443HighLevelTalent entity. @author MyEclipse Persistence Tools
 */

@Table(name = "S443_HighLevelTalent$", schema = "dbo", catalog = "TDMS")
public class S443_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private String talentType;
	private Integer talentNum;
	private Date time;
	private String note;
	public Integer getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}
	public String getTalentType() {
		return talentType;
	}
	public void setTalentType(String talentType) {
		this.talentType = talentType;
	}
	public Integer getTalentNum() {
		return talentNum;
	}
	public void setTalentNum(Integer talentNum) {
		this.talentNum = talentNum;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
	



}