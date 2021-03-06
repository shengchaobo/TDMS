/* 
* @Title: LoginUserBean.java
* @Package cn.bjtu.bean
* @Description 
* @author Lei Xia
*      Email: xialei199023@163.com
* @copyright BJTU(C)2014
* @date 2014-4-18 下午09:09:46
* @version V1.0 
*/
package cn.nit.bean;

/**
 * 用户登录的实体类
 * @author lenovo
 *
 */
public class UserinfoBean {
	
	/**  自增长序号  */
	private Integer SeqNumber ;

	/**  教职工Id号  */
	private String TeaID ;

	/**  教职工姓名  */
	private String TeaName ;
	
	/**  教师登录密码  */
	private String TeaPasswd ;
	
	/**  教师部门名称  */
	private String FromOffice ;
	
	/**  教师电子邮箱  */
	private String TeaEmail ;
	
	/**  备注  */
	private String UserNote ;
	
	/**  部门编号  */
	private String UnitID ;
	
	/** 所属角色 **/
	private String RoleName;
	
	/** 所属角色 ID**/
	private String RoleID;
	
	/** 帐号是否被停用**/
	private boolean availability;


	public String getTeaID() {
		return TeaID;
	}

	public void setTeaID(String TeaID) {
		this.TeaID = TeaID;
	}

	public String getTeaName() {
		return this.TeaName;
	}

	public void setTeaName(String TeaName) {
		this.TeaName = TeaName;
	}

	public String getTeaPasswd() {
		return this.TeaPasswd;
	}

	public void setTeaPasswd(String TeaPasswd) {
		this.TeaPasswd = TeaPasswd;
	}

	public String getFromOffice() {
		return this.FromOffice;
	}

	public void setFromOffice(String FromOffice) {
		this.FromOffice = FromOffice;
	}

	public String getTeaEmail() {
		return this.TeaEmail;
	}

	public void setTeaEmail(String TeaEmail) {
		this.TeaEmail = TeaEmail;
	}

	public String getUserNote() {
		return UserNote;
	}

	public void setUserNote(String UserNote) {
		this.UserNote = UserNote;
	}

	public String getUnitID() {
		return UnitID;
	}

	public void setUnitID(String unitID) {
		UnitID = unitID;
	}

	public void setRoleID(String roleID) {
		RoleID = roleID;
	}

	public String getRoleID() {
		return RoleID;
	}

	public void setRoleName(String roleName) {
		RoleName = roleName;
	}

	public String getRoleName() {
		return RoleName;
	}

	public void setSeqNumber(Integer seqNumber) {
		SeqNumber = seqNumber;
	}

	public Integer getSeqNumber() {
		return SeqNumber;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public boolean isAvailability() {
		return availability;
	}


}
