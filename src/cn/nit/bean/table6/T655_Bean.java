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
	private Double cet4passRate;
	private Double cet6passRate;
	private Double jiangxiNcrepassRate;
	private Double nationNcrepassRate;
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
	public Double getCet4passRate() {
		return cet4passRate;
	}
	public void setCet4passRate(Double cet4passRate) {
		this.cet4passRate = cet4passRate;
	}
	public Double getCet6passRate() {
		return cet6passRate;
	}
	public void setCet6passRate(Double cet6passRate) {
		this.cet6passRate = cet6passRate;
	}
	public Double getJiangxiNcrepassRate() {
		return jiangxiNcrepassRate;
	}
	public void setJiangxiNcrepassRate(Double jiangxiNcrepassRate) {
		this.jiangxiNcrepassRate = jiangxiNcrepassRate;
	}
	public Double getNationNcrepassRate() {
		return nationNcrepassRate;
	}
	public void setNationNcrepassRate(Double nationNcrepassRate) {
		this.nationNcrepassRate = nationNcrepassRate;
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