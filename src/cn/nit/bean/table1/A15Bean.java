package cn.nit.bean.table1;

import java.util.Date;

public class A15Bean {
	
	private int SeqNumber;
	/**国家级科研机构*/
	private int NationResNum;
	private double NationResRatio;
	/**省部级科研机构*/
	private int ProviResNum;
	private double ProviResRatio;
	/**市级科研机构*/
	private int CityResNum;
	private double CityResRatio;
	/**校级科研机构*/
    private int SchResNum;
    private double SchResRatio;
    
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
	public double getNationResRatio() {
		return NationResRatio;
	}
	public void setNationResRatio(double nationResRatio) {
		NationResRatio = nationResRatio;
	}
	public int getProviResNum() {
		return ProviResNum;
	}
	public void setProviResNum(int proviResNum) {
		ProviResNum = proviResNum;
	}
	public double getProviResRatio() {
		return ProviResRatio;
	}
	public void setProviResRatio(double proviResRatio) {
		ProviResRatio = proviResRatio;
	}
	public int getCityResNum() {
		return CityResNum;
	}
	public void setCityResNum(int cityResNum) {
		CityResNum = cityResNum;
	}
	public double getCityResRatio() {
		return CityResRatio;
	}
	public void setCityResRatio(double cityResRatio) {
		CityResRatio = cityResRatio;
	}
	public int getSchResNum() {
		return SchResNum;
	}
	public void setSchResNum(int schResNum) {
		SchResNum = schResNum;
	}
	public double getSchResRatio() {
		return SchResRatio;
	}
	public void setSchResRatio(double schResRatio) {
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
