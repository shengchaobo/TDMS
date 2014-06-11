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

	private String unitId;
	private String teaUnit;
	private Integer exchangeStuSum;
	private Integer fromSchToOverseas;
	private Integer fromSchToDomestic;
	private Integer fromDomesticToSch;
	private Integer fromOverseasToSch;
	private Date time;
	private String note;

	// Constructors

	/** default constructor */
	public T659_Bean() {
	}


	/** full constructor */
	public T659_Bean(String teaUnit, String unitId, Integer exchangeStuSum,
			Integer fromSchToOverseas, Integer fromSchToDomestic,
			Integer fromDomesticToSch, Integer fromOverseasToSch, Date time,
			String note) {
	
		this.unitId = unitId;
		this.teaUnit = teaUnit;
		this.exchangeStuSum = exchangeStuSum;
		this.fromSchToOverseas = fromSchToOverseas;
		this.fromSchToDomestic = fromSchToDomestic;
		this.fromDomesticToSch = fromDomesticToSch;
		this.fromOverseasToSch = fromOverseasToSch;
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

	@Column(name = "ExchangeStuSum")
	public Integer getExchangeStuSum() {
		return this.exchangeStuSum;
	}

	public void setExchangeStuSum(Integer exchangeStuSum) {
		this.exchangeStuSum = exchangeStuSum;
	}

	@Column(name = "FromSchToOverseas")
	public Integer getFromSchToOverseas() {
		return this.fromSchToOverseas;
	}

	public void setFromSchToOverseas(Integer fromSchToOverseas) {
		this.fromSchToOverseas = fromSchToOverseas;
	}

	@Column(name = "FromSchToDomestic")
	public Integer getFromSchToDomestic() {
		return this.fromSchToDomestic;
	}

	public void setFromSchToDomestic(Integer fromSchToDomestic) {
		this.fromSchToDomestic = fromSchToDomestic;
	}

	@Column(name = "FromDomesticToSch")
	public Integer getFromDomesticToSch() {
		return this.fromDomesticToSch;
	}

	public void setFromDomesticToSch(Integer fromDomesticToSch) {
		this.fromDomesticToSch = fromDomesticToSch;
	}

	@Column(name = "FromOverseasToSch")
	public Integer getFromOverseasToSch() {
		return this.fromOverseasToSch;
	}

	public void setFromOverseasToSch(Integer fromOverseasToSch) {
		this.fromOverseasToSch = fromOverseasToSch;
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