package cn.nit.action.table1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table1.S15Bean;
import cn.nit.bean.table1.T11Bean;
import cn.nit.dao.table1.S15DAO;
import cn.nit.excel.imports.table1.S15Excel;
import cn.nit.service.table1.S15Service;

public class S15Action {
	
	/**S15的service類*/
	private S15Service s15Ser=new S15Service();
	
	/**S15的Bean類*/
	private S15Bean s15Bean=new S15Bean();
	
	/**S15的Excel類*/
	private S15Excel s15Excel=new S15Excel();
	
	/**S15的Dao類*/
	private S15DAO s15Dao=new S15DAO();
		
		/**
		 * 输出json格式数据
		 * */
		public void autidingdata(){
	
			System.out.println("=======================");
			Date time=new Date();
			String date=time.toString();
			String year=date.substring(date.length()-4);
			String info =s15Ser.autidingdata(year) ;
			PrintWriter out = null ;
	
			try{
				getResponse().setContentType("text/html; charset=UTF-8") ;
				out = getResponse().getWriter() ;
				out.print(info) ;
			}catch(Exception e){
				e.printStackTrace() ;
				return ;
			}finally{
				if(out != null){
					out.close() ;
				}
			}
		}
		
		/**Excel表導出*/
		public InputStream getInputStream(){

			InputStream inputStream = null ;

			try {
				
				List<S15Bean> list=new ArrayList<S15Bean>(); 
	            Date time=new Date();
	            String time1=time.toString();
	            String year=time1.substring(time1.length()-4, time1.length());
	            list=s15Dao.forExcel(year);
	            inputStream = new ByteArrayInputStream(s15Excel.writeExcel(list).toByteArray());
				

			} catch (Exception e) {
				e.printStackTrace();
				return null ;
			}

			return inputStream ;
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


}
