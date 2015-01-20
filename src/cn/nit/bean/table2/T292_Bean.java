package cn.nit.bean.table2;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T292UndergraTeaExpInfoFinance entity. @author MyEclipse Persistence Tools
 */
@Table(name = "T292_UndergraTeaExpInfo_Finance$", schema = "dbo", catalog = "TDMS")
public class T292_Bean implements
		java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private Double schTeaExpTotal;
	private Double undergraTeaExpTotal;
	private Double dayTeaExp;
	private Double teaReformExp;
	private Double courseExp;
	private Double majorExp;
	private Double textbookExp;
	private Double praTeaExpTotal;
	private Double expTeaExp;
	private Double praTeaExp;
	private Double outSchPraExp;
	private Double stuActExp;
	private Double teaTrainExp;
	private Double otherTeaExp;
	private Date time;
	private String note;
	private int checkState;

	@Id
	@Column(name = "SeqNumber", unique = true, nullable = false)
	public Integer getSeqNumber() {
		return this.seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	@Column(name = "SchTeaExpTotal", precision = 18)
	public Double getSchTeaExpTotal() {
		return this.schTeaExpTotal;
	}

	public void setSchTeaExpTotal(Double schTeaExpTotal) {
		this.schTeaExpTotal = schTeaExpTotal;
	}

	@Column(name = "UndergraTeaExpTotal", precision = 18)
	public Double getUndergraTeaExpTotal() {
		return this.undergraTeaExpTotal;
	}

	public void setUndergraTeaExpTotal(Double undergraTeaExpTotal) {
		this.undergraTeaExpTotal = undergraTeaExpTotal;
	}

	@Column(name = "DayTeaExp", precision = 18)
	public Double getDayTeaExp() {
		return this.dayTeaExp;
	}

	public void setDayTeaExp(Double dayTeaExp) {
		this.dayTeaExp = dayTeaExp;
	}

	@Column(name = "TeaReformExp", precision = 18)
	public Double getTeaReformExp() {
		return this.teaReformExp;
	}

	public void setTeaReformExp(Double teaReformExp) {
		this.teaReformExp = teaReformExp;
	}

	@Column(name = "CourseExp", precision = 18)
	public Double getCourseExp() {
		return this.courseExp;
	}

	public void setCourseExp(Double courseExp) {
		this.courseExp = courseExp;
	}

	@Column(name = "MajorExp", precision = 18)
	public Double getMajorExp() {
		return this.majorExp;
	}

	public void setMajorExp(Double majorExp) {
		this.majorExp = majorExp;
	}

	@Column(name = "TextbookExp", precision = 18)
	public Double getTextbookExp() {
		return this.textbookExp;
	}

	public void setTextbookExp(Double textbookExp) {
		this.textbookExp = textbookExp;
	}

	@Column(name = "PraTeaExpTotal", precision = 18)
	public Double getPraTeaExpTotal() {
		return this.praTeaExpTotal;
	}

	public void setPraTeaExpTotal(Double praTeaExpTotal) {
		this.praTeaExpTotal = praTeaExpTotal;
	}

	@Column(name = "ExpTeaExp", precision = 18)
	public Double getExpTeaExp() {
		return this.expTeaExp;
	}

	public void setExpTeaExp(Double expTeaExp) {
		this.expTeaExp = expTeaExp;
	}

	@Column(name = "PraTeaExp", precision = 18)
	public Double getPraTeaExp() {
		return this.praTeaExp;
	}

	public void setPraTeaExp(Double praTeaExp) {
		this.praTeaExp = praTeaExp;
	}

	@Column(name = "OutSchPraExp", precision = 18)
	public Double getOutSchPraExp() {
		return this.outSchPraExp;
	}

	public void setOutSchPraExp(Double outSchPraExp) {
		this.outSchPraExp = outSchPraExp;
	}

	@Column(name = "StuActExp", precision = 18)
	public Double getStuActExp() {
		return this.stuActExp;
	}

	public void setStuActExp(Double stuActExp) {
		this.stuActExp = stuActExp;
	}

	@Column(name = "TeaTrainExp", precision = 18)
	public Double getTeaTrainExp() {
		return this.teaTrainExp;
	}

	public void setTeaTrainExp(Double teaTrainExp) {
		this.teaTrainExp = teaTrainExp;
	}

	@Column(name = "OtherTeaExp", precision = 18)
	public Double getOtherTeaExp() {
		return this.otherTeaExp;
	}

	public void setOtherTeaExp(Double otherTeaExp) {
		this.otherTeaExp = otherTeaExp;
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