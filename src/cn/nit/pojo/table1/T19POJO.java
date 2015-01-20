package cn.nit.pojo.table1;

import java.util.Date;

public class T19POJO {
	
	private int SeqNumber;
	private String RewardName;//奖励名称
	private String RewardLevel;//级别
	private String RewardLevelID;
	private String RewardFromUnit;//授予单位
	private String UnitName;//获奖单位
	private String UnitID;//单位号
	private Date RewardTime;//获奖时间
	private int CheckState;//s审核状态
	private String Note;
	private Date Time;
	
	
	public int getSeqNumber() {
		return SeqNumber;
	}
	public void setSeqNumber(int seqNumber) {
		SeqNumber = seqNumber;
	}
	public String getRewardName() {
		return RewardName;
	}
	public void setRewardName(String rewardName) {
		RewardName = rewardName;
	}
	public String getRewardLevel() {
		return RewardLevel;
	}
	public void setRewardLevel(String rewardLevel) {
		RewardLevel = rewardLevel;
	}
	public String getRewardLevelID() {
		return RewardLevelID;
	}
	public void setRewardLevelID(String rewardLevelID) {
		RewardLevelID = rewardLevelID;
	}
	
	public String getRewardFromUnit() {
		return RewardFromUnit;
	}
	public void setRewardFromUnit(String rewardFromUnit) {
		RewardFromUnit = rewardFromUnit;
	}
	public String getUnitName() {
		return UnitName;
	}
	public void setUnitName(String unitName) {
		UnitName = unitName;
	}
	public String getUnitID() {
		return UnitID;
	}
	public void setUnitID(String unitID) {
		UnitID = unitID;
	}
	public Date getRewardTime() {
		return RewardTime;
	}
	public void setRewardTime(Date rewardTime) {
		RewardTime = rewardTime;
	}
	public String getNote() {
		return Note;
	}
	public void setNote(String note) {
		Note = note;
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
	
	

}
