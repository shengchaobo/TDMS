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
	private int seqNumber;
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
	public String getArtType() {
		return artType;
	}
	public void setArtType(String artType) {
		this.artType = artType;
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
	public Integer getSumEnrollNum() {
		return sumEnrollNum;
	}
	public void setSumEnrollNum(Integer sumEnrollNum) {
		this.sumEnrollNum = sumEnrollNum;
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
	public Integer getSumLowestScore() {
		return sumLowestScore;
	}
	public void setSumLowestScore(Integer sumLowestScore) {
		this.sumLowestScore = sumLowestScore;
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
	public Integer getSumAvgScore() {
		return sumAvgScore;
	}
	public void setSumAvgScore(Integer sumAvgScore) {
		this.sumAvgScore = sumAvgScore;
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