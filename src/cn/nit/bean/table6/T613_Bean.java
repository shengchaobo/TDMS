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
	private int seqNumber;
	private Integer coTrainStuLastYearNum;
	private Integer coTrainStuThisYearNum;
	private Integer foreignStuLastYearNum;
	private Integer foreignStuThisYearNum;
	private Date time;
	private String note;
	
	public int getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}
	public Integer getCoTrainStuLastYearNum() {
		return coTrainStuLastYearNum;
	}
	public void setCoTrainStuLastYearNum(Integer coTrainStuLastYearNum) {
		this.coTrainStuLastYearNum = coTrainStuLastYearNum;
	}
	public Integer getCoTrainStuThisYearNum() {
		return coTrainStuThisYearNum;
	}
	public void setCoTrainStuThisYearNum(Integer coTrainStuThisYearNum) {
		this.coTrainStuThisYearNum = coTrainStuThisYearNum;
	}
	public Integer getForeignStuLastYearNum() {
		return foreignStuLastYearNum;
	}
	public void setForeignStuLastYearNum(Integer foreignStuLastYearNum) {
		this.foreignStuLastYearNum = foreignStuLastYearNum;
	}
	public Integer getForeignStuThisYearNum() {
		return foreignStuThisYearNum;
	}
	public void setForeignStuThisYearNum(Integer foreignStuThisYearNum) {
		this.foreignStuThisYearNum = foreignStuThisYearNum;
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