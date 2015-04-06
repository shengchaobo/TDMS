package cn.nit.action.table7;


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
import cn.nit.bean.table4.T441_Bean;
import cn.nit.bean.table7.T733_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.table7.T733_DAO;
import cn.nit.pojo.table7.T733POJO;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table7.T733_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

public class T733_Action {
	
	T733_Service t733_Sr=new T733_Service();
	
	T733_Bean eachUnitTeachResActInfo=new T733_Bean();
	
//	private T733_DAO t733_Dao=new T733_DAO();
	
	
	/**  部门管理Service类  */
	private DiDepartmentService deSer = new DiDepartmentService() ;
	
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
	
	/**  下载的excelName  */
	private String excelName ;
	/**导出选择年份*/
	private String selectYear;
	
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
	
	public void insert(){
		eachUnitTeachResActInfo.setTime(new Date());
		
		//具体教学单位
	    UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String fillUnitID = bean.getUnitID();
		eachUnitTeachResActInfo.setFillUnitID(fillUnitID);
		String teaUnit = deSer.getName(fillUnitID);
		eachUnitTeachResActInfo.setUnitID(fillUnitID);
		eachUnitTeachResActInfo.setUnitName(teaUnit);
		PrintWriter out=null;
		
		boolean flag;
		flag=t733_Sr.insert(eachUnitTeachResActInfo);
		
		try {
			getResponse().setContentType("text/html;charset=UTF-8");
			out=getResponse().getWriter();
			if (flag) {
				out.print("{\"state\":true,data:\"录入成功!!!\"}");
				
			} else {
				out.print("{\"state\":false,data:\"录入失败!!!\"}");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("{\"state\":false,data:\"录入失败!!!\"}");
		}finally{
			if(out!=null){
				out.close();
			}
		}
		out.flush();
		
		
	}
	
	
/**  为界面加载数据  */
	
	public void auditingData(){
		
		if(this.page == null || this.page.equals("") || !page.matches("[\\d]+")){
			return ;
		}
		
		if(this.rows == null || this.rows.equals("") || !rows.matches("[\\d]+")){
			return ;
		}
		
		String cond = null;
		StringBuffer conditions = new StringBuffer();
		
		if( this.getQueryYear()==null){			
			 Calendar now = Calendar.getInstance();  
			 this.setQueryYear(now.get(Calendar.YEAR)+"");
			 conditions.append(" and Time like '" + this.queryYear + "%'");
			 cond = conditions.toString();
		}else{	
				conditions.append(" and Time like '" + this.queryYear + "%'");
				cond = conditions.toString();
		}

		
		//具体教学单位
	    UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String fillUnitID = bean.getUnitID();
		
		String pages = t733_Sr.auditingData(cond, fillUnitID, Integer.parseInt(page), Integer.parseInt(rows)) ;
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
		//eachUnitTeachResActInfo.setTime(new Date());
		boolean flag=t733_Sr.update(eachUnitTeachResActInfo);
		
		PrintWriter out=null;
		
		try {
			out=getResponse().getWriter();
			if(flag){
				out.print("{\"state\":true,data:\"编辑成功!!!\"}") ;
			}else{
				out.print("{\"state\":true,data:\"编辑失败!!!\"}") ;
			}
			out.flush() ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("{\"state\":false,data:\"系统错误，请联系管理员!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}

	/**  根据数据的id删除数据  */
	public void deleteByIds(){
		System.out.println("ids=" + ids) ;
		boolean flag = t733_Sr.deleteByIds(ids) ;
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
	
	public InputStream getInputStream(){
		
		
		//具体教学单位
	    UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String fillUnitID = bean.getUnitID();

		InputStream inputStream = null ;
		
		UserinfoBean userBean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String sheetName = null;
		List<T733_Bean> list = null;
		
		if("111".equals(userBean.getRoleID())){
			String year = (String)request.getSession().getAttribute("allYear") ;
			list = t733_Sr.totalList(year);
			sheetName = "表7-3-3各单位开展教学研究活动情况";
		}else{						
			list = t733_Sr.totalList(this.getSelectYear(),fillUnitID);						
			sheetName = this.excelName;
		}
		
		try {
//			List<T733POJO> list = t733_Sr.totalList(this.getSelectYear());
//			String sheetName = this.excelName;
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("单位名称");columns.add("单位号");columns.add("会议日期");columns.add("参会人员情况");columns.add("参会人数");columns.add("会议主要议题或内容");
			columns.add("会议形成的主要决议或共识");columns.add("备注");
			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("UnitName", 1);maplist.put("UnitID", 2);maplist.put("MeetingDate", 3);maplist.put("MeetingMemberInfo", 4);maplist.put("MeetingNum", 5);maplist.put("MeetingTheme", 6);
			maplist.put("MeetingResult", 7);maplist.put("Note", 8);
			
			inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}

		return inputStream ;
	}
	
	
	public String execute() throws Exception{
		request.setCharacterEncoding("UTF-8") ;
		System.out.println("excelName=============" + this.excelName) ;
		return "success" ;
	}


	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	public HttpSession getSession(){
		return getRequest().getSession();
	}
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	public T733_Bean getEachUnitTeachResActInfo() {
		return eachUnitTeachResActInfo;
	}
	public void setEachUnitTeachResActInfo(T733_Bean eachUnitTeachResActInfo) {
		this.eachUnitTeachResActInfo = eachUnitTeachResActInfo;
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


	public String getSelectYear() {
		return selectYear;
	}


	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}

    
	

}
