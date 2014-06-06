package cn.nit.pojo.table1;

import java.util.Date;

public class T17POJO {
	
	/**校友会（党员办）*/
	private int SeqNumber;//序号
	private String ClubName ;//校友会名称
	private Date BuildYear;//设立时间
	private String Place;//地点
	private Date Time;
	private String Note;
	
	public int getSeqNumber() {
		return SeqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		SeqNumber = seqNumber;
	}
	public String getClubName() {
		return ClubName;
	}
	public void setClubName(String clubName) {
		ClubName = clubName;
	}
	public Date getBuildYear() {
		return BuildYear;
	}
	public void setBuildYear(Date buildYear) {
		BuildYear = buildYear;
	}
	public String getPlace() {
		return Place;
	}
	public void setPlace(String place) {
		Place = place;
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
