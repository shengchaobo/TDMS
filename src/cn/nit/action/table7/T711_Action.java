package cn.nit.action.table7;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table7.T711_Bean;
import cn.nit.service.di.DiAwardLevelService;
import cn.nit.service.table7.T711_Service;

public class T711_Action {

	
	private T711_Service diAwardLevelSr=new T711_Service();
	
	private T711_Bean teaManagerAwardInfoTeaTea=new T711_Bean();
	
	public void insert(){
		System.out.println(teaManagerAwardInfoTeaTea.getUnitID()) ;
		teaManagerAwardInfoTeaTea.setTime(new Date());
		
		boolean flag= diAwardLevelSr.insert(teaManagerAwardInfoTeaTea);
        PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
//			getResponse().setHeader("Content-type", "text/html");  
			out = getResponse().getWriter() ;
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

	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest() ;
	}
	
	public HttpSession getSession(){
		return getRequest().getSession() ;
	}
	
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse() ;
	}
		
	public T711_Bean getTeaManagerAwardInfoTeaTea(){
		return teaManagerAwardInfoTeaTea;
	}
	
	
	public void setTeaManagerAwardInfoTeaTea(T711_Bean teaManagerAwardInfoTeaTea){
		
		
		this.teaManagerAwardInfoTeaTea=teaManagerAwardInfoTeaTea;
	}
	
	
	}
	

