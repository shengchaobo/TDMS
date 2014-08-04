package cn.nit.bean.table2;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T241BookNumLib entity. @author MyEclipse Persistence Tools
 */
@Table(name = "T241_BookNum_Lib$", schema = "dbo", catalog = "TDMS")
public class T241_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private Integer paperBookNum;
	private Integer paperJonalNum;
	private Integer paperJonalType;
	private Integer digBookType;
	private Integer chiDigBookType;
	private Integer forDigBookType;
	private Integer digBookSize;
	private Integer digJonalType;
	private Integer databaseNum;
	private Integer otherDatabase;
	private Date time;
	private String note;

	// Property accessors
	@Id
	@Column(name = "SeqNumber", unique = true, nullable = false)
	public Integer getSeqNumber() {
		return this.seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	@Column(name = "PaperBookNum")
	public Integer getPaperBookNum() {
		return this.paperBookNum;
	}

	public void setPaperBookNum(Integer paperBookNum) {
		this.paperBookNum = paperBookNum;
	}

	@Column(name = "PaperJonalNum")
	public Integer getPaperJonalNum() {
		return this.paperJonalNum;
	}

	public void setPaperJonalNum(Integer paperJonalNum) {
		this.paperJonalNum = paperJonalNum;
	}

	@Column(name = "PaperJonalType")
	public Integer getPaperJonalType() {
		return this.paperJonalType;
	}

	public void setPaperJonalType(Integer paperJonalType) {
		this.paperJonalType = paperJonalType;
	}

	@Column(name = "DigBookType")
	public Integer getDigBookType() {
		return this.digBookType;
	}

	public void setDigBookType(Integer digBookType) {
		this.digBookType = digBookType;
	}

	@Column(name = "ChiDigBookType")
	public Integer getChiDigBookType() {
		return this.chiDigBookType;
	}

	public void setChiDigBookType(Integer chiDigBookType) {
		this.chiDigBookType = chiDigBookType;
	}

	@Column(name = "ForDigBookType")
	public Integer getForDigBookType() {
		return this.forDigBookType;
	}

	public void setForDigBookType(Integer forDigBookType) {
		this.forDigBookType = forDigBookType;
	}

	@Column(name = "DigBookSize")
	public Integer getDigBookSize() {
		return this.digBookSize;
	}

	public void setDigBookSize(Integer digBookSize) {
		this.digBookSize = digBookSize;
	}

	@Column(name = "DigJonalType")
	public Integer getDigJonalType() {
		return this.digJonalType;
	}

	public void setDigJonalType(Integer digJonalType) {
		this.digJonalType = digJonalType;
	}

	@Column(name = "DatabaseNum")
	public Integer getDatabaseNum() {
		return this.databaseNum;
	}

	public void setDatabaseNum(Integer databaseNum) {
		this.databaseNum = databaseNum;
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

	public Integer getOtherDatabase() {
		return otherDatabase;
	}

	public void setOtherDatabase(Integer otherDatabase) {
		this.otherDatabase = otherDatabase;
	}

}