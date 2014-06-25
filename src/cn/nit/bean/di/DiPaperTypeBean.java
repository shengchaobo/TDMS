package cn.nit.bean.di;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DiPaperType entity. @author MyEclipse Persistence Tools
 */

@Table(name = "DiPaperType", schema = "dbo", catalog = "TDMS")
public class DiPaperTypeBean implements java.io.Serializable {

	// Fields

	private String indexId;
	private String paperType;

	// Constructors

	/** default constructor */
	public DiPaperTypeBean() {
	}

	/** full constructor */
	public DiPaperTypeBean(String indexId, String paperType) {
		this.indexId = indexId;
		this.paperType = paperType;
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

	@Column(name = "PaperType", nullable = false)
	public String getPaperType() {
		return this.paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

}