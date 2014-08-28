package cn.nit.action.table4;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import cn.nit.excel.imports.table4.T411_Excel;
import cn.nit.service.table4.T411_Service;
import cn.nit.util.ExcelUtil;

public class T411_Action {
	
	private String rows; //每页显示的记录数
	
	private String page; //当前第几页
	
	//private String ids; //删除的id
	
	private String searchID; //用于查询的教工号
	
	private String excelName; //excel导出名字
	
	private String queryword; //查询字段
	

	private T411_Service T411_services = new T411_Service();
	
	private T411_Bean T411_bean = new T411_Bean();
	
	private T411_Dao T411_dao = new T411_Dao();
	

	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	
	//查询出所有教师信息
	public void loadTeaInfo() throws Exception{
		
		HttpServletResponse response = ServletActionContext.getResponse() ;	
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		//String cond = (String)session.getAttribute("Conditions");
		String cond = null;
		if(this.getSearchID()!= null){
			cond = " and TeaId LIKE '" + this.getSearchID() + "%'";
			System.out.println(cond);
		}
		
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
	
	
	public InputStream getInputStream() throws UnsupportedEncodingException{

		InputStream inputStream = null ;
		
		try {
/*			response.reset();
			response.addHeader("Content-Disposition", "attachment;fileName="
                      + java.net.URLEncoder.encode(excelName,"UTF-8"));*/
			
			List<T411_Bean> list = T411_dao.totalList();
						
			String sheetName = this.excelName;
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("姓名");columns.add("教工号");columns.add("性别");columns.add("出生年月");
			columns.add("入校时间");columns.add("任职状态");columns.add("参加本科教学工作时间");columns.add("身份代码");
			columns.add("所属部门");columns.add("所属部门单位号");columns.add("所属教学单位");columns.add("所属教学单位号");
			columns.add("所属教研室");columns.add("所属教研室单位号");columns.add("学历");columns.add("最高学位");
			columns.add("毕业学校");columns.add("专业");columns.add("学缘");columns.add("行政职务");
			columns.add("专业技术职称");columns.add("教学系列职称");columns.add("非教学系列职称");columns.add("学科类别");
			columns.add("是否双师型教师");columns.add("是否具有行业背景");columns.add("是否具有工程背景");columns.add("是否列入师资库");

			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("teaName", 1);maplist.put("teaId", 2);maplist.put("gender", 3);maplist.put("birthday", 4);
			maplist.put("admisTime", 5);maplist.put("teaState", 6);maplist.put("beginWorkTime", 7);maplist.put("idcode", 8);
			maplist.put("fromOffice", 9);maplist.put("officeID", 10);maplist.put("fromUnit", 11);maplist.put("unitId", 12);
			maplist.put("fromTeaResOffice", 13);maplist.put("teaResOfficeID", 14);maplist.put("education", 15);maplist.put("topDegree", 16);
			maplist.put("graSch", 17);maplist.put("major", 18);maplist.put("source", 19);maplist.put("adminLevel", 20);
			maplist.put("majTechTitle", 21);maplist.put("teaTitle", 22);maplist.put("notTeaTitle", 23);maplist.put("subjectClass", 24);
			maplist.put("doubleTea", 25);maplist.put("industry", 26);maplist.put("engineer", 27);maplist.put("teaBase", 28);
			
			
			inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}

		return inputStream ;
	}
	
	public String execute() throws Exception{
		request.setCharacterEncoding("UTF-8") ;
		System.out.println("excelName=============" + this.excelName) ;
		return "success" ;
	}
	
	
	//由T411导出T431
	public void loadT431() throws Exception{
		
		//System.out.println(this.getQueryword());
		
		List<T431_Bean> list = T411_services.getT43List(1,this.getQueryword());
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
		
		List<T431_Bean> list = T411_services.getT43List(2,this.getQueryword());
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
		
		List<T431_Bean> list = T411_services.getT43List(3,this.getQueryword());
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
		
		List<T431_Bean> list = T411_services.getT43List(4,this.queryword);
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
		
		List<T431_Bean> list = T411_services.getT43List(6,this.getQueryword());
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
	
/*	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}*/

	public void setSearchID(String searchID) {
		this.searchID = searchID;
	}

	public String getSearchID() {
		return searchID;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
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

	public void setQueryword(String queryword) {
		this.queryword = queryword;
	}

	public String getQueryword() {
		return queryword;
	}
}