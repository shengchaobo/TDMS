package cn.nit.action.table7;


import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table7.T733_Bean;
import cn.nit.service.table7.T733_Service;

public class T733_Action {
	
	T733_Service T733_Sr=new T733_Service();
	
	T733_Bean eachUnitTeachResActInfo=new T733_Bean();
	
	public void insert(){
		eachUnitTeachResActInfo.setTime(new Date());
		PrintWriter out=null;
		
		boolean flag;
		flag=T733_Sr.insert(eachUnitTeachResActInfo);
		
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
			out.print("{\"state\":false,data:\"录入失败!!!\"}");
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
	public HttpSession getSession(){
		return getRequest().getSession();
	}
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	public T733_Bean getEachUnitTeachResActInfo() {
		return eachUnitTeachResActInfo;
	}
	public void setEachUnitTeachResActInfo(T733_Bean eachUnitTeachResActInfo) {
		this.eachUnitTeachResActInfo = eachUnitTeachResActInfo;
	}
	

}
