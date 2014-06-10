package cn.nit.bean.table6;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T623ArtStuAmdiNumAdmission entity. @author Yuan
 */

@Table(name = "T623_ArtStuAmdiNum_Admission$", schema = "dbo", catalog = "TDMS")
public class T623_Bean implements java.io.Serializable {

	// Fields
	private String province;
	private String artType;
	private String batch;
	private Integer libEnrollNum;
	private Integer sciEnrollNum;
	private Integer sumEnrollNum;
	private Integer libLowestScore;
	private Integer sciLowestScore;
	private Integer sumLowestScore;
	private Integer libAvgScore;
	private Integer sciAvgScore;
	private Integer sumAvgScore;
	private Date time;
	private String note;

	// Constructors

	/** default constructor */
	public T623_Bean() {
	}

	/** full constructor */
	public T623_Bean(String province,
			String artType, String batch, Integer libEnrollNum,
			Integer sciEnrollNum, Integer sumEnrollNum, Integer libLowestScore,
			Integer sciLowestScore, Integer sumLowestScore,
			Integer libAvgScore, Integer sciAvgScore, Integer sumAvgScore,
			Date time, String note) {
		this.province = province;
		this.artType = artType;
		this.batch = batch;
		this.libEnrollNum = libEnrollNum;
		this.sciEnrollNum = sciEnrollNum;
		this.sumEnrollNum = sumEnrollNum;
		this.libLowestScore = libLowestScore;
		this.sciLowestScore = sciLowestScore;
		this.sumLowestScore = sumLowestScore;
		this.libAvgScore = libAvgScore;
		this.sciAvgScore = sciAvgScore;
		this.sumAvgScore = sumAvgScore;
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

	@Column(name = "ArtType")
	public String getArtType() {
		return this.artType;
	}

	public void setArtType(String artType) {
		this.artType = artType;
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

	@Column(name = "SumEnrollNum")
	public Integer getSumEnrollNum() {
		return this.sumEnrollNum;
	}

	public void setSumEnrollNum(Integer sumEnrollNum) {
		this.sumEnrollNum = sumEnrollNum;
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

	@Column(name = "SumLowestScore")
	public Integer getSumLowestScore() {
		return this.sumLowestScore;
	}

	public void setSumLowestScore(Integer sumLowestScore) {
		this.sumLowestScore = sumLowestScore;
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

	@Column(name = "SumAvgScore")
	public Integer getSumAvgScore() {
		return this.sumAvgScore;
	}

	public void setSumAvgScore(Integer sumAvgScore) {
		this.sumAvgScore = sumAvgScore;
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