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
import cn.nit.bean.table7.T711_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.table7.T711_DAO;
import cn.nit.pojo.table7.T711POJO;
import cn.nit.service.CheckService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table7.T711_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

public class T711_Action {

	
	private T711_Service t711_Sr=new T711_Service();
	
	private T711_Bean t711_Bean=new T711_Bean();
	
//	private T711_DAO t711_DAO=new T711_DAO();
	
	private CheckService check_services = new CheckService();
	
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
	/**  审核状态显示判别标志  */
	private int checkNum ;
	
	/**  该标志是用来区分显示审核通过数据的年为空时的两种情况，
	 * 一种是被审核用户的当前年数据显示，另一种是审核用户审核通过数据的显示  
	 * 用0来代表后者
	 * */
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
	
	
	public void insert(){
		//System.out.println(t711_Bean.getUnitID()) ;
		t711_Bean.setTime(new Date());
		t711_Bean.setCheckState(Constants.WAIT_CHECK);
		//具体教学单位
	    UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String fillUnitID = bean.getUnitID();
		t711_Bean.setFillUnitID(fillUnitID);
		String teaUnit = deSer.getName(fillUnitID);
		t711_Bean.setUnitID(fillUnitID);
		t711_Bean.setTeaUnit(teaUnit);
		boolean flag= t711_Sr.insert(t711_Bean);
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
		//具体教学单位
		UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String fillUnitID;
		String tempUnitID = bean.getUnitID().substring(0,1);
		if("3".equals(tempUnitID)){
			fillUnitID = bean.getUnitID();
		}else{
			fillUnitID = null;
		}
		
		String pages = t711_Sr.auditingData(cond, fillUnitID, Integer.parseInt(page), Integer.parseInt(rows)) ;
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
		int tag =0;
		//获得该条数据审核状态
		int state = t711_Sr.getCheckState(t711_Bean.getSeqNumber());
		System.out.println("test"+state);
		//如果审核状态是待审核，则直接修改
		if(state == Constants.WAIT_CHECK){
			System.out.println("test"+state);
			t711_Bean.setCheckState(Constants.WAIT_CHECK);
			flag = t711_Sr.update(t711_Bean) ;
			if(flag) tag = 1;
		}
		//如果是审核不通过，则修改该条数据，并将审核状态调节为待审核，同时删除该条数据在checkInfo表的信息
		if(state == Constants.NOPASS_CHECK){
			t711_Bean.setCheckState(Constants.WAIT_CHECK);
			boolean flag1 = t711_Sr.update(t711_Bean) ;
			boolean flag2 = check_services.delete("T711",t711_Bean.getSeqNumber());
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
		boolean flag = t711_Sr.deleteCoursesByIds(ids) ;
		//删除审核不通过信息
		check_services.delete("T711", ids);
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
	
		boolean flag = t711_Sr.updateCheck(this.getSeqNum(),this.getCheckNum());
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
	
		boolean flag = t711_Sr.checkAll();
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

		InputStream inputStream = null ;
		
		UserinfoBean userBean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String sheetName = null;
		List<T711POJO> list = null;
		
		if("111".equals(userBean.getRoleID())){
			String year = (String)request.getSession().getAttribute("allYear") ;
			list = t711_Sr.totalList("111",year,Constants.PASS_CHECK);
			sheetName = "表7-1-1教学管理人员获得教学成果奖情况（教学单位-教务处）";
		}else{			
			String fillUnitID = userBean.getUnitID();			
			list =  t711_Sr.totalList(fillUnitID,this.getSelectYear(),Constants.PASS_CHECK);						
			sheetName = this.excelName;
		}
		
		try {
			
//			List<T711POJO> list = t711_Sr.totalList(fillUnitID,this.getSelectYear(),Constants.PASS_CHECK);
//			String sheetName = this.excelName;
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("教学单位");columns.add("单位号");columns.add("姓名");columns.add("教工号");columns.add("奖励名称");columns.add("级别");
			columns.add("等级");columns.add("获奖时间");columns.add("授予单位");columns.add("批文号");columns.add("合作教师人数");columns.add("其他合作教师");
			columns.add("备注");
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("TeaUnit", 1);maplist.put("UnitID", 2);maplist.put("Name", 3);maplist.put("TeaID", 4);maplist.put("AwardName", 5);maplist.put("AwardLevel", 6);
			maplist.put("AwardRank", 7);maplist.put("AwardTime7", 8);maplist.put("AwardFromUnit", 9);maplist.put("AppvlID", 10);maplist.put("JoinTeaNum", 11);maplist.put("OtherJoinTeaInfo", 12);
			maplist.put("Note", 13);	
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
		
	




	public T711_Bean getT711_Bean() {
		return t711_Bean;
	}




	public void setT711_Bean(T711_Bean t711_Bean) {
		this.t711_Bean = t711_Bean;
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
	
	
	}
	

