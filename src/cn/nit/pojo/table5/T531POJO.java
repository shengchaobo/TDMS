package cn.nit.pojo.table5;

import java.util.Date;

public class T531POJO {
	
	private int SeqNumber;
	private String Name;
	private String Type;
	private String ItemLevel;
	private Date buildTime;
	private String TeaUnit;
	private String TeaUnitID;
	
	private int JoinStuNum;
	private String Note;
	private Date Time;
	
	public int getSeqNumber() {
		return SeqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		SeqNumber = seqNumber;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getItemLevel() {
		return ItemLevel;
	}
	public void setItemLevel(String itemLevel) {
		ItemLevel = itemLevel;
	}
	public Date getBuildTime() {
		return buildTime;
	}
	public void setBuildTime(Date buildTime) {
		this.buildTime = buildTime;
	}
	public String getTeaUnit() {
		return TeaUnit;
	}
	public void setTeaUnit(String teaUnit) {
		TeaUnit = teaUnit;
	}
	public String getTeaUnitID() {
		return TeaUnitID;
	}
	public void setTeaUnitID(String teaUnitID) {
		TeaUnitID = teaUnitID;
	}
	public int getJoinStuNum() {
		return JoinStuNum;
	}
	public void setJoinStuNum(int joinStuNum) {
		JoinStuNum = joinStuNum;
	}
	public String getNote() {
		return Note;
	}
	public void setNote(String note) {
		Note = note;
	}
	public Date getTime() {
		return Time;
	}
	public void setTime(Date time) {
		Time = time;
	}
	

}
