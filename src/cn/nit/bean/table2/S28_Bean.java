package cn.nit.bean.table2;

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


public class S28_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
    private Double FixedAsset ;
    private Double PlantAsset;
    private Double NewAddAsset;
	private Date time;
	private String note;

	// Constructors
	// Property accessors
	@Id
	@Column(name = "SeqNumber", unique = true, nullable = false)
	public Integer getSeqNumber() {
		return this.seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	public Double getFixedAsset() {
		return FixedAsset;
	}

	public void setFixedAsset(Double fixedAsset) {
		FixedAsset = fixedAsset;
	}

	public Double getPlantAsset() {
		return PlantAsset;
	}

	public void setPlantAsset(Double plantAsset) {
		PlantAsset = plantAsset;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}


}