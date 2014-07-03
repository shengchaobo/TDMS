package cn.nit.action.di;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.UserinfoBean;
import cn.nit.service.di.DIUserManagerService;
import cn.nit.util.MD5Util;

public class DIUserManagerAction {
	
	/**  用户管理Service类  */
	private DIUserManagerService userManagerSer = new DIUserManagerService() ;
	
	/**  新建用户的实体类  */
	private UserinfoBean userinfo = new UserinfoBean() ;

	/**  用户的SeqNumber  */
	private String ids ;
	
	/**  数据分页的当前页  */
	private String page ;
	
	/**  当前页共显示的总记录数  */
	private String rows ;
	
	/**
	 * 初始化加载用户
	 */
	public void loadUsers(){
		
		if(this.page == null || this.page.equals("") || !page.matches("[\\d]+")){
			return ;
		}
		
		if(this.rows == null || this.rows.equals("") || !rows.matches("[\\d]+")){
			return ;
		}
		
		String json = userManagerSer.loadUsers(null, Integer.parseInt(page), Integer.parseInt(rows)) ;
		PrintWriter out = null ;
		
		try{
			out = getResponse().getWriter() ;
			out.print(json) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return ;
		}finally{
			out.flush() ;
			
			if(out != null){
				out.close() ;
			}
		}
	}
	
	/**
	 * 编辑用户
	 */
	public void edit(){
		
		boolean flag = userManagerSer.update(userinfo) ;
		PrintWriter out = null ;
		
		try{
			out = getResponse().getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"用户更新成功!!!\"}") ;
			}else{
				out.print("{\"state\":true,data:\"用户更新失败!!!\"}") ;
			}
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"系统错误，请联系管理员!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	/**
	 * 添加用户
	 */
	public void insert(){
		
		userinfo.setTeaPasswd(MD5Util.encode("123456")) ;
		boolean flag = userManagerSer.insert(userinfo) ;
		PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			
			if(flag){
				out.print("{\"state\":true,data:\"用户添加成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"用户添加失败!!!\"}") ;
			}
			
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"用户添加失败!!!\"}") ;
		}finally{
			out.flush() ;
			
			if(out != null){
				out.close() ;
			}
		}
	}
	
	/**
	 * 删除用户
	 */
	public void deleteByIds(){
		
		boolean flag = userManagerSer.deleteByIds(ids) ;
		PrintWriter out = null ;
		
		try{
			out = getResponse().getWriter() ;
			
			if(flag){
				out.print("{\"state\":true,data:\"用户删除成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"用户删除失败!!!\"}") ;
			}
			
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"系统错误，请联系管理员!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	/**
	 * 重置密码
	 */
	public void resetPassword(){
		
		boolean flag = userManagerSer.resetPassword(ids) ;
		PrintWriter out = null ;
		
		try{
			out = getResponse().getWriter() ;
			
			if(flag){
				out.print("{\"state\":true,data:\"用户密码重置成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"用户密码重置失败!!!\"}") ;
			}
			
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"系统错误，请联系管理员!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	/**
	 * 获取request
	 * @return
	 *
	 * @time: 2014-5-14/下午03:07:00
	 */
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest() ;
	}
	
	/**
	 * 获取session
	 * @return
	 *
	 * @time: 2014-5-14/下午03:07:14
	 */
	public HttpSession getSession(){
		return getRequest().getSession() ;
	}
	
	/**
	 * 获取response
	 * @return
	 *
	 * @time: 2014-5-14/下午03:07:24
	 */
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse() ;
	}

	public void setUserinfo(UserinfoBean userinfo){
		this.userinfo = userinfo ;
	}
	
	public UserinfoBean getUserinfo(){
		return this.userinfo ;
	}
	
	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}
}
