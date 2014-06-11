package cn.nit.action.table7;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table7.T743_Bean;
import cn.nit.service.table7.T743_Service;

public class T743_Action {

	T743_Service t743_Sr=new T743_Service();
	
	T743_Bean courseBuildAssessAC=new T743_Bean();
	
	public void insert(){
		
		courseBuildAssessAC.setTime(new Date());
		boolean flag;
		PrintWriter out=null;
		
		flag=t743_Sr.insert(courseBuildAssessAC);
		
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

	public T743_Bean getCourseBuildAssessAC() {
		return courseBuildAssessAC;
	}

	public void setCourseBuildAssessAC(T743_Bean courseBuildAssessAC) {
		this.courseBuildAssessAC = courseBuildAssessAC;
	}
	
	
}
