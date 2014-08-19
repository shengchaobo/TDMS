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
import cn.nit.bean.table1.T151Bean;
import cn.nit.bean.table1.T181Bean;
import cn.nit.dao.table1.T18DAO;
import cn.nit.excel.imports.table1.T181Excel;
import cn.nit.service.table1.T181Service;
import cn.nit.util.TimeUtil;


public class T181Action {
	
	/**  表181的Service类  */
	private T181Service t181Ser = new T181Service() ;
	
	/**  表181的Bean实体类  */
	private T181Bean t181Bean = new T181Bean() ;
	
	/**  表181的Dao类  */
	private T18DAO t181Dao = new T18DAO() ;
	
	/**  表181的Excel实体类  */
	private T181Excel t181Excel = new T181Excel() ;
	
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
	
	/**  逐条插入数据  */
	public void insert(){
//		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		t181Bean.setTime(new Date()) ;
		t181Bean.setFillDept("1012");//教务处
//		System.out.println(t181Bean.getCooperInsLevel());
//		System.out.println(t181Bean.getUnitLevel());
//		System.out.println(t181Bean.getUnitID());
//		System.out.println(t151Bean.getResInsID());
//		System.out.println(t151Bean.getResInsName());
		//这还没确定,设置填报者的职工号与部门号
//		UserRoleBean userinfo = (UserRoleBean)getSession().getAttribute("userinfo") ;
//		undergraCSBaseTea.setFillTeaID(userinfo.getTeaID()) ;
		boolean flag = t181Ser.insert(t181Bean) ;
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

			String pages = t181Ser.auditingData(cond, "1012", Integer.parseInt(page), Integer.parseInt(rows)) ;
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
////		System.out.println("輸出輸出輸出");
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
//		String pages = t181Ser.auditingData(conditions, "1012", Integer.parseInt(page), Integer.parseInt(rows)) ;
//		PrintWriter out = null ;
//		
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
//	/**  生成查询条件  （查询数据） */
//	public void auditingConditions(){
//		
//		String sqlConditions = t181Ser.gernateAuditingConditions(seqNum, startTime, endTime) ;
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
//	
	/**  编辑数据  */
	public void edit(){

//		System.out.println("插入数据");
		t181Bean.setTime(new Date());
		t181Bean.setFillDept("1012");
		boolean flag = t181Ser.update(t181Bean) ;
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
		boolean flag = t181Ser.deleteCoursesByIds(ids) ;
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
			
			List<T181Bean> list = t181Dao.totalList();
			
			String sheetName = this.getExcelName();
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("合作机构名称");columns.add("合作机构类型");columns.add("合作机构级别");columns.add("签订协议时间");
			columns.add("我方单位");columns.add("单位号");columns.add("我方单位级别");columns.add("备注");

			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("CooperInsName", 1);maplist.put("CooperInsType", 2);maplist.put("CooperInsLevel", 3);maplist.put("SignedTime", 4);
			maplist.put("UnitName", 5);maplist.put("UnitID", 6);maplist.put("UnitLevel", 7);maplist.put("Note", 8);
			
			//inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());
			inputStream = new ByteArrayInputStream(t181Excel.batchExport(list, sheetName, maplist, columns).toByteArray());
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

	public T181Bean getT181Bean() {
		return t181Bean;
	}

	public void setT181Bean(T181Bean t181Bean) {
		this.t181Bean = t181Bean;
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

	public static void main(String args[]){
		String match = "[\\d]+" ;
		System.out.println("23gfhf4".matches(match)) ;
	}

}
