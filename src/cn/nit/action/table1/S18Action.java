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

import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table1.S18Bean;
import cn.nit.dao.table1.S18DAO;
import cn.nit.excel.imports.table1.S18Excel;

import cn.nit.service.table1.S18Service;
import cn.nit.util.TimeUtil;

public class S18Action {
	

	/**  表S18的Service类  */
	private S18Service s18Ser = new S18Service() ;
	
	/**  表S18的Bean实体类  */
	private S18Bean s18Bean = new S18Bean() ;
	
	/**  表S18的DAO类  */
	private S18DAO s18Dao = new S18DAO() ;
	
	/**  表S18的Excel类  */
	private S18Excel s18Excel = new S18Excel() ;


	/**  为界面加载数据  */
	public void auditingData(){
		
//		System.out.println("=========");
		Date date=new Date();
		String cuYear=date.toString();
		String year=cuYear.substring(cuYear.length()-4, cuYear.length());
		
		String pages = s18Ser.autidingdata(year);
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
			
			List<S18Bean> list=new ArrayList<S18Bean>(); 
            Date time=new Date();
            String time1=time.toString();
            String year=time1.substring(time1.length()-4, time1.length());
            list=s18Dao.forExcel(year);
            inputStream = new ByteArrayInputStream(s18Excel.writeExcel(list).toByteArray());
			

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

	public S18Bean getS18Bean() {
		return s18Bean;
	}

	public void setS18Bean(S18Bean s18Bean) {
		this.s18Bean = s18Bean;
	}

}
