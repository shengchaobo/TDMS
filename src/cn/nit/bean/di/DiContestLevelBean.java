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
 * DiContestLevel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DiContestLevel", schema = "dbo", catalog = "TDMS")
public class DiContestLevelBean implements java.io.Serializable {

	// Fields

	private String indexId;
	private String contestLevel;

	// Constructors

	/** default constructor */
	public DiContestLevelBean() {
	}

	/** minimal constructor */
	public DiContestLevelBean(String indexId) {
		this.indexId = indexId;
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

	@Column(name = "ContestLevel")
	public String getContestLevel() {
		return this.contestLevel;
	}

	public void setContestLevel(String contestLevel) {
		this.contestLevel = contestLevel;
	}
}