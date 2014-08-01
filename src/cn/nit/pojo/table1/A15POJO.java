package cn.nit.pojo.table1;

import java.util.Date;

public class A15POJO {
	
	private int SeqNumber;
	/**国家级科研机构*/
	private int NationResNum;
	private String NationResRatio;
	/**省部级科研机构*/
	private int ProviResNum;
	private String ProviResRatio;
	/**市级科研机构*/
	private int CityResNum;
	private String CityResRatio;
	/**校级科研机构*/
    private int SchResNum;
    private String SchResRatio;
    
    
    /**总计*/
    private int SumResNum;
    private Date Time; 
    private String Note;
    
	public int getSeqNumber() {
		return SeqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		SeqNumber = seqNumber;
	}
	public int getNationResNum() {
		return NationResNum;
	}
	public void setNationResNum(int nationResNum) {
		NationResNum = nationResNum;
	}
	public String getNationResRatio() {
		return NationResRatio;
	}
	public void setNationResRatio(String nationResRatio) {
		NationResRatio = nationResRatio;
	}
	public int getProviResNum() {
		return ProviResNum;
	}
	public void setProviResNum(int proviResNum) {
		ProviResNum = proviResNum;
	}
	public String getProviResRatio() {
		return ProviResRatio;
	}
	public void setProviResRatio(String proviResRatio) {
		ProviResRatio = proviResRatio;
	}
	public int getCityResNum() {
		return CityResNum;
	}
	public void setCityResNum(int cityResNum) {
		CityResNum = cityResNum;
	}
	public String getCityResRatio() {
		return CityResRatio;
	}
	public void setCityResRatio(String cityResRatio) {
		CityResRatio = cityResRatio;
	}
	public int getSchResNum() {
		return SchResNum;
	}
	public void setSchResNum(int schResNum) {
		SchResNum = schResNum;
	}
	public String getSchResRatio() {
		return SchResRatio;
	}
	public void setSchResRatio(String schResRatio) {
		SchResRatio = schResRatio;
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
	public int getSumResNum() {
		return SumResNum;
	}
	public void setSumResNum(int sumResNum) {
		SumResNum = sumResNum;
	}

}
