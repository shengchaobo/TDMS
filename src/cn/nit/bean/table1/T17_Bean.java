package cn.nit.bean.table1;

import java.util.Date;

public class T17_Bean {
	
	/**校友会（党员办）*/
	private int SeqNumber;//序号
	private String ClubName ;//校友会名称
	private Date BuildYear;//设立时间
	private String Place;//地点
	private int CheckState;//审核状态
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
	public void setClubName(String ClubName) {
		this.ClubName = ClubName;
	}
	
	public String getPlace() {
		return Place;
	}
	public void setPlace(String Place) {
		this.Place = Place;
	}
	public Date getBuildYear() {
		return BuildYear;
	}
	public void setBuildYear(Date BuildYear) {
		this.BuildYear = BuildYear;
	}
	public int getCheckState() {
		return CheckState;
	}
	public void setCheckState(int checkState) {
		CheckState = checkState;
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
	public void setNote(String Note) {
		this.Note = Note;
	}
}
