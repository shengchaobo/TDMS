package cn.nit.bean.table6;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T641ScholarLoanSubsidyStu entity. @author MyEclipse Persistence Tools
 */
@Table(name = "T641_ScholarLoanSubsidy_Stu$", schema = "dbo", catalog = "TDMS")
public class T641_Bean implements
		java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private Double sumAidFund;
	private Integer sumAidNum;
	private Double govAidFund;
	private Integer govAidNum;
	private Double socialAidFund;
	private Integer socialAidNum;
	private Double schAidFund;
	private Integer schAidNum;
	private Double nationAidFund;
	private Integer nationAidNum;
	private Double workStudyFund;
	private Integer workStudyNum;
	private Double tuitionWaiberFund;
	private Integer tuitionWaiberNum;
	private Double tempFund;
	private Integer tempNum;
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

	@Column(name = "SumAidFund", precision = 18)
	public Double getSumAidFund() {
		return this.sumAidFund;
	}

	public void setSumAidFund(Double sumAidFund) {
		this.sumAidFund = sumAidFund;
	}

	@Column(name = "SumAidNum")
	public Integer getSumAidNum() {
		return this.sumAidNum;
	}

	public void setSumAidNum(Integer sumAidNum) {
		this.sumAidNum = sumAidNum;
	}

	@Column(name = "GovAidFund", precision = 18)
	public Double getGovAidFund() {
		return this.govAidFund;
	}

	public void setGovAidFund(Double govAidFund) {
		this.govAidFund = govAidFund;
	}

	@Column(name = "GovAidNum")
	public Integer getGovAidNum() {
		return this.govAidNum;
	}

	public void setGovAidNum(Integer govAidNum) {
		this.govAidNum = govAidNum;
	}

	@Column(name = "SocialAidFund", precision = 18)
	public Double getSocialAidFund() {
		return this.socialAidFund;
	}

	public void setSocialAidFund(Double socialAidFund) {
		this.socialAidFund = socialAidFund;
	}

	@Column(name = "SocialAidNum")
	public Integer getSocialAidNum() {
		return this.socialAidNum;
	}

	public void setSocialAidNum(Integer socialAidNum) {
		this.socialAidNum = socialAidNum;
	}

	@Column(name = "SchAidFund", precision = 18)
	public Double getSchAidFund() {
		return this.schAidFund;
	}

	public void setSchAidFund(Double schAidFund) {
		this.schAidFund = schAidFund;
	}

	@Column(name = "SchAidNum")
	public Integer getSchAidNum() {
		return this.schAidNum;
	}

	public void setSchAidNum(Integer schAidNum) {
		this.schAidNum = schAidNum;
	}

	@Column(name = "NationAidFund", precision = 18)
	public Double getNationAidFund() {
		return this.nationAidFund;
	}

	public void setNationAidFund(Double nationAidFund) {
		this.nationAidFund = nationAidFund;
	}

	@Column(name = "NationAidNum")
	public Integer getNationAidNum() {
		return this.nationAidNum;
	}

	public void setNationAidNum(Integer nationAidNum) {
		this.nationAidNum = nationAidNum;
	}

	@Column(name = "WorkStudyFund", precision = 18)
	public Double getWorkStudyFund() {
		return this.workStudyFund;
	}

	public void setWorkStudyFund(Double workStudyFund) {
		this.workStudyFund = workStudyFund;
	}

	@Column(name = "WorkStudyNum")
	public Integer getWorkStudyNum() {
		return this.workStudyNum;
	}

	public void setWorkStudyNum(Integer workStudyNum) {
		this.workStudyNum = workStudyNum;
	}

	@Column(name = "TuitionWaiberFund", precision = 18)
	public Double getTuitionWaiberFund() {
		return this.tuitionWaiberFund;
	}

	public void setTuitionWaiberFund(Double tuitionWaiberFund) {
		this.tuitionWaiberFund = tuitionWaiberFund;
	}

	@Column(name = "TuitionWaiberNum")
	public Integer getTuitionWaiberNum() {
		return this.tuitionWaiberNum;
	}

	public void setTuitionWaiberNum(Integer tuitionWaiberNum) {
		this.tuitionWaiberNum = tuitionWaiberNum;
	}

	@Column(name = "TempFund", precision = 18)
	public Double getTempFund() {
		return this.tempFund;
	}

	public void setTempFund(Double tempFund) {
		this.tempFund = tempFund;
	}

	public int getCheckState() {
		return CheckState;
	}

	public void setCheckState(int checkState) {
		CheckState = checkState;
	}

	@Column(name = "TempNum")
	public Integer getTempNum() {
		return this.tempNum;
	}

	public void setTempNum(Integer tempNum) {
		this.tempNum = tempNum;
	}

}