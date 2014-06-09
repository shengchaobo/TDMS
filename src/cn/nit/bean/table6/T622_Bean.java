package cn.nit.bean.table6;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T622LibAndSciAdmiNumAdmission entity. @author Yuan
 */
@Table(name = "T622_LibAndSciAdmiNum_Admission$", schema = "dbo", catalog = "TDMS")
public class T622_Bean implements java.io.Serializable {

	// Fields

	private String province;
	private String batch;
	private Integer libEnrollNum;
	private Integer sciEnrollNum;
	private Integer libLowestScore;
	private Integer sciLowestScore;
	private Integer libAvgScore;
	private Integer sciAvgScore;
	private Date time;
	private String note;

	// Constructors

	/** default constructor */
	public T622_Bean() {
	}

	/** full constructor */
	public T622_Bean(String province,
			String batch, Integer libEnrollNum, Integer sciEnrollNum,
			Integer libLowestScore, Integer sciLowestScore,
			Integer libAvgScore, Integer sciAvgScore, Date time, String note) {
		this.province = province;
		this.batch = batch;
		this.libEnrollNum = libEnrollNum;
		this.sciEnrollNum = sciEnrollNum;
		this.libLowestScore = libLowestScore;
		this.sciLowestScore = sciLowestScore;
		this.libAvgScore = libAvgScore;
		this.sciAvgScore = sciAvgScore;
		this.time = time;
		this.note = note;
	}

	// Property accessors

	@Column(name = "Province")
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "Batch")
	public String getBatch() {
		return this.batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	@Column(name = "LibEnrollNum")
	public Integer getLibEnrollNum() {
		return this.libEnrollNum;
	}

	public void setLibEnrollNum(Integer libEnrollNum) {
		this.libEnrollNum = libEnrollNum;
	}

	@Column(name = "SciEnrollNum")
	public Integer getSciEnrollNum() {
		return this.sciEnrollNum;
	}

	public void setSciEnrollNum(Integer sciEnrollNum) {
		this.sciEnrollNum = sciEnrollNum;
	}

	@Column(name = "LibLowestScore")
	public Integer getLibLowestScore() {
		return this.libLowestScore;
	}

	public void setLibLowestScore(Integer libLowestScore) {
		this.libLowestScore = libLowestScore;
	}

	@Column(name = "SciLowestScore")
	public Integer getSciLowestScore() {
		return this.sciLowestScore;
	}

	public void setSciLowestScore(Integer sciLowestScore) {
		this.sciLowestScore = sciLowestScore;
	}

	@Column(name = "LibAvgScore")
	public Integer getLibAvgScore() {
		return this.libAvgScore;
	}

	public void setLibAvgScore(Integer libAvgScore) {
		this.libAvgScore = libAvgScore;
	}

	@Column(name = "SciAvgScore")
	public Integer getSciAvgScore() {
		return this.sciAvgScore;
	}

	public void setSciAvgScore(Integer sciAvgScore) {
		this.sciAvgScore = sciAvgScore;
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