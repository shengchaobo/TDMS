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
	private int seqNumber;
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
	
	public int getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public Integer getLibEnrollNum() {
		return libEnrollNum;
	}
	public void setLibEnrollNum(Integer libEnrollNum) {
		this.libEnrollNum = libEnrollNum;
	}
	public Integer getSciEnrollNum() {
		return sciEnrollNum;
	}
	public void setSciEnrollNum(Integer sciEnrollNum) {
		this.sciEnrollNum = sciEnrollNum;
	}
	public Integer getLibLowestScore() {
		return libLowestScore;
	}
	public void setLibLowestScore(Integer libLowestScore) {
		this.libLowestScore = libLowestScore;
	}
	public Integer getSciLowestScore() {
		return sciLowestScore;
	}
	public void setSciLowestScore(Integer sciLowestScore) {
		this.sciLowestScore = sciLowestScore;
	}
	public Integer getLibAvgScore() {
		return libAvgScore;
	}
	public void setLibAvgScore(Integer libAvgScore) {
		this.libAvgScore = libAvgScore;
	}
	public Integer getSciAvgScore() {
		return sciAvgScore;
	}
	public void setSciAvgScore(Integer sciAvgScore) {
		this.sciAvgScore = sciAvgScore;
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