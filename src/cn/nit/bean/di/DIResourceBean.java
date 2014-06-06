package cn.nit.bean.di;

/**
 * 
 * 数据审核表的实体类
 * @author lenovo
 *
 */
public class DIResourceBean {

	/**  自增长字段  */
	private int IndexID ;
	
	/**  审核表表名  */
	private String TableName ;
	
	/**  角色ID  */
	private String RoleID ;
	
	/**  审核单位  */
	private String RoleFirst ;
	
	/**  备注  */
	private String note ;

	public int getIndexID() {
		return IndexID;
	}

	public void setIndexID(int indexID) {
		IndexID = indexID;
	}

	public String getTableName() {
		return TableName;
	}

	public void setTableName(String tableName) {
		TableName = tableName;
	}

	public String getRoleID() {
		return RoleID;
	}

	public void setRoleID(String roleID) {
		RoleID = roleID;
	}

	public String getRoleFirst() {
		return RoleFirst;
	}

	public void setRoleFirst(String roleFirst) {
		RoleFirst = roleFirst;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
}
