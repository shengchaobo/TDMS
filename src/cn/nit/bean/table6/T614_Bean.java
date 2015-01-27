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

	private int seqNumber;
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
	private int CheckState;
	
	public int getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}
	public Integer getPreppyLastYearNum() {
		return preppyLastYearNum;
	}
	public void setPreppyLastYearNum(Integer preppyLastYearNum) {
		this.preppyLastYearNum = preppyLastYearNum;
	}
	public Integer getPreppyThisYearNum() {
		return preppyThisYearNum;
	}
	public void setPreppyThisYearNum(Integer preppyThisYearNum) {
		this.preppyThisYearNum = preppyThisYearNum;
	}
	public Integer getAdvStuLastYearNum() {
		return advStuLastYearNum;
	}
	public void setAdvStuLastYearNum(Integer advStuLastYearNum) {
		this.advStuLastYearNum = advStuLastYearNum;
	}
	public Integer getAdvStuThisYearNum() {
		return advStuThisYearNum;
	}
	public void setAdvStuThisYearNum(Integer advStuThisYearNum) {
		this.advStuThisYearNum = advStuThisYearNum;
	}
	public Integer getAdultLastYearNum() {
		return adultLastYearNum;
	}
	public void setAdultLastYearNum(Integer adultLastYearNum) {
		this.adultLastYearNum = adultLastYearNum;
	}
	public Integer getAdultThisYearNum() {
		return adultThisYearNum;
	}
	public void setAdultThisYearNum(Integer adultThisYearNum) {
		this.adultThisYearNum = adultThisYearNum;
	}
	public Integer getNightUniLastYearNum() {
		return nightUniLastYearNum;
	}
	public void setNightUniLastYearNum(Integer nightUniLastYearNum) {
		this.nightUniLastYearNum = nightUniLastYearNum;
	}
	public Integer getNightUniThisYearNum() {
		return nightUniThisYearNum;
	}
	public void setNightUniThisYearNum(Integer nightUniThisYearNum) {
		this.nightUniThisYearNum = nightUniThisYearNum;
	}
	public Integer getCorrespdCoLastYearNum() {
		return correspdCoLastYearNum;
	}
	public void setCorrespdCoLastYearNum(Integer correspdCoLastYearNum) {
		this.correspdCoLastYearNum = correspdCoLastYearNum;
	}
	public Integer getCorrespdThisYearNum() {
		return correspdThisYearNum;
	}
	public void setCorrespdThisYearNum(Integer correspdThisYearNum) {
		this.correspdThisYearNum = correspdThisYearNum;
	}
	public Integer getNetStuLastYearNum() {
		return netStuLastYearNum;
	}
	public void setNetStuLastYearNum(Integer netStuLastYearNum) {
		this.netStuLastYearNum = netStuLastYearNum;
	}
	public Integer getNetStuThisYearNum() {
		return netStuThisYearNum;
	}
	public void setNetStuThisYearNum(Integer netStuThisYearNum) {
		this.netStuThisYearNum = netStuThisYearNum;
	}
	public Integer getSelfStudyLastYearNum() {
		return selfStudyLastYearNum;
	}
	public void setSelfStudyLastYearNum(Integer selfStudyLastYearNum) {
		this.selfStudyLastYearNum = selfStudyLastYearNum;
	}
	public int getCheckState() {
		return CheckState;
	}
	public void setCheckState(int checkState) {
		CheckState = checkState;
	}
	public Integer getSelfStudyThisYearNum() {
		return selfStudyThisYearNum;
	}
	public void setSelfStudyThisYearNum(Integer selfStudyThisYearNum) {
		this.selfStudyThisYearNum = selfStudyThisYearNum;
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