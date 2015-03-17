package cn.nit.bean.table1;

import java.util.Date;

/**表T16办学指导思想*/
public class T16_Bean {
	
	private int SeqNumber;
	private String Item;
	private String Contents;
	private Date Time;
	private String Note;
	
	public int getSeqNumber() {
		return SeqNumber;
	}

	public void setSeqNumber(int seqNumber) {
		SeqNumber = seqNumber;
	}

	public String getItem() {
		return Item;
	}

	public void setItem(String item) {
		Item = item;
	}

	public String getContents() {
		return Contents;
	}

	public void setContents(String contents) {
		Contents = contents;
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

	public static void main(String arg[])
	{
		Date ti=new Date();
		String str=ti.toString();
		String str1=str.substring(str.length()-4, str.length()) ;
		System.out.println(str1);
	}
	

}
