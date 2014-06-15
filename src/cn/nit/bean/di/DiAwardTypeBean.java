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
 * DiAwardType entity. @author MyEclipse Persistence Tools
 */

@Table(name = "DiAwardType", schema = "dbo", catalog = "TDMS")
public class DiAwardTypeBean implements java.io.Serializable {

	// Fields

	private String indexId;
	private String awardType;

	// Constructors

	/** default constructor */
	public DiAwardTypeBean() {
	}

	/** minimal constructor */
	public DiAwardTypeBean(String indexId, String awardType) {
		this.indexId = indexId;
		this.awardType = awardType;
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

	@Column(name = "AwardType", nullable = false)
	public String getAwardType() {
		return this.awardType;
	}

	public void setAwardType(String awardType) {
		this.awardType = awardType;
	}
}