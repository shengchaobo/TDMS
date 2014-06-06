/* 
* @Title: LoginUser.java
* @Package cn.bjtu.pojo
* @Description 
* @author Lei Xia
*      Email: xialei199023@163.com
* @copyright BJTU(C)2014
* @date 2014-4-18 下午10:41:34
* @version V1.0 
*/
package cn.nit.pojo;

/**
 * 
 * @author Lei Xia
 * @time: 2014-4-18/下午10:41:34
 */
public class LoginUser {
	
	/**  用户编号  */
	private String userId ;
	
	/**  用户密码  */
	private String password ;

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
