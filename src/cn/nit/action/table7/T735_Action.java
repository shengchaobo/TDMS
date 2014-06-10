package cn.nit.action.table7;


import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table7.T735_Bean;
import cn.nit.service.table7.T735_Service;

public class T735_Action {
	T735_Service teachManageAssessInfoTeaSr=new T735_Service();
	T735_Bean teachManageAssessInfoTea=new T735_Bean();
	public void insert(){
		teachManageAssessInfoTea.setTime(new Date());
		boolean flag;
		PrintWriter out=null;
		
		flag=teachManageAssessInfoTeaSr.insert(teachManageAssessInfoTea);
		
		
		try {
			getResponse().setContentType("text/html;charset=UTF-8");
			out=getResponse().getWriter();
			if (flag) {
				out.print("{\"state\":true,data:\"录入成功!!!\"}");
				
			} else {
				out.print("{\"state\":false,data:\"录入失败!!!\"}");

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("{\"state\":false,data:\"录入失败!!!\"}") ;
		}finally{
			if(out!=null){
				out.close();
			}
		}
		out.flush();
		
	}
	
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	public HttpSession getsSession(){
		return getRequest().getSession();
	}
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}

	public T735_Bean getTeachManageAssessInfoTea() {
		return teachManageAssessInfoTea;
	}

	public void setTeachManageAssessInfoTea(
			T735_Bean teachManageAssessInfoTea) {
		this.teachManageAssessInfoTea = teachManageAssessInfoTea;
	}
	

}
