package cn.nit.bean.table2;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T293UndergraTeaIncomeFinance entity. @author MyEclipse Persistence Tools
 */
@Table(name = "T293_UndergraTeaIncome_Finance$", schema = "dbo", catalog = "TDMS")
public class T293_Bean implements
		java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private Double sumIncom;
	private Double sumUndergraIncome;
	private Double allocateFund;
	private Double nationFund;
	private Double localFund;
	private Double undergraTuition;
	private Double eduReformFund;
	private Double sumOtherIncome;
	private Double otherAllocateFund;
	private Double otherNationFund;
	private Double otherLocalFund;
	private Double otherTuition;
	private Double graTuition;
	private Double juniorTuition;
	private Double netTeaTuition;
	private Double donation;
	private Double otherIncome;
	private Date time;
	private String note;

	@Column(name = "SeqNumber", unique = true, nullable = false)
	public Integer getSeqNumber() {
		return this.seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	@Column(name = "SumIncom", precision = 18)
	public Double getSumIncom() {
		return this.sumIncom;
	}

	public void setSumIncom(Double sumIncom) {
		this.sumIncom = sumIncom;
	}

	@Column(name = "SumUndergraIncome", nullable = false, precision = 18)
	public Double getSumUndergraIncome() {
		return this.sumUndergraIncome;
	}

	public void setSumUndergraIncome(Double sumUndergraIncome) {
		this.sumUndergraIncome = sumUndergraIncome;
	}

	@Column(name = "AllocateFund", precision = 18)
	public Double getAllocateFund() {
		return this.allocateFund;
	}

	public void setAllocateFund(Double allocateFund) {
		this.allocateFund = allocateFund;
	}

	@Column(name = "NationFund", precision = 18)
	public Double getNationFund() {
		return this.nationFund;
	}

	public void setNationFund(Double nationFund) {
		this.nationFund = nationFund;
	}

	@Column(name = "LocalFund", precision = 18)
	public Double getLocalFund() {
		return this.localFund;
	}

	public void setLocalFund(Double localFund) {
		this.localFund = localFund;
	}

	@Column(name = "UndergraTuition", precision = 18)
	public Double getUndergraTuition() {
		return this.undergraTuition;
	}

	public void setUndergraTuition(Double undergraTuition) {
		this.undergraTuition = undergraTuition;
	}

	@Column(name = "EduReformFund", precision = 18)
	public Double getEduReformFund() {
		return this.eduReformFund;
	}

	public void setEduReformFund(Double eduReformFund) {
		this.eduReformFund = eduReformFund;
	}

	@Column(name = "SumOtherIncome", precision = 18)
	public Double getSumOtherIncome() {
		return this.sumOtherIncome;
	}

	public void setSumOtherIncome(Double sumOtherIncome) {
		this.sumOtherIncome = sumOtherIncome;
	}

	@Column(name = "OtherAllocateFund", precision = 18)
	public Double getOtherAllocateFund() {
		return this.otherAllocateFund;
	}

	public void setOtherAllocateFund(Double otherAllocateFund) {
		this.otherAllocateFund = otherAllocateFund;
	}

	@Column(name = "OtherNationFund", precision = 18)
	public Double getOtherNationFund() {
		return this.otherNationFund;
	}

	public void setOtherNationFund(Double otherNationFund) {
		this.otherNationFund = otherNationFund;
	}

	@Column(name = "OtherLocalFund", precision = 18)
	public Double getOtherLocalFund() {
		return this.otherLocalFund;
	}

	public void setOtherLocalFund(Double otherLocalFund) {
		this.otherLocalFund = otherLocalFund;
	}

	@Column(name = "OtherTuition", precision = 18)
	public Double getOtherTuition() {
		return this.otherTuition;
	}

	public void setOtherTuition(Double otherTuition) {
		this.otherTuition = otherTuition;
	}

	@Column(name = "GraTuition", precision = 18)
	public Double getGraTuition() {
		return this.graTuition;
	}

	public void setGraTuition(Double graTuition) {
		this.graTuition = graTuition;
	}

	@Column(name = "JuniorTuition", precision = 18)
	public Double getJuniorTuition() {
		return this.juniorTuition;
	}

	public void setJuniorTuition(Double juniorTuition) {
		this.juniorTuition = juniorTuition;
	}

	@Column(name = "NetTeaTuition", precision = 18)
	public Double getNetTeaTuition() {
		return this.netTeaTuition;
	}

	public void setNetTeaTuition(Double netTeaTuition) {
		this.netTeaTuition = netTeaTuition;
	}

	@Column(name = "Donation", precision = 18)
	public Double getDonation() {
		return this.donation;
	}

	public void setDonation(Double donation) {
		this.donation = donation;
	}

	@Column(name = "OtherIncome", precision = 18)
	public Double getOtherIncome() {
		return this.otherIncome;
	}

	public void setOtherIncome(Double otherIncome) {
		this.otherIncome = otherIncome;
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