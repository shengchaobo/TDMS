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
 * DiResearchTeam entity. @author MyEclipse Persistence Tools
 */

@Table(name = "DiResearchTeam", schema = "dbo", catalog = "TDMS")
public class DiResearchTeamBean implements java.io.Serializable {

	// Fields

	private String indexId;
	private String researchTeam;

	// Constructors

	/** default constructor */
	public DiResearchTeamBean() {
	}

	/** minimal constructor */
	public DiResearchTeamBean(String indexId) {
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

	@Column(name = "ResearchTeam")
	public String getResearchTeam() {
		return this.researchTeam;
	}

	public void setResearchTeam(String researchTeam) {
		this.researchTeam = researchTeam;
	}
}