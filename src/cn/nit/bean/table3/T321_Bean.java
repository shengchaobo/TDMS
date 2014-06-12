package cn.nit.bean.table3;

import java.util.Date;

public class T321_Bean {
	
	private int SeqNumber;
	private String MainClassName;
	private String MainClassID;
	private int ByPassTime;
	private String MajorNameInSch;
	private String MajorID;
	private String UnitName;
	private String UintID;
	private Date Time;
	private String Note;
	
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
	
	public String getUintID() {
		return UintID;
	}

	public void setUintID(String UintID) {
		this.UintID = UintID;
	}
	
	public Date getTime(){
		return Time;
	}
	
	public void setTime(Date Time){
		this.Time=Time;
	}
	
	public String getNote(){
		return Note;
	}
	
	public void setNote(String Note){
		this.Note=Note;
	}
	
	

}
