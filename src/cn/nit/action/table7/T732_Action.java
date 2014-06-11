package cn.nit.action.table7;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table7.T732_Bean;
import cn.nit.service.table7.T732_Service;

public class T732_Action {
	T732_Service t732_Sr=new T732_Service();
	
	T732_Bean teaLeadInClassInfo=new T732_Bean();
	
	public void insert(){
		teaLeadInClassInfo.setTime(new Date());
		boolean flag;
		PrintWriter out=null;
		
		flag=t732_Sr.insert(teaLeadInClassInfo);
		
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
	public T732_Bean getTeaLeadInClassInfo() {
		return teaLeadInClassInfo;
	}
	public void setTeaLeadInClassInfo(T732_Bean teaLeadInClassInfo) {
		this.teaLeadInClassInfo = teaLeadInClassInfo;
	}

	
	
}
