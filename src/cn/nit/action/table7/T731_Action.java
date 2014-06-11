package cn.nit.action.table7;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table7.T731_Bean;
import cn.nit.service.table7.T731_Service;

public class T731_Action {
	
	T731_Service T731_Sr=new T731_Service();
	
	T731_Bean schleadInClass=new T731_Bean();
	public void insert(){
		schleadInClass.setTime(new Date());
		
		boolean flag;
		PrintWriter out=null;	
		
		flag=T731_Sr.insert(schleadInClass);
		
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
	
	
	public T731_Bean getSchleadInClass() {
		return schleadInClass;
	}
	public void setSchleadInClass(T731_Bean schleadInClass) {
		this.schleadInClass = schleadInClass;
	}
	

}
