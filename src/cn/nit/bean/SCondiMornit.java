package cn.nit.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SCondiMornit entity. @author MyEclipse Persistence Tools
 */

public class SCondiMornit implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private Integer undergraNum;
	private Integer fulltimeStuNum;
	private Double undergraRatio;
	private Integer totalStuNum;
	private Integer inNum;
	private Integer outNum;
	private Double inOutRatio;
	private Integer minorNum;
	private Double minorNumRatio;
	private Double graduRatio;
	private Double degreeRatio;
	private Double stuEmployRatio;
	private Integer fullTimeTeachNum;
	private Double graduDegreeRatio;
	private Double adminLevelRatio;
	private Integer outHireTeaNum;
	private Integer teacherNum;
	private Double stuTeaRatio;
	private Integer totalFieldNum;
	private Integer totalScoreNum;
	private Double praRatio;
	private Double optionRatio;
	private Double profNumRatio;
	private Double profCourseRatio;
	private Double areaPerStu;
	private Double housePerStu;
	private Double labPerStu;
	private Double dormPerStu;
	private Double equPerStu;
	private Double newEquRatio;
	private Double booksPerStu;
	private Double inbooksPerStu;
	private Double comPerStu;
	private Double mediaPerStu;
	private Double teachFeePerStu;
	private Double labFeePerStu;
	private Double practiceFeePerStu;
	private String note;
	private Date time;

	// Property accessors
	@Id
	@Column(name = "SeqNumber", unique = true, nullable = false)
	public Integer getSeqNumber() {
		return this.seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	@Column(name = "UndergraNum")
	public Integer getUndergraNum() {
		return this.undergraNum;
	}

	public void setUndergraNum(Integer undergraNum) {
		this.undergraNum = undergraNum;
	}

	@Column(name = "FulltimeStuNum")
	public Integer getFulltimeStuNum() {
		return this.fulltimeStuNum;
	}

	public void setFulltimeStuNum(Integer fulltimeStuNum) {
		this.fulltimeStuNum = fulltimeStuNum;
	}

	@Column(name = "UndergraRatio", precision = 18)
	public Double getUndergraRatio() {
		return this.undergraRatio;
	}

	public void setUndergraRatio(Double undergraRatio) {
		this.undergraRatio = undergraRatio;
	}

	@Column(name = "TotalStuNum")
	public Integer getTotalStuNum() {
		return this.totalStuNum;
	}

	public void setTotalStuNum(Integer totalStuNum) {
		this.totalStuNum = totalStuNum;
	}

	@Column(name = "InNum")
	public Integer getInNum() {
		return this.inNum;
	}

	public void setInNum(Integer inNum) {
		this.inNum = inNum;
	}

	@Column(name = "OutNum")
	public Integer getOutNum() {
		return this.outNum;
	}

	public void setOutNum(Integer outNum) {
		this.outNum = outNum;
	}

	@Column(name = "InOutRatio", precision = 18)
	public Double getInOutRatio() {
		return this.inOutRatio;
	}

	public void setInOutRatio(Double inOutRatio) {
		this.inOutRatio = inOutRatio;
	}

	@Column(name = "MinorNum")
	public Integer getMinorNum() {
		return this.minorNum;
	}

	public void setMinorNum(Integer minorNum) {
		this.minorNum = minorNum;
	}

	@Column(name = "MinorNumRatio", precision = 18)
	public Double getMinorNumRatio() {
		return this.minorNumRatio;
	}

	public void setMinorNumRatio(Double minorNumRatio) {
		this.minorNumRatio = minorNumRatio;
	}

	@Column(name = "GraduRatio", precision = 18)
	public Double getGraduRatio() {
		return this.graduRatio;
	}

	public void setGraduRatio(Double graduRatio) {
		this.graduRatio = graduRatio;
	}

	@Column(name = "DegreeRatio", precision = 18)
	public Double getDegreeRatio() {
		return this.degreeRatio;
	}

	public void setDegreeRatio(Double degreeRatio) {
		this.degreeRatio = degreeRatio;
	}

	@Column(name = "StuEmployRatio", precision = 18)
	public Double getStuEmployRatio() {
		return this.stuEmployRatio;
	}

	public void setStuEmployRatio(Double stuEmployRatio) {
		this.stuEmployRatio = stuEmployRatio;
	}

	@Column(name = "GraduDegreeRatio", precision = 18)
	public Double getGraduDegreeRatio() {
		return this.graduDegreeRatio;
	}

	public void setGraduDegreeRatio(Double graduDegreeRatio) {
		this.graduDegreeRatio = graduDegreeRatio;
	}

	@Column(name = "AdminLevelRatio", precision = 18)
	public Double getAdminLevelRatio() {
		return this.adminLevelRatio;
	}

	public void setAdminLevelRatio(Double adminLevelRatio) {
		this.adminLevelRatio = adminLevelRatio;
	}

	@Column(name = "OutHireTeaNum")
	public Integer getOutHireTeaNum() {
		return this.outHireTeaNum;
	}

	public void setOutHireTeaNum(Integer outHireTeaNum) {
		this.outHireTeaNum = outHireTeaNum;
	}

	@Column(name = "TeacherNum")
	public Integer getTeacherNum() {
		return this.teacherNum;
	}

	public void setTeacherNum(Integer teacherNum) {
		this.teacherNum = teacherNum;
	}

	@Column(name = "StuTeaRatio", precision = 18)
	public Double getStuTeaRatio() {
		return this.stuTeaRatio;
	}

	public void setStuTeaRatio(Double stuTeaRatio) {
		this.stuTeaRatio = stuTeaRatio;
	}

	@Column(name = "TotalFieldNum")
	public Integer getTotalFieldNum() {
		return this.totalFieldNum;
	}

	public void setTotalFieldNum(Integer totalFieldNum) {
		this.totalFieldNum = totalFieldNum;
	}

	@Column(name = "TotalScoreNum")
	public Integer getTotalScoreNum() {
		return this.totalScoreNum;
	}

	public void setTotalScoreNum(Integer totalScoreNum) {
		this.totalScoreNum = totalScoreNum;
	}

	@Column(name = "PraRatio", precision = 18)
	public Double getPraRatio() {
		return this.praRatio;
	}

	public void setPraRatio(Double praRatio) {
		this.praRatio = praRatio;
	}

	@Column(name = "OptionRatio", precision = 18)
	public Double getOptionRatio() {
		return this.optionRatio;
	}

	public void setOptionRatio(Double optionRatio) {
		this.optionRatio = optionRatio;
	}

	@Column(name = "ProfNumRatio", precision = 18)
	public Double getProfNumRatio() {
		return this.profNumRatio;
	}

	public void setProfNumRatio(Double profNumRatio) {
		this.profNumRatio = profNumRatio;
	}

	@Column(name = "ProfCourseRatio", precision = 18)
	public Double getProfCourseRatio() {
		return this.profCourseRatio;
	}

	public void setProfCourseRatio(Double profCourseRatio) {
		this.profCourseRatio = profCourseRatio;
	}

	@Column(name = "AreaPerStu", precision = 18)
	public Double getAreaPerStu() {
		return this.areaPerStu;
	}

	public void setAreaPerStu(Double areaPerStu) {
		this.areaPerStu = areaPerStu;
	}

	@Column(name = "HousePerStu", precision = 18)
	public Double getHousePerStu() {
		return this.housePerStu;
	}

	public void setHousePerStu(Double housePerStu) {
		this.housePerStu = housePerStu;
	}

	@Column(name = "LabPerStu", precision = 18)
	public Double getLabPerStu() {
		return this.labPerStu;
	}

	public void setLabPerStu(Double labPerStu) {
		this.labPerStu = labPerStu;
	}

	@Column(name = "DormPerStu", precision = 18)
	public Double getDormPerStu() {
		return this.dormPerStu;
	}

	public void setDormPerStu(Double dormPerStu) {
		this.dormPerStu = dormPerStu;
	}

	@Column(name = "EquPerStu", precision = 18)
	public Double getEquPerStu() {
		return this.equPerStu;
	}

	public void setEquPerStu(Double equPerStu) {
		this.equPerStu = equPerStu;
	}

	@Column(name = "NewEquRatio", precision = 18)
	public Double getNewEquRatio() {
		return this.newEquRatio;
	}

	public void setNewEquRatio(Double newEquRatio) {
		this.newEquRatio = newEquRatio;
	}

	@Column(name = "BooksPerStu", precision = 18)
	public Double getBooksPerStu() {
		return this.booksPerStu;
	}

	public void setBooksPerStu(Double booksPerStu) {
		this.booksPerStu = booksPerStu;
	}

	@Column(name = "InbooksPerStu", precision = 18)
	public Double getInbooksPerStu() {
		return this.inbooksPerStu;
	}

	public void setInbooksPerStu(Double inbooksPerStu) {
		this.inbooksPerStu = inbooksPerStu;
	}

	@Column(name = "ComPerStu", precision = 18)
	public Double getComPerStu() {
		return this.comPerStu;
	}

	public void setComPerStu(Double comPerStu) {
		this.comPerStu = comPerStu;
	}

	@Column(name = "MediaPerStu", precision = 18)
	public Double getMediaPerStu() {
		return this.mediaPerStu;
	}

	public void setMediaPerStu(Double mediaPerStu) {
		this.mediaPerStu = mediaPerStu;
	}

	@Column(name = "TeachFeePerStu", precision = 18)
	public Double getTeachFeePerStu() {
		return this.teachFeePerStu;
	}

	public void setTeachFeePerStu(Double teachFeePerStu) {
		this.teachFeePerStu = teachFeePerStu;
	}

	@Column(name = "LabFeePerStu", precision = 18)
	public Double getLabFeePerStu() {
		return this.labFeePerStu;
	}

	public void setLabFeePerStu(Double labFeePerStu) {
		this.labFeePerStu = labFeePerStu;
	}

	@Column(name = "PracticeFeePerStu", precision = 18)
	public Double getPracticeFeePerStu() {
		return this.practiceFeePerStu;
	}

	public void setPracticeFeePerStu(Double practiceFeePerStu) {
		this.practiceFeePerStu = practiceFeePerStu;
	}

	@Column(name = "Note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Time", nullable = false, length = 10)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setFullTimeTeachNum(Integer fullTimeTeachNum) {
		this.fullTimeTeachNum = fullTimeTeachNum;
	}

	public Integer getFullTimeTeachNum() {
		return fullTimeTeachNum;
	}

}