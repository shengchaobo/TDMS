package cn.nit.pojo.table3;

import java.util.Date;

public class T311POJO {
	
private int SeqNumber;
	
	private String PostDocStaName ;
	
	private  Date  SetTime ;
	
	private String ResearcherNum ;
	
	private String UnitName ;
	
	private String UnitID ;
	
	private Date Time;
	
	private String Note;
	
	private int CheckState;
	
	public int getSeqNumber() {
		return SeqNumber;
	}

	public void setSeqNumber(int SeqNumber) {
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

	public String getResearcherNum() {
		return ResearcherNum;
	}

	public void setResearcherNum(String ResearcherNum) {
		this.ResearcherNum = ResearcherNum;
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
