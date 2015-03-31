package cn.nit.action.table5;

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
import cn.nit.bean.table5.T533_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.table5.T533DAO;
import cn.nit.excel.imports.table5.T533Excel;
import cn.nit.service.CheckService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table5.T533Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

public class T533Action {
	
//	/**  表T533的数据库操作类  */
//	private T533DAO t533Dao = new T533DAO() ;
	
	private T533Excel t533Excel=new T533Excel();

	/**  表533的Service类  */
	private T533Service t533Ser = new T533Service() ;
	
	private CheckService check_services = new CheckService();
	
	/**  表533的Bean实体类  */
	private T533_Bean t533Bean = new T533_Bean() ;
	
	/**excel导出名字*/
	private String excelName; //
	
	/**导出数据说要的年份*/
	private String Year;//
	

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
	

	/**  审核状态显示判别标志  */
	private int checkNum ;
	
	/**  导出时间  */
	private String selectYear ;
	private int checkFlag ;
	
	
	public int getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(int checkFlag) {
		this.checkFlag = checkFlag;
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
	
	
	/**  部门管理Service类  */
	private DiDepartmentService deSer = new DiDepartmentService() ;
	
	UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
	String fillUnitID = bean.getUnitID();
	
	/**  逐条插入数据  */
	public void insert(){
//		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		t533Bean.setTime(new Date()) ;
		t533Bean.setCheckState(Constants.WAIT_CHECK);
		String teaUnit = deSer.getName(fillUnitID);
		t533Bean.setTeaUnit(teaUnit);
		t533Bean.setUnitID(fillUnitID);
		t533Bean.setFillUnitID(fillUnitID);

//		这还没确定,设置填报者的职工号与部门号
//		UserRoleBean userinfo = (UserRoleBean)getSession().getAttribute("userinfo") ;
//		undergraCSBaseTea.setFillTeaID(userinfo.getTeaID()) ;
		
		boolean flag = t533Ser.insert(t533Bean) ;
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
			
			if(this.getSeqNum() == null && this.getStartTime() == null && this.getEndTime() == null && this.getCheckNum() == 0){			
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
				
				//审核状态判断
				if(this.getCheckNum() == Constants.WAIT_CHECK ){
					conditions.append(" and CheckState=" + this.getCheckNum()) ;
				}else if(this.getCheckNum() == (Constants.PASS_CHECK)){
					conditions.append(" and CheckState=" + this.getCheckNum()) ;
					if(this.getQueryYear() != null){
						conditions.append(" and Time like '" + this.queryYear + "%'");
					}else{
						if(this.getCheckFlag()!=0){
						 Calendar now = Calendar.getInstance();  
						 this.setQueryYear(now.get(Calendar.YEAR)+"");
						 conditions.append(" and Time like '" + this.queryYear + "%'");
						}
					}
				}else if(this.getCheckNum() == (Constants.NOPASS_CHECK)){
					conditions.append(" and CheckState=" + this.getCheckNum()) ;
				}else if(this.getCheckNum() == (Constants.NO_CHECK)){
					conditions.append(" and CheckState!=" + Constants.PASS_CHECK) ;
				}
				
				cond = conditions.toString();
			}
			
			//判断登入用户是否为教学单位
			String tempfield1 = bean.getUnitID().substring(0, 1);
			String tempfield = bean.getUnitID();
			if(!"3".equals(tempfield1)){//如不是教学单位，填报单位设置为空
				tempfield = null;
			}

			String pages = t533Ser.auditingData(cond, tempfield, Integer.parseInt(page), Integer.parseInt(rows)) ;

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

		 boolean flag = false;
		 int tag = 0;
		//获得该条数据审核状态
			int state = t533Ser.getCheckState(t533Bean.getSeqNumber());
//			System.out.println("test"+state);
			//如果审核状态是待审核，则直接修改
			if(state == Constants.WAIT_CHECK){
				System.out.println("test"+state);
				t533Bean.setCheckState(Constants.WAIT_CHECK);
				flag = t533Ser.update(t533Bean) ;
				if(flag) tag = 1;
			}
			//如果是审核不通过，则修改该条数据，并将审核状态调节为待审核，同时删除该条数据在checkInfo表的信息
			if(state == Constants.NOPASS_CHECK){
				t533Bean.setCheckState(Constants.WAIT_CHECK);
				boolean flag1 = t533Ser.update(t533Bean) ;
				boolean flag2 = check_services.delete("T533",t533Bean.getSeqNumber());
				if(flag1&&flag2){
					flag = true;
					tag = 2;
				}
			}
		
		PrintWriter out = null ;
		
		try{
			out = getResponse().getWriter() ;
			if(tag == 1){
				out.print("{\"state\":true,data:\"修改成功!!!\"}") ;
			}
			else if(tag == 2){
				out.print("{\"state\":true,data:\"修改成功!!!\",tag:2}") ;
			}
			else{
				out.print("{\"state\":true,data:\"修改失败!!!\"}") ;
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
		boolean flag = t533Ser.deleteCoursesByIds(ids) ;
		//删除审核不通过信息
		check_services.delete("T533", ids);
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
	
	/**  修改某条数据的审核状态  */
	public void updateCheck(){
		HttpServletResponse response = ServletActionContext.getResponse();
	
		boolean flag = t533Ser.updateCheck(this.getSeqNum(),this.getCheckNum());
		PrintWriter out = null ;
		
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"修改审核状态成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"修改审核状态失败!!!\"}") ;
			}
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"修改审核状态失败!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	/**  全部审核通过  */
	public void checkAll(){
		HttpServletResponse response = ServletActionContext.getResponse();
	
		boolean flag = t533Ser.checkAll();
		PrintWriter out = null ;
		
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"一键审核成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"一键审核失败!!!\"}") ;
			}
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"一键审核失败!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	/**数据导出*/
	public InputStream getInputStream(){
		
		UserinfoBean userBean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String sheetName = null;
		List<T533_Bean> list= null;
		
		if("111".equals(userBean.getRoleID())){
			String year = (String)request.getSession().getAttribute("allYear") ;
			list = t533Ser.totalList("111",year,Constants.PASS_CHECK);
			sheetName = "表5-3-3分专业实验情况（教学单位-教务处）";
		}else{			
			String fillUnitID = userBean.getUnitID();			
			list =  t533Ser.totalList(fillUnitID,this.getSelectYear(),Constants.PASS_CHECK);						
			sheetName = this.excelName;
		}
		InputStream inputStream = null ;

		try {
			
			//List<T533_Bean> list = t533Ser.totalList(fillUnitID,this.getSelectYear(),Constants.PASS_CHECK);
			
			//String sheetName = this.excelName;
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("教学单位");columns.add("单位号");columns.add("专业名称");columns.add("专业代码");
			columns.add("有实验的课程（门）");columns.add("独立设置的实验课程（门）");columns.add("综合性、设计性实验教学（门）");
			columns.add("实验开出率（%）");	

			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("TeaUnit", 1);maplist.put("UnitID", 2);maplist.put("MajorName", 3);maplist.put("MajorID", 4);
			maplist.put("ExpCSNum", 5);maplist.put("IndepentExpCSNum", 6);maplist.put("DesignExpCSNum", 7);maplist.put("ExpRatio", 8);
			
			
			//inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());
			inputStream = new ByteArrayInputStream(t533Excel.exportExcel(list, sheetName, maplist,columns).toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}
//        System.out.println(inputStream);
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

	public T533_Bean getT533Bean() {
		return t533Bean;
	}

	public void setT533Bean(T533_Bean t533Bean) {
		this.t533Bean = t533Bean;
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
	
	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		this.Year = year;
	}

	public int getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}

	public String getSelectYear() {
		return selectYear;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
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


}
