package cn.nit.action.table1;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table1.T11Bean;

import cn.nit.service.table1.T11Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

/**
 * 
 * @author lenovo
 */
public class T11Action {

	/**  表11的Service类  */
	private T11Service t11Ser = new T11Service() ;
	
	/**  表11的Bean实体类  */
	private T11Bean t11Bean = new T11Bean() ;
	/** 接收年份*/
	private String Year ;
	
	
	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		this.Year = year;
	}

	/**  逐条插入数据  */
	public void insert(){
//		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		t11Bean.setTime(new Date()) ;
//		System.out.println(t151Bean.getResInsID());
//		System.out.println(t151Bean.getResInsName());
		//这还没确定,设置填报者的职工号与部门号
//		UserRoleBean userinfo = (UserRoleBean)getSession().getAttribute("userinfo") ;
//		undergraCSBaseTea.setFillTeaID(userinfo.getTeaID()) ;
		
		boolean flag = t11Ser.insert(t11Bean) ;
		PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
//			getResponse().setHeader("Content-type", "text/html");  
			out = getResponse().getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"录入成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"录入失败!!!\"}") ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"录入失败!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
		out.flush() ;
	}

	/**  为界面加载数据  */
	public void auditingData(){
		
		Date date=new Date();
		String cuYear=date.toString();
		String year=cuYear.substring(cuYear.length()-4, cuYear.length());
		
		String pages = t11Ser.auditingData(year) ;
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
	

	/**  编辑数据  */
	public void edit(){
		
		t11Bean.setTime(new Date()) ;
		Date ti=TimeUtil.changeDateY(this.Year);
		t11Bean.setSch_BeginTime(ti);
//		System.out.println(t11Bean.getTime());
		boolean flag = t11Ser.update(t11Bean) ;
		PrintWriter out = null ;
		
		try{
			out = getResponse().getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"修改成功!!!\"}") ;
			}else{
				out.print("{\"state\":true,data:\"删除失败!!!\"}") ;
			}
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"系统错误，请联系管理员!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
//	/**  根据数据的id删除数据  */
//	public void deleteCoursesByIds(){
//		System.out.println("ids=" + ids) ;
//		boolean flag = t151Ser.deleteCoursesByIds(ids) ;
//		PrintWriter out = null ;
//		
//		try{
//			out = getResponse().getWriter() ;
//			
//			if(flag){
//				out.print("{\"state\":true,data:\"数据删除成功!!!\"}") ;
//			}else{
//				out.print("{\"state\":false,data:\"数据删除失败!!!\"}") ;
//			}
//			
//			out.flush() ;
//		}catch(Exception e){
//			e.printStackTrace() ;
//			out.print("{\"state\":false,data:\"系统错误，请联系管理员!!!\"}") ;
//		}finally{
//			if(out != null){
//				out.close() ;
//			}
//		}
//	}
	
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
//
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

	public T11Bean getT11Bean() {
		return t11Bean;
	}

	public void setT11Bean(T11Bean t11Bean) {
		this.t11Bean = t11Bean;
	}
	
	public static void main(String args[]){
		String match = "[\\d]+" ;
		System.out.println("23gfhf4".matches(match)) ;
	}
}
