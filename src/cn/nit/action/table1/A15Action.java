package cn.nit.action.table1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table1.A15Bean;
import cn.nit.service.table1.A15Service;
import cn.nit.util.ExcelUtil;

public class A15Action {
	

	/**  表A15的Service类  */
	private A15Service a15Ser = new A15Service() ;
	
	/**  表A15的Bean实体类  */
	private A15Bean a15Bean = new A15Bean() ;

	/**  为界面加载数据  */
	public void auditingData(){
		
//		System.out.println("=========");
		Date date=new Date();
		String cuYear=date.toString();
		String year=cuYear.substring(cuYear.length()-4, cuYear.length());
		
		String pages = a15Ser.autidingdata(year);
//		System.out.println("pages:"+pages);
		PrintWriter out = null ;
//		boolean flag=false;
//		if(pages!=null){
//			flag=true;
//		}
//		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			out.print(pages) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	public String execute() throws Exception{

		getResponse().setContentType("application/octet-stream;charset=UTF-8") ;
		return "success" ;
	}
	


//	public InputStream getInputStream(){
//
//		InputStream inputStream = null ;
//
//		try {
//			inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel().toByteArray()) ;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null ;
//		}
//
//		return inputStream ;
//	}


	
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

	public A15Bean getA15Bean() {
		return a15Bean;
	}

	public void setA15Bean(A15Bean a15Bean) {
		this.a15Bean = a15Bean;
	}

	

}
