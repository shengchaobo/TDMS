package cn.nit.action.table7;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table7.T744_Bean;
import cn.nit.service.table7.T744_Service;

public class T744_Action {
	
	T744_Service t744_Sr=new T744_Service();
	
	T744_Bean majBuildAssessAC=new T744_Bean();
	
	public void insert(){
		
		majBuildAssessAC.setTime(new Date());
		boolean flag;
		PrintWriter out=null;
		flag= t744_Sr.insert(majBuildAssessAC);
		
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
	public T744_Bean getMajBuildAssessAC() {
		return majBuildAssessAC;
	}
	public void setMajBuildAssessAC(T744_Bean majBuildAssessAC) {
		this.majBuildAssessAC = majBuildAssessAC;
	}
	


}
