package cn.nit.bean.table2;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T283FixedAssetLib entity. @author MyEclipse Persistence Tools
 */
@Table(name = "T283_FixedAsset_Lib$", schema = "dbo", catalog = "TDMS")
public class T283_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private Double sumFixedAsset;
	private Double displayAsset;
	private Double bookAsset;
	private Double otherAsset;
	private Date time;
	private String note;

	// Constructors

	/** default constructor */
	
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

	@Column(name = "DisplayAsset", precision = 18)
	public Double getDisplayAsset() {
		return this.displayAsset;
	}

	public void setDisplayAsset(Double displayAsset) {
		this.displayAsset = displayAsset;
	}

	@Column(name = "BookAsset", precision = 18)
	public Double getBookAsset() {
		return this.bookAsset;
	}

	public void setBookAsset(Double bookAsset) {
		this.bookAsset = bookAsset;
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