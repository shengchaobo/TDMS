package cn.nit.bean.table1;

import java.util.Date;

public class T152Bean {
	
	private int SeqNumber;
	private String ResInsName;//科研机构名称
	private String ResInsID;//单位号
	private String ResInsLevel;//科研单位级别
	private String Type;//类别
	private boolean BuildCondition;//共建情况:是
	private boolean BiOpen;//是否开放
	private String OpenCondition;//开放情况
	private String TeaUnit;//TeaUnit
	private String UnitID;//教学单位
	private Date BeginYear;//开设年份
	private double HouseArea;//面积
	private Date Time;
	private String Note;
	private String audit;
	private int position;
	
	public int getSeqNumber() {
		return SeqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		SeqNumber = seqNumber;
	}
	public String getResInsName() {
		return ResInsName;
	}
	public void setResInsName(String resInsName) {
		ResInsName = resInsName;
	}
	public String getResInsID() {
		return ResInsID;
	}
	public void setResInsID(String resInsID) {
		ResInsID = resInsID;
	}
public String getResInsLevel() {
		return ResInsLevel;
	}
	public void setResInsLevel(String resInsLevel) {
		ResInsLevel = resInsLevel;
	}
	//	public String getResInsLevel() {
//		return ResInsLevel;
//	}
//	public void setResInsLevel(String resInsLevel) {
//		ResInsLevel = resInsLevel;
//	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public boolean isBuildCondition() {
		return BuildCondition;
	}
	public void setBuildCondition(boolean buildCondition) {
		BuildCondition = buildCondition;
	}
	public boolean isBiOpen() {
		return BiOpen;
	}
	public void setBiOpen(boolean biOpen) {
		BiOpen = biOpen;
	}
	public String getOpenCondition() {
		return OpenCondition;
	}
	public void setOpenCondition(String openCondition) {
		OpenCondition = openCondition;
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
	public Date getBeginYear() {
		return BeginYear;
	}
	public void setBeginYear(Date beginYear) {
		BeginYear = beginYear;
	}
	public double getHouseArea() {
		return HouseArea;
	}
	public void setHouseArea(double houseArea) {
		HouseArea = houseArea;
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
	public String getAudit() {
		return audit;
	}
	public void setAudit(String audit) {
		this.audit = audit;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
}
