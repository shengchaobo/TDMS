package cn.nit.action.table3;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table1.S18Bean;
import cn.nit.bean.table3.A321_Bean;

import cn.nit.dao.table3.A321_DAO;
import cn.nit.excel.imports.table3.A321Excel;




import cn.nit.pojo.table3.A321POJO;
import cn.nit.service.table3.A321_Service;
import cn.nit.util.ExcelUtil;

public class A321_Action {
	
	
	private A321_Service a321_Service = new A321_Service() ;
		
		private A321_Bean a321_Bean = new A321_Bean() ;
		
		private A321_DAO a321_DAO=new A321_DAO();
	
		
		private A321Excel a321Excel = new A321Excel() ;

		


		



		

		
		/**  为界面加载数据  */
	public void auditingData(){
			
			System.out.println("一定输出来");
			Date date=new Date();	
			String sdate=date.toString();
			String year=sdate.substring(sdate.length()-4, sdate.length());
			
			
			String result = a321_Service.auditingData(year) ;
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



		
		public A321_Service getA321_Service() {
			return a321_Service;
		}




		public void setA321_Service(A321_Service a321Service) {
			a321_Service = a321Service;
		}




		public A321_Bean getA321_Bean() {
			return a321_Bean;
		}




		public void setA321_Bean(A321_Bean a321Bean) {
			a321_Bean = a321Bean;
		}
		
		
//		public InputStream getInputStream(){
//
//			InputStream inputStream = null ;
//
//			try {
//				
//				List<S18Bean> list=new ArrayList<S18Bean>(); 
//	            Date time=new Date();
//	            String time1=time.toString();
//	            String year=time1.substring(time1.length()-4, time1.length());
//	            list=s18Dao.forExcel(year);
//	            inputStream = new ByteArrayInputStream(s18Excel.writeExcel(list).toByteArray());
//				
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				return null ;
//			}
//
//			return inputStream ;
//		}
		
		public InputStream getInputStream(){
			
			InputStream inputStream =null;
			
			try{
				System.out.println("愁死了");
				Date date=new Date();	
				String sdate=date.toString();
				String year=sdate.substring(sdate.length()-4, sdate.length());
				List<A321POJO> list = a321_DAO.exportData(year);
				inputStream=new ByteArrayInputStream(a321Excel.exportExcel(list).toByteArray());
				
				
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
		public A321_DAO getA321_DAO() {
			return a321_DAO;
		}




		public void setA321_DAO(A321_DAO a321DAO) {
			a321_DAO = a321DAO;
		}




		public A321Excel getA321Excel() {
			return a321Excel;
		}




		public void setA321Excel(A321Excel a321Excel) {
			this.a321Excel = a321Excel;
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



		





		public static void main(String args[]){
			String match = "[\\d]+" ;
			System.out.println("23gfhf4".matches(match)) ;
		}


}
