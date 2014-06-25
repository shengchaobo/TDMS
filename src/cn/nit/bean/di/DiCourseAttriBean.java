package cn.nit.bean.di;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DiCourseAttri entity. @author MyEclipse Persistence Tools
 */

public class DiCourseAttriBean implements java.io.Serializable {

	// Fields

	private String indexId;
	private String courseAttri;

	// Constructors

	/** default constructor */
	public DiCourseAttriBean() {
	}

	/** full constructor */
	public DiCourseAttriBean(String indexId, String courseAttri) {
		this.indexId = indexId;
		this.courseAttri = courseAttri;
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

	@Column(name = "CourseAttri", nullable = false)
	public String getCourseAttri() {
		return this.courseAttri;
	}

	public void setCourseAttri(String courseAttri) {
		this.courseAttri = courseAttri;
	}

}