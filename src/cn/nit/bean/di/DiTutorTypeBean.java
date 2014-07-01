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
 * DiTutorType entity. @author MyEclipse Persistence Tools
 */

public class DiTutorTypeBean implements java.io.Serializable {

	// Fields

	private String indexId;
	private String tutorType;

	// Constructors

	/** default constructor */
	public DiTutorTypeBean() {
	}

	/** minimal constructor */
	public DiTutorTypeBean(String indexId, String tutorType) {
		this.indexId = indexId;
		this.tutorType = tutorType;
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

	@Column(name = "TutorType", nullable = false)
	public String getTutorType() {
		return this.tutorType;
	}

	public void setTutorType(String tutorType) {
		this.tutorType = tutorType;
	}

}