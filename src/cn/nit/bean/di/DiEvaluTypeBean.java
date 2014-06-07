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
 * DiEvaluType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DiEvaluType", schema = "dbo", catalog = "TDMS")
public class DiEvaluTypeBean implements java.io.Serializable {

	// Fields

	private String indexId;
	private String evaluType;


	// Constructors

	/** default constructor */
	public DiEvaluTypeBean() {
	}

	/** minimal constructor */
	public DiEvaluTypeBean(String indexId, String evaluType) {
		this.indexId = indexId;
		this.evaluType = evaluType;
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

	@Column(name = "EvaluType", nullable = false)
	public String getEvaluType() {
		return this.evaluType;
	}

	public void setEvaluType(String evaluType) {
		this.evaluType = evaluType;
	}

}