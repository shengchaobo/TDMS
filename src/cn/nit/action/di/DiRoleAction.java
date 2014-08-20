package cn.nit.action.di;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.RoleBean;
import cn.nit.bean.UserinfoBean;
import cn.nit.bean.UsersBean;
import cn.nit.bean.di.DiUserRoleBean;
import cn.nit.service.di.DIUserManagerService;
import cn.nit.service.di.DiRoleService;
import cn.nit.service.di.DiUserRoleService;
import cn.nit.util.MD5Util;

public class DiRoleAction {
	
	HttpServletResponse response = ServletActionContext.getResponse();
	
	/**  角色管理Service类  */
	private DiRoleService roleSer = new DiRoleService() ;
	
	/**  新建角色的实体类  */
	private RoleBean role_bean = new RoleBean() ;

	/**  角色编号 */
	private String ids ;
	
	/**  数据分页的当前页  */
	private String page ;
	
	/**  当前页共显示的总记录数  */
	private String rows ;
	
	private String searchID; //用于查询的教工号
	
	/**
	 * 初始化加载用户
	 */
	public void loadRoles(){
		
		if(this.page == null || this.page.equals("") || !page.matches("[\\d]+")){
			return ;
		}
		
		if(this.rows == null || this.rows.equals("") || !rows.matches("[\\d]+")){
			return ;
		}
		
		String cond = null;
		if(this.getSearchID()!= null){
			cond = " and RoleID LIKE '" + this.getSearchID() + "%'";
			System.out.println(cond);
		}
		
		String json = roleSer.loadRoles(cond, Integer.parseInt(page), Integer.parseInt(rows)) ;
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
				
		boolean flag = roleSer.update(role_bean) ;
		
		PrintWriter out = null ;
		
		try{
			
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"角色更新成功!!!\"}") ;
			}else{
				out.print("{\"state\":true,data:\"角色更新失败!!!\"}") ;
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
		
		//首先该用 户是否已存数据库
		Boolean flag0 = roleSer.hasRole(role_bean.getRoleID());
		
		boolean flag = false;
		//如果该用户不存数据库，可以添加
		if(flag0 == false){			
			flag = roleSer.insert(role_bean) ;
		}
		
		PrintWriter out = null ;
		
		try{
			
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			
			if(flag0){
				out.print("{\"state\":true,data:\"该角色已存在!!!\"}") ;
			}else{
				if(flag){
					out.print("{\"state\":true,data:\"角色添加成功!!!\"}") ;
				}else{
					out.print("{\"state\":false,data:\"角色添加失败!!!\"}") ;
				}
			}

			
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"角色添加失败!!!\"}") ;
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
		
		boolean flag = roleSer.deleteByIds(ids) ;
		PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			
			if(flag){
				out.print("{\"state\":true,data:\"角色删除成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"角色删除失败!!!\"}") ;
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
	
	
	public void loadDiRoles(){
		
		List<RoleBean> list = roleSer.getList() ;
		//将数据转换为json格式
		JSON json = JSONSerializer.toJSON(list) ;
		PrintWriter out = null ;
		
		try {
			//设置输出内容的格式为json
			response.setContentType("application/json; charset=UTF-8") ;
			out = response.getWriter() ;
			//设置数据的内容的编码格式
			String outPrint = URLDecoder.decode(json.toString(), "UTF-8") ;
			out.print(outPrint) ;
			out.flush() ;
		} catch (Exception e) {
			e.printStackTrace();
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

	public void setSearchID(String searchID) {
		this.searchID = searchID;
	}

	public String getSearchID() {
		return searchID;
	}

	public void setRole_bean(RoleBean role_bean) {
		this.role_bean = role_bean;
	}

	public RoleBean getRole_bean() {
		return role_bean;
	}
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
}
