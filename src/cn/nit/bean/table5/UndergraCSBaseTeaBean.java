/* 
* @Title: UndergraCSBaseTea.java
* @Package cn.bjtu.bean
* @Description 
* @author Lei Xia
*      Email: xialei199023@163.com
* @copyright BJTU(C)2014
* @date 2014-5-13 下午10:43:40
* @version V1.0 
*/
package cn.nit.bean.table5;

import java.util.Date;

/**
 * 对应表“T511_UndergraCSBase_Tea”
 * @author lenovo
 */
public class UndergraCSBaseTeaBean {
	
	private int SeqNumber ;
	
	private String CSName ;
	
	private String CSID ;
	
	private String CSUnit ;
	
	private String UnitID ;
	
	private String FromTeaResOffice ;
	
	private String TeaResOfficeID ;
	
	private String CSType ;
	
	private String CSNature ;
	
	private String State ;
	
	private String PubCSType ;
	
	private Date time ;
	
	private String Note ;
	
	private String FillTeaID ;
	
	private String FillUnitID ;
	
	/**  审核单位ID号  */
	private String audit ;

	public int getSeqNumber() {
		return SeqNumber;
	}

	public void setSeqNumber(int SeqNumber) {
		this.SeqNumber = SeqNumber;
	}

	public String getCSName() {
		return CSName;
	}

	public void setCSName(String CSName) {
		this.CSName = CSName;
	}

	public String getCSID() {
		return CSID;
	}

	public void setCSID(String CSID) {
		this.CSID = CSID;
	}

	public String getCSUnit() {
		return CSUnit;
	}

	public void setCSUnit(String CSUnit) {
		this.CSUnit = CSUnit;
	}

	public String getUnitID() {
		return UnitID;
	}

	public void setUnitID(String UnitID) {
		this.UnitID = UnitID;
	}

	public String getFromTeaResOffice() {
		return FromTeaResOffice;
	}

	public void setFromTeaResOffice(String FromTeaResOffice) {
		this.FromTeaResOffice = FromTeaResOffice;
	}

	public String getTeaResOfficeID() {
		return TeaResOfficeID;
	}

	public void setTeaResOfficeID(String TeaResOfficeID) {
		this.TeaResOfficeID = TeaResOfficeID;
	}

	public String getCSType() {
		return CSType;
	}

	public void setCSType(String CSType) {
		this.CSType = CSType;
	}

	public String getCSNature() {
		return CSNature;
	}

	public void setCSNature(String CSNature) {
		this.CSNature = CSNature;
	}

	public String getState() {
		return State;
	}

	public void setState(String State) {
		this.State = State;
	}

	public String getPubCSType() {
		return PubCSType;
	}

	public void setPubCSType(String PubCSType) {
		this.PubCSType = PubCSType;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String Note) {
		this.Note = Note;
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

	public String getAudit(){
		return this.audit ;
	}
	
	public void setAudit(String audit){
		this.audit = audit ;
	}
}
