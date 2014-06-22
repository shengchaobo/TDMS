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
	private int seqNumber;
	private String stuInfoBaseUrl;
	private Integer lastYearSumNum;
	private Integer thisYearSumNum;
	private Integer undergraLastYearNum;
	private Integer undergraThisYearNum;
	private Integer juniorLastYearNum;
	private Integer juniorThisYearNum;
	private Date time;
	private String note;
	
	public int getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}
	public String getStuInfoBaseUrl() {
		return stuInfoBaseUrl;
	}
	public void setStuInfoBaseUrl(String stuInfoBaseUrl) {
		this.stuInfoBaseUrl = stuInfoBaseUrl;
	}
	public Integer getLastYearSumNum() {
		return lastYearSumNum;
	}
	public void setLastYearSumNum(Integer lastYearSumNum) {
		this.lastYearSumNum = lastYearSumNum;
	}
	public Integer getThisYearSumNum() {
		return thisYearSumNum;
	}
	public void setThisYearSumNum(Integer thisYearSumNum) {
		this.thisYearSumNum = thisYearSumNum;
	}
	public Integer getUndergraLastYearNum() {
		return undergraLastYearNum;
	}
	public void setUndergraLastYearNum(Integer undergraLastYearNum) {
		this.undergraLastYearNum = undergraLastYearNum;
	}
	public Integer getUndergraThisYearNum() {
		return undergraThisYearNum;
	}
	public void setUndergraThisYearNum(Integer undergraThisYearNum) {
		this.undergraThisYearNum = undergraThisYearNum;
	}
	public Integer getJuniorLastYearNum() {
		return juniorLastYearNum;
	}
	public void setJuniorLastYearNum(Integer juniorLastYearNum) {
		this.juniorLastYearNum = juniorLastYearNum;
	}
	public Integer getJuniorThisYearNum() {
		return juniorThisYearNum;
	}
	public void setJuniorThisYearNum(Integer juniorThisYearNum) {
		this.juniorThisYearNum = juniorThisYearNum;
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