package cn.nit.bean.table2;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T284FixedAssetFile entity. @author MyEclipse Persistence Tools
 */
public class T285_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
    private String teaUnit;
    private String unitID;
    private Integer sumEquNum;
    private Integer aboveTenEquNum;
    private Double sumEquAsset;
    private Double newAddAsset;
    private Double aboveTenEquAsset;
	private Date time;
	private String note;
	private int checkState;
	
	// Property accessors
	@Id
	@Column(name = "SeqNumber", unique = true, nullable = false)
	public Integer getSeqNumber() {
		return this.seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setTeaUnit(String teaUnit) {
		this.teaUnit = teaUnit;
	}

	public String getTeaUnit() {
		return teaUnit;
	}

	public void setUnitID(String unitID) {
		this.unitID = unitID;
	}

	public String getUnitID() {
		return unitID;
	}

	public void setSumEquNum(Integer sumEquNum) {
		this.sumEquNum = sumEquNum;
	}

	public Integer getSumEquNum() {
		return sumEquNum;
	}

	public void setAboveTenEquNum(Integer aboveTenEquNum) {
		this.aboveTenEquNum = aboveTenEquNum;
	}

	public Integer getAboveTenEquNum() {
		return aboveTenEquNum;
	}

	public void setSumEquAsset(Double sumEquAsset) {
		this.sumEquAsset = sumEquAsset;
	}

	public Double getSumEquAsset() {
		return sumEquAsset;
	}

	public void setNewAddAsset(Double newAddAsset) {
		this.newAddAsset = newAddAsset;
	}

	public Double getNewAddAsset() {
		return newAddAsset;
	}

	public void setAboveTenEquAsset(Double aboveTenEquAsset) {
		this.aboveTenEquAsset = aboveTenEquAsset;
	}

	public Double getAboveTenEquAsset() {
		return aboveTenEquAsset;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNote() {
		return note;
	}

	public void setCheckState(int checkState) {
		this.checkState = checkState;
	}

	public int getCheckState() {
		return checkState;
	}

}