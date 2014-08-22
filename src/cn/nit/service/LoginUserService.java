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
import cn.nit.dao.di.DIUserManagerDAO;
import cn.nit.pojo.LoginUser;

/**
 * 登录Serverice类
 * @author Lei Xia
 * @time: 2014-4-18/下午10:39:20
 */
public class LoginUserService {
	
	private DIUserManagerDAO userManagerDao = new DIUserManagerDAO() ;
	public List<UserinfoBean> login(LoginUser user){		
		List<UserinfoBean> list = userManagerDao.getUserById(user.getUserId()) ;
		return list ;
	}
	
	public List<UserinfoBean> getUserRoleById(String TeaID){
		return userManagerDao.getUserRoleById(TeaID) ;
	}
	
}
