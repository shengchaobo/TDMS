package cn.nit.bean.table4;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * S443HighLevelTalent entity. @author MyEclipse Persistence Tools
 */

@Table(name = "S443_HighLevelTalent$", schema = "dbo", catalog = "TDMS")
public class S443_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private Integer sumTalent;
	private Integer scienceTalent;
	private Integer engneerTalent;
	private Integer overseasTalent;
	private Integer yangtzeTalent;
	private Integer youthTalent;
	private Integer expertTalent;
	private Integer excellentTalent;
	private Integer youthTeaTalent;
	private Integer highLevelTalent;
	private Integer youthOverseas;
	private Date time;
	private String note;

	@Column(name = "SumTalent")
	
	
	public Integer getSumTalent() {
		return this.sumTalent;
	}

	public Integer getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	public void setSumTalent(Integer sumTalent) {
		this.sumTalent = sumTalent;
	}

	@Column(name = "ScienceTalent")
	public Integer getScienceTalent() {
		return this.scienceTalent;
	}

	public void setScienceTalent(Integer scienceTalent) {
		this.scienceTalent = scienceTalent;
	}

	@Column(name = "EngneerTalent")
	public Integer getEngneerTalent() {
		return this.engneerTalent;
	}

	public void setEngneerTalent(Integer engneerTalent) {
		this.engneerTalent = engneerTalent;
	}

	@Column(name = "OverseasTalent")
	public Integer getOverseasTalent() {
		return this.overseasTalent;
	}

	public void setOverseasTalent(Integer overseasTalent) {
		this.overseasTalent = overseasTalent;
	}

	@Column(name = "YangtzeTalent")
	public Integer getYangtzeTalent() {
		return this.yangtzeTalent;
	}

	public void setYangtzeTalent(Integer yangtzeTalent) {
		this.yangtzeTalent = yangtzeTalent;
	}

	@Column(name = "YouthTalent")
	public Integer getYouthTalent() {
		return this.youthTalent;
	}

	public void setYouthTalent(Integer youthTalent) {
		this.youthTalent = youthTalent;
	}

	@Column(name = "ExpertTalent")
	public Integer getExpertTalent() {
		return this.expertTalent;
	}

	public void setExpertTalent(Integer expertTalent) {
		this.expertTalent = expertTalent;
	}

	@Column(name = "ExcellentTalent")
	public Integer getExcellentTalent() {
		return this.excellentTalent;
	}

	public void setExcellentTalent(Integer excellentTalent) {
		this.excellentTalent = excellentTalent;
	}

	@Column(name = "YouthTeaTalent")
	public Integer getYouthTeaTalent() {
		return this.youthTeaTalent;
	}

	public void setYouthTeaTalent(Integer youthTeaTalent) {
		this.youthTeaTalent = youthTeaTalent;
	}

	@Column(name = "HighLevelTalent")
	public Integer getHighLevelTalent() {
		return this.highLevelTalent;
	}

	public void setHighLevelTalent(Integer highLevelTalent) {
		this.highLevelTalent = highLevelTalent;
	}

	@Column(name = "YouthOverseas")
	public Integer getYouthOverseas() {
		return this.youthOverseas;
	}

	public void setYouthOverseas(Integer youthOverseas) {
		this.youthOverseas = youthOverseas;
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