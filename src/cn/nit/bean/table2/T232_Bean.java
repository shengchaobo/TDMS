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
public class T232_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private Integer computerRoom;
	private Integer computerNum;
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

	public void setComputerRoom(Integer computerRoom) {
		this.computerRoom = computerRoom;
	}

	public Integer getComputerRoom() {
		return computerRoom;
	}

	public void setComputerNum(Integer computerNum) {
		this.computerNum = computerNum;
	}

	public Integer getComputerNum() {
		return computerNum;
	}

}