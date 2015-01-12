package cn.nit.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.CheckInfo;
import cn.nit.service.CheckService;

public class CheckInfoAction {
	
	private String tableName;
	private CheckInfo checkInfo = new CheckInfo();
	
	private CheckService check_services = new CheckService();
	
	public void loadInfo(){
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		HttpServletResponse response = ServletActionContext.getResponse();
			
		String checkInfo  = check_services.loadInfo(this.getTableName());
		
		PrintWriter out = null ;
		
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			
			if(checkInfo == null){
				out.print("{\"state\":true,data:\"无审核不通过信息\"}") ;
			}else{
				out.print("{\"state\":false,data:\"" + checkInfo + "\"}") ;
			}
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	public void addInfo(){
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		HttpServletResponse response = ServletActionContext.getResponse();
			
		boolean flag  = check_services.addInfo(checkInfo);
		
		PrintWriter out = null ;
		
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"理由录入成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"理由录入失败!!!\"}") ;
			}
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"理由录入失败!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}

	public void setCheckInfo(CheckInfo checkInfo) {
		this.checkInfo = checkInfo;
	}

	public CheckInfo getCheckInfo() {
		return checkInfo;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}
}
