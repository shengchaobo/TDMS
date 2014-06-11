package cn.nit.action.table1;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table1.T17Bean;
import cn.nit.service.table1.T17Service;

public class T17Action {
	
	/**  表17的Service类  */
	private T17Service t17Ser = new T17Service() ;
	
	/**  表17的Bean实体类  */
	private T17Bean t17Bean = new T17Bean() ;
	
	private Date BuildYear;
	
	/**  待审核数据的查询的序列号  */
	private int seqNum ;
	
	/**  待审核数据查询的起始时间  */
	private Date startTime ;
	
	/**  待审核数据查询的结束时间  */
	private Date endTime ;
	
	/**  数据的SeqNumber编号  */
	private String ids ;
	
	/**  当前查询的是第几页  */
	private String page ;
	
	/**每页显示的条数  */
	private String rows ;
	
	/**  逐条插入数据  */
	public void insert(){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		t17Bean.setTime(new Date()) ;
//		String cuYear=(this.BuildYear).toString();
//		String year=cuYear.substring(cuYear.length()-4, cuYear.length()) ;
//		t17Bean.setBuildYear(year);
		//这还没确定,设置填报者的职工号与部门号
//		UserRoleBean userinfo = (UserRoleBean)getSession().getAttribute("userinfo") ;
//		undergraCSBaseTea.setFillTeaID(userinfo.getTeaID()) ;
		boolean flag = t17Ser.insert(t17Bean) ;
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
		
		if(this.page == null || this.page.equals("") || !page.matches("[\\d]+")){
			return ;
		}
		
		if(this.rows == null || this.rows.equals("") || !rows.matches("[\\d]+")){
			return ;
		}
		
		String conditions = (String) getSession().getAttribute("auditingConditions") ;
		String pages = t17Ser.auditingData(null, null, Integer.parseInt(page), Integer.parseInt(rows)) ;
//		System.out.println(pages);
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
	
	/**  生成查询条件 （查询数据）  */
	public void auditingConditions(){
		
		String sqlConditions = t17Ser.gernateAuditingConditions(seqNum, startTime, endTime) ;
		getSession().setAttribute("auditingConditions", sqlConditions) ;
		PrintWriter out = null ;
		
		try{
			out = getResponse().getWriter() ;
			out.print("{\"state\":true,data:\"查询失败!!!\"}") ;
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"查询失败!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	/**  编辑数据  */
	public void editSch(){

		System.out.println("编辑数据！");
		t17Bean.setTime(new Date());
//		System.out.println(t17Bean.getTime());
//		System.out.println(t17Bean.getSeqNumber());
//		System.out.println(t17Bean.getClubName());
//		System.out.println(t17Bean.getNote());
//		System.out.println(t17Bean.getPlace());
//		System.out.println(t17Bean.getBuildYear());
		boolean flag = t17Ser.update(t17Bean) ;
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
	
	/**  根据数据的id删除数据  */
	public void deleteCoursesByIds(){
		System.out.println("ids=" + ids) ;
		boolean flag = t17Ser.deleteCoursesByIds(ids) ;
		PrintWriter out = null ;
		
		try{
			out = getResponse().getWriter() ;
			
			if(flag){
				out.print("{\"state\":true,data:\"数据删除成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"数据删除失败!!!\"}") ;
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
//	public String execute() throws Exception{
//
//		getResponse().setContentType("application/octet-stream;charset=UTF-8") ;
//		return "success" ;
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

	public T17Bean getT17Bean() {
		return t17Bean;
	}

	public void setT17Bean(T17Bean t17Bean) {
		this.t17Bean = t17Bean;
	}

	public void setSeqNum(int seqNum){
		this.seqNum = seqNum ;
	}
	
	public void setStartTime(Date startTime){
		this.startTime = startTime ;
	}
	
	public void setEndTime(Date endTime){
		this.endTime = endTime ;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setPage(String page){
		this.page = page ;
	}
	
	public void setRows(String rows){
		this.rows = rows ;
	}
	 public Date getBuildYear() {
		return BuildYear;
	}

	public void setBuildYear(Date BuildYear) {
		this.BuildYear = BuildYear;
	}

	public static void main(String arg[])
	 {
//         Date da=new Date();
//         String str=da.toString();
//         String str1=str.substring(str.length()-4, str.length());
//         System.out.println(str1);
	 }

}
