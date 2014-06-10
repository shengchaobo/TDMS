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

	private Integer stuClubSum;
	private Integer stuClubSciNum;
	private Integer stuClubHumanNum;
	private Integer stuClubSportNum;
	private Integer stuClubArtNum;
	private Integer otherStuClub;
	private Integer joinStuSum;
	private Integer joinClubSciNum;
	private Integer stuClubHumanNum1;
	private Integer joinClubSportNum;
	private Integer joinClubArtNum;
	private Integer joinOtherClub;
	private Date time;
	private String note;

	// Constructors

	/** default constructor */
	public T66_Bean() {
	}


	/** full constructor */
	public T66_Bean(Integer stuClubSum,
			Integer stuClubSciNum, Integer stuClubHumanNum,
			Integer stuClubSportNum, Integer stuClubArtNum,
			Integer otherStuClub, Integer joinStuSum, Integer joinClubSciNum,
			Integer stuClubHumanNum1, Integer joinClubSportNum,
			Integer joinClubArtNum, Integer joinOtherClub, Date time,
			String note) {
		this.stuClubSum = stuClubSum;
		this.stuClubSciNum = stuClubSciNum;
		this.stuClubHumanNum = stuClubHumanNum;
		this.stuClubSportNum = stuClubSportNum;
		this.stuClubArtNum = stuClubArtNum;
		this.otherStuClub = otherStuClub;
		this.joinStuSum = joinStuSum;
		this.joinClubSciNum = joinClubSciNum;
		this.stuClubHumanNum1 = stuClubHumanNum1;
		this.joinClubSportNum = joinClubSportNum;
		this.joinClubArtNum = joinClubArtNum;
		this.joinOtherClub = joinOtherClub;
		this.time = time;
		this.note = note;
	}

	// Property accessors

	@Column(name = "StuClubSum")
	public Integer getStuClubSum() {
		return this.stuClubSum;
	}

	public void setStuClubSum(Integer stuClubSum) {
		this.stuClubSum = stuClubSum;
	}

	@Column(name = "StuClubSciNum")
	public Integer getStuClubSciNum() {
		return this.stuClubSciNum;
	}

	public void setStuClubSciNum(Integer stuClubSciNum) {
		this.stuClubSciNum = stuClubSciNum;
	}

	@Column(name = "StuClubHumanNum")
	public Integer getStuClubHumanNum() {
		return this.stuClubHumanNum;
	}

	public void setStuClubHumanNum(Integer stuClubHumanNum) {
		this.stuClubHumanNum = stuClubHumanNum;
	}

	@Column(name = "StuClubSportNum")
	public Integer getStuClubSportNum() {
		return this.stuClubSportNum;
	}

	public void setStuClubSportNum(Integer stuClubSportNum) {
		this.stuClubSportNum = stuClubSportNum;
	}

	@Column(name = "StuClubArtNum")
	public Integer getStuClubArtNum() {
		return this.stuClubArtNum;
	}

	public void setStuClubArtNum(Integer stuClubArtNum) {
		this.stuClubArtNum = stuClubArtNum;
	}

	@Column(name = "OtherStuClub")
	public Integer getOtherStuClub() {
		return this.otherStuClub;
	}

	public void setOtherStuClub(Integer otherStuClub) {
		this.otherStuClub = otherStuClub;
	}

	@Column(name = "JoinStuSum")
	public Integer getJoinStuSum() {
		return this.joinStuSum;
	}

	public void setJoinStuSum(Integer joinStuSum) {
		this.joinStuSum = joinStuSum;
	}

	@Column(name = "JoinClubSciNum")
	public Integer getJoinClubSciNum() {
		return this.joinClubSciNum;
	}

	public void setJoinClubSciNum(Integer joinClubSciNum) {
		this.joinClubSciNum = joinClubSciNum;
	}

	@Column(name = "StuClubHumanNum1")
	public Integer getStuClubHumanNum1() {
		return this.stuClubHumanNum1;
	}

	public void setStuClubHumanNum1(Integer stuClubHumanNum1) {
		this.stuClubHumanNum1 = stuClubHumanNum1;
	}

	@Column(name = "JoinClubSportNum")
	public Integer getJoinClubSportNum() {
		return this.joinClubSportNum;
	}

	public void setJoinClubSportNum(Integer joinClubSportNum) {
		this.joinClubSportNum = joinClubSportNum;
	}

	@Column(name = "JoinClubArtNum")
	public Integer getJoinClubArtNum() {
		return this.joinClubArtNum;
	}

	public void setJoinClubArtNum(Integer joinClubArtNum) {
		this.joinClubArtNum = joinClubArtNum;
	}

	@Column(name = "JoinOtherClub")
	public Integer getJoinOtherClub() {
		return this.joinOtherClub;
	}

	public void setJoinOtherClub(Integer joinOtherClub) {
		this.joinOtherClub = joinOtherClub;
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