package cn.nit.action.table3;

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
import cn.nit.bean.table3.T33_Bean;
import cn.nit.dao.table3.T33_DAO;
import cn.nit.excel.imports.table3.T33Excel;


import cn.nit.service.table3.T33_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

public class T33_Action {
	
private T33_Service t33_Service = new T33_Service() ;
	
	private T33_Bean t33_Bean = new T33_Bean() ;
	
	private T33_DAO t33_DAO = new T33_DAO();
	/**  表181的Excel实体类  */
	private T33Excel t33Excel = new T33Excel() ;
	
	/**excel导出名字*/
	private String excelName; //
	
	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
	
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
		t33_Bean.setTime(new Date()) ;
		//这还没确定,设置填报者的职工号与部门号
		//UserInfo userinfo = (UserInfo)getSession().getAttribute("userinfo") ;
		//undergraCSBaseTea.setFillTeaID(userinfo.getTeaID()) ;
		boolean flag = t33_Service.insert(t33_Bean) ;
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
		
 	System.out.println("愁死我得了");
		
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
		String pages = t33_Service.auditingData(cond, null, Integer.parseInt(page), Integer.parseInt(rows)) ;
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

//		System.out.println("插入数据");
		t33_Bean.setTime(new Date());

		boolean flag = t33_Service.update(t33_Bean) ;
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
		boolean flag = t33_Service.deleteCoursesByIds(ids) ;
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
			
			List<T33_Bean> list = t33_DAO.totalList(this.getSelectYear());
			
			String sheetName = this.getExcelName();
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("教学单位");columns.add("单位号");columns.add("专业名称");
			columns.add("专业代码");columns.add("专业方向名称");columns.add("批准设置时间");
			columns.add("首次招生时间");columns.add("修业年限");columns.add("特色专业");columns.add("重点专业");
			columns.add("专业带头人姓名");columns.add("专业带头人是否专职");columns.add("专业负责人姓名");columns.add("专业负责人是否专职");
			columns.add("备注");
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("TeaUnit", 1);maplist.put("UnitID", 2);maplist.put("MajorName", 3);maplist.put("MajorID", 4);
			maplist.put("MajorFieldName", 5);maplist.put("AppvlSetTime", 6);maplist.put("FirstAdmisTime", 7);maplist.put("MajorYearLimit", 8);
			maplist.put("IsSepcialMajor", 9);maplist.put("IsKeyMajor", 10);maplist.put("MajorLeader", 11);maplist.put("LIsFullTime", 12);
			maplist.put("MajorChargeMan", 13);maplist.put("CIsFullTime", 14);maplist.put("Note",15);
			
			//inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());
			inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist, columns).toByteArray());
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

	
	
	public T33_Service getT33_Service() {
		return t33_Service;
	}

	public void setT33_Service(T33_Service t33Service) {
		t33_Service = t33Service;
	}

	public T33_Bean getT33_Bean() {
		return t33_Bean;
	}

	public void setT33_Bean(T33_Bean t33Bean) {
		t33_Bean = t33Bean;
	}

	public T33_DAO getT33_DAO() {
		return t33_DAO;
	}

	public void setT33_DAO(T33_DAO t33DAO) {
		t33_DAO = t33DAO;
	}

	public T33Excel getT33Excel() {
		return t33Excel;
	}

	public void setT33Excel(T33Excel t33Excel) {
		this.t33Excel = t33Excel;
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
