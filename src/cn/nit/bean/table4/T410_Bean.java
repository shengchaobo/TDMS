package cn.nit.bean.table4;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T410TeaResInfoRes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T410_TeaResInfo_Res$", schema = "dbo", catalog = "TDMS")
public class T410_Bean implements java.io.Serializable {

	// Fields
	private Integer resItemNum;
	private Double resItemFund;
	private Integer hresItemNum;
	private Double hitemFund;
	private Integer hhumanItemNum;
	private Double hhumanItemFund;
	private Integer zresItemNum;
	private Double zitemFund;
	private Integer zhumanItemNum;
	private Double zhumanItemFund;
	private Integer resAwardNum;
	private Integer nationResAward;
	private Integer proviResAward;
	private Integer cityResAward;
	private Integer schResAward;
	private Integer paperNum;
	private Integer sci;
	private Integer ssci;
	private Integer ei;
	private Integer istp;
	private Integer inlandCoreJnal;
	private Integer cssci;
	private Integer cscd;
	private Integer otherPaper;
	private Integer publicationNum;
	private Integer treatises;
	private Integer translation;
	private Integer patentNum;
	private Integer inventPatent;
	private Integer utilityPatent;
	private Integer designPatent;
	private Date time;
	private String note;

	@Column(name = "ResItemNum")
	public Integer getResItemNum() {
		return this.resItemNum;
	}

	public void setResItemNum(Integer resItemNum) {
		this.resItemNum = resItemNum;
	}

	@Column(name = "ResItemFund", precision = 18)
	public Double getResItemFund() {
		return this.resItemFund;
	}

	public void setResItemFund(Double resItemFund) {
		this.resItemFund = resItemFund;
	}

	@Column(name = "HResItemNum")
	public Integer getHresItemNum() {
		return this.hresItemNum;
	}

	public void setHresItemNum(Integer hresItemNum) {
		this.hresItemNum = hresItemNum;
	}

	@Column(name = "HItemFund", precision = 18)
	public Double getHitemFund() {
		return this.hitemFund;
	}

	public void setHitemFund(Double hitemFund) {
		this.hitemFund = hitemFund;
	}

	@Column(name = "HhumanItemNum")
	public Integer getHhumanItemNum() {
		return this.hhumanItemNum;
	}

	public void setHhumanItemNum(Integer hhumanItemNum) {
		this.hhumanItemNum = hhumanItemNum;
	}

	@Column(name = "HhumanItemFund", precision = 18)
	public Double getHhumanItemFund() {
		return this.hhumanItemFund;
	}

	public void setHhumanItemFund(Double hhumanItemFund) {
		this.hhumanItemFund = hhumanItemFund;
	}

	@Column(name = "ZResItemNum")
	public Integer getZresItemNum() {
		return this.zresItemNum;
	}

	public void setZresItemNum(Integer zresItemNum) {
		this.zresItemNum = zresItemNum;
	}

	@Column(name = "ZItemFund", precision = 18)
	public Double getZitemFund() {
		return this.zitemFund;
	}

	public void setZitemFund(Double zitemFund) {
		this.zitemFund = zitemFund;
	}

	@Column(name = "ZhumanItemNum")
	public Integer getZhumanItemNum() {
		return this.zhumanItemNum;
	}

	public void setZhumanItemNum(Integer zhumanItemNum) {
		this.zhumanItemNum = zhumanItemNum;
	}

	@Column(name = "ZhumanItemFund", precision = 18)
	public Double getZhumanItemFund() {
		return this.zhumanItemFund;
	}

	public void setZhumanItemFund(Double zhumanItemFund) {
		this.zhumanItemFund = zhumanItemFund;
	}

	@Column(name = "ResAwardNum")
	public Integer getResAwardNum() {
		return this.resAwardNum;
	}

	public void setResAwardNum(Integer resAwardNum) {
		this.resAwardNum = resAwardNum;
	}

