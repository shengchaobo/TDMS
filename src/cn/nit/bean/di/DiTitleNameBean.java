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
 * DiTitleName entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DiTitleName", schema = "dbo", catalog = "TDMS")
public class DiTitleNameBean implements java.io.Serializable {

	// Fields

	private String indexId;
	private String titleName;

	// Constructors

	/** default constructor */
	public DiTitleNameBean() {
	}

	/** minimal constructor */
	public DiTitleNameBean(String indexId, String titleName) {
		this.indexId = indexId;
		this.titleName = titleName;
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

	@Column(name = "TitleName", nullable = false)
	public String getTitleName() {
		return this.titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
}