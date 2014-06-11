package cn.nit.pojo.table1;

import java.util.Date;

/**表T181签订合作协议机构（教务处）*/
	public class T181POJO {
	
     
		private int SeqNumber;
		/**填表部门*/
		private String CooperInsName;
		/**合作机构类型*/
		private String CooperInsType;//
		/**合作机构级别*/
		private String CooperInsLevel;//
		private String CooperInsLevelID;
		
		/**签订协议时间*/
		private Date SignedTime;//
		/**我方单位*/
		private String UnitName;//
		/**单位号*/
		private String UnitID;//
		/**我方单位级别*/
		private String UnitLevel;//
		private String UnitLevelID;
		/**备注*/
		private String Note;
		public Date getTime() {
			return Time;
		}
		public void setTime(Date time) {
			Time = time;
		}
		private Date Time;
		
		
		public String getCooperInsLevelID() {
			return CooperInsLevelID;
		}
		public void setCooperInsLevelID(String cooperInsLevelID) {
			CooperInsLevelID = cooperInsLevelID;
		}
		public String getUnitLevelID() {
			return UnitLevelID;
		}
		public void setUnitLevelID(String unitLevelID) {
			UnitLevelID = unitLevelID;
		}
	
		
		public int getSeqNumber() {
			return SeqNumber;
		}
		public void setSeqNumber(int seqNumber) {
			SeqNumber = seqNumber;
		}
		public String getCooperInsName() {
			return CooperInsName;
		}
		public void setCooperInsName(String cooperInsName) {
			CooperInsName = cooperInsName;
		}
		public String getCooperInsType() {
			return CooperInsType;
		}
		public void setCooperInsType(String cooperInsType) {
			CooperInsType = cooperInsType;
		}
		public String getCooperInsLevel() {
			return CooperInsLevel;
		}
		public void setCooperInsLevel(String cooperInsLevel) {
			CooperInsLevel = cooperInsLevel;
		}
		public Date getSignedTime() {
			return SignedTime;
		}
		public void setSignedTime(Date signedTime) {
			SignedTime = signedTime;
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
		public String getUnitLevel() {
			return UnitLevel;
		}
		public void setUnitLevel(String unitLevel) {
			UnitLevel = unitLevel;
		}
		public String getNote() {
			return Note;
		}
		public void setNote(String note) {
			Note = note;
		}
	

}
