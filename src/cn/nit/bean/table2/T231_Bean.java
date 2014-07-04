package cn.nit.bean.table2;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * T23ClassroomTea entity. @author MyEclipse Persistence Tools
 */
@Table(name = "T23_Classroom_Tea$", schema = "dbo", catalog = "TDMS")
public class T231_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private Double classrmArea;
	private Integer classNum;
	private Integer computerNum;
	private Integer mediaNum;
	private Integer classSeatNum;
	private Integer computerSitNum;
	private Integer mediaSitNum;
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



	public Integer getClassNum() {
		return classNum;
	}

	public void setClassNum(Integer classNum) {
		this.classNum = classNum;
	}

	public Integer getComputerNum() {
		return computerNum;
	}

	public void setComputerNum(Integer computerNum) {
		this.computerNum = computerNum;
	}

	public Integer getMediaNum() {
		return mediaNum;
	}

	public void setMediaNum(Integer mediaNum) {
		this.mediaNum = mediaNum;
	}

	public Integer getClassSeatNum() {
		return classSeatNum;
	}

	public void setClassSeatNum(Integer classSeatNum) {
		this.classSeatNum = classSeatNum;
	}

	public Integer getComputerSitNum() {
		return computerSitNum;
	}

	public void setComputerSitNum(Integer computerSitNum) {
		this.computerSitNum = computerSitNum;
	}

	public Integer getMediaSitNum() {
		return mediaSitNum;
	}

	public void setMediaSitNum(Integer mediaSitNum) {
		this.mediaSitNum = mediaSitNum;
	}

	@Column(name = "Note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return time;
	}

	public void setClassrmArea(Double classrmArea) {
		this.classrmArea = classrmArea;
	}

	public Double getClassrmArea() {
		return classrmArea;
	}

}