package cn.nit.bean.table6;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T66StuClubYlc entity. @author Yuan
 */

@Table(name = "T66_StuClub_YLC$", schema = "dbo", catalog = "TDMS")
public class T66_Bean implements java.io.Serializable {

	// Fields
	private int seqNumber;
	private Integer stuClubSum;
	private Integer stuClubSciNum;
	private Integer stuClubHumanNum;
	private Integer stuClubSportNum;
	private Integer stuClubArtNum;
	private Integer otherStuClub;
	private Integer joinStuSum;
	private Integer joinClubSciNum;
	private Integer joinClubHumanNum;
	private Integer joinClubSportNum;
	private Integer joinClubArtNum;
	private Integer joinOtherClub;
	private Date time;
	private String note;
	private int CheckState;
	
	
	public int getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}
	public Integer getStuClubSum() {
		return stuClubSum;
	}
	public void setStuClubSum(Integer stuClubSum) {
		this.stuClubSum = stuClubSum;
	}
	public Integer getStuClubSciNum() {
		return stuClubSciNum;
	}
	public void setStuClubSciNum(Integer stuClubSciNum) {
		this.stuClubSciNum = stuClubSciNum;
	}
	public Integer getStuClubHumanNum() {
		return stuClubHumanNum;
	}
	public void setStuClubHumanNum(Integer stuClubHumanNum) {
		this.stuClubHumanNum = stuClubHumanNum;
	}
	public Integer getStuClubSportNum() {
		return stuClubSportNum;
	}
	public void setStuClubSportNum(Integer stuClubSportNum) {
		this.stuClubSportNum = stuClubSportNum;
	}
	public Integer getStuClubArtNum() {
		return stuClubArtNum;
	}
	public void setStuClubArtNum(Integer stuClubArtNum) {
		this.stuClubArtNum = stuClubArtNum;
	}
	public Integer getOtherStuClub() {
		return otherStuClub;
	}
	public void setOtherStuClub(Integer otherStuClub) {
		this.otherStuClub = otherStuClub;
	}
	public Integer getJoinStuSum() {
		return joinStuSum;
	}
	public void setJoinStuSum(Integer joinStuSum) {
		this.joinStuSum = joinStuSum;
	}
	public Integer getJoinClubSciNum() {
		return joinClubSciNum;
	}
	public void setJoinClubSciNum(Integer joinClubSciNum) {
		this.joinClubSciNum = joinClubSciNum;
	}
	public Integer getJoinClubSportNum() {
		return joinClubSportNum;
	}
	public void setJoinClubSportNum(Integer joinClubSportNum) {
		this.joinClubSportNum = joinClubSportNum;
	}
	public Integer getJoinClubArtNum() {
		return joinClubArtNum;
	}
	public void setJoinClubArtNum(Integer joinClubArtNum) {
		this.joinClubArtNum = joinClubArtNum;
	}
	public Integer getJoinOtherClub() {
		return joinOtherClub;
	}
	public void setJoinOtherClub(Integer joinOtherClub) {
		this.joinOtherClub = joinOtherClub;
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
	public int getCheckState() {
		return CheckState;
	}
	public void setCheckState(int checkState) {
		CheckState = checkState;
	}
	public void setJoinClubHumanNum(Integer joinClubHumanNum) {
		this.joinClubHumanNum = joinClubHumanNum;
	}
	public Integer getJoinClubHumanNum() {
		return joinClubHumanNum;
	}

	
}