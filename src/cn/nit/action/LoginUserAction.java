/* 
* @Title: LoginUserAction.java
* @Package cn.bjtu.action
* @Description 
* @author Lei Xia
*      Email: xialei199023@163.com
* @copyright BJTU(C)2014
* @date 2014-4-18 下午09:17:31
* @version V1.0 
*/
package cn.nit.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;


import cn.nit.bean.UserinfoBean;
import cn.nit.pojo.LoginUser;
import cn.nit.service.LoginUserService;
import cn.nit.util.MD5Util;

/**
 * 
 * @author Lei Xia
 * @time: 2014-4-18/下午09:17:31
 */
public class LoginUserAction {
	
	/**  登录POJO类*/
	private LoginUser user ;
	
	private String redirect ;
	
	/**  日志记录  */
	private Logger logger = Logger.getLogger(LoginUserAction.class) ;
	
	/**  登录service类   */
	private LoginUserService loginService = new LoginUserService() ;
	
	/**
	 * 职工登录方法
	 *
	 * @time: 2014-4-21/下午04:47:08
	 */
	public void login(){
		
		List<UserinfoBean> list = loginService.getUserRoleById(user.getUserId()) ;
		HttpServletRequest request = ServletActionContext.getRequest() ;
		HttpServletResponse response = ServletActionContext.getResponse() ;
		response.setContentType("text/html;charset=UTF-8") ;
		PrintWriter out = null ;
		
		try{
			out = response.getWriter() ;
			boolean flag = validate() ;
			
			if(!flag){
				out.print("{success:false,msg:'登录信息存在不合法字符，请修改！！！'}") ;
				return ;
			}
			
			if(list == null || list.isEmpty()){
				out.print("{success:false,msg:'该职工号不存在！！！'}") ;
				logger.info("登录错误") ;
				return ;
			}else{
				UserinfoBean userinfo = list.get(0) ;
				String password = MD5Util.encode(user.getPassword()) ;
				
				if(userinfo.isAvailability()==true){
					out.print("{success:false,msg:'该帐号被停用，如有疑问，请联系系统管理员！！！'}") ;
					return ;
				}
				
				if(userinfo.getTeaPasswd().equals(password)){
					request.getSession().setAttribute("userinfo", userinfo) ;
					out.print("{success:true,url:'" + redirect +"'}") ;
//					return ;
				}else{
					out.print("{success:false,msg:'密码错误！！！'}") ;
					return ;
				}
			}
			
		}catch(Exception e){
			e.printStackTrace() ;
			logger.info(e.getMessage()) ;
			out.print("{success:false,msg:'后台发生异常，请联系管理员！！！'}") ;
			return ;
		}finally{
			if(out != null){
				out.flush() ;
				out.close() ;
			}
		}
	}
	
	/**
	 * 防止SQL注入
	 * @return
	 *
	 * @time: 2014-4-21/上午11:24:51
	 */
	private boolean validate(){
		String validates[] = {"or", "and", "where"} ;
		
		for(String validate : validates){
			if(user.getPassword().contains(validate)){
				return false ;
			}
		}
		
		return true ;
	}
	
	
	/**
	 * 退出系统
	 * @return
	 *
	 * @time: 2014-4-21/上午11:24:51
	 */
	public String exit() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession() ;
		UserinfoBean user = (UserinfoBean) session.getAttribute("userinfo") ;
		
		if(user != null){
			session.removeAttribute("userinfo");
		}
		//session.invalidate();
        return "exit";
	}
	
	public void setUser(LoginUser user){
		this.user = user ;
	}
	
	public LoginUser getUser(){
		return this.user ;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}
}
