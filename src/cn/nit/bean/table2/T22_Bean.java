package cn.nit.bean.table2;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * T22FloorAreaLog entity. @author MyEclipse Persistence Tools
 */
@Table(name = "T22_FloorArea_Log$", schema = "dbo", catalog = "TDMS")
public class T22_Bean implements java.io.Serializable {

	// Fields

	private Integer seqNumber;
	private Double admOfficeArea;
	private Integer admOfficeNum;
	private Double libArea;
	private Integer libNum;
	private Double libRoomArea;
	private Integer libRoomSitNum;
	private Double museumArea;
	private Integer museumNum;
	private Double schHisHallArea;
	private Integer schHisHallNum;
	private Double gymArea;
	private Integer gymNum;
	private Double sportArea;
	private Integer sportNum;
	private Double stuCenterArea;
	private Integer stuCenterNum;
	private Double hallArea;
	private Integer hallNum;
	private Double stuCanteenArea;
	private Integer stuCanteenNum;
	private Double stuDormiArea;
	private Integer stuDormiNum;
	private Double otherArea;
	private Integer otherNum;
	private Date time;
	private String note;
	private int checkState;


	// Property accessors
	@Id
	@Column(name = "SeqNumber", unique = true, nullable = false)
	public Integer getSeqNumber() {
		return this.seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	@Column(name = "AdmOfficeArea", precision = 18)
	public Double getAdmOfficeArea() {
		return this.admOfficeArea;
	}

	public void setAdmOfficeArea(Double admOfficeArea) {
		this.admOfficeArea = admOfficeArea;
	}

	@Column(name = "AdmOfficeNum")
	public Integer getAdmOfficeNum() {
		return this.admOfficeNum;
	}

	public void setAdmOfficeNum(Integer admOfficeNum) {
		this.admOfficeNum = admOfficeNum;
	}

	@Column(name = "LibArea", precision = 18)
	public Double getLibArea() {
		return this.libArea;
	}

	public void setLibArea(Double libArea) {
		this.libArea = libArea;
	}

	@Column(name = "LibNum")
	public Integer getLibNum() {
		return this.libNum;
	}

	public void setLibNum(Integer libNum) {
		this.libNum = libNum;
	}

	@Column(name = "LibRoomArea", precision = 18)
	public Double getLibRoomArea() {
		return this.libRoomArea;
	}

	public void setLibRoomArea(Double libRoomArea) {
		this.libRoomArea = libRoomArea;
	}

	@Column(name = "LibRoomSitNum")
	public Integer getLibRoomSitNum() {
		return this.libRoomSitNum;
	}

	public void setLibRoomSitNum(Integer libRoomSitNum) {
		this.libRoomSitNum = libRoomSitNum;
	}

	@Column(name = "MuseumArea", precision = 18)
	public Double getMuseumArea() {
		return this.museumArea;
	}

	public void setMuseumArea(Double museumArea) {
		this.museumArea = museumArea;
	}

	@Column(name = "MuseumNum")
	public Integer getMuseumNum() {
		return this.museumNum;
	}

	public void setMuseumNum(Integer museumNum) {
		this.museumNum = museumNum;
	}

	@Column(name = "SchHisHallArea", precision = 18)
	public Double getSchHisHallArea() {
		return this.schHisHallArea;
	}

	public void setSchHisHallArea(Double schHisHallArea) {
		this.schHisHallArea = schHisHallArea;
	}

	@Column(name = "SchHisHallNum")
	public Integer getSchHisHallNum() {
		return this.schHisHallNum;
	}

	public void setSchHisHallNum(Integer schHisHallNum) {
		this.schHisHallNum = schHisHallNum;
	}

	@Column(name = "GymArea", precision = 18)
	public Double getGymArea() {
		return this.gymArea;
	}

	public void setGymArea(Double gymArea) {
		this.gymArea = gymArea;
	}

	@Column(name = "GymNum")
	public Integer getGymNum() {
		return this.gymNum;
	}

	public void setGymNum(Integer gymNum) {
		this.gymNum = gymNum;
	}

	@Column(name = "SportArea", precision = 18)
	public Double getSportArea() {
		return this.sportArea;
	}

	public void setSportArea(Double sportArea) {
		this.sportArea = sportArea;
	}

	@Column(name = "SportNum")
	public Integer getSportNum() {
		return this.sportNum;
	}

	public void setSportNum(Integer sportNum) {
		this.sportNum = sportNum;
	}

	@Column(name = "StuCenterArea", precision = 18)
	public Double getStuCenterArea() {
		return this.stuCenterArea;
	}

	public void setStuCenterArea(Double stuCenterArea) {
		this.stuCenterArea = stuCenterArea;
	}

	@Column(name = "StuCenterNum")
	public Integer getStuCenterNum() {
		return this.stuCenterNum;
	}

	public void setStuCenterNum(Integer stuCenterNum) {
		this.stuCenterNum = stuCenterNum;
	}

	@Column(name = "HallArea", precision = 18)
	public Double getHallArea() {
		return this.hallArea;
	}

	public void setHallArea(Double hallArea) {
		this.hallArea = hallArea;
	}

	@Column(name = "HallNum")
	public Integer getHallNum() {
		return this.hallNum;
	}

	public void setHallNum(Integer hallNum) {
		this.hallNum = hallNum;
	}

	@Column(name = "StuCanteenArea", precision = 18)
	public Double getStuCanteenArea() {
		return this.stuCanteenArea;
	}

	public void setStuCanteenArea(Double stuCanteenArea) {
		this.stuCanteenArea = stuCanteenArea;
	}

	@Column(name = "StuCanteenNum")
	public Integer getStuCanteenNum() {
		return this.stuCanteenNum;
	}

	public void setStuCanteenNum(Integer stuCanteenNum) {
		this.stuCanteenNum = stuCanteenNum;
	}

	@Column(name = "StuDormiArea", precision = 18)
	public Double getStuDormiArea() {
		return this.stuDormiArea;
	}

	public void setStuDormiArea(Double stuDormiArea) {
		this.stuDormiArea = stuDormiArea;
	}

	@Column(name = "StuDormiNum")
	public Integer getStuDormiNum() {
		return this.stuDormiNum;
	}

	public void setStuDormiNum(Integer stuDormiNum) {
		this.stuDormiNum = stuDormiNum;
	}

	@Column(name = "OtherArea", precision = 18)
	public Double getOtherArea() {
		return this.otherArea;
	}

	public void setOtherArea(Double otherArea) {
		this.otherArea = otherArea;
	}

	@Column(name = "OtherNum")
	public Integer getOtherNum() {
		return this.otherNum;
	}

	public void setOtherNum(Integer otherNum) {
		this.otherNum = otherNum;
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

	public void setCheckState(int checkState) {
		this.checkState = checkState;
	}

	public int getCheckState() {
		return checkState;
	}

}