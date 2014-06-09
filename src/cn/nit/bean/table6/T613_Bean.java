package cn.nit.bean.table6;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T613ForeignStuNumInfoIntra entity. @author Yuan
 */

@Table(name = "T613_ForeignStuNumInfo_Intra$", schema = "dbo", catalog = "TDMS")
public class T613_Bean implements java.io.Serializable {

	// Fields

	private Integer coTrainStuLastYearNum;
	private Integer coTrainStuThisYearNum;
	private Integer foreignStuLastYearNum;
	private Integer foreignStuThisYearNum;
	private Date time;
	private String note;

	// Constructors

	/** default constructor */
	public T613_Bean() {
	}


	/** full constructor */
	public T613_Bean(Integer coTrainStuLastYearNum, Integer coTrainStuThisYearNum,
			Integer foreignStuLastYearNum, Integer foreignStuThisYearNum,
			Date time, String note) {

		this.coTrainStuLastYearNum = coTrainStuLastYearNum;
		this.coTrainStuThisYearNum = coTrainStuThisYearNum;
		this.foreignStuLastYearNum = foreignStuLastYearNum;
		this.foreignStuThisYearNum = foreignStuThisYearNum;
		this.time = time;
		this.note = note;
	}

	// Property accessors

	@Column(name = "CoTrainStuLastYearNum")
	public Integer getCoTrainStuLastYearNum() {
		return this.coTrainStuLastYearNum;
	}

	public void setCoTrainStuLastYearNum(Integer coTrainStuLastYearNum) {
		this.coTrainStuLastYearNum = coTrainStuLastYearNum;
	}

	@Column(name = "CoTrainStuThisYearNum")
	public Integer getCoTrainStuThisYearNum() {
		return this.coTrainStuThisYearNum;
	}

	public void setCoTrainStuThisYearNum(Integer coTrainStuThisYearNum) {
		this.coTrainStuThisYearNum = coTrainStuThisYearNum;
	}

	@Column(name = "ForeignStuLastYearNum")
	public Integer getForeignStuLastYearNum() {
		return this.foreignStuLastYearNum;
	}

	public void setForeignStuLastYearNum(Integer foreignStuLastYearNum) {
		this.foreignStuLastYearNum = foreignStuLastYearNum;
	}

	@Column(name = "ForeignStuThisYearNum")
	public Integer getForeignStuThisYearNum() {
		return this.foreignStuThisYearNum;
	}

	public void setForeignStuThisYearNum(Integer foreignStuThisYearNum) {
		this.foreignStuThisYearNum = foreignStuThisYearNum;
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