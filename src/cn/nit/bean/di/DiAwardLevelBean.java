package cn.nit.bean.di;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * DiAwardLevel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DiAwardLevel", schema = "dbo", catalog = "TDMS")
public class DiAwardLevelBean implements java.io.Serializable {

	// Fields

	private String indexId;
	private String awardLevel;


	// Constructors

	/** default constructor */
	public DiAwardLevelBean() {
	}

	/** minimal constructor */
	public DiAwardLevelBean(String indexId, String awardLevel) {
		this.indexId = indexId;
		this.awardLevel = awardLevel;
	}


	// Property accessors
	public String getIndexId() {
		return this.indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	public String getAwardLevel() {
		return this.awardLevel;
	}

	public void setAwardLevel(String awardLevel) {
		this.awardLevel = awardLevel;
	}
	
}