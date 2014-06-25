package cn.nit.action.table3;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.other.UserRoleBean;

import cn.nit.bean.table3.S31_Bean;
import cn.nit.dao.table3.S31_DAO;
import cn.nit.excel.imports.table3.S31Excel;




import cn.nit.pojo.table3.S31POJO;
import cn.nit.service.table3.S31_Service;


public class S31_Action {
	
private S31_Service s31_Service = new S31_Service() ;
	
	private S31_Bean s31_Bean = new S31_Bean() ;
	
	private S31_DAO  s31_DAO=new S31_DAO();

	
	private S31Excel s31Excel = new S31Excel() ;
	



	

	
	/**  为界面加载数据  */
	public void auditingData(){
		
		System.out.println("一定输出来");
		Date date=new Date();	
		String sdate=date.toString();
		String year=sdate.substring(sdate.length()-4, sdate.length());
		
		
		String result = s31_Service.auditingData(year) ;
		PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			out.print(result) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	
	public InputStream getInputStream(){
		
		InputStream inputStream =null;
		
		try{
			System.out.println("愁死了");
			Date date=new Date();	
			String sdate=date.toString();
			String year=sdate.substring(sdate.length()-4, sdate.length());
			List<S31POJO> list = s31_DAO.exportData(year);
			inputStream=new ByteArrayInputStream(s31Excel.exportExcel(list).toByteArray());
			
			
		}catch (Exception e){
			e.printStackTrace();
			return null;
			
			
		}
		return inputStream;
		
	}













	public String execute() throws Exception{

		getResponse().setContentType("application/octet-stream;charset=UTF-8") ;
		return "success" ;
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
	
	public UserRoleBean getUserinfo(){
		return (UserRoleBean)getSession().getAttribute("userinfo") ;
	}



	
	public S31_Service getS31_Service() {
		return s31_Service;
	}




	public void setS31_Service(S31_Service s31Service) {
		s31_Service = s31Service;
	}




	public S31_Bean getS31_Bean() {
		return s31_Bean;
	}




	public void setS31_Bean(S31_Bean s31Bean) {
		s31_Bean = s31Bean;
	}




	public static void main(String args[]){
		String match = "[\\d]+" ;
		System.out.println("23gfhf4".matches(match)) ;
	}

}
