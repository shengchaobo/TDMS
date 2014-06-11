package cn.nit.action.table7;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table7.T721_Bean;
import cn.nit.service.table7.T721_Service;

public class T721_Action {

	
	T721_Service teachResItemTeaSr=new T721_Service();
	
	T721_Bean teachResItemTea=new T721_Bean();
	
	public void insert(){
		
		teachResItemTea.setTime(new Date());
		System.out.println(teachResItemTea.getApplvExp());
		
		boolean flag=teachResItemTeaSr.insert(teachResItemTea);
		
		PrintWriter out=null;
		
		
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
		out.flush() ;
		
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
	
	public T721_Bean getTeachResItemTea(){
		
		return teachResItemTea;
	}
	
	public void setTeachResItemTea(T721_Bean teachResItemTea){
		
		this.teachResItemTea=teachResItemTea;
	}
}
