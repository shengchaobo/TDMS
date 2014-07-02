package cn.nit.bean.table7;

import java.util.Date;

public class T735_Bean {
	
	private int SeqNumber;
	
	private String TeaUnit;
	
	private String UnitID;
	
	private String AssessResult;
	
	private String AssessYear;
	
	private Date Time;
	
	private String Note;
	 
	private String FillTeaID ;
		
    private String FillUnitID ;
				
	/**  审核单位ID号  */
	private String audit ;

	public int getSeqNumber() {
		return SeqNumber;
	}

	public void setSeqNumber(int seqNumber) {
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

	public String getAssessResult() {
		return AssessResult;
	}

	public void setAssessResult(String assessResult) {
		AssessResult = assessResult;
	}


	public String getAssessYear() {
		return AssessYear;
	}

	public void setAssessYear(String assessYear) {
		AssessYear = assessYear;
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

	public String getAudit() {
		return audit;
	}

	public void setAudit(String audit) {
		this.audit = audit;
	}
	
	
	

}
