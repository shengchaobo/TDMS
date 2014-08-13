package cn.nit.bean.table6;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T284FixedAssetFile entity. @author MyEclipse Persistence Tools
 */
public class T616_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
    private String stuType;
    private Integer sumGraNum;
    private Integer graOutNum;
    private Integer graHongNum;
    private Integer graAoNum;
    private Integer graTaiNum;
    private Integer sumDegreeNum;
    private Integer degreeOutNum;
    private Integer degreeHongNum;
    private Integer degreeAoNum;
    private Integer degreeTaiNum;
    private Integer sumAdmisNum;
    private Integer admisOutNum;
    private Integer admisHongNum;
    private Integer admisAoNum;
    private Integer admisTaiNum;
    private Integer sumInSchNum;
    private Integer inSchOutNum;
    private Integer inSchHongNum;
    private Integer inSchAoNum;
    private Integer inSchTaiNum;
    
    
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setStuType(String stuType) {
		this.stuType = stuType;
	}

	public String getStuType() {
		return stuType;
	}

	public void setSumGraNum(Integer sumGraNum) {
		this.sumGraNum = sumGraNum;
	}

	public Integer getSumGraNum() {
		return sumGraNum;
	}

	public void setGraOutNum(Integer graOutNum) {
		this.graOutNum = graOutNum;
	}

	public Integer getGraOutNum() {
		return graOutNum;
	}
	
	public void setGraHongNum(Integer graHongNum) {
		this.graHongNum = graHongNum;
	}

	public Integer getGraHongNum() {
		return graHongNum;
	}
	
	public void setGraAoNum(Integer graAoNum) {
		this.graAoNum = graAoNum;
	}

	public Integer getGraAoNum() {
		return graAoNum;
	}
	
	public void setGraTaiNum(Integer graTaiNum) {
		this.graTaiNum = graTaiNum;
	}

	public Integer getGraTaiNum() {
		return graTaiNum;
	}
	
	public void setSumDegreeNum(Integer sumDegreeNum) {
		this.sumDegreeNum = sumDegreeNum;
	}

	public Integer getSumDegreeNum() {
		return sumDegreeNum;
	}
	
	public void setDegreeOutNum(Integer degreeOutNum) {
		this.degreeOutNum = degreeOutNum;
	}

	public Integer getDegreeOutNum() {
		return degreeOutNum;
	}
	
	public void setDegreeHongNum(Integer degreeHongNum) {
		this.degreeHongNum = degreeHongNum;
	}

	public Integer getDegreeHongNum() {
		return degreeHongNum;
	}
	
	public void setDegreeAoNum(Integer degreeAoNum) {
		this.degreeAoNum = degreeAoNum;
	}

	public Integer getDegreeAoNum() {
		return degreeAoNum;
	}
	
	public void setDegreeTaiNum(Integer degreeTaiNum) {
		this.degreeTaiNum = degreeTaiNum;
	}

	public Integer getDegreeTaiNum() {
		return degreeTaiNum;
	}
	
	public void setSumAdmisNum(Integer sumAdmisNum) {
		this.sumAdmisNum = sumAdmisNum;
	}

	public Integer getSumAdmisNum() {
		return sumAdmisNum;
	}
	
	public void setAdmisOutNum(Integer admisOutNum) {
		this.admisOutNum = admisOutNum;
	}

	public Integer getAdmisOutNum() {
		return admisOutNum;
	}
	
	public void setAdmisHongNum(Integer admisHongNum) {
		this.admisHongNum = admisHongNum;
	}

	public Integer getAdmisHongNum() {
		return admisHongNum;
	}
	
	public void setAdmisAoNum(Integer admisAoNum) {
		this.admisAoNum = admisAoNum;
	}

	public Integer getAdmisAoNum() {
		return admisAoNum;
	}
	
	public void setAdmisTaiNum(Integer admisTaiNum) {
		this.admisTaiNum = admisTaiNum;
	}

	public Integer getAdmisTaiNum() {
		return admisTaiNum;
	}
	
	public void setSumInSchNum(Integer sumInSchNum) {
		this.sumInSchNum = sumInSchNum;
	}

	public Integer getSumInSchNum() {
		return sumInSchNum;
	}
	
	public void setInSchOutNum(Integer inSchOutNum) {
		this.inSchOutNum = inSchOutNum;
	}

	public Integer getInSchOutNum() {
		return inSchOutNum;
	}
	
	public void setInSchHongNum(Integer inSchHongNum) {
		this.inSchHongNum = inSchHongNum;
	}

	public Integer getInSchHongNum() {
		return inSchHongNum;
	}
	
	public void setInSchAoNum(Integer inSchAoNum) {
		this.inSchAoNum = inSchAoNum;
	}

	public Integer getInSchAoNum() {
		return inSchAoNum;
	}
	
	public void setInSchTaiNum(Integer inSchTaiNum) {
		this.inSchTaiNum = inSchTaiNum;
	}

	public Integer getInSchTaiNum() {
		return inSchTaiNum;
	}

	

	public void setNote(String note) {
		this.note = note;
	}

	public String getNote() {
		return note;
	}

}