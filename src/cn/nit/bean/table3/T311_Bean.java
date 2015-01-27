
package cn.nit.bean.table3;

import java.util.Date;

public class T311_Bean {
	
	private Integer SeqNumber;
	
	private String PostDocStaName ;
	
	private  Date  SetTime ;
	
	private int ResearcherNum ;
	
	private String UnitName ;
	
	private String UnitID ;
	
	private Date Time;
	
	private String Note;
	
	private int CheckState;
	
	public Integer getSeqNumber() {
		return SeqNumber;
	}

	public void setSeqNumber(Integer SeqNumber) {
		this.SeqNumber = SeqNumber;
	}

	public String getPostDocStaName() {
		return PostDocStaName;
	}

	public void setPostDocStaName(String PostDocStaName) {
		this.PostDocStaName = PostDocStaName;
	}

	public Date getSetTime() {
		return SetTime;
	}

	public void setSetTime(Date SetTime) {
		this.SetTime = SetTime;
	}



	public int getResearcherNum() {
		return ResearcherNum;
	}

	public void setResearcherNum(int researcherNum) {
		ResearcherNum = researcherNum;
	}

	public String getUnitName() {
		return UnitName;
	}

	public void setUnitName(String UnitName) {
		this.UnitName = UnitName;
	}

	public String getUnitID() {
		return UnitID;
	}

	public void setUnitID(String UnitID) {
		this.UnitID = UnitID;
	}

	public Date getTime() {
		return Time;
	}

	public void setTime(Date Time) {
		this.Time = Time;
	}
	
	public int getCheckState() {
		return CheckState;
	}

	public void setCheckState(int checkState) {
		CheckState = checkState;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String Note) {
		this.Note = Note;
	}

}
