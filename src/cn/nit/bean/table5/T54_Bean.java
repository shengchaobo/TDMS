package cn.nit.bean.table5;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * T54OutClassActAndLetureYlc entity. @author MyEclipse Persistence Tools
 */
@Table(name = "T54_OutClassActAndLeture_YLC$", schema = "dbo", catalog = "TDMS")
public class T54_Bean implements
		java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private Integer lectureSumNum;
	private Integer schLecture;
	private Integer collegeLecture;
	private Integer actItemSumNum;
	private Integer nationActItem;
	private Integer proviActItem;
	private Integer schActItem;
	private Date time;
	private String note;

	@Id
	@Column(name = "SeqNumber", unique = true, nullable = false)
	public Integer getSeqNumber() {
		return this.seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	@Column(name = "LectureSumNum")
	public Integer getLectureSumNum() {
		return this.lectureSumNum;
	}

	public void setLectureSumNum(Integer lectureSumNum) {
		this.lectureSumNum = lectureSumNum;
	}

	@Column(name = "SchLecture")
	public Integer getSchLecture() {
		return this.schLecture;
	}

	public void setSchLecture(Integer schLecture) {
		this.schLecture = schLecture;
	}

	@Column(name = "CollegeLecture")
	public Integer getCollegeLecture() {
		return this.collegeLecture;
	}

	public void setCollegeLecture(Integer collegeLecture) {
		this.collegeLecture = collegeLecture;
	}

	@Column(name = "ActItemSumNum")
	public Integer getActItemSumNum() {
		return this.actItemSumNum;
	}

	public void setActItemSumNum(Integer actItemSumNum) {
		this.actItemSumNum = actItemSumNum;
	}

	@Column(name = "NationActItem")
	public Integer getNationActItem() {
		return this.nationActItem;
	}

	public void setNationActItem(Integer nationActItem) {
		this.nationActItem = nationActItem;
	}

	@Column(name = "ProviActItem")
	public Integer getProviActItem() {
		return this.proviActItem;
	}

	public void setProviActItem(Integer proviActItem) {
		this.proviActItem = proviActItem;
	}

	@Column(name = "SchActItem")
	public Integer getSchActItem() {
		return this.schActItem;
	}

	public void setSchActItem(Integer schActItem) {
		this.schActItem = schActItem;
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

}