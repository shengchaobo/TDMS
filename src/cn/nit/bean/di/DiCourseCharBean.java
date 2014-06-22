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
 * DiCourseChar entity. @author MyEclipse Persistence Tools
 */

public class DiCourseCharBean implements java.io.Serializable {

	// Fields

	private String indexId;
	private String courseChar;

	// Constructors

	/** default constructor */
	public DiCourseCharBean() {
	}

	/** minimal constructor */
	public DiCourseCharBean(String indexId, String courseChar) {
		this.indexId = indexId;
		this.courseChar = courseChar;
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

	@Column(name = "CourseChar", nullable = false)
	public String getCourseChar() {
		return this.courseChar;
	}

	public void setCourseChar(String courseChar) {
		this.courseChar = courseChar;
	}

}