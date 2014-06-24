package cn.nit.action.table1;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.ByteArrayInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table1.S17Bean;
import cn.nit.bean.table1.T11Bean;
import cn.nit.bean.table1.T17Bean;
import cn.nit.dao.table1.S17DAO;
import cn.nit.excel.imports.table1.S17Excel;
import cn.nit.excel.imports.table1.T11Excel;
import cn.nit.service.table1.S17Service;
import cn.nit.util.ExcelUtil;

public class S17Action {
	

	/**  表S17的Service类  */
	private S17Service s17Ser = new S17Service() ;
	
	/**  表S17的Bean实体类  */
	private S17Bean s17Bean = new S17Bean() ;
	
	/**  表17的DAO类  */
	private S17DAO s17Dao = new S17DAO() ;
	
	/**  表17的Excel实体类  */
	private S17Excel s17Excel = new S17Excel() ;
	

	/**  为界面加载数据  */
	public void auditingData(){
		
//		System.out.println("=========");
		Date date=new Date();
		String cuYear=date.toString();
		String year=cuYear.substring(cuYear.length()-4, cuYear.length());
		
		String pages = s17Ser.autidingdata(year);
//		System.out.println("pages:"+pages);
		PrintWriter out = null ;
		
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
	
	public InputStream getInputStream(){

		InputStream inputStream = null ;

		try {
			
			List<S17Bean> list=new ArrayList<S17Bean>(); 
            Date time=new Date();
            String time1=time.toString();
            String year=time1.substring(time1.length()-4, time1.length());
            list=s17Dao.forExcel(year);
            inputStream = new ByteArrayInputStream(s17Excel.writeExcel(list).toByteArray());
			

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
	
	public UserRoleBean getUserinfo(){
		return (UserRoleBean)getSession().getAttribute("userinfo") ;
	}

	public S17Bean getS17Bean() {
		return s17Bean;
	}

	public void setS17Bean(S17Bean s17Bean) {
		this.s17Bean = s17Bean;
	}

}
