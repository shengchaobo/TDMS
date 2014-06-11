package cn.nit.action.table7;


import java.io.PrintWriter;
import java.util.Date;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table7.T745_Bean;
import cn.nit.service.table7.T745_Service;

public class T745_Action {
	
	T745_Service teachWorkAssessACSr=new T745_Service();
	
	T745_Bean teachWorkAssessAC=new T745_Bean();
	
	public void insert(){
		
		teachWorkAssessAC.setTime(new Date());
		
		boolean flag=teachWorkAssessACSr.insert(teachWorkAssessAC);
		
		PrintWriter out=null;
		
		
		
		try {
			getResponse().setContentType("text/html;charset=UTF-8");
			out=getResponse().getWriter();
			if(flag){
				out.print("{\"state\":true,data:\"录入成功!!!\"}");
			}else
			{
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
	


	public T745_Bean getTeachWorkAssessAC() {
		return teachWorkAssessAC;
	}


	public void setTeachWorkAssessAC(T745_Bean teachWorkAssessAC) {
		this.teachWorkAssessAC = teachWorkAssessAC;
	}
	
}
