package cn.nit.action.table4;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.table4.T411_Bean;
import cn.nit.bean.table4.T431_Bean;
import cn.nit.dao.table4.T411_Dao;
import cn.nit.service.table4.T411_Service;
import cn.nit.util.ExcelUtil;

public class T411_Action {
	
	private String rows; //每页显示的记录数
	
	private String page; //当前第几页
	
	private String ids; //删除的id
	
	private String searchID; //用于查询的教工号
	

	private T411_Service T411_services = new T411_Service();
	
	private T411_Bean T411_bean = new T411_Bean();
	


	HttpServletResponse response = ServletActionContext.getResponse() ;
	
	
	//查询出所有教师信息
	public void loadTeaInfo() throws Exception{
		
		HttpServletResponse response = ServletActionContext.getResponse() ;	
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		String cond = (String)session.getAttribute("Conditions");
		
		//System.out.println(cond);
		
		List<T411_Bean> list = T411_services.getPageTeaInfoList(cond,null,this.getRows(),this.getPage()) ;
		String TeaInfoJson = this.toBeJson(list,T411_services.getTotal(cond,null));
		
		PrintWriter out = null ;

		if(TeaInfoJson == null){			
			return ;
		}else{
			try {
				
				System.out.println(TeaInfoJson) ;
				response.setContentType("application/json;charset=UTF-8") ;
				out = response.getWriter() ;
				out.print(TeaInfoJson) ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(out != null){
					out.flush() ;
					out.close() ;
				}
			}
		}
	}

	//将分页系统的总数以及当前页的list转化一个json传页面显示
	private String toBeJson(List<T411_Bean> list, int total) throws Exception{
		// TODO Auto-generated method stub
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();

		JSONObject testjson = new JSONObject();
		testjson.accumulate("total", total);
		testjson.accumulate("rows", list);
		
        String json = testjson.toString();
        System.out.println(json) ;
		return json;
	}

	//查出所有
	public void loadT411() throws Exception{
		
		List<T411_Bean> list = T411_services.getList();
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
	
	//插入一个新的教职工
	public void insert(){
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		HttpServletResponse response = ServletActionContext.getResponse();
		
		boolean flag = T411_services.insert(T411_bean);
		PrintWriter out = null ;
		
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"录入成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"录入失败!!!\"}") ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"录入失败!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
		out.flush() ;
	}
	
	//生成查询条件，本质是生成查询条件
	public void singleSearch(){
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String conditions = null;
		
		if(this.getSearchID() == null || this.getSearchID().equals("")){
			conditions = null;
		}else{
			conditions = " and TeaId LIKE '" + this.getSearchID() + "%'";
		}
		
		session.setAttribute("Conditions", conditions) ;
		
		PrintWriter out = null ;
		
		try{
			out = response.getWriter() ;
			out.print("{\"state\":true,data:\"查询失败!!!\"}") ;
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"查询失败!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	/**  编辑数据  */
	public void edit(){

		boolean flag = T411_services.update(T411_bean) ;
		PrintWriter out = null ;
	
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"修改成功!!!\"}") ;
			}else{
				out.print("{\"state\":true,data:\"修改失败!!!\"}") ;
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
	
	
	public InputStream getInputStream(){

		InputStream inputStream = null ;

		try {
			inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel().toByteArray()) ;
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}

		return inputStream ;
	}

	
	
/*	*//**  根据数据的id删除数据  *//*
	public void deleteByIds(){
		System.out.println("ids=" + this.getIds()) ;
		boolean flag = T411_services.deleteByIds(ids) ;
		PrintWriter out = null ;
		
		try{
			
			
			response.setContentType("application/json; charset=UTF-8") ;
			out = response.getWriter() ;			
			if(flag){
				out.print("{\"state\":true,data:\"数据删除成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"数据删除失败!!!\"}") ;
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
	}*/
	
	
	//由T411导出T431
	public void loadT431() throws Exception{
		
		List<T431_Bean> list = T411_services.getT43List(1);
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
	
	//由T411导出T432
	public void loadT432() throws Exception{
		
		List<T431_Bean> list = T411_services.getT43List(2);
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
	
	//由T411导出T433
	public void loadT433() throws Exception{
		
		List<T431_Bean> list = T411_services.getT43List(3);
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
	
	//由T411导出T434
	public void loadT434() throws Exception{
		
		List<T431_Bean> list = T411_services.getT43List(4);
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
	
	//由T411导出T436
	public void loadT436() throws Exception{
		
		List<T431_Bean> list = T411_services.getT43List(6);
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

	public String getRows() {
		return rows;
	}


	public void setRows(String rows) {
		this.rows = rows;
	}


	public String getPage() {
		return page;
	}


	public void setPage(String page) {
		this.page = page;
	}
	
	public T411_Bean getT411_bean() {
		return T411_bean;
	}

	public void setT411_bean(T411_Bean t411Bean) {
		T411_bean = t411Bean;
	}
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setSearchID(String searchID) {
		this.searchID = searchID;
	}

	public String getSearchID() {
		return searchID;
	}
}