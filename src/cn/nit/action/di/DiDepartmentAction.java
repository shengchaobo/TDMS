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



import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.service.di.DiDepartmentService;



public class DiDepartmentAction {

	HttpServletResponse response = ServletActionContext.getResponse();
	
	/**  部门管理Service类  */
	private DiDepartmentService deSer = new DiDepartmentService() ;
	
	/**  新建部门的实体类  */
	private DiDepartmentBean de_bean = new DiDepartmentBean() ;

	public DiDepartmentBean getDe_bean() {
		return de_bean;
	}

	public void setDe_bean(DiDepartmentBean deBean) {
		de_bean = deBean;
	}

	/**  部门编号 */
	private String ids ;
	
	/**  数据分页的当前页  */
	private String page ;
	
	/**  当前页共显示的总记录数  */
	private String rows ;
	
	private String searchID; //用于查询的部门号
	//查出所�?
	/**
	 * 初始化加载用户
	 */
	public void loadDes(){
		
		if(this.page == null || this.page.equals("") || !page.matches("[\\d]+")){
			return ;
		}
		
		if(this.rows == null || this.rows.equals("") || !rows.matches("[\\d]+")){
			return ;
		}
		
		String cond = null;
		if(this.getSearchID()!= null){
			cond = " and UnitID LIKE '" + this.getSearchID() + "%'";
			System.out.println(cond);
		}
		
		String json = deSer.loadDes(cond, Integer.parseInt(page), Integer.parseInt(rows));
		PrintWriter out = null ;
		
		try{
			out = getResponse().getWriter();
			out.print(json);
		}catch(Exception e){
			e.printStackTrace();
			return ;
		}finally{
			out.flush();
			
			if(out != null){
				out.close();
			}
		}
	}
	
	public void loadDepartment(){
		
		List<DiDepartmentBean> list = deSer.getList() ;
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
	
	//查出科研
	public void loadDIDepartmentSci() throws Exception{
		
		List<DiDepartmentBean> list = deSer.getListSci() ;
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
	 * 编辑部门
	 */
	public void edit(){
//		System.out.println("编辑编辑编辑");
//		System.out.println(de_bean.getClass1());
//		System.out.println(de_bean.getClass1());
//		System.out.println(de_bean.getClass2());
//		System.out.println(de_bean.getFunctions());
//		System.out.println(de_bean.getLeader());
				
		boolean flag = deSer.update(de_bean) ;
		
		PrintWriter out = null ;
		
		try{
			
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"更新成功!!!\"}") ;
			}else{
				out.print("{\"state\":true,data:\"更新失败!!!\"}") ;
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
	 * 添加部门
	 */
	public void insert(){
		
		//首先该用 户是否已存数据库
		Boolean flag0 = deSer.hasDe(de_bean.getUnitId());
		
		boolean flag = false;
		//如果该用户不存数据库，可以添加
		if(flag0 == false){			
			flag = deSer.insert(de_bean) ;
		}
		
		PrintWriter out = null ;
		
		try{
			
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			
			if(flag0){
				out.print("{\"state\":true,data:\"该部门已存在!!!\"}") ;
			}else{
				if(flag){
					out.print("{\"state\":true,data:\"添加成功!!!\"}") ;
				}else{
					out.print("{\"state\":false,data:\"添加失败!!!\"}") ;
				}
			}

			
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"添加失败!!!\"}") ;
		}finally{
			out.flush() ;
			
			if(out != null){
				out.close() ;
			}
		}
	}
	
	public void deleteByIds(){
		
		boolean flag = deSer.deleteByIds(ids) ;
		PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			
			if(flag){
				out.print("{\"state\":true,data:\"删除成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"删除失败!!!\"}") ;
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

	public void setDepartment_bean(DiDepartmentBean de_bean) {
		this.de_bean = de_bean;
	}

	public DiDepartmentBean getDepartment_bean() {
		return de_bean;
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


