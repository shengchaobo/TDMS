package cn.nit.action.table7;


import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;


import cn.nit.bean.table7.T712_Bean;

import cn.nit.service.table7.T712_Service;

public class T712_Action {
	

	private T712_Service teaManagerPaperInfoTeaTeaSr=new T712_Service();
	
	private T712_Bean teaManagerPaperInfoTeaTea=new T712_Bean();
	
	public void insert(){
		
		teaManagerPaperInfoTeaTea.setTime(new Date());	
		
		boolean flag=teaManagerPaperInfoTeaTeaSr.insert(teaManagerPaperInfoTeaTea);
		
		PrintWriter out=null;
		
		
		
		try {
			getResponse().setContentType("text/html;charset=UTF-8");
			out=getResponse().getWriter();
			if(flag){
				out.print("{\"state\":true,data:\"录入成功!!!\"}");
			}else{
				out.print("{\"state\":false,data:\"录入失败!!!\"}");
			}
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("{\"state\":false,data:\"录入失败!!!\"}") ;
		}finally{
			if (out!=null) {
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
	
	
	public T712_Bean getTeaManagerPaperInfoTeaTea(){
		
		return teaManagerPaperInfoTeaTea;
	}
	public void setTeaManagerPaperInfoTeaTea(T712_Bean teaManagerPaperInfoTeaTea){
		
		this.teaManagerPaperInfoTeaTea=teaManagerPaperInfoTeaTea;
		
		
	}

}
