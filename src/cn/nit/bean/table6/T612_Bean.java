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

	// Constructors

	/** default constructor */
	public T612_Bean() {
	}

	/** full constructor */
	public T612_Bean(Integer masterLastYearNum,
			Integer masterThisYearNum, Integer fullTimeMasterLastYearNum,
			Integer fullTimeMasterThisYearNum,
			Integer partTimeMasterLastYearNum,
			Integer partTimeMasterThisYearNum, Integer doctorLastYearNum,
			Integer doctorThisYearNum, Integer fullTimeDoctorLastYearNum,
			Integer fullTimeDoctorThisYearNum,
			Integer partTimeDoctorLastYearNum,
			Integer partTimeDoctorThisYearNum, Date time, String note) {

		this.masterLastYearNum = masterLastYearNum;
		this.masterThisYearNum = masterThisYearNum;
		this.fullTimeMasterLastYearNum = fullTimeMasterLastYearNum;
		this.fullTimeMasterThisYearNum = fullTimeMasterThisYearNum;
		this.partTimeMasterLastYearNum = partTimeMasterLastYearNum;
		this.partTimeMasterThisYearNum = partTimeMasterThisYearNum;
		this.doctorLastYearNum = doctorLastYearNum;
		this.doctorThisYearNum = doctorThisYearNum;
		this.fullTimeDoctorLastYearNum = fullTimeDoctorLastYearNum;
		this.fullTimeDoctorThisYearNum = fullTimeDoctorThisYearNum;
		this.partTimeDoctorLastYearNum = partTimeDoctorLastYearNum;
		this.partTimeDoctorThisYearNum = partTimeDoctorThisYearNum;
		this.time = time;
		this.note = note;
	}

	// Property accessors

	@Column(name = "MasterLastYearNum")
	public Integer getMasterLastYearNum() {
		return this.masterLastYearNum;
	}

	public void setMasterLastYearNum(Integer masterLastYearNum) {
		this.masterLastYearNum = masterLastYearNum;
	}

	@Column(name = "MasterThisYearNum")
	public Integer getMasterThisYearNum() {
		return this.masterThisYearNum;
	}

	public void setMasterThisYearNum(Integer masterThisYearNum) {
		this.masterThisYearNum = masterThisYearNum;
	}

	@Column(name = "FullTimeMasterLastYearNum")
	public Integer getFullTimeMasterLastYearNum() {
		return this.fullTimeMasterLastYearNum;
	}

	public void setFullTimeMasterLastYearNum(Integer fullTimeMasterLastYearNum) {
		this.fullTimeMasterLastYearNum = fullTimeMasterLastYearNum;
	}

	@Column(name = "FullTimeMasterThisYearNum")
	public Integer getFullTimeMasterThisYearNum() {
		return this.fullTimeMasterThisYearNum;
	}

	public void setFullTimeMasterThisYearNum(Integer fullTimeMasterThisYearNum) {
		this.fullTimeMasterThisYearNum = fullTimeMasterThisYearNum;
	}

	@Column(name = "PartTimeMasterLastYearNum")
	public Integer getPartTimeMasterLastYearNum() {
		return this.partTimeMasterLastYearNum;
	}

	public void setPartTimeMasterLastYearNum(Integer partTimeMasterLastYearNum) {
		this.partTimeMasterLastYearNum = partTimeMasterLastYearNum;
	}

	@Column(name = "PartTimeMasterThisYearNum")
	public Integer getPartTimeMasterThisYearNum() {
		return this.partTimeMasterThisYearNum;
	}

	public void setPartTimeMasterThisYearNum(Integer partTimeMasterThisYearNum) {
		this.partTimeMasterThisYearNum = partTimeMasterThisYearNum;
	}

	@Column(name = "DoctorLastYearNum")
	public Integer getDoctorLastYearNum() {
		return this.doctorLastYearNum;
	}

	public void setDoctorLastYearNum(Integer doctorLastYearNum) {
		this.doctorLastYearNum = doctorLastYearNum;
	}

	@Column(name = "DoctorThisYearNum")
	public Integer getDoctorThisYearNum() {
		return this.doctorThisYearNum;
	}

	public void setDoctorThisYearNum(Integer doctorThisYearNum) {
		this.doctorThisYearNum = doctorThisYearNum;
	}

	@Column(name = "FullTimeDoctorLastYearNum")
	public Integer getFullTimeDoctorLastYearNum() {
		return this.fullTimeDoctorLastYearNum;
	}

	public void setFullTimeDoctorLastYearNum(Integer fullTimeDoctorLastYearNum) {
		this.fullTimeDoctorLastYearNum = fullTimeDoctorLastYearNum;
	}

	@Column(name = "FullTimeDoctorThisYearNum")
	public Integer getFullTimeDoctorThisYearNum() {
		return this.fullTimeDoctorThisYearNum;
	}

	public void setFullTimeDoctorThisYearNum(Integer fullTimeDoctorThisYearNum) {
		this.fullTimeDoctorThisYearNum = fullTimeDoctorThisYearNum;
	}

	@Column(name = "PartTimeDoctorLastYearNum")
	public Integer getPartTimeDoctorLastYearNum() {
		return this.partTimeDoctorLastYearNum;
	}

	public void setPartTimeDoctorLastYearNum(Integer partTimeDoctorLastYearNum) {
		this.partTimeDoctorLastYearNum = partTimeDoctorLastYearNum;
	}

	@Column(name = "PartTimeDoctorThisYearNum")
	public Integer getPartTimeDoctorThisYearNum() {
		return this.partTimeDoctorThisYearNum;
	}

	public void setPartTimeDoctorThisYearNum(Integer partTimeDoctorThisYearNum) {
		this.partTimeDoctorThisYearNum = partTimeDoctorThisYearNum;
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