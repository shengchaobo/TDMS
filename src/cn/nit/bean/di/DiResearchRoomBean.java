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
 * DiResearchRoom entity. @author MyEclipse Persistence Tools
 */

@Table(name = "DiResearchRoom", schema = "dbo", catalog = "TDMS")
public class DiResearchRoomBean implements java.io.Serializable {

	// Fields

	private String unitId;
	private String parentId;
	private String researchName;

	// Constructors

	/** default constructor */
	public DiResearchRoomBean() {
	}

	/** minimal constructor */
	public DiResearchRoomBean(String unitId, String researchName) {
		this.unitId = unitId;
		this.researchName = researchName;
	}

	// Property accessors
	@Id
	@Column(name = "UnitID", unique = true, nullable = false)
	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	@Column(name = "ParentID")
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "ResearchName", nullable = false)
	public String getResearchName() {
		return this.researchName;
	}

	public void setResearchName(String researchName) {
		this.researchName = researchName;
	}
}