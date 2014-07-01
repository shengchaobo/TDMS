package cn.nit.bean.table2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * T23ClassroomTea entity. @author MyEclipse Persistence Tools
 */
@Table(name = "T23_Classroom_Tea$", schema = "dbo", catalog = "TDMS")
public class T23_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private String classrmArea;
	private String classNum;
	private String computerNum;
	private String mediaNum;
	private String classSeatNum;
	private String computerSitNum;
	private String mediaSitNum;
	private Integer time;
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

	@Column(name = "ClassrmArea")
	public String getClassrmArea() {
		return this.classrmArea;
	}

	public void setClassrmArea(String classrmArea) {
		this.classrmArea = classrmArea;
	}

	@Column(name = "ClassNum")
	public String getClassNum() {
		return this.classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	@Column(name = "ComputerNum")
	public String getComputerNum() {
		return this.computerNum;
	}

	public void setComputerNum(String computerNum) {
		this.computerNum = computerNum;
	}

	@Column(name = "MediaNum")
	public String getMediaNum() {
		return this.mediaNum;
	}

	public void setMediaNum(String mediaNum) {
		this.mediaNum = mediaNum;
	}

	@Column(name = "ClassSeatNum")
	public String getClassSeatNum() {
		return this.classSeatNum;
	}

	public void setClassSeatNum(String classSeatNum) {
		this.classSeatNum = classSeatNum;
	}

	@Column(name = "ComputerSitNum")
	public String getComputerSitNum() {
		return this.computerSitNum;
	}

	public void setComputerSitNum(String computerSitNum) {
		this.computerSitNum = computerSitNum;
	}

	@Column(name = "MediaSitNum")
	public String getMediaSitNum() {
		return this.mediaSitNum;
	}

	public void setMediaSitNum(String mediaSitNum) {
		this.mediaSitNum = mediaSitNum;
	}

	@Column(name = "Time")
	public Integer getTime() {
		return this.time;
	}

	public void setTime(Integer time) {
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