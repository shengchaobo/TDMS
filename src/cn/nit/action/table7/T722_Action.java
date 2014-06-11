package cn.nit.action.table7;


import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table7.T722_Bean;
import cn.nit.service.table7.T722_Service;

public class T722_Action {
	
	T722_Service teachAchieveAwardTeaSr=new T722_Service();
	
	T722_Bean teachAchieveAwardTea=new T722_Bean();
	public void insert(){
		teachAchieveAwardTea.setTime(new Date());
		PrintWriter out=null;
		boolean flag;
		flag=teachAchieveAwardTeaSr.insert(teachAchieveAwardTea);
		
		
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

    public T722_Bean getTeachAchieveAwardTea() {
		return teachAchieveAwardTea;
	}
	public void setTeachAchieveAwardTea(T722_Bean teachAchieveAwardTea){
		this.teachAchieveAwardTea=teachAchieveAwardTea;
	}
}
