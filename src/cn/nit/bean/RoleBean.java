package cn.nit.bean;

/**
 * 用户角色的实体类
 * @author lenovo
 *
 */
public class RoleBean {

	/**  角色编号  */
	private String RoleID ;
	
	/**  角色名称   */
	private String RoleName ;
	
	/**  所属教研室  */
	private String UnitName ;
	
	/**  角色描述  */
	private String RoleDest ;

	public String getRoleID() {
		return RoleID;
	}

	public void setRoleID(String RoleID) {
		this.RoleID = RoleID;
	}

	public String getRoleName() {
		return RoleName;
	}

	public void setRoleName(String RoleName) {
		this.RoleName = RoleName;
	}

	public String getUnitName() {
		return UnitName;
	}

	public void setUnitName(String UnitName) {
		this.UnitName = UnitName;
	}

	public String getRoleDest() {
		return RoleDest;
	}

	public void setRoleDest(String RoleDest) {
		this.RoleDest = RoleDest;
	}
	
	
}
