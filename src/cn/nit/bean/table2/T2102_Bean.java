package cn.nit.bean.table2;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T2102JobEduCouserAdmission entity. @author MyEclipse Persistence Tools
 */
@Table(name = "T2102_JobEduCouser_Admission$", schema = "dbo", catalog = "TDMS")
public class T2102_Bean implements
		java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private Integer courseNum;
	private Integer courseTimes;
	private Integer teaNum;
	private Integer stuNum;
	private Date time;
	private String note;

	// Property accessors
	@Id
	@Column(name = "SeqNumber", unique = true, nullable = false)
	public Integer getSeqNumber() {
		return this.seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	@Column(name = "CourseNum")
	public Integer getCourseNum() {
		return this.courseNum;
	}

	public void setCourseNum(Integer courseNum) {
		this.courseNum = courseNum;
	}

	@Column(name = "CourseTimes")
	public Integer getCourseTimes() {
		return this.courseTimes;
	}

	public void setCourseTimes(Integer courseTimes) {
		this.courseTimes = courseTimes;
	}

	@Column(name = "TeaNum")
	public Integer getTeaNum() {
		return this.teaNum;
	}

	public void setTeaNum(Integer teaNum) {
		this.teaNum = teaNum;
	}

	@Column(name = "StuNum")
	public Integer getStuNum() {
		return this.stuNum;
	}

	public void setStuNum(Integer stuNum) {
		this.stuNum = stuNum;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Time", length = 10)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "Note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}