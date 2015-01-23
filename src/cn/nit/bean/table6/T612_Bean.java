package cn.nit.bean.table6;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T612MasterNumInfoGra entity. @author Yuan
 */

@Table(name = "T612_MasterNumInfo_Gra$", schema = "dbo", catalog = "TDMS")
public class T612_Bean implements java.io.Serializable {

	// Fields
	private int seqNumber;
	private Integer masterLastYearNum;
	private Integer masterThisYearNum;
	private Integer fullTimeMasterLastYearNum;
	private Integer fullTimeMasterThisYearNum;
	private Integer partTimeMasterLastYearNum;
	private Integer partTimeMasterThisYearNum;
	private Integer doctorLastYearNum;
	private Integer doctorThisYearNum;
	private Integer fullTimeDoctorLastYearNum;
	private Integer fullTimeDoctorThisYearNum;
	private Integer partTimeDoctorLastYearNum;
	private Integer partTimeDoctorThisYearNum;
	private Date time;
	private String note;
	private int CheckState;
	
	
	public int getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}
	public Integer getMasterLastYearNum() {
		return masterLastYearNum;
	}
	public void setMasterLastYearNum(Integer masterLastYearNum) {
		this.masterLastYearNum = masterLastYearNum;
	}
	public Integer getMasterThisYearNum() {
		return masterThisYearNum;
	}
	public void setMasterThisYearNum(Integer masterThisYearNum) {
		this.masterThisYearNum = masterThisYearNum;
	}
	public Integer getFullTimeMasterLastYearNum() {
		return fullTimeMasterLastYearNum;
	}
	public void setFullTimeMasterLastYearNum(Integer fullTimeMasterLastYearNum) {
		this.fullTimeMasterLastYearNum = fullTimeMasterLastYearNum;
	}
	public Integer getFullTimeMasterThisYearNum() {
		return fullTimeMasterThisYearNum;
	}
	public void setFullTimeMasterThisYearNum(Integer fullTimeMasterThisYearNum) {
		this.fullTimeMasterThisYearNum = fullTimeMasterThisYearNum;
	}
	public Integer getPartTimeMasterLastYearNum() {
		return partTimeMasterLastYearNum;
	}
	public void setPartTimeMasterLastYearNum(Integer partTimeMasterLastYearNum) {
		this.partTimeMasterLastYearNum = partTimeMasterLastYearNum;
	}
	public Integer getPartTimeMasterThisYearNum() {
		return partTimeMasterThisYearNum;
	}
	public void setPartTimeMasterThisYearNum(Integer partTimeMasterThisYearNum) {
		this.partTimeMasterThisYearNum = partTimeMasterThisYearNum;
	}
	public Integer getDoctorLastYearNum() {
		return doctorLastYearNum;
	}
	public void setDoctorLastYearNum(Integer doctorLastYearNum) {
		this.doctorLastYearNum = doctorLastYearNum;
	}
	public Integer getDoctorThisYearNum() {
		return doctorThisYearNum;
	}
	public void setDoctorThisYearNum(Integer doctorThisYearNum) {
		this.doctorThisYearNum = doctorThisYearNum;
	}
	public Integer getFullTimeDoctorLastYearNum() {
		return fullTimeDoctorLastYearNum;
	}
	public void setFullTimeDoctorLastYearNum(Integer fullTimeDoctorLastYearNum) {
		this.fullTimeDoctorLastYearNum = fullTimeDoctorLastYearNum;
	}
	public Integer getFullTimeDoctorThisYearNum() {
		return fullTimeDoctorThisYearNum;
	}
	public void setFullTimeDoctorThisYearNum(Integer fullTimeDoctorThisYearNum) {
		this.fullTimeDoctorThisYearNum = fullTimeDoctorThisYearNum;
	}
	public Integer getPartTimeDoctorLastYearNum() {
		return partTimeDoctorLastYearNum;
	}
	public void setPartTimeDoctorLastYearNum(Integer partTimeDoctorLastYearNum) {
		this.partTimeDoctorLastYearNum = partTimeDoctorLastYearNum;
	}
	public Integer getPartTimeDoctorThisYearNum() {
		return partTimeDoctorThisYearNum;
	}
	public void setPartTimeDoctorThisYearNum(Integer partTimeDoctorThisYearNum) {
		this.partTimeDoctorThisYearNum = partTimeDoctorThisYearNum;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getCheckState() {
		return CheckState;
	}
	public void setCheckState(int checkState) {
		CheckState = checkState;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	

}