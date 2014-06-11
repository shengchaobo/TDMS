package cn.nit.action.table1;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table1.T16Bean;
import cn.nit.service.table1.T16Service;


public class T16Action {
	
	/**  表16的Service类  */
	private T16Service t16Ser = new T16Service() ;
	
	/**  表16的Bean实体类  */
	private T16Bean t16Bean = new T16Bean() ;
	
	/**  待审核数据的查询的序列号  */
	private int seqNum ;
//	
//	/**  待审核数据查询的起始时间  */
//	private Date startTime ;
//	
//	/**  待审核数据查询的结束时间  */
//	private Date endTime ;
//	
//	/**  数据的SeqNumber编号  */
//	private String ids ;
//	
//	/**  当前查询的是第几页  */
//	private String page ;
//	
//	/**每页显示的条数  */
//	private String rows ;
//	
	/**  逐条插入数据  */
	public void insert(){
//		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		t16Bean.setTime(new Date()) ;
		//这还没确定,设置填报者的职工号与部门号
//		UserRoleBean userinfo = (UserRoleBean)getSession().getAttribute("userinfo") ;
//		undergraCSBaseTea.setFillTeaID(userinfo.getTeaID()) ;
		boolean flag = t16Ser.insert(t16Bean) ;
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
		
//		System.out.println("輸出輸出輸出");
		Date currentTi=new Date();
		String str=currentTi.toString();
		String Year=str.substring(str.length()-4, str.length()) ;
		
		String pages = t16Ser.auditingData(Year) ;
//		System.out.println("pages="+pages);
		PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
//			if(pages=="")
//			{
//				out.print("{success:false,errorMsg:'还未录入数据！请添加shu'}");
//			}else
//			{
				out.print(pages) ;
//			}
				
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

		boolean flag = t16Ser.update(t16Bean) ;
		PrintWriter out = null ;
		
		try{
			out = getResponse().getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"删除成功!!!\"}") ;
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

	public T16Bean getT16Bean() {
		return t16Bean;
	}

	public void setT16Bean(T16Bean t16Bean) {
		this.t16Bean = t16Bean;
	}

	public void setSeqNum(int seqNum){
		this.seqNum = seqNum ;
	}
	
	

}
