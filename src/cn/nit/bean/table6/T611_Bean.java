package cn.nit.bean.table6;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T611UnderGraStuNuminfoTea entity. @author Yuan
 */
@Table(name = "T611_UnderGraStuNuminfo_Tea$", schema = "dbo", catalog = "TDMS")
public class T611_Bean implements java.io.Serializable {

	// Fields

	private String stuInfoBaseUrl;
	private Integer lastYearSumNum;
	private Integer thisYearSumNum;
	private Integer undergraLastYearNum;
	private Integer undergraThisYearNum;
	private Integer juniorLastYearNum;
	private Integer juniorThisYearNum;
	private Date time;
	private String note;

	// Constructors

	/** default constructor */
	public T611_Bean() {
	}


	/** full constructor */
	public T611_Bean(String stuInfoBaseUrl,
			Integer lastYearSumNum, Integer thisYearSumNum,
			Integer undergraLastYearNum, Integer undergraThisYearNum,
			Integer juniorLastYearNum, Integer juniorThisYearNum, Date time,
			String note) {
		this.stuInfoBaseUrl = stuInfoBaseUrl;
		this.lastYearSumNum = lastYearSumNum;
		this.thisYearSumNum = thisYearSumNum;
		this.undergraLastYearNum = undergraLastYearNum;
		this.undergraThisYearNum = undergraThisYearNum;
		this.juniorLastYearNum = juniorLastYearNum;
		this.juniorThisYearNum = juniorThisYearNum;
		this.time = time;
		this.note = note;
	}

	// Property accessors

	@Column(name = "StuInfoBaseUrl")
	public String getStuInfoBaseUrl() {
		return this.stuInfoBaseUrl;
	}

	public void setStuInfoBaseUrl(String stuInfoBaseUrl) {
		this.stuInfoBaseUrl = stuInfoBaseUrl;
	}

	@Column(name = "LastYearSumNum")
	public Integer getLastYearSumNum() {
		return this.lastYearSumNum;
	}

	public void setLastYearSumNum(Integer lastYearSumNum) {
		this.lastYearSumNum = lastYearSumNum;
	}

	@Column(name = "ThisYearSumNum")
	public Integer getThisYearSumNum() {
		return this.thisYearSumNum;
	}

	public void setThisYearSumNum(Integer thisYearSumNum) {
		this.thisYearSumNum = thisYearSumNum;
	}

	@Column(name = "UndergraLastYearNum")
	public Integer getUndergraLastYearNum() {
		return this.undergraLastYearNum;
	}

	public void setUndergraLastYearNum(Integer undergraLastYearNum) {
		this.undergraLastYearNum = undergraLastYearNum;
	}

	@Column(name = "UndergraThisYearNum")
	public Integer getUndergraThisYearNum() {
		return this.undergraThisYearNum;
	}

	public void setUndergraThisYearNum(Integer undergraThisYearNum) {
		this.undergraThisYearNum = undergraThisYearNum;
	}

	@Column(name = "JuniorLastYearNum")
	public Integer getJuniorLastYearNum() {
		return this.juniorLastYearNum;
	}

	public void setJuniorLastYearNum(Integer juniorLastYearNum) {
		this.juniorLastYearNum = juniorLastYearNum;
	}

	@Column(name = "JuniorThisYearNum")
	public Integer getJuniorThisYearNum() {
		return this.juniorThisYearNum;
	}

	public void setJuniorThisYearNum(Integer juniorThisYearNum) {
		this.juniorThisYearNum = juniorThisYearNum;
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