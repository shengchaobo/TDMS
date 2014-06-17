package cn.nit.pojo.table1;

import java.util.Date;

public class S17POJO {
	
	
	private int SeqNumber;
	/**校友会总数*/
	private int SumSchFriNum;
	/**境内校友会总数*/
	private int InlandNum;
	/**境外校友会总数*/
	private int OutlandNum;
	private Date Time;
	private String Note;
	public int getSeqNumber() {
		return SeqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		SeqNumber = seqNumber;
	}
	public int getSumSchFriNum() {
		return SumSchFriNum;
	}
	public void setSumSchFriNum(int sumSchFriNum) {
		SumSchFriNum = sumSchFriNum;
	}
	public int getInlandNum() {
		return InlandNum;
	}
	public void setInlandNum(int inlandNum) {
		InlandNum = inlandNum;
	}
	public int getOutlandNum() {
		return OutlandNum;
	}
	public void setOutlandNum(int outlandNum) {
		OutlandNum = outlandNum;
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
	

}
