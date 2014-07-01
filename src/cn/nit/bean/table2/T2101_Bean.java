package cn.nit.bean.table2;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T2101QuaEduInfoYlc entity. @author MyEclipse Persistence Tools
 */
@Table(name = "T2101_QuaEduInfo_YLC$", schema = "dbo", catalog = "TDMS")
public class T2101_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private Integer quaEduItemNum;
	private Integer quaEduBaseNum;
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

	@Column(name = "QuaEduItemNum")
	public Integer getQuaEduItemNum() {
		return this.quaEduItemNum;
	}

	public void setQuaEduItemNum(Integer quaEduItemNum) {
		this.quaEduItemNum = quaEduItemNum;
	}

	@Column(name = "QuaEduBaseNum")
	public Integer getQuaEduBaseNum() {
		return this.quaEduBaseNum;
	}

	public void setQuaEduBaseNum(Integer quaEduBaseNum) {
		this.quaEduBaseNum = quaEduBaseNum;
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