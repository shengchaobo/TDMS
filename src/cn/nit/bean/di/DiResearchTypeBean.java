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
 * DiResearchType entity. @author MyEclipse Persistence Tools
 */

public class DiResearchTypeBean implements java.io.Serializable {

	// Fields

	private String indexId;
	private String researchType;

	// Constructors

	/** default constructor */
	public DiResearchTypeBean() {
	}

	/** minimal constructor */
	public DiResearchTypeBean(String indexId, String researchType) {
		this.indexId = indexId;
		this.researchType = researchType;
	}
	
	// Property accessors
	@Id
	@Column(name = "IndexID", unique = true, nullable = false)
	public String getIndexId() {
		return this.indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	@Column(name = "ResearchType", nullable = false)
	public String getResearchType() {
		return this.researchType;
	}

	public void setResearchType(String researchType) {
		this.researchType = researchType;
	}
}