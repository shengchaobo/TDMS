package cn.nit.action.table5;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.table5.T512_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.table5.T512_DAO;
import cn.nit.pojo.table5.T512POJO;
import cn.nit.service.CheckService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table5.T512_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

public class T512_Action {
	
	private T512_Service t512_Sr=new T512_Service();
	
	private T512_Bean t512_Bean=new T512_Bean();
	
	private T512_DAO t512_DAO=new T512_DAO();
	
	/**取得某个表的审核信息*/
	private CheckService check_services = new CheckService();
	
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
	
	/**  审核状态显示判别标志  */
	private int checkNum ;
	
	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	/**  部门管理Service类  */
	private DiDepartmentService deSer = new DiDepartmentService() ;
	
	UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
	String fillUnitID = bean.getUnitID();
	
	public void insert(){
//		System.out.println("+++++++++++++++++++++++++++++++");
		t512_Bean.setTime(new Date());
//		System.out.println(new Date());
		//具体教学单位
		t512_Bean.setFillUnitID(fillUnitID);
		t512_Bean.setFillTeaID(bean.getTeaID());
		String unitName = deSer.getName(fillUnitID);
		t512_Bean.setCSUnit(unitName);//开课单位
		t512_Bean.setUnitID(fillUnitID);//开课单位号
		t512_Bean.setCheckState(Constants.WAIT_CHECK);
		boolean flag= t512_Sr.insert(t512_Bean);
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
		
            System.out.println("-------------------------------");
		if(this.page == null || this.page.equals("") || !page.matches("[\\d]+")){
			return ;
		}
		
		if(this.rows == null || this.rows.equals("") || !rows.matches("[\\d]+")){
			return ;
		}
		
		String cond = null;
		StringBuffer conditions = new StringBuffer();
		
		if(this.getSeqNum() == null && this.getStartTime() == null && this.getEndTime() == null && this.getCheckNum()==0){			
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
			}else if(this.getCheckNum() == (Constants.NOPASS_CHECK)){
				conditions.append(" and CheckState=" + this.getCheckNum()) ;
			}else if(this.getCheckNum() == (Constants.NO_CHECK)){
				conditions.append(" and CheckState!=" + Constants.PASS_CHECK) ;
			}
//			System.out.println("++++++++++++++++++++++++++++");
//			System.out.println("checkNum:"+this.getCheckNum());
			cond = conditions.toString();
		}
		
		
		
		//具体教学单位
		String tempUnitID = bean.getUnitID().substring(0,1);
		if(!"3".equals(tempUnitID)){
			fillUnitID = null;
		}
		
		
		String pages = t512_Sr.auditingData(cond, fillUnitID, Integer.parseInt(page), Integer.parseInt(rows)) ;
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

	public int getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}

	/**  编辑数据  */
	public void edit(){
		
		boolean flag = false;
		int tag = 0;
		//获得该条数据审核状态
		int state = t512_Sr.getCheckState(t512_Bean.getSeqNumber());
		System.out.println("test"+state);
		//如果审核状态是待审核，则直接修改
		if(state == Constants.WAIT_CHECK){
			System.out.println("test"+state);
			t512_Bean.setCheckState(Constants.WAIT_CHECK);
			flag = t512_Sr.update(t512_Bean) ;
			if(flag) tag = 1;
		}
		//如果是审核不通过，则修改该条数据，并将审核状态调节为待审核，同时删除该条数据在checkInfo表的信息
		if(state == Constants.NOPASS_CHECK){
			t512_Bean.setCheckState(Constants.WAIT_CHECK);
			boolean flag1 = t512_Sr.update(t512_Bean) ;
			boolean flag2 = check_services.delete("T512",t512_Bean.getSeqNumber());
			if(flag1&&flag2){
				flag = true;
				tag = 2;
			}
		}
		
		PrintWriter out=null;
		
		try {
			out=getResponse().getWriter();
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
	public void deleteCoursesByIds(){
		System.out.println("ids=" + ids) ;
		boolean flag = t512_Sr.deleteCoursesByIds(ids) ;
		//删除审核不通过信息
		check_services.delete("T512", ids);
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
	
		boolean flag = t512_Sr.updateCheck(this.getSeqNum(),this.getCheckNum());
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
	
		boolean flag = t512_Sr.checkAll();
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
	
	public InputStream getInputStream(){
		
		System.out.println("export");

		InputStream inputStream = null ;
		
		try {
//			//具体教学单位
//		    UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
//			String fillUnitID = bean.getUnitID();
			List<T512POJO> list = t512_DAO.totalList(this.getSelectYear(),fillUnitID,Constants.PASS_CHECK);
			String sheetName = this.excelName;
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("学期");columns.add("开课单位");columns.add("单位号");columns.add("上课专业名称");columns.add("上课专业代码");
			columns.add("课程名称");columns.add("课程编号");columns.add("课程类别");columns.add("课程性质");columns.add("公选课类别");
			columns.add("是否双语授课");columns.add("学分");columns.add("总学时");columns.add("理论学时");columns.add("实践学时");
			columns.add("考核方式");columns.add("实习、设计时间");columns.add("授课年级");columns.add("授课班级");columns.add("开课班号");
			columns.add("合班情况");columns.add("学生人数");columns.add("任课教师");columns.add("教工号");
             columns.add("是否符合岗位资格");columns.add("教师职称");
			columns.add("使用情况");columns.add("是否规划教材");columns.add("是否获奖教材");
			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("Term", 1);maplist.put("CSUnit", 2);maplist.put("UnitID", 3);maplist.put("CSMajorName", 4);maplist.put("CSMajorID", 5);
			maplist.put("CSName", 6);maplist.put("CSID", 7);maplist.put("CSType", 8);maplist.put("CSNature", 9);maplist.put("PubCSType", 10);
			maplist.put("IsDoubleCS", 11);maplist.put("Credit", 12);maplist.put("SumCSHour", 13);maplist.put("TheoryCSHour", 14);
			maplist.put("PraCSHour", 15);maplist.put("ExamWay", 16);maplist.put("PlanTime", 17);maplist.put("CSGrade", 18);
			maplist.put("CSClass", 19);maplist.put("ClassID", 20);maplist.put("ClassInfo",21);maplist.put("StuNum", 22);
			maplist.put("CSTea", 23);	maplist.put("TeaID", 24);
			maplist.put("IsAccordJob", 25);maplist.put("TeaTitle", 26);maplist.put("BookUseInfo", 27);maplist.put("IsPlanbook", 28);maplist.put("IsAwardbook", 29);
			
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
		return ServletActionContext.getRequest() ;
	}
	
	public HttpSession getSession(){
		return getRequest().getSession() ;
	}
	
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse() ;
	}

	public T512_Bean getT512_Bean() {
		return t512_Bean;
	}

	public void setT512_Bean(T512_Bean t512_Bean) {
		this.t512_Bean = t512_Bean;
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
