package cn.nit.bean.table6;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T641ScholarLoanSubsidyStu entity. @author Yuan
 */

@Table(name = "T641_ScholarLoanSubsidy_Stu$", schema = "dbo", catalog = "TDMS")
public class T641_Bean implements java.io.Serializable {

	// Fields
	private int seqNumber;
	private String item;
	private Double aidFund;
	private Integer aidStuNum;
	private Date time;
	private String note;
	
	public int getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Double getAidFund() {
		return aidFund;
	}
	public void setAidFund(Double aidFund) {
		this.aidFund = aidFund;
	}
	public Integer getAidStuNum() {
		return aidStuNum;
	}
	public void setAidStuNum(Integer aidStuNum) {
		this.aidStuNum = aidStuNum;
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