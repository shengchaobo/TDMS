package cn.nit.bean.table2;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T242CurYearAddBookLib entity. @author MyEclipse Persistence Tools
 */
@Table(name = "T242_CurYearAddBook_Lib$", schema = "dbo", catalog = "TDMS")
public class T242_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private Integer addPaperBookNum;
	private Integer addDigBookType;
	private Double literAcqusExps;
	private Integer bookTurnover;
	private Integer digResVisit;
	private Date time;
	private String note;
	private int CheckState;

	// Constructors
	@Id
	@Column(name = "SeqNumber", unique = true, nullable = false)
	public Integer getSeqNumber() {
		return this.seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	@Column(name = "AddPaperBookNum", nullable = false)
	public Integer getAddPaperBookNum() {
		return this.addPaperBookNum;
	}

	public void setAddPaperBookNum(Integer addPaperBookNum) {
		this.addPaperBookNum = addPaperBookNum;
	}

	@Column(name = "AddDigBookType")
	public Integer getAddDigBookType() {
		return this.addDigBookType;
	}

	public void setAddDigBookType(Integer addDigBookType) {
		this.addDigBookType = addDigBookType;
	}

	@Column(name = "LiterAcqusExps", precision = 18)
	public Double getLiterAcqusExps() {
		return this.literAcqusExps;
	}

	public void setLiterAcqusExps(Double literAcqusExps) {
		this.literAcqusExps = literAcqusExps;
	}

	@Column(name = "BookTurnover")
	public Integer getBookTurnover() {
		return this.bookTurnover;
	}

	public void setBookTurnover(Integer bookTurnover) {
		this.bookTurnover = bookTurnover;
	}

	@Column(name = "DigResVisit")
	public Integer getDigResVisit() {
		return this.digResVisit;
	}

	public void setDigResVisit(Integer digResVisit) {
		this.digResVisit = digResVisit;
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

	public void setCheckState(int checkState) {
		CheckState = checkState;
	}

	public int getCheckState() {
		return CheckState;
	}

}