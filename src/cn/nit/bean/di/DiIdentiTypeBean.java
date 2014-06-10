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
 * DiIdentiType entity. @author MyEclipse Persistence Tools
 */

@Table(name = "DiIdentiType", schema = "dbo", catalog = "TDMS")
public class DiIdentiTypeBean implements java.io.Serializable {

	// Fields

	private String indexId;
	private String identiType;


	@Column(name = "IndexID", unique = true, nullable = false)
	public String getIndexId() {
		return this.indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	@Column(name = "IdentiType", nullable = false)
	public String getIdentiType() {
		return this.identiType;
	}

	public void setIdentiType(String identiType) {
		this.identiType = identiType;
	}
}