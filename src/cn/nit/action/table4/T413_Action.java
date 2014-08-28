package cn.nit.action.table4;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.table4.T411_Bean;
import cn.nit.bean.table4.T413_Bean;
import cn.nit.dao.table4.T411_Dao;
import cn.nit.dao.table4.T413_Dao;
import cn.nit.service.table4.T411_Service;
import cn.nit.service.table4.T413_Service;
import cn.nit.util.ExcelUtil;

public class T413_Action {
	
	private String rows; //每页显示的记录数
	
	private String page; //当前第几页
	
	private T413_Service T413_services = new T413_Service();
	
	private T413_Bean T413_bean = new T413_Bean();
	
	private T411_Service T411_services = new T411_Service();
	
	private T411_Bean T411_bean = new T411_Bean();
	
	private T413_Dao T413_dao = new T413_Dao();
	
	private String searchID; //用于查询的教工号
	
	private String excelName; //excel导出名字
	


	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	
	//查询出所有教师信息
	public void loadTeaInfo() throws Exception{
		
		HttpServletResponse response = ServletActionContext.getResponse() ;	
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		String cond = null;
		if(this.getSearchID()!= null){
			cond = " and TeaId LIKE '" + this.getSearchID() + "%'";
			System.out.println(cond);
		}
		
		//具体教学单位
		UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String fillUnitID = bean.getUnitID();
		
		List<T413_Bean> list = T413_services.getPageTeaInfoList(cond,fillUnitID,this.getRows(),this.getPage()) ;
		String TeaInfoJson = this.toBeJson(list,T413_services.getTotal(cond,fillUnitID));
		//private JSONObject jsonObj;
		
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
	private String toBeJson(List<T413_Bean> list, int total) throws Exception{
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

	
	//插入一个新的教职工
	public void insert(){
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		HttpServletResponse response = ServletActionContext.getResponse();
		//插入教学单位
		//具体教学单位
		UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String fillUnitID = bean.getUnitID();
		T413_bean.setFillUnitID(fillUnitID);
		boolean flag = T413_services.insert(T413_bean);
		T411_bean.setTeaName(T413_bean.getName());
		T411_bean.setTeaId(T413_bean.getTeaId());
		T411_bean.setGender(T413_bean.getGender());
		T411_bean.setBirthday(T413_bean.getBirthday());
		T411_bean.setAdmisTime(null);
		T411_bean.setTeaState(T413_bean.getTeaState());
		T411_bean.setBeginWorkTime(null);
		T411_bean.setIdcode("40009");
		T411_bean.setTeaFlag("外聘");
		T411_bean.setOfficeID(T413_bean.getUnitId());
		T411_bean.setFromOffice(T413_bean.getUnitName());
		T411_bean.setUnitId(T413_bean.getUnitId());
		T411_bean.setFromUnit(T413_bean.getUnitName());
		T411_bean.setFromTeaResOffice(null);
		T411_bean.setTeaResOfficeID(null);
		T411_bean.setEducation(T413_bean.getEducation());
		T411_bean.setTopDegree(T413_bean.getTopDegree());
		T411_bean.setGraSch(null);
		T411_bean.setSource(null);
		T411_bean.setAdminLevel(null);
		T411_bean.setMajTechTitle(T413_bean.getTechTitle());
		T411_bean.setTeaTitle(null);
		T411_bean.setNotTeaTitle(null);
		T411_bean.setSubjectClass(T413_bean.getSubjectClass());
		T411_bean.setIndustry(false);
		T411_bean.setDoubleTea(false);
		T411_bean.setEngineer(false);
		T411_bean.setTeaBase(false);
		T411_bean.setNote(T413_bean.getNote());				
		boolean flag1 = T411_services.insert(T411_bean);
		PrintWriter out = null ;
		
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(flag&&flag1){
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

		boolean flag = T413_services.update(T413_bean) ;
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
			
			//具体教学单位
			UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
			String fillUnitID = bean.getUnitID();
			
			List<T413_Bean> list = T413_dao.totalList(fillUnitID);
						
			String sheetName = this.getExcelName();
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("教学单位");columns.add("单位号");columns.add("姓名");columns.add("教工号");
			columns.add("性别");columns.add("出生年月");columns.add("聘任时间");columns.add("任职状态");columns.add("聘期（个月）");
			columns.add("学历");columns.add("最高学位");columns.add("专业技术职称");columns.add("学科类别");
			columns.add("工作单位类别");columns.add("导师类别");columns.add("地区");
			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("unitName", 1);maplist.put("unitId", 1);maplist.put("name", 3);maplist.put("teaId", 4);
			maplist.put("gender", 5);maplist.put("birthday", 6);maplist.put("hireBeginTime", 7);maplist.put("teaState", 8);
			maplist.put("hireTimeLen", 9);;maplist.put("education", 10);maplist.put("topDegree", 11);
			maplist.put("techTitle", 12);maplist.put("subjectClass", 13);
			maplist.put("workUnitType", 14);maplist.put("tutorType", 15);maplist.put("region", 16);
			
			
			inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}

		return inputStream ;
	}
	
	public String execute() throws Exception{
		request.setCharacterEncoding("UTF-8") ;
		System.out.println("excelName=============" + excelName) ;
		return "success" ;
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
	
	public T413_Bean getT413_bean() {
		return T413_bean;
	}

	public void setT413_bean(T413_Bean T413Bean) {
		T413_bean = T413Bean;
	}

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
		return excelName;
	}
}