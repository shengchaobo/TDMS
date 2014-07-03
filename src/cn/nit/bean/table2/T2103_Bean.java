package cn.nit.bean.table2;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T2103ProfTrainProf entity. @author MyEclipse Persistence Tools
 */
@Table(name = "T2103_ProfTrain_Prof$", schema = "dbo", catalog = "TDMS")
public class T2103_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private Integer stuProfTrainNum;
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

	@Column(name = "StuProfTrainNum")
	public Integer getStuProfTrainNum() {
		return this.stuProfTrainNum;
	}

	public void setStuProfTrainNum(Integer stuProfTrainNum) {
		this.stuProfTrainNum = stuProfTrainNum;
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