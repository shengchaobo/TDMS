package cn.nit.pojo.table1;

import java.util.Date;

public class T12POJO {
	
	private String UnitID;
	private String UnitName;
//	private String UnitType;
//	/**单位职能*/
	private String Functions;
	/**单位负责人*/
	private String Leader;
	private String TeaID;
	
	private String Note;
	
	
	public String getUnitID() {
		return UnitID;
	}
	public void setUnitID(String unitID) {
		UnitID = unitID;
	}
	public String getUnitName() {
		return UnitName;
	}
	public void setUnitName(String unitName) {
		UnitName = unitName;
	}
//	public String getUnitType() {
//		return UnitType;
//	}
//	public void setUnitType(String unitType) {
//		UnitType = unitType;
//	}
	public String getFunctions() {
		return Functions;
	}
	public void setFunctions(String functions) {
		Functions = functions;
	}
	public String getLeader() {
		return Leader;
	}
	public void setLeader(String leader) {
		Leader = leader;
	}
	public String getTeaID() {
		return TeaID;
	}
	public void setTeaID(String teaID) {
		TeaID = teaID;
	}
	
	public String getNote() {
		return Note;
	}
	public void setNote(String note) {
		Note = note;
	}
	
	

}
