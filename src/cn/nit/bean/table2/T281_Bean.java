package cn.nit.bean.table2;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T281FixedAssetLog entity. @author MyEclipse Persistence Tools
 */
@Table(name = "T281_FixedAsset_Log$", schema = "dbo", catalog = "TDMS")
public class T281_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private Double sumFixedAsset;
	private Double archAsset;
	private Double floraFaunaAsset;
	private Double otherAsset;
	private Date time;
	private String note;
	private int CheckState;

	// Constructors

	/** default constructor */
	public T281_Bean() {
	}

	/** minimal constructor */
	public T281_Bean(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	/** full constructor */
	public T281_Bean(Integer seqNumber, Double sumFixedAsset,
			Double archAsset, Double floraFaunaAsset,
			Double otherAsset, Date time, String note) {
		this.seqNumber = seqNumber;
		this.sumFixedAsset = sumFixedAsset;
		this.archAsset = archAsset;
		this.floraFaunaAsset = floraFaunaAsset;
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

	@Column(name = "ArchAsset", precision = 18)
	public Double getArchAsset() {
		return this.archAsset;
	}

	public void setArchAsset(Double archAsset) {
		this.archAsset = archAsset;
	}

	@Column(name = "FloraFaunaAsset", precision = 18)
	public Double getFloraFaunaAsset() {
		return this.floraFaunaAsset;
	}

	public void setFloraFaunaAsset(Double floraFaunaAsset) {
		this.floraFaunaAsset = floraFaunaAsset;
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