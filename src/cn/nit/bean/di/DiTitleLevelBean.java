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
 * DiTitleLevel entity. @author MyEclipse Persistence Tools
 */

public class DiTitleLevelBean implements java.io.Serializable {

	// Fields

	private String indexId;
	private String titleLevel;

	// Constructors

	/** default constructor */
	public DiTitleLevelBean() {
	}

	/** minimal constructor */
	public DiTitleLevelBean(String indexId, String titleLevel) {
		this.indexId = indexId;
		this.titleLevel = titleLevel;
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

	@Column(name = "TitleLevel", nullable = false)
	public String getTitleLevel() {
		return this.titleLevel;
	}

	public void setTitleLevel(String titleLevel) {
		this.titleLevel = titleLevel;
	}
}