package cn.nit.action.table1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.table1.T12_Bean;
import cn.nit.bean.table1.T172_Bean;
//import cn.nit.dao.table1.T172DAO;
import cn.nit.service.table1.T172Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

public class T172_Action {
	
	/**  表172的Service类  */
	private T172Service t172Ser = new T172Service() ;
	
	/**  表172的Bean实体类  */
	private T172_Bean t172Bean = new T172_Bean() ;
	
//	/**  表17的数据库操作类  */
//	private T172DAO t172Dao = new T172DAO() ;
	
//	/**  表17的Excel实体类  */
//	private T17Excel t17Excel = new T17Excel() ;
	
	/**excel导出名字*/
	private String excelName; //
	
	public String getExcelName() {
		try {
			this.excelName = URLEncoder.encode(excelName, "UTF-8");
			//this.saveFile = new String(saveFile.getBytes("ISO-8859-1"),"UTF-8");// 中文乱码解决
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
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
	
	/**  导出时间  */
	private String selectYear ;
	
	public String getSelectYear() {
		return selectYear;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}

	/**  审核通过数据按年时间查询  */
	private String queryYear ;
	
	
	public String getQueryYear() {
		return queryYear;
	}

	public void setQueryYear(String queryYear) {
		this.queryYear = queryYear;
	}
	
	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	/**  逐条插入数据  */
	public void insert(){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
//		t172Bean.setTime(new Date()) ;

		boolean flag = t172Ser.insert(t172Bean) ;
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
			
			if(this.getSeqNum() == null && this.getStartTime() == null && this.getEndTime() == null 
					&& this.getQueryYear()==null){
				//System.out.println("+++++++++++++++++++++++");
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
				if(this.getQueryYear() != null){
					if(this.getSeqNum() ==null&&this.getStartTime() == null&&this.getEndTime() == null){
						conditions.append(" Time like '" + this.queryYear + "%'");
					}
					else{
						conditions.append(" and Time like '" + this.queryYear + "%'");
					}
					
				}else{
					 Calendar now = Calendar.getInstance();  
					 this.setQueryYear(now.get(Calendar.YEAR)+"");
					 conditions.append(" and Time like '" + this.queryYear + "%'");
				}
				cond = conditions.toString();
			}

			String pages = t172Ser.auditingData(cond, null, Integer.parseInt(page), Integer.parseInt(rows)) ;
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
	public void editSch(){

//		System.out.println("编辑数据！");
//		System.out.println(t172Bean.getActName());
//		System.out.println(t172Bean.getSeqNumber());
//		System.out.println(t172Bean.getActPlace());
//		System.out.println(t172Bean.getActType());
//		System.out.println(t172Bean.getFriName());
//		System.out.println(t172Bean.getNote());
//		System.out.println(t172Bean.getUnitID());
//		System.out.println(t172Bean.getUnitName());
//		System.out.println(t172Bean.getActTime());
		boolean flag = t172Ser.update(t172Bean) ;
		
		PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"编辑成功!!!\"}") ;
			}else{
				out.print("{\"state\":true,data:\"编辑失败!!!\"}") ;
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
//		System.out.println("ids=" + ids) ;
		boolean flag = t172Ser.deleteCoursesByIds(ids) ;
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
		
		UserinfoBean userBean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		List<T172_Bean> list   = null;
		String sheetName = null;
		
		if("111".equals(userBean.getRoleID())){
			String year = (String)request.getSession().getAttribute("allYear") ;
			list =  t172Ser.totalList(year);
			sheetName = "表1-7-2校友返校交流情况（党院办）";
		}else{
			list = t172Ser.totalList(this.getSelectYear());
			sheetName = this.excelName;
		}

		try {
			
			//List<T172_Bean> list = t172Ser.totalList();
			
			//String sheetName = this.excelName;
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("校友姓名");columns.add("交流活动名称");columns.add("交流活动类型");
			columns.add("交流时间");columns.add("交流地点");columns.add("协办单位");
			columns.add("单位号");columns.add("备注");

			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("FriName", 1);maplist.put("ActName", 2);maplist.put("ActType", 3);
			maplist.put("ActTime", 4);maplist.put("ActPlace", 5);maplist.put("UnitName", 6);
			maplist.put("UnitID", 7);maplist.put("Note", 8);
			//inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());
			inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist, columns).toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}

		return inputStream ;
	}
	

	public String execute() throws Exception{

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

	public T172_Bean getT172Bean() {
		return t172Bean;
	}

	public void setT172Bean(T172_Bean t172Bean) {
		this.t172Bean = t172Bean;
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
