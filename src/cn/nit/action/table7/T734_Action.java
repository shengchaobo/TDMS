package cn.nit.action.table7;


import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table7.T734_Bean;
import cn.nit.service.table7.T734_Service;

public class T734_Action {
	
	T734_Service T734_Sr=new T734_Service();
	
	T734_Bean teachAccidentTea=new T734_Bean();
	
	public void insert(){
		
		teachAccidentTea.setTime(new Date());
		
		boolean flag;
		PrintWriter out=null;
		
		flag=T734_Sr.insert(teachAccidentTea);
		
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
	public T734_Bean getTeachAccidentTea() {
		return teachAccidentTea;
	}
	public void setTeachAccidentTea(T734_Bean teachAccidentTea) {
		this.teachAccidentTea = teachAccidentTea;
	}
	

}
