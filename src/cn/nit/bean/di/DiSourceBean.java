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
 * DiSource entity. @author MyEclipse Persistence Tools
 */

@Table(name = "DiSource", schema = "dbo", catalog = "TDMS")
public class DiSourceBean implements java.io.Serializable {

	// Fields

	private String indexId;
	private String source;
	// Constructors

	/** default constructor */
	public DiSourceBean() {
	}

	/** minimal constructor */
	public DiSourceBean(String indexId, String source) {
		this.indexId = indexId;
		this.source = source;
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

	@Column(name = "Source", nullable = false)
	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}