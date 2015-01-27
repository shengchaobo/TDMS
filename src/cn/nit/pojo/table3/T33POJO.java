package cn.nit.pojo.table3;

import java.util.Date;

public class T33POJO {
	
	private int SeqNumber;
	
	private String TeaUnit;
	
	private String UnitID;
	
	private String MajorName;
	
	private String MajorID;
	
	private String MajorFieldName;
	
	private Date AppvlSetTime;
	
	private Date FirstAdmisTime;
	
	private int MajorYearLimit;
	
	private boolean IsSepcialMajor;
	
	private boolean IsKeyMajor;
	
	private String MajorLeader;
	
	private boolean LIsFullTime;
	
	private String MajorChargeMan;
	
	private boolean CIsFullTime;
	
	private Date Time;
	
	private String Note;
	
	private int CheckState;
	
	public int getSeqNumber() {
		return SeqNumber;
	}

	public void setSeqNumber(int SeqNumber) {
		this.SeqNumber = SeqNumber;
	}
	
	public String getTeaUnit() {
		return TeaUnit;
	}

	public void setTeaUnit(String TeaUnit) {
		this.TeaUnit = TeaUnit;
	}
	
	public String getMajorName() {
		return MajorName;
	}

	public void setMajorName(String MajorName) {
		this.MajorName = MajorName;
	}
	
	public String getUnitID() {
		return UnitID;
	}

	public void setUnitID(String UnitID) {
		this.UnitID = UnitID;
	}

	public String getMajorID() {
		return MajorID;
	}

	public void setMajorID(String MajorID) {
		this.MajorID = MajorID;
	}

	public String getMajorFieldName() {
		return MajorFieldName;
	}

	public void setMajorFieldName(String MajorFieldName) {
		this.MajorFieldName = MajorFieldName;
	}

	public Date getAppvlSetTime() {
		return AppvlSetTime;
	}

	public void setAppvlSetTime(Date AppvlSetTime) {
		this.AppvlSetTime = AppvlSetTime;
	}
	
	public Date getFirstAdmisTime() {
		return FirstAdmisTime;
	}

	public void setFirstAdmisTime(Date FirstAdmisTime) {
		this.FirstAdmisTime = FirstAdmisTime;
	}

	public int getMajorYearLimit() {
		return MajorYearLimit;
	}

	public void setMajorYearLimit(int MajorYearLimit) {
		this.MajorYearLimit = MajorYearLimit;
	}
	
	public boolean getIsSepcialMajor() {
		return IsSepcialMajor;
	}

	public void setIsSepcialMajor(boolean IsSepcialMajor) {
		this.IsSepcialMajor = IsSepcialMajor;
	}

	public boolean getIsKeyMajor() {
		return IsKeyMajor;
	}

	public void setIsKeyMajor(boolean IsKeyMajor) {
		this.IsKeyMajor = IsKeyMajor;
	}
	
	public String getMajorLeader() {
		return MajorLeader;
	}

	public void setMajorLeader(String MajorLeader) {
		this.MajorLeader = MajorLeader;
	}
	
	public boolean getLIsFullTime() {
		return LIsFullTime;
	}

	public void setLIsFullTime(boolean LIsFullTime) {
		this.LIsFullTime = LIsFullTime;
	}

	public String getMajorChargeMan() {
		return MajorChargeMan;
	}

	public void setMajorChargeMan(String MajorChargeMan) {
		this.MajorChargeMan = MajorChargeMan;
	}
	
	public boolean getCIsFullTime() {
		return CIsFullTime;
	}

	public void setCIsFullTime(boolean CIsFullTime) {
		this.CIsFullTime = CIsFullTime;
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
