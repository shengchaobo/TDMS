package cn.nit.bean.table2;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T291TeaExpInfoFinance entity. @author MyEclipse Persistence Tools
 */
@Table(name = "T291_TeaExpInfo_Finance$", schema = "dbo", catalog = "TDMS")
public class T291_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private Double sumTeaExp;
	private Double teaExpBudget;
	private Double specialExp;
	private Date time;
	private String note;
	private int checkState;

	// Constructors

	/** default constructor */
	@Id
	@Column(name = "SeqNumber", unique = true, nullable = false)
	public Integer getSeqNumber() {
		return this.seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	@Column(name = "SumTeaExp", precision = 18)
	public Double getSumTeaExp() {
		return this.sumTeaExp;
	}

	public void setSumTeaExp(Double sumTeaExp) {
		this.sumTeaExp = sumTeaExp;
	}

	@Column(name = "TeaExpBudget", precision = 18)
	public Double getTeaExpBudget() {
		return this.teaExpBudget;
	}

	public void setTeaExpBudget(Double teaExpBudget) {
		this.teaExpBudget = teaExpBudget;
	}

	@Column(name = "SpecialExp", precision = 18)
	public Double getSpecialExp() {
		return this.specialExp;
	}

	public void setSpecialExp(Double specialExp) {
		this.specialExp = specialExp;
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

	public void setCheckState(int checkState) {
		this.checkState = checkState;
	}

	public int getCheckState() {
		return checkState;
	}

}