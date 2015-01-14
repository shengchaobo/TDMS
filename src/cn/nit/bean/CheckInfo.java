package cn.nit.bean;

import javax.persistence.Table;

/**
 * CheckInfo entity. @author MyEclipse Persistence Tools
 */
@Table(name = "CheckInfo", schema = "dbo", catalog = "TDMS")
public class CheckInfo implements java.io.Serializable {

	// Fields
	private String tableID;
	private int checkID;
	private String checkInfo;
	private int checkType;
	private String fillUnitID;
	
	public String getTableID() {
		return tableID;
	}
	public void setTableID(String tableID) {
		this.tableID = tableID;
	}

	public String getCheckInfo() {
		return checkInfo;
	}
	public void setCheckInfo(String checkInfo) {
		this.checkInfo = checkInfo;
	}
	public void setCheckID(int checkID) {
		this.checkID = checkID;
	}
	public int getCheckID() {
		return checkID;
	}
	public void setCheckType(int checkType) {
		this.checkType = checkType;
	}
	public int getCheckType() {
		return checkType;
	}
	public void setFillUnitID(String fillUnitID) {
		this.fillUnitID = fillUnitID;
	}
	public String getFillUnitID() {
		return fillUnitID;
	}



}