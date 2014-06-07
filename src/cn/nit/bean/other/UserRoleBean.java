package cn.nit.bean.other;

/**
 * 保存到session中的用户相关信息
 * @author lenovo
 *
 */
public class UserRoleBean {

	/**  教职工Id号  */
	private String TeaID ;

	private String TeaPasswd ;
	
	/**  教职工姓名  */
	private String TeaName ;
	
	/**  教师部门ID号  */
	private String FromOffice ;
	
	/**  教师电子邮箱  */
	private String TeaEmail ;
	
	/**  用户角色  */
	private String roleId ;

	public String getTeaID() {
		return TeaID;
	}

	public void setTeaID(String teaID) {
		TeaID = teaID;
	}

	public String getTeaPasswd() {
		return TeaPasswd;
	}

	public void setTeaPasswd(String teaPasswd) {
		TeaPasswd = teaPasswd;
	}

	public String getTeaName() {
		return TeaName;
	}

	public void setTeaName(String teaName) {
		TeaName = teaName;
	}

	public String getFromOffice() {
		return FromOffice;
	}

	public void setFromOffice(String fromOffice) {
		FromOffice = fromOffice;
	}

	public String getTeaEmail() {
		return TeaEmail;
	}

	public void setTeaEmail(String teaEmail) {
		TeaEmail = teaEmail;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
