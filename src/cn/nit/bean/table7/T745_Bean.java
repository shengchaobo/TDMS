package cn.nit.bean.table7;

import java.util.Date;

public class T745_Bean {
	
	private Integer SeqNumber;
	
	private String TeaUnit;
	
	private String UnitID;
	
	private String AssessYear;
	
	private String AssessResult;
	
	private String AppvlID;
	
	private Date Time;
	
	private String Note;
    
	private String FillTeaID ;
	
	private String FillUnitID ;
		
	/**  审核单位ID号  */
	private String audit ;
	private int CheckState;

	

	public Integer getSeqNumber() {
		return SeqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		SeqNumber = seqNumber;
	}

	public String getTeaUnit() {
		return TeaUnit;
	}

	public void setTeaUnit(String teaUnit) {
		TeaUnit = teaUnit;
	}

	public String getUnitID() {
		return UnitID;
	}

	public void setUnitID(String unitID) {
		UnitID = unitID;
	}

	public String getAssessYear() {
		return AssessYear;
	}

	public void setAssessYear(String assessYear) {
		AssessYear = assessYear;
	}

	public String getAssessResult() {
		return AssessResult;
	}

	public void setAssessResult(String assessResult) {
		AssessResult = assessResult;
	}

	public String getAppvlID() {
		return AppvlID;
	}

	public void setAppvlID(String appvlID) {
		AppvlID = appvlID;
	}

	public Date getTime() {
		return Time;
	}

	public void setTime(Date time) {
		Time = time;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	public String getFillTeaID() {
		return FillTeaID;
	}

	public void setFillTeaID(String fillTeaID) {
		FillTeaID = fillTeaID;
	}

	public String getFillUnitID() {
		return FillUnitID;
	}

	public void setFillUnitID(String fillUnitID) {
		FillUnitID = fillUnitID;
	}

	public int getCheckState() {
		return CheckState;
	}

	public void setCheckState(int checkState) {
		CheckState = checkState;
	}

	public String getAudit() {
		return audit;
	}

	public void setAudit(String audit) {
		this.audit = audit;
	}
	
	
	
	
	

}
