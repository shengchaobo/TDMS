package cn.nit.bean.table6;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T655Cet46andJiangxiNcreTea entity. @author Yuan
 */

@Table(name = "T655_CET46NCRE_Tea$", schema = "dbo", catalog = "TDMS")
public class T655_Bean implements java.io.Serializable {

	// Fields
	private int seqNumber;
	private String unitId;
	private String teaUnit;
	private double CET4PassRate;
	private double CET6PassRate;
	private double jiangxiNCREPassRate;
	private Date time;
	private String note;
	
	public int getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getTeaUnit() {
		return teaUnit;
	}
	public void setTeaUnit(String teaUnit) {
		this.teaUnit = teaUnit;
	}
	public double getCET4PassRate() {
		return CET4PassRate;
	}
	public void setCET4PassRate(double cET4PassRate) {
		CET4PassRate = cET4PassRate;
	}
	public void setCET6PassRate(double cET6PassRate) {
		CET6PassRate = cET6PassRate;
	}
	public void setJiangxiNCREPassRate(double jiangxiNCREPassRate) {
		this.jiangxiNCREPassRate = jiangxiNCREPassRate;
	}
	
	public Double getCET6PassRate() {
		return CET6PassRate;
	}
	public void setCET6PassRate(Double cET6PassRate) {
		CET6PassRate = cET6PassRate;
	}
	public Double getJiangxiNCREPassRate() {
		return jiangxiNCREPassRate;
	}
	public void setJiangxiNCREPassRate(Double jiangxiNCREPassRate) {
		this.jiangxiNCREPassRate = jiangxiNCREPassRate;
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