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
 * DiTalentType entity. @author MyEclipse Persistence Tools
 */

public class DiTalentTypeBean implements java.io.Serializable {

	// Fields

	private String indexId;
	private String talentType;

	// Constructors

	/** default constructor */
	public DiTalentTypeBean() {
	}

	/** minimal constructor */
	public DiTalentTypeBean(String indexId, String talentType) {
		this.indexId = indexId;
		this.talentType = talentType;
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

	@Column(name = "TalentType", nullable = false)
	public String getTalentType() {
		return this.talentType;
	}

	public void setTalentType(String talentType) {
		this.talentType = talentType;
	}
}