package cn.nit.bean.di;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DiContestScope entity. @author MyEclipse Persistence Tools
 */

public class DiContestScopeBean implements java.io.Serializable {

	// Fields

	private String indexId;
	private String contestScope;

	// Constructors

	/** default constructor */
	public DiContestScopeBean() {
	}

	/** full constructor */
	public DiContestScopeBean(String indexId, String contestScope) {
		this.indexId = indexId;
		this.contestScope = contestScope;
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

	@Column(name = "ContestScope", nullable = false)
	public String getContestScope() {
		return this.contestScope;
	}

	public void setContestScope(String contestScope) {
		this.contestScope = contestScope;
	}

}