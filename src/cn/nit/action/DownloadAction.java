package cn.nit.action;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class DownloadAction {

	private String className ;
	private String methodName ;
	public OutputStream getOutputStream(){
		
		OutputStream os = null ;
		
		try{
			Class clazz = Class.forName(className) ;
			Method method = clazz.getDeclaredMethod(methodName, List.class, HttpServletRequest.class) ;
			os = (OutputStream)method.invoke(clazz.newInstance()) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return null ;
		}
		return os ;
	}
	
	public String execute(){
		
		return "success" ;
	}
	
	public void setClassName(String className){
		this.className = className ;
	}
	
	public void setMethodName(String methodName){
		this.methodName = methodName ;
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
}
