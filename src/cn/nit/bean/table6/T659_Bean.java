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
 * T659StuExchangeInfoTeaInter entity. @author Yuan
 */

@Table(name = "T659_StuExchangeInfo_TeaInter$", schema = "dbo", catalog = "TDMS")
public class T659_Bean implements java.io.Serializable {

	// Fields
	private int seqNumber;
	private String unitId;
	private String teaUnit;
	private Integer exchangeStuSum;
	private Integer fromSchToOverseas;
	private Integer fromSchToDomestic;
	private Integer fromDomesticToSch;
	private Integer fromOverseasToSch;
	private Date time;
	private String note;
	private String FillUnitID;
	private int CheckState;
	
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
	public Integer getExchangeStuSum() {
		return exchangeStuSum;
	}
	public void setExchangeStuSum(Integer exchangeStuSum) {
		this.exchangeStuSum = exchangeStuSum;
	}
	public Integer getFromSchToOverseas() {
		return fromSchToOverseas;
	}
	public void setFromSchToOverseas(Integer fromSchToOverseas) {
		this.fromSchToOverseas = fromSchToOverseas;
	}
	public Integer getFromSchToDomestic() {
		return fromSchToDomestic;
	}
	public void setFromSchToDomestic(Integer fromSchToDomestic) {
		this.fromSchToDomestic = fromSchToDomestic;
	}
	public Integer getFromDomesticToSch() {
		return fromDomesticToSch;
	}
	public void setFromDomesticToSch(Integer fromDomesticToSch) {
		this.fromDomesticToSch = fromDomesticToSch;
	}
	public Integer getFromOverseasToSch() {
		return fromOverseasToSch;
	}
	public void setFromOverseasToSch(Integer fromOverseasToSch) {
		this.fromOverseasToSch = fromOverseasToSch;
	}
	public Date getTime() {
		return time;
	}
	public int getCheckState() {
		return CheckState;
	}
	public void setCheckState(int checkState) {
		CheckState = checkState;
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
	public String getFillUnitID() {
		return FillUnitID;
	}
	public void setFillUnitID(String fillUnitID) {
		FillUnitID = fillUnitID;
	}


	

}