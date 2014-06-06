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
 * DiCourseCategories entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DiCourseCategories", schema = "dbo", catalog = "TDMS")
public class DiCourseCategoriesBean implements java.io.Serializable {

	// Fields

	private String indexId;
	private String courseCategories;

	// Constructors

	/** default constructor */
	public DiCourseCategoriesBean() {
	}

	/** minimal constructor */
	public DiCourseCategoriesBean(String indexId) {
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

	@Column(name = "CourseCategories")
	public String getCourseCategories() {
		return this.courseCategories;
	}

	public void setCourseCategories(String courseCategories) {
		this.courseCategories = courseCategories;
	}

}