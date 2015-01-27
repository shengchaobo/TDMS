package cn.nit.pojo.table3;

import java.util.Date;

public class T321POJO {
	
	private int SeqNumber;
	private String MainClassName;
	private String MainClassID;
	private int ByPassTime;
	private String MajorNameInSch;
	private String MajorID;
	private String UnitName;
	private String UnitID;
	private Date Time;
	private String Note;
	private int CheckState;
	
	
	public int getSeqNumber() {
		return SeqNumber;
	}

	public void setSeqNumber(int SeqNumber) {
		this.SeqNumber = SeqNumber;
	}
	
	public String getMainClassName() {
		return MainClassName;
	}

	public void setMainClassName(String MainClassName) {
		this.MainClassName = MainClassName;
	}
	
	public String getMainClassID() {
		return MainClassID;
	}

	public void setMainClassID(String MainClassID) {
		this.MainClassID = MainClassID;
	}
	
	public String getUnitName() {
		return UnitName;
	}

	public void setUnitName(String UnitName) {
		this.UnitName = UnitName;
	}
	
	public int getByPassTime() {
		return ByPassTime;
	}

	public void setByPassTime(int ByPassTime) {
		this.ByPassTime = ByPassTime;
	}
	
	public String getMajorNameInSch() {
		return MajorNameInSch;
	}

	public void setMajorNameInSch(String MajorNameInSch) {
		this.MajorNameInSch = MajorNameInSch;
	}

	public String getMajorID() {
		return MajorID;
	}

	public void setMajorID(String MajorID) {
		this.MajorID = MajorID;
	}
	
	public String getUnitID() {
		return UnitID;
	}

	public void setUnitID(String UnitID) {
		this.UnitID = UnitID;
	}
	
	public Date getTime(){
		return Time;
	}
	
	public void setTime(Date Time){
		this.Time=Time;
	}
	
	public int getCheckState() {
		return CheckState;
	}

	public void setCheckState(int checkState) {
		CheckState = checkState;
	}

	public String getNote(){
		return Note;
	}
	
	public void setNote(String Note){
		this.Note=Note;
	}

}
