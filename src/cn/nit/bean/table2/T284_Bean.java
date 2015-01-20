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
@Table(name = "T284_FixedAsset_File$", schema = "dbo", catalog = "TDMS")
public class T284_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private Double sumFixedAsset;
	private Double fileAsset;
	private Double otherAsset;
	private Date time;
	private String note;
	private int CheckState;
	
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

	@Column(name = "FileAsset", precision = 18)
	public Double getFileAsset() {
		return this.fileAsset;
	}

	public void setFileAsset(Double fileAsset) {
		this.fileAsset = fileAsset;
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

	public void setCheckState(int checkState) {
		CheckState = checkState;
	}

	public int getCheckState() {
		return CheckState;
	}

}