	@Column(name = "NationResAward")
	public Integer getNationResAward() {
		return this.nationResAward;
	}

	public void setNationResAward(Integer nationResAward) {
		this.nationResAward = nationResAward;
	}

	@Column(name = "ProviResAward")
	public Integer getProviResAward() {
		return this.proviResAward;
	}

	public void setProviResAward(Integer proviResAward) {
		this.proviResAward = proviResAward;
	}

	@Column(name = "CityResAward")
	public Integer getCityResAward() {
		return this.cityResAward;
	}

	public void setCityResAward(Integer cityResAward) {
		this.cityResAward = cityResAward;
	}

	@Column(name = "SchResAward")
	public Integer getSchResAward() {
		return this.schResAward;
	}

	public void setSchResAward(Integer schResAward) {
		this.schResAward = schResAward;
	}

	@Column(name = "SCI")
	public Integer getSci() {
		return this.sci;
	}

	public void setSci(Integer sci) {
		this.sci = sci;
	}

	@Column(name = "SSCI")
	public Integer getSsci() {
		return this.ssci;
	}

	public void setSsci(Integer ssci) {
		this.ssci = ssci;
	}

	@Column(name = "EI")
	public Integer getEi() {
		return this.ei;
	}

	public void setEi(Integer ei) {
		this.ei = ei;
	}

	@Column(name = "ISTP", nullable = false)
	public Integer getIstp() {
		return this.istp;
	}

	public void setIstp(Integer istp) {
		this.istp = istp;
	}

	@Column(name = "InlandCoreJnal")
	public Integer getInlandCoreJnal() {
		return this.inlandCoreJnal;
	}

	public void setInlandCoreJnal(Integer inlandCoreJnal) {
		this.inlandCoreJnal = inlandCoreJnal;
	}

	@Column(name = "CSSCI")
	public Integer getCssci() {
		return this.cssci;
	}

	public void setCssci(Integer cssci) {
		this.cssci = cssci;
	}

	@Column(name = "CSCD")
	public Integer getCscd() {
		return this.cscd;
	}

	public void setCscd(Integer cscd) {
		this.cscd = cscd;
	}

	@Column(name = "PublicationNum")
	public Integer getPublicationNum() {
		return this.publicationNum;
	}

	public void setPublicationNum(Integer publicationNum) {
		this.publicationNum = publicationNum;
	}

	@Column(name = "Treatises")
	public Integer getTreatises() {
		return this.treatises;
	}

	public void setTreatises(Integer treatises) {
		this.treatises = treatises;
	}

	@Column(name = "translation")
	public Integer getTranslation() {
		return this.translation;
	}

	public void setTranslation(Integer translation) {
		this.translation = translation;
	}

	@Column(name = "PatentNum")
	public Integer getPatentNum() {
		return this.patentNum;
	}

	public void setPatentNum(Integer patentNum) {
		this.patentNum = patentNum;
	}

	@Column(name = "InventPatent")
	public Integer getInventPatent() {
		return this.inventPatent;
	}

	public void setInventPatent(Integer inventPatent) {
		this.inventPatent = inventPatent;
	}

	@Column(name = "UtilityPatent")
	public Integer getUtilityPatent() {
		return this.utilityPatent;
	}

	public void setUtilityPatent(Integer utilityPatent) {
		this.utilityPatent = utilityPatent;
	}

	@Column(name = "DesignPatent")
	public Integer getDesignPatent() {
		return this.designPatent;
	}

	public void setDesignPatent(Integer designPatent) {
		this.designPatent = designPatent;
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

	public void setPaperNum(Integer paperNum) {
		this.paperNum = paperNum;
	}

	public Integer getPaperNum() {
		return paperNum;
	}

	public void setOtherPaper(Integer otherPaper) {
		this.otherPaper = otherPaper;
	}

	public Integer getOtherPaper() {
		return otherPaper;
	}

}