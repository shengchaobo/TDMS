package cn.nit.action.table7;


import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table7.T741_Bean;

import cn.nit.service.table7.T741_Service;

public class T741_Action {
	
	T741_Service t741_Sr=new T741_Service();
	
	T741_Bean teachAbilityAssessAC=new T741_Bean();
	
	public void insert(){
		teachAbilityAssessAC.setTime(new Date());
		PrintWriter out=null;
		boolean flag;
		
		flag=t741_Sr.insert(teachAbilityAssessAC);
		
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
	public T741_Bean getTeachAbilityAssessAC() {
		return teachAbilityAssessAC;
	}
	public void setTeachAbilityAssessAC(T741_Bean teachAbilityAssessAC) {
		this.teachAbilityAssessAC = teachAbilityAssessAC;
	}

	
	

}
