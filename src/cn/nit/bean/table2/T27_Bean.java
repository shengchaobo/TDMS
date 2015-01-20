package cn.nit.bean.table2;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T27SchNetworkNic entity. @author MyEclipse Persistence Tools
 */
@Table(name = "T27_SchNetwork_NIC$", schema = "dbo", catalog = "TDMS")
public class T27_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private Double mainBw;
	private Double exitBw;
	private Integer accessPoinum;
	private Integer classrmNum;
	private Integer dormiNum;
	private Integer officeNum;
	private Double sumMemSpace;
	private Double avgTeaMemSpace;
	private Double avgStuMemSpace;
	private String webTeahingUrl;
	private String teaManageUrl;
	private Date time;
	private String note;
	private int CheckState;

	// Constructors

	/** default constructor */
	public T27_Bean() {
	}

	/** minimal constructor */
	public T27_Bean(Integer seqNumber, String note) {
		this.seqNumber = seqNumber;
		this.note = note;
	}

	/** full constructor */
	public T27_Bean(Integer seqNumber, Double mainBw,
			Double exitBw, Integer accessPoinum, Integer classrmNum,
			Integer dormiNum, Integer officeNum, Double sumMemSpace,
			Double avgTeaMemSpace, Double avgStuMemSpace,
			String webTeahingUrl, Date time, String note) {
		this.seqNumber = seqNumber;
		this.mainBw = mainBw;
		this.exitBw = exitBw;
		this.accessPoinum = accessPoinum;
		this.classrmNum = classrmNum;
		this.dormiNum = dormiNum;
		this.officeNum = officeNum;
		this.sumMemSpace = sumMemSpace;
		this.avgTeaMemSpace = avgTeaMemSpace;
		this.avgStuMemSpace = avgStuMemSpace;
		this.webTeahingUrl = webTeahingUrl;
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

	@Column(name = "MainBW", precision = 18)
	public Double getMainBw() {
		return this.mainBw;
	}

	public void setMainBw(Double mainBw) {
		this.mainBw = mainBw;
	}

	@Column(name = "ExitBW", precision = 18)
	public Double getExitBw() {
		return this.exitBw;
	}

	public void setExitBw(Double exitBw) {
		this.exitBw = exitBw;
	}

	@Column(name = "AccessPOINum")
	public Integer getAccessPoinum() {
		return this.accessPoinum;
	}

	public void setAccessPoinum(Integer accessPoinum) {
		this.accessPoinum = accessPoinum;
	}

	@Column(name = "ClassrmNum")
	public Integer getClassrmNum() {
		return this.classrmNum;
	}

	public void setClassrmNum(Integer classrmNum) {
		this.classrmNum = classrmNum;
	}

	@Column(name = "DormiNum")
	public Integer getDormiNum() {
		return this.dormiNum;
	}

	public void setDormiNum(Integer dormiNum) {
		this.dormiNum = dormiNum;
	}

	@Column(name = "OfficeNum")
	public Integer getOfficeNum() {
		return this.officeNum;
	}

	public void setOfficeNum(Integer officeNum) {
		this.officeNum = officeNum;
	}

	@Column(name = "SumMemSpace", precision = 18)
	public Double getSumMemSpace() {
		return this.sumMemSpace;
	}

	public void setSumMemSpace(Double sumMemSpace) {
		this.sumMemSpace = sumMemSpace;
	}

	@Column(name = "AvgTeaMemSpace", precision = 18)
	public Double getAvgTeaMemSpace() {
		return this.avgTeaMemSpace;
	}

	public void setAvgTeaMemSpace(Double avgTeaMemSpace) {
		this.avgTeaMemSpace = avgTeaMemSpace;
	}

	@Column(name = "AvgStuMemSpace", precision = 18)
	public Double getAvgStuMemSpace() {
		return this.avgStuMemSpace;
	}

	public void setAvgStuMemSpace(Double avgStuMemSpace) {
		this.avgStuMemSpace = avgStuMemSpace;
	}

	@Column(name = "WebTeahingUrl")
	public String getWebTeahingUrl() {
		return this.webTeahingUrl;
	}

	public void setWebTeahingUrl(String webTeahingUrl) {
		this.webTeahingUrl = webTeahingUrl;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Time", length = 10)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "Note", nullable = false)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setTeaManageUrl(String teaManageUrl) {
		this.teaManageUrl = teaManageUrl;
	}

	public String getTeaManageUrl() {
		return teaManageUrl;
	}

	public void setCheckState(int checkState) {
		CheckState = checkState;
	}

	public int getCheckState() {
		return CheckState;
	}

}