package cn.nit.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.CheckInfo;
import cn.nit.bean.UserinfoBean;
import cn.nit.constants.Constants;
import cn.nit.service.CheckService;

public class CheckInfoAction {
	
	private String tableName;
	private int checkType;
	
	private CheckInfo checkInfo = new CheckInfo();
	
	private CheckService check_services = new CheckService();
		
	
	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	public void loadInfo(){		
		//System.out.println(this.getCheckType());
		String checkInfo  = check_services.loadInfo(this.getTableName(),this.getCheckType());
		
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
		
		//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		//System.out.println(checkInfo.getCheckType());
		if(checkInfo.getCheckType() == Constants.CTypeTwo){
			//具体教学单位
			UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
			String fillUnitID = bean.getUnitID();
			checkInfo.setFillUnitID(fillUnitID);
		}
			
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

	public void setCheckType(int checkType) {
		this.checkType = checkType;
	}

	public int getCheckType() {
		return checkType;
	}
}
