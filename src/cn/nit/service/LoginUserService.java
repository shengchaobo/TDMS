/* 
* @Title: LoginUserService.java
* @Package cn.bjtu.service
* @Description 
* @author Lei Xia
*      Email: xialei199023@163.com
* @copyright BJTU(C)2014
* @date 2014-4-18 下午10:39:20
* @version V1.0 
*/
package cn.nit.service;

import java.util.List;

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.other.UserRoleBean;
import cn.nit.dao.UserManagerDAO;
import cn.nit.dbconnection.DBConnection;
import cn.nit.pojo.LoginUser;
import cn.nit.util.MD5Util;

/**
 * 登录Serverice类
 * @author Lei Xia
 * @time: 2014-4-18/下午10:39:20
 */
public class LoginUserService {
	
	private UserManagerDAO userManagerDao = new UserManagerDAO() ;
	public List<UserinfoBean> login(LoginUser user){
		
		List<UserinfoBean> list = userManagerDao.getUserById(user.getUserId()) ;
		return list ;
	}
	
	public List<UserRoleBean> getUserRoleById(String TeaID){
		return userManagerDao.getUserRoleById(TeaID) ;
	}
	
}
