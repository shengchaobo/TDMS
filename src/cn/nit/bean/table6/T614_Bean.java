package cn.nit.bean.table6;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T614ContinueEduStuNumInfoTaef entity. @author Yuan
 */

@Table(name = "T614_ContinueEduStuNumInfo_TAEF$", schema = "dbo", catalog = "TDMS")
public class T614_Bean implements java.io.Serializable {

	// Fields


	private Integer preppyLastYearNum;
	private Integer preppyThisYearNum;
	private Integer advStuLastYearNum;
	private Integer advStuThisYearNum;
	private Integer adultLastYearNum;
	private Integer adultThisYearNum;
	private Integer nightUniLastYearNum;
	private Integer nightUniThisYearNum;
	private Integer correspdCoLastYearNum;
	private Integer correspdThisYearNum;
	private Integer netStuLastYearNum;
	private Integer netStuThisYearNum;
	private Integer selfStudyLastYearNum;
	private Integer selfStudyThisYearNum;
	private Date time;
	private String note;

	// Constructors

	/** default constructor */
	public T614_Bean() {
	}


	/** full constructor */
	public T614_Bean(
			Integer preppyLastYearNum, Integer preppyThisYearNum,
			Integer advStuLastYearNum, Integer advStuThisYearNum,
			Integer adultLastYearNum, Integer adultThisYearNum,
			Integer nightUniLastYearNum, Integer nightUniThisYearNum,
			Integer correspdCoLastYearNum, Integer correspdThisYearNum,
			Integer netStuLastYearNum, Integer netStuThisYearNum,
			Integer selfStudyLastYearNum, Integer selfStudyThisYearNum,
			Date time, String note) {

		this.preppyLastYearNum = preppyLastYearNum;
		this.preppyThisYearNum = preppyThisYearNum;
		this.advStuLastYearNum = advStuLastYearNum;
		this.advStuThisYearNum = advStuThisYearNum;
		this.adultLastYearNum = adultLastYearNum;
		this.adultThisYearNum = adultThisYearNum;
		this.nightUniLastYearNum = nightUniLastYearNum;
		this.nightUniThisYearNum = nightUniThisYearNum;
		this.correspdCoLastYearNum = correspdCoLastYearNum;
		this.correspdThisYearNum = correspdThisYearNum;
		this.netStuLastYearNum = netStuLastYearNum;
		this.netStuThisYearNum = netStuThisYearNum;
		this.selfStudyLastYearNum = selfStudyLastYearNum;
		this.selfStudyThisYearNum = selfStudyThisYearNum;
		this.time = time;
		this.note = note;
	}

	// Property accessors

	@Column(name = "PreppyLastYearNum")
	public Integer getPreppyLastYearNum() {
		return this.preppyLastYearNum;
	}

	public void setPreppyLastYearNum(Integer preppyLastYearNum) {
		this.preppyLastYearNum = preppyLastYearNum;
	}

	@Column(name = "PreppyThisYearNum")
	public Integer getPreppyThisYearNum() {
		return this.preppyThisYearNum;
	}

	public void setPreppyThisYearNum(Integer preppyThisYearNum) {
		this.preppyThisYearNum = preppyThisYearNum;
	}

	@Column(name = "AdvStuLastYearNum")
	public Integer getAdvStuLastYearNum() {
		return this.advStuLastYearNum;
	}

	public void setAdvStuLastYearNum(Integer advStuLastYearNum) {
		this.advStuLastYearNum = advStuLastYearNum;
	}

	@Column(name = "AdvStuThisYearNum")
	public Integer getAdvStuThisYearNum() {
		return this.advStuThisYearNum;
	}

	public void setAdvStuThisYearNum(Integer advStuThisYearNum) {
		this.advStuThisYearNum = advStuThisYearNum;
	}

	@Column(name = "AdultLastYearNum")
	public Integer getAdultLastYearNum() {
		return this.adultLastYearNum;
	}

	public void setAdultLastYearNum(Integer adultLastYearNum) {
		this.adultLastYearNum = adultLastYearNum;
	}

	@Column(name = "AdultThisYearNum")
	public Integer getAdultThisYearNum() {
		return this.adultThisYearNum;
	}

	public void setAdultThisYearNum(Integer adultThisYearNum) {
		this.adultThisYearNum = adultThisYearNum;
	}

	@Column(name = "NightUniLastYearNum")
	public Integer getNightUniLastYearNum() {
		return this.nightUniLastYearNum;
	}

	public void setNightUniLastYearNum(Integer nightUniLastYearNum) {
		this.nightUniLastYearNum = nightUniLastYearNum;
	}

	@Column(name = "NightUniThisYearNum")
	public Integer getNightUniThisYearNum() {
		return this.nightUniThisYearNum;
	}

	public void setNightUniThisYearNum(Integer nightUniThisYearNum) {
		this.nightUniThisYearNum = nightUniThisYearNum;
	}

	@Column(name = "CorrespdCoLastYearNum")
	public Integer getCorrespdCoLastYearNum() {
		return this.correspdCoLastYearNum;
	}

	public void setCorrespdCoLastYearNum(Integer correspdCoLastYearNum) {
		this.correspdCoLastYearNum = correspdCoLastYearNum;
	}

	@Column(name = "CorrespdThisYearNum")
	public Integer getCorrespdThisYearNum() {
		return this.correspdThisYearNum;
	}

	public void setCorrespdThisYearNum(Integer correspdThisYearNum) {
		this.correspdThisYearNum = correspdThisYearNum;
	}

	@Column(name = "NetStuLastYearNum")
	public Integer getNetStuLastYearNum() {
		return this.netStuLastYearNum;
	}

	public void setNetStuLastYearNum(Integer netStuLastYearNum) {
		this.netStuLastYearNum = netStuLastYearNum;
	}

	@Column(name = "NetStuThisYearNum")
	public Integer getNetStuThisYearNum() {
		return this.netStuThisYearNum;
	}

	public void setNetStuThisYearNum(Integer netStuThisYearNum) {
		this.netStuThisYearNum = netStuThisYearNum;
	}

	@Column(name = "SelfStudyLastYearNum")
	public Integer getSelfStudyLastYearNum() {
		return this.selfStudyLastYearNum;
	}

	public void setSelfStudyLastYearNum(Integer selfStudyLastYearNum) {
		this.selfStudyLastYearNum = selfStudyLastYearNum;
	}

	@Column(name = "SelfStudyThisYearNum")
	public Integer getSelfStudyThisYearNum() {
		return this.selfStudyThisYearNum;
	}

	public void setSelfStudyThisYearNum(Integer selfStudyThisYearNum) {
		this.selfStudyThisYearNum = selfStudyThisYearNum;
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