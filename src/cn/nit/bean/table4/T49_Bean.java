package cn.nit.bean.table4;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T49TeaPublishTxbookTeaTea entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T49_TeaPublishTxbook_TeaTea$", schema = "dbo", catalog = "TDMS")
public class T49_Bean implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	private Integer seqNumber;
	private String teaUnit;
	private String unitId;
	private Integer complileBookNum;
	private Integer writeBookNum;
	private Integer sumPlanBook;
	private Integer interPlanBook;
	private Integer nationPlanBook;
	private Integer proviPlanBook;
	private Integer cityPlanBook;
	private Integer schPlanBook;
	private Integer sumAwardBook;
	private Integer interAwardBook;
	private Integer nationAwardBook;
	private Integer proviAwardBook;
	private Integer cityAwardBook;
	private Integer schAwardBook;
	private Date time;
	private String note;

	// Constructors

	/** default constructor */
	public T49_Bean() {
	}

	/** minimal constructor */
	public T49_Bean(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	/** full constructor */
	public T49_Bean(Integer seqNumber,
			String teaUnit,
			Integer complileBookNum, Integer writeBookNum,
			Integer sumPlanBook, Integer interPlanBook,
			Integer nationPlanBook, Integer proviPlanBook,
			Integer cityPlanBook, Integer schPlanBook,
			Integer sumAwardBook, Integer interAwardBook,
			Integer nationAwardBook, Integer proviAwardBook,
			Integer cityAwardBook, Integer schAwardBook, Date time,
			String note) {
		this.seqNumber = seqNumber;
		this.teaUnit = teaUnit;
		this.complileBookNum = complileBookNum;
		this.writeBookNum = writeBookNum;
		this.sumPlanBook = sumPlanBook;
		this.interPlanBook = interPlanBook;
		this.nationPlanBook = nationPlanBook;
		this.proviPlanBook = proviPlanBook;
		this.cityPlanBook = cityPlanBook;
		this.schPlanBook = schPlanBook;
		this.sumAwardBook = sumAwardBook;
		this.interAwardBook = interAwardBook;
		this.nationAwardBook = nationAwardBook;
		this.proviAwardBook = proviAwardBook;
		this.cityAwardBook = cityAwardBook;
		this.schAwardBook = schAwardBook;
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

	@Column(name = "TeaUnit")
	public String getTeaUnit() {
		return this.teaUnit;
	}

	public void setTeaUnit(String teaUnit) {
		this.teaUnit = teaUnit;
	}

	@Column(name = "ComplileBookNum")
	public Integer getComplileBookNum() {
		return this.complileBookNum;
	}

	public void setComplileBookNum(Integer complileBookNum) {
		this.complileBookNum = complileBookNum;
	}

	@Column(name = "WriteBookNum")
	public Integer getWriteBookNum() {
		return this.writeBookNum;
	}

	public void setWriteBookNum(Integer writeBookNum) {
		this.writeBookNum = writeBookNum;
	}

	@Column(name = "SumPlanBook")
	public Integer getSumPlanBook() {
		return this.sumPlanBook;
	}

	public void setSumPlanBook(Integer sumPlanBook) {
		this.sumPlanBook = sumPlanBook;
	}

	@Column(name = "InterPlanBook")
	public Integer getInterPlanBook() {
		return this.interPlanBook;
	}

	public void setInterPlanBook(Integer interPlanBook) {
		this.interPlanBook = interPlanBook;
	}

	@Column(name = "NationPlanBook")
	public Integer getNationPlanBook() {
		return this.nationPlanBook;
	}

	public void setNationPlanBook(Integer nationPlanBook) {
		this.nationPlanBook = nationPlanBook;
	}

	@Column(name = "ProviPlanBook")
	public Integer getProviPlanBook() {
		return this.proviPlanBook;
	}

	public void setProviPlanBook(Integer proviPlanBook) {
		this.proviPlanBook = proviPlanBook;
	}

	@Column(name = "CityPlanBook")
	public Integer getCityPlanBook() {
		return this.cityPlanBook;
	}

	public void setCityPlanBook(Integer cityPlanBook) {
		this.cityPlanBook = cityPlanBook;
	}

	@Column(name = "SchPlanBook")
	public Integer getSchPlanBook() {
		return this.schPlanBook;
	}

	public void setSchPlanBook(Integer schPlanBook) {
		this.schPlanBook = schPlanBook;
	}

	@Column(name = "SumAwardBook")
	public Integer getSumAwardBook() {
		return this.sumAwardBook;
	}

	public void setSumAwardBook(Integer sumAwardBook) {
		this.sumAwardBook = sumAwardBook;
	}

	@Column(name = "InterAwardBook")
	public Integer getInterAwardBook() {
		return this.interAwardBook;
	}

	public void setInterAwardBook(Integer interAwardBook) {
		this.interAwardBook = interAwardBook;
	}

	@Column(name = "NationAwardBook")
	public Integer getNationAwardBook() {
		return this.nationAwardBook;
	}

	public void setNationAwardBook(Integer nationAwardBook) {
		this.nationAwardBook = nationAwardBook;
	}

	@Column(name = "ProviAwardBook")
	public Integer getProviAwardBook() {
		return this.proviAwardBook;
	}

	public void setProviAwardBook(Integer proviAwardBook) {
		this.proviAwardBook = proviAwardBook;
	}

	@Column(name = "CityAwardBook")
	public Integer getCityAwardBook() {
		return this.cityAwardBook;
	}

	public void setCityAwardBook(Integer cityAwardBook) {
		this.cityAwardBook = cityAwardBook;
	}

	@Column(name = "SchAwardBook")
	public Integer getSchAwardBook() {
		return this.schAwardBook;
	}

	public void setSchAwardBook(Integer schAwardBook) {
		this.schAwardBook = schAwardBook;
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

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitId() {
		return unitId;
	}

}