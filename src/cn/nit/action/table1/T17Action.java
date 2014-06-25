package cn.nit.action.table1;

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
import cn.nit.bean.table1.T151Bean;
import cn.nit.bean.table1.T17Bean;
import cn.nit.dao.table1.T17DAO;
import cn.nit.excel.imports.table1.T17Excel;
import cn.nit.service.table1.T17Service;
import cn.nit.util.TimeUtil;

public class T17Action {
	
	/**  表17的Service类  */
	private T17Service t17Ser = new T17Service() ;
	
	/**  表17的Bean实体类  */
	private T17Bean t17Bean = new T17Bean() ;
	
	/**  表17的数据库操作类  */
	private T17DAO t17Dao = new T17DAO() ;
	
	/**  表17的Excel实体类  */
	private T17Excel t17Excel = new T17Excel() ;
	
	/**excel导出名字*/
	private String excelName; //
	
	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	private Date BuildYear;
	
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
			
//			System.out.println("輸出輸出輸出");
			
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
					conditions.append(" SeqNumber=" + this.getSeqNum()) ;
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

			String pages = t17Ser.auditingData(cond, null, Integer.parseInt(page), Integer.parseInt(rows)) ;
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
//

//	/**  为界面加载数据  */
//	public void auditingData(){
//		
//		if(this.page == null || this.page.equals("") || !page.matches("[\\d]+")){
//			return ;
//		}
//		
//		if(this.rows == null || this.rows.equals("") || !rows.matches("[\\d]+")){
//			return ;
//		}
//		
//		String conditions = (String) getSession().getAttribute("auditingConditions") ;
//		String pages = t17Ser.auditingData(null, null, Integer.parseInt(page), Integer.parseInt(rows)) ;
////		System.out.println(pages);
//		PrintWriter out = null ;
//		try{
//			getResponse().setContentType("text/html; charset=UTF-8") ;
//			out = getResponse().getWriter() ;
//			out.print(pages) ;
//		}catch(Exception e){
//			e.printStackTrace() ;
//			return ;
//		}finally{
//			if(out != null){
//				out.close() ;
//			}
//		}
//	}
//	
//	/**  生成查询条件 （查询数据）  */
//	public void auditingConditions(){
//		
//		String sqlConditions = t17Ser.gernateAuditingConditions(seqNum, startTime, endTime) ;
//		getSession().setAttribute("auditingConditions", sqlConditions) ;
//		PrintWriter out = null ;
//		
//		try{
//			out = getResponse().getWriter() ;
//			out.print("{\"state\":true,data:\"查询失败!!!\"}") ;
//			out.flush() ;
//		}catch(Exception e){
//			e.printStackTrace() ;
//			out.print("{\"state\":false,data:\"查询失败!!!\"}") ;
//		}finally{
//			if(out != null){
//				out.close() ;
//			}
//		}
//	}
	
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
	
	/**数据导出*/
	public InputStream getInputStream(){

		InputStream inputStream = null ;

		try {
			
			List<T17Bean> list = t17Dao.totalList();
			
			String sheetName = this.getExcelName();
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("校友会名称");columns.add("设立时间");columns.add("地点");columns.add("备注");

			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("ClubName", 1);maplist.put("BuildYear", 2);maplist.put("Place", 3);maplist.put("Time", 4);
			maplist.put("Note", 5);
			//inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());
			inputStream = new ByteArrayInputStream(t17Excel.batchExport(list, sheetName, maplist, columns).toByteArray());
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

	public T17Bean getT17Bean() {
		return t17Bean;
	}

	public void setT17Bean(T17Bean t17Bean) {
		this.t17Bean = t17Bean;
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
