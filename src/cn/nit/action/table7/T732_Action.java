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
import cn.nit.bean.table7.T732_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.table7.T732_DAO;
import cn.nit.pojo.table7.T732POJO;
import cn.nit.service.CheckService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table7.T732_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

public class T732_Action {
	T732_Service t732_Sr=new T732_Service();
	
	T732_Bean teaLeadInClassInfo=new T732_Bean();
	
//	private T732_DAO t732_Dao=new T732_DAO();
	
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
		teaLeadInClassInfo.setTime(new Date());
		teaLeadInClassInfo.setCheckState(Constants.WAIT_CHECK);
		UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String fillUnitID = bean.getUnitID();
		teaLeadInClassInfo.setFillUnitID(fillUnitID);
		teaLeadInClassInfo.setUnitID(fillUnitID);
		String setCSUnit= deSer.getName(fillUnitID);
		teaLeadInClassInfo.setSetCSUnit(setCSUnit);
		boolean flag;
		PrintWriter out=null;
		
		flag=t732_Sr.insert(teaLeadInClassInfo);
		
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
		
		String pages = t732_Sr.auditingData(cond, fillUnitID, Integer.parseInt(page), Integer.parseInt(rows)) ;
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
		int state = t732_Sr.getCheckState(teaLeadInClassInfo.getSeqNumber());
		System.out.println("test"+state);
		//如果审核状态是待审核，则直接修改
		if(state == Constants.WAIT_CHECK){
			System.out.println("test"+state);
			teaLeadInClassInfo.setCheckState(Constants.WAIT_CHECK);
			flag = t732_Sr.update(teaLeadInClassInfo) ;
			if(flag) tag = 1;
		}
		//如果是审核不通过，则修改该条数据，并将审核状态调节为待审核，同时删除该条数据在checkInfo表的信息
		if(state == Constants.NOPASS_CHECK){
			teaLeadInClassInfo.setCheckState(Constants.WAIT_CHECK);
			boolean flag1 = t732_Sr.update(teaLeadInClassInfo) ;
			boolean flag2 = check_services.delete("T732",teaLeadInClassInfo.getSeqNumber());
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
	public void deleteByIds(){
		System.out.println("ids=" + ids) ;
		boolean flag = t732_Sr.deleteByIds(ids) ;
		//删除审核不通过信息
		check_services.delete("T732", ids);
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
	
		boolean flag = t732_Sr.updateCheck(this.getSeqNum(),this.getCheckNum());
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
	
		boolean flag = t732_Sr.checkAll();
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
		List<T732POJO> list = null;
		
		if("111".equals(userBean.getRoleID())){
			String year = (String)request.getSession().getAttribute("allYear") ;
			list = t732_Sr.totalList("111",year,Constants.PASS_CHECK);
			sheetName = "表7-3-2教学单位领导听课情况（教学单位--教务处）";
		}else{			
			String fillUnitID = userBean.getUnitID();			
			list = t732_Sr.totalList(fillUnitID,this.getSelectYear(),Constants.PASS_CHECK);					
			sheetName = this.excelName;
		}
		
		try {
			
//			List<T732POJO> list = t732_Sr.totalList(fillUnitID,this.getSelectYear(),Constants.PASS_CHECK);
//			String sheetName = this.excelName;

			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("听课学期");columns.add("教学单位领导姓名");columns.add("领导教工号");columns.add("行政职务");columns.add("听课日期");columns.add("授课教师");
			columns.add("授课教教工号");columns.add("听课课程");columns.add("课程编号");columns.add("开课单位");columns.add("单位号");columns.add("上课班级");columns.add("综合评价");
			columns.add("备注");
			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("AttendClassTerm", 1);maplist.put("LeaderName", 2);maplist.put("LeaderTeaID", 3);maplist.put("AdminTitle", 4);maplist.put("AttendClassTime", 5);maplist.put("LectureTea", 6);
			maplist.put("LectureTeaID", 7);maplist.put("LectureCS", 8);maplist.put("CSID", 9);maplist.put("SetCSUnit", 10);maplist.put("UnitID", 11);maplist.put("LectureClass", 12);maplist.put("Evaluate", 13);
			maplist.put("Note", 14);
			
			inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}

		return inputStream ;
	}
	
	
	public String execute() throws Exception{
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
	public T732_Bean getTeaLeadInClassInfo() {
		return teaLeadInClassInfo;
	}
	public void setTeaLeadInClassInfo(T732_Bean teaLeadInClassInfo) {
		this.teaLeadInClassInfo = teaLeadInClassInfo;
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
