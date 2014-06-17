package cn.nit.action.table1;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table1.T12Bean;
import cn.nit.service.table1.T12Service;
import cn.nit.util.TimeUtil;

public class T12Action {
	
	
	/**  表12的Service类  */
	private T12Service t12Ser = new T12Service() ;
	
	/**  表12的Bean实体类  */
	private T12Bean t12Bean = new T12Bean() ;
	/**  逐条插入数据  */
//	public void insert(){
////		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
//		t12Bean.setTime(new Date()) ;
////		System.out.println(t151Bean.getResInsID());
////		System.out.println(t151Bean.getResInsName());
//		//这还没确定,设置填报者的职工号与部门号
////		UserRoleBean userinfo = (UserRoleBean)getSession().getAttribute("userinfo") ;
////		undergraCSBaseTea.setFillTeaID(userinfo.getTeaID()) ;
//		
//		boolean flag = t12Ser.insert(t12Bean) ;
//		PrintWriter out = null ;
//		
//		try{
//			getResponse().setContentType("text/html; charset=UTF-8") ;
////			getResponse().setHeader("Content-type", "text/html");  
//			out = getResponse().getWriter() ;
//			if(flag){
//				out.print("{\"state\":true,data:\"录入成功!!!\"}") ;
//			}else{
//				out.print("{\"state\":false,data:\"录入失败!!!\"}") ;
//			}
//		}catch(Exception e){
//			e.printStackTrace() ;
//			out.print("{\"state\":false,data:\"录入失败!!!\"}") ;
//		}finally{
//			if(out != null){
//				out.close() ;
//			}
//		}
//		out.flush() ;
//	}

	/**  为界面加载数据  */
	public void auditingData(){
		
		Date date=new Date();
		String cuYear=date.toString();
		String year=cuYear.substring(cuYear.length()-4, cuYear.length());
		
		String pages = t12Ser.auditingData(year) ;
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

	public T12Bean getT12Bean() {
		return t12Bean;
	}

	public void setT12Bean(T12Bean t12Bean) {
		this.t12Bean = t12Bean;
	}
	

}
