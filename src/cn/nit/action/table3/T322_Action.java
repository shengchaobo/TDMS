package cn.nit.action.table3;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;


import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table3.T322_Bean;

import cn.nit.service.table3.T322_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

public class T322_Action {
	private T322_Service t322_Service = new T322_Service() ;
	
	
	private T322_Bean t322_Bean = new T322_Bean() ;
	
	/**  待审核数据的查询的序列号  */
	private Integer seqNum ;
	
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
	
	private String selectYear;
	
	public void insert(){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		t322_Bean.setTime(new Date()) ;
		//这还没确定,设置填报者的职工号与部门号
		//UserInfo userinfo = (UserInfo)getSession().getAttribute("userinfo") ;
		//undergraCSBaseTea.setFillTeaID(userinfo.getTeaID()) ;
		//boolean flag = t322_Service.insert(t322_Bean) ;
		boolean flag = false;
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


	
	public T322_Bean gett322_Bean() {
		return t322_Bean;
	}

	public void sett322_Bean(T322_Bean t322_Bean) {
		this.t322_Bean = t322_Bean;
	}
	
	/**  为界面加载数据  */
public void auditingData(){
		
/*//		System.out.println("輸出輸出輸出");
		
		if(this.page == null || this.page.equals("") || !page.matches("[\\d]+")){
			return ;
		}
		
		if(this.rows == null || this.rows.equals("") || !rows.matches("[\\d]+")){
			return ;
		}
		
		String cond = null;
		StringBuffer conditions = new StringBuffer();
		
		if(this.getSeqNum() == null && this.getStartTime() == null && this.getEndTime() == null){			
			cond = null;	
		}else{			
			if(this.getSeqNum()!=null){
				conditions.append(" and SeqNumber=" + this.getSeqNum()) ;
			}
			
			if(this.getStartTime() != null){
				conditions.append(" and cast(CONVERT(DATE, Time)as datetime)>=cast(CONVERT(DATE, '" 
						+ TimeUtil.changeFormat4(this.startTime) + "')as datetime)") ;
			}
			
			if(this.getEndTime() != null){
				conditions.append(" and cast(CONVERT(DATE, Time)as datetime)<=cast(CONVERT(DATE, '" 
						+ TimeUtil.changeFormat4(this.getEndTime()) + "')as datetime)") ;
			}
			cond = conditions.toString();
		}
		String pages = t322_Service.auditingData(cond, null, Integer.parseInt(page), Integer.parseInt(rows)) ;
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
		}*/
	}
	
/*	*//**  生成查询条件  （查询数据） *//*
	public void auditingConditions(){
		
		String sqlConditions = t322_Service.gernateAuditingConditions(seqNum, startTime, endTime) ;
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
	}*/
	
	/**  编辑数据  */
	public void edit(){

/*//		System.out.println("插入数据");
		t322_Bean.setTime(new Date());

		boolean flag = t322_Service.update(t322_Bean) ;
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
		}*/
	}
	
	/**  根据数据的id删除数据  */
	public void deleteCoursesByIds(){
/*		System.out.println("ids=" + ids) ;
		boolean flag = t322_Service.deleteCoursesByIds(ids) ;
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
		}*/
	}
	
	public InputStream getInputStream(){

		InputStream inputStream = null ;

		try {
			//inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel().toByteArray()) ;
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






	
	public T322_Service getT322_Service() {
		return t322_Service;
	}



	public void setT322_Service(T322_Service t322Service) {
		t322_Service = t322Service;
	}



	public T322_Bean getT322_Bean() {
		return t322_Bean;
	}



	public void setT322_Bean(T322_Bean t322Bean) {
		t322_Bean = t322Bean;
	}



	public Integer getSeqNum() {
		return seqNum;
	}



	public void setSeqNum(Integer seqNum) {
		this.seqNum = seqNum;
	}



	public Date getStartTime() {
		return startTime;
	}



	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}



	public Date getEndTime() {
		return endTime;
	}



	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}



	public String getIds() {
		return ids;
	}



	public void setIds(String ids) {
		this.ids = ids;
	}



	public String getPage() {
		return page;
	}



	public void setPage(String page) {
		this.page = page;
	}



	public String getRows() {
		return rows;
	}



	public void setRows(String rows) {
		this.rows = rows;
	}



	public String getSelectYear() {
		return selectYear;
	}



	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}



	public static void main(String args[]){
		String match = "[\\d]+" ;
		System.out.println("23gfhf4".matches(match)) ;
	}

}
