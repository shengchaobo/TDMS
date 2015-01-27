package cn.nit.bean.table3;

import java.util.Date;

public class T312_Bean {

	private Integer SeqNumber;
	
	private String StaName ;
	
	private String StaID ;
	
	private String UnitName ;
	
	private String UnitID ;
	
	private String StaType;
	
	private Date Time;
	
	private String Note;
	
	private int CheckState;
	
	public Integer getSeqNumber() {
		return SeqNumber;
	}

	public void setSeqNumber(Integer SeqNumber) {
		this.SeqNumber = SeqNumber;
	}

	public String getStaName() {
		return StaName;
	}

	public void setStaName(String StaName) {
		this.StaName = StaName;
	}

	public String getStaID() {
		return StaID;
	}

	public void setStaID(String StaID) {
		this.StaID = StaID;
	}

	public String getUnitName() {
		return UnitName;
	}

	public void setUnitName(String UnitName) {
		this.UnitName = UnitName;
	}

	public String getStaType() {
		return StaType;
	}

	public void setStaType(String StaType) {
		this.StaType = StaType;
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
