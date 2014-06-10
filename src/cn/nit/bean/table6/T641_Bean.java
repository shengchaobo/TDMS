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
	private String item;
	private Double aidFund;
	private Integer aidStuNum;
	private Date time;
	private String note;

	// Constructors

	/** default constructor */
	public T641_Bean() {
	}

	/** full constructor */
	public T641_Bean(String item,
			Double aidFund, Integer aidStuNum, Date time, String note) {
		this.item = item;
		this.aidFund = aidFund;
		this.aidStuNum = aidStuNum;
		this.time = time;
		this.note = note;
	}

	// Property accessors

	@Column(name = "Item", nullable = false)
	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	@Column(name = "AidFund", precision = 18)
	public Double getAidFund() {
		return this.aidFund;
	}

	public void setAidFund(Double aidFund) {
		this.aidFund = aidFund;
	}

	@Column(name = "AidStuNum")
	public Integer getAidStuNum() {
		return this.aidStuNum;
	}

	public void setAidStuNum(Integer aidStuNum) {
		this.aidStuNum = aidStuNum;
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