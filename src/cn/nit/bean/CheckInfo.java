package cn.nit.bean;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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



}