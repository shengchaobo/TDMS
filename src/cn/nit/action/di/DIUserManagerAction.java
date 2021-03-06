package cn.nit.action.di;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.UsersBean;
import cn.nit.bean.di.DiUserRoleBean;
import cn.nit.bean.table4.T42_Bean;
import cn.nit.service.di.DIUserManagerService;
import cn.nit.service.di.DiUserRoleService;
import cn.nit.util.ExcelUtil;
import cn.nit.util.MD5Util;


public class DIUserManagerAction {
	
	/**  用户管理Service类  */
	private DIUserManagerService userManagerSer = new DIUserManagerService() ;
	
	/**  用户角色管理Service类  */
	private DiUserRoleService userRoleSer = new DiUserRoleService() ;
	
	/**  新建用户的实体类  */
	private UserinfoBean userinfo = new UserinfoBean() ;

	/**  用户的SeqNumber  */
	private String ids ;
	
	/**  数据分页的当前页  */
	private String page ;
	
	/**  当前页共显示的总记录数  */
	private String rows ;
	
	private String searchID; //用于查询的教工号
	
	/**  新密码  */
	private String newPsd;
	
	/**  下载的excelName  */
	private String excelName ;
	
	/**  停用帐户标识  */
	private String switchFlag;
	
	HttpServletRequest request = ServletActionContext.getRequest() ;
	UserinfoBean user = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
	
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
		
		String cond = null;
		if(this.getSearchID()!= null){
			cond = " and Users.TeaID LIKE '" + this.getSearchID() + "%'";
			System.out.println(cond);
		}
		
		String json = userManagerSer.loadUsers(cond, Integer.parseInt(page), Integer.parseInt(rows)) ;
		PrintWriter out = null ;
		
		try{
			//设置输出内容的格式为json
			getResponse().setContentType("application/json; charset=UTF-8") ;
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
		
		
		//将userInfoBean对应数据usersBean以及DiUserRoleBean
		UsersBean userBean = new UsersBean();
		userBean.setSeqNumber(userinfo.getSeqNumber());
		userBean.setTeaID(userinfo.getTeaID());
		userBean.setTeaPasswd(MD5Util.encode("123456"));
		userBean.setTeaName(userinfo.getTeaName());
		userBean.setTeaEmail(userinfo.getTeaEmail());
		userBean.setFromOffice(userinfo.getFromOffice());
		userBean.setUnitID(userinfo.getUnitID());
		userBean.setUserNote(userinfo.getUserNote());

	
		DiUserRoleBean userRoleBean = new DiUserRoleBean();
		userRoleBean.setTeaID(userinfo.getTeaID());
		userRoleBean.setRoleID(userinfo.getRoleID());
		
		boolean flag = userManagerSer.update(userBean) ;
		boolean flag1 = userRoleSer.update(userRoleBean);
		
		PrintWriter out = null ;
		
		try{
			
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			if(flag&&flag1){
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
		
		//首先该用 户是否已存数据库
		Boolean flag0 = userManagerSer.hasUser(userinfo.getTeaID());
		
		//System.out.println(flag0);
		
		boolean flag = false;
		boolean flag1 = false;
		//如果该用户不存数据库，可以添加
		if(flag0 == false){
			
			//将userInfoBean对应数据usersBean以及DiUserRoleBean
			UsersBean userBean = new UsersBean();
			userBean.setSeqNumber(userinfo.getSeqNumber());
			userBean.setTeaID(userinfo.getTeaID());
			userBean.setTeaPasswd(MD5Util.encode("123456"));
			userBean.setTeaName(userinfo.getTeaName());
			userBean.setTeaEmail(userinfo.getTeaEmail());
			userBean.setFromOffice(userinfo.getFromOffice());
			userBean.setUnitID(userinfo.getUnitID());
			userBean.setUserNote(userinfo.getUserNote());
			userBean.setAvailability(false);
			
			DiUserRoleBean userRoleBean = new DiUserRoleBean();
			userRoleBean.setTeaID(userinfo.getTeaID());
			userRoleBean.setRoleID(userinfo.getRoleID());
			
			flag = userManagerSer.insert(userBean) ;
			flag1 = userRoleSer.insert(userRoleBean);
		}

		
		PrintWriter out = null ;
		
		try{
			
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			
			if(flag0){
				out.print("{\"state\":true,data:\"该用户已存在!!!\"}") ;
			}else{
				if(flag&&flag1){
					out.print("{\"state\":true,data:\"用户添加成功!!!\"}") ;
				}else{
					out.print("{\"state\":false,data:\"用户添加失败!!!\"}") ;
				}
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
		boolean flag1 = userRoleSer.deleteByIds(ids) ;
		PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			
			if(flag&&flag1){
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
	 * 开停用户
	 */
	public void switchIds(){
		
		boolean flag = userManagerSer.switchIds(ids,this.getSwitchFlag()) ;

		PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			if("0".equals(this.getSwitchFlag())){
				if(flag){
					out.print("{\"state\":true,data:\"帐户启用成功!!!\"}") ;
				}else{
					out.print("{\"state\":false,data:\"帐户启用失败!!!\"}") ;
				}
			}else{
				if(flag){
					out.print("{\"state\":true,data:\"帐户停用成功!!!\"}") ;
				}else{
					out.print("{\"state\":false,data:\"帐户停用失败!!!\"}") ;
				}
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
			getResponse().setContentType("text/html; charset=UTF-8") ;
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
	 * 修改密码
	 */
	public void alertPassword(){
		
		boolean flag = userManagerSer.alertPassword(user.getTeaID(), this.newPsd) ;
		PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			
			if(flag){
				out.print("{\"state\":true,data:\"修改密码成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"修改密码失败!!!\"}") ;
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
	
	public InputStream getInputStream() throws UnsupportedEncodingException{

		InputStream inputStream = null ;
		
		try {
/*			response.reset();
			response.addHeader("Content-Disposition", "attachment;fileName="
                      + java.net.URLEncoder.encode(excelName,"UTF-8"));*/
			
			List<UserinfoBean> list = userManagerSer.totalList();
						
			String sheetName = this.excelName;
			
			List<String> columns = new ArrayList<String>();
			
			columns.add("序号");
			columns.add("用户名");columns.add("姓名");columns.add("单位号");columns.add("单位名称");
			columns.add("电子邮箱");columns.add("用户角色");columns.add("备注");
			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("TeaID", 1);maplist.put("TeaName", 2);maplist.put("UnitID", 3);maplist.put("FromOffice", 4);
			maplist.put("TeaEmail", 5);maplist.put("RoleName", 6);maplist.put("UserNote", 7);
						
			inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}

		return inputStream ;
	}
	
	public String execute() throws Exception{
		System.out.println("excelName=============" + this.excelName) ;
		return "success" ;
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

	public void setSearchID(String searchID) {
		this.searchID = searchID;
	}

	public String getSearchID() {
		return searchID;
	}

	public void setNewPsd(String newPsd) {
		this.newPsd = newPsd;
	}

	public String getNewPsd() {
		return newPsd;
	}
	
	public String getExcelName() {
		try {
			this.excelName = URLEncoder.encode(excelName, "UTF-8");
			//this.saveFile = new String(saveFile.getBytes("ISO-8859-1"),"UTF-8");// 中文乱码解决
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	public void setSwitchFlag(String switchFlag) {
		this.switchFlag = switchFlag;
	}

	public String getSwitchFlag() {
		return switchFlag;
	}
}
