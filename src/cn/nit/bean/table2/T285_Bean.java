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
    private String TeaUnit;
    private String UnitID;
    private Integer SumEquNum;
    private Integer AboveTenEquNum;
    private Double SumEquAsset;
    private Double NewAddAsset;
    private Double AboveTenEquAsset;
	private Date time;
	private String note;
	
	// Property accessors
	@Id
	@Column(name = "SeqNumber", unique = true, nullable = false)
	public Integer getSeqNumber() {
		return this.seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	@Column(name = "Note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getTeaUnit() {
		return TeaUnit;
	}

	public void setTeaUnit(String teaUnit) {
		TeaUnit = teaUnit;
	}

	public String getUnitID() {
		return UnitID;
	}

	public void setUnitID(String unitID) {
		UnitID = unitID;
	}

	public Integer getSumEquNum() {
		return SumEquNum;
	}

	public void setSumEquNum(Integer sumEquNum) {
		SumEquNum = sumEquNum;
	}

	public Integer getAboveTenEquNum() {
		return AboveTenEquNum;
	}

	public void setAboveTenEquNum(Integer aboveTenEquNum) {
		AboveTenEquNum = aboveTenEquNum;
	}

	public Double getSumEquAsset() {
		return SumEquAsset;
	}

	public void setSumEquAsset(Double sumEquAsset) {
		SumEquAsset = sumEquAsset;
	}

	public Double getAboveTenEquAsset() {
		return AboveTenEquAsset;
	}

	public void setAboveTenEquAsset(Double aboveTenEquAsset) {
		AboveTenEquAsset = aboveTenEquAsset;
	}

	public Double getNewAddAsset() {
		return NewAddAsset;
	}

	public void setNewAddAsset(Double newAddAsset) {
		NewAddAsset = newAddAsset;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}