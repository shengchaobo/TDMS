package cn.nit.action.table4;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table4.T411_Bean;
import cn.nit.bean.table4.T413_Bean;
import cn.nit.service.table4.T411_Service;
import cn.nit.service.table4.T413_Service;

public class T413_Action {
	
	private String rows; //每页显示的记录数
	
	private String page; //当前第几页
	
	private T413_Service T413_services = new T413_Service();
	
	private T413_Bean T413_bean = new T413_Bean();
	
	private T411_Service T411_services = new T411_Service();
	
	private T411_Bean T411_bean = new T411_Bean();
	

	HttpServletResponse response = ServletActionContext.getResponse() ;
	
	
	//查询出所有教师信息
	public void loadTeaInfo() throws Exception{
		
		HttpServletResponse response = ServletActionContext.getResponse() ;	
		List<T413_Bean> list = T413_services.getPageTeaInfoList(this.getRows(),this.getPage()) ;
		String TeaInfoJson = this.toBeJson(list,T413_services.getTotal());
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
		
		boolean flag = T413_services.insert(T413_bean);
		T411_bean.setTeaName(T413_bean.getName());
		T411_bean.setTeaId(T413_bean.getTeaId());
		T411_bean.setGender(T413_bean.getGender());
		T411_bean.setBirthday(T413_bean.getBirthday());
		T411_bean.setBeginWorkTime(T411_bean.getBeginWorkTime());
		T411_bean.setTeaState(T413_bean.getTeaState());
		T411_bean.setBeginWorkTime(null);
		T411_bean.setIdcode("40009");
		T411_bean.setTeaFlag("外聘");
		T411_bean.setOfficeID(null);
		T411_bean.setFromOffice(null);
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
		T411_bean.setNote(T413_bean.getName());				
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
}