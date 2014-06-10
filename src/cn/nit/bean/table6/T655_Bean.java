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

@Table(name = "T655_CET46AndJiangxiNCRE_Tea$", schema = "dbo", catalog = "TDMS")
public class T655_Bean implements java.io.Serializable {

	// Fields

	private String unitId;
	private String teaUnit;
	private Double cet4passRate;
	private Double cet6passRate;
	private Double jiangxiNcrepassRate;
	private Double nationNcrepassRate;
	private Date time;
	private String note;

	// Constructors

	/** default constructor */
	public T655_Bean() {
	}


	/** full constructor */
	public T655_Bean(String teaUnit, String unitId,  Double cet4passRate,
			Double cet6passRate, Double jiangxiNcrepassRate,
			Double nationNcrepassRate, Date time, String note) {

		this.unitId = unitId;
		this.teaUnit = teaUnit;
		this.cet4passRate = cet4passRate;
		this.cet6passRate = cet6passRate;
		this.jiangxiNcrepassRate = jiangxiNcrepassRate;
		this.nationNcrepassRate = nationNcrepassRate;
		this.time = time;
		this.note = note;
	}

	// Property accessors

	@JoinColumn(name = "UnitID")
	public String getUnitID() {
		return this.unitId;
	}

	public void setUnitID(String unitId) {
		this.unitId = unitId;
	}

	@Column(name = "TeaUnit")
	public String getTeaUnit() {
		return this.teaUnit;
	}

	public void setTeaUnit(String teaUnit) {
		this.teaUnit = teaUnit;
	}

	@Column(name = "CET4PassRate", precision = 18)
	public Double getCet4passRate() {
		return this.cet4passRate;
	}

	public void setCet4passRate(Double cet4passRate) {
		this.cet4passRate = cet4passRate;
	}

	@Column(name = "CET6PassRate", precision = 18)
	public Double getCet6passRate() {
		return this.cet6passRate;
	}

	public void setCet6passRate(Double cet6passRate) {
		this.cet6passRate = cet6passRate;
	}

	@Column(name = "JiangxiNCREPassRate", precision = 18)
	public Double getJiangxiNcrepassRate() {
		return this.jiangxiNcrepassRate;
	}

	public void setJiangxiNcrepassRate(Double jiangxiNcrepassRate) {
		this.jiangxiNcrepassRate = jiangxiNcrepassRate;
	}

	@Column(name = "NationNCREPassRate", precision = 18)
	public Double getNationNcrepassRate() {
		return this.nationNcrepassRate;
	}

	public void setNationNcrepassRate(Double nationNcrepassRate) {
		this.nationNcrepassRate = nationNcrepassRate;
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