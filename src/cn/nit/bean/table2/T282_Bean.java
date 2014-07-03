package cn.nit.bean.table2;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T282FixedAssetEqu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T282_FixedAsset_EQU$", schema = "dbo", catalog = "TDMS")
public class T282_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private Double sumFixedAsset;
	private Double onlyEqu;
	private Double generalEqu;
	private Double furniture;
	private Double otherAsset;
	private Date time;
	private String note;

	// Constructors

	/** default constructor */
	public T282_Bean() {
	}

	/** minimal constructor */
	public T282_Bean(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	/** full constructor */
	public T282_Bean(Integer seqNumber, Double sumFixedAsset,
			Double onlyEqu, Double generalEqu, Double furniture,
			Double otherAsset, Date time, String note) {
		this.seqNumber = seqNumber;
		this.sumFixedAsset = sumFixedAsset;
		this.onlyEqu = onlyEqu;
		this.generalEqu = generalEqu;
		this.furniture = furniture;
		this.otherAsset = otherAsset;
		this.time = time;
		this.note = note;
	}

	// Property accessors
	@Id
	@Column(name = "SeqNumber", unique = true, nullable = false)
	public Integer getSeqNumber() {
		return this.seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	@Column(name = "SumFixedAsset", precision = 18)
	public Double getSumFixedAsset() {
		return this.sumFixedAsset;
	}

	public void setSumFixedAsset(Double sumFixedAsset) {
		this.sumFixedAsset = sumFixedAsset;
	}

	@Column(name = "OnlyEqu", precision = 18)
	public Double getOnlyEqu() {
		return this.onlyEqu;
	}

	public void setOnlyEqu(Double onlyEqu) {
		this.onlyEqu = onlyEqu;
	}

	@Column(name = "GeneralEqu", precision = 18)
	public Double getGeneralEqu() {
		return this.generalEqu;
	}

	public void setGeneralEqu(Double generalEqu) {
		this.generalEqu = generalEqu;
	}

	@Column(name = "Furniture", precision = 18)
	public Double getFurniture() {
		return this.furniture;
	}

	public void setFurniture(Double furniture) {
		this.furniture = furniture;
	}

	@Column(name = "OtherAsset", precision = 18)
	public Double getOtherAsset() {
		return this.otherAsset;
	}

	public void setOtherAsset(Double otherAsset) {
		this.otherAsset = otherAsset;
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