package cn.nit.action.table3;

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

import cn.nit.constants.Constants;
import cn.nit.dao.table1.T18DAO;
import cn.nit.dao.table3.T321_DAO;

import cn.nit.excel.imports.table3.T321Excel;
import cn.nit.service.CheckService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.bean.UserinfoBean;
import cn.nit.bean.di.DiDepartmentBean;


import cn.nit.bean.table3.T311_Bean;
import cn.nit.bean.table3.T321_Bean;





import cn.nit.service.table3.T321_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;



public class T321_Action {
	

	
	
	private T321_Service t321_Service = new T321_Service() ;
	
	int num=0;
	
	private T321_Bean t321_Bean = new T321_Bean() ;
	
	private CheckService check_services = new CheckService();
	
//	/**  表181的Dao类  */
//	private T321_DAO t321_DAO = new T321_DAO() ;
	
	/**  表181的Excel实体类  */
	private T321Excel t321Excel = new T321Excel() ;
	
	/**excel导出名字*/
	private String excelName; //
	
	private int checkFlag ;
	
	
	public int getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(int checkFlag) {
		this.checkFlag = checkFlag;
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
	
	/**  审核状态显示判别标志  */
	private int checkNum ;
	
	/**  审核通过数据按年时间查询  */
	private String queryYear ;
	public String getQueryYear() {
		return queryYear;
	}

	public void setQueryYear(String queryYear) {
		this.queryYear = queryYear;
	}


	
//	public List<Integer> getNumofMainTrain(){
//		List<Integer> list1=new ArrayList<Integer> ();
//		List<DiDepartmentBean> list = diDepartmentSer.getList() ;
//		for(int i=0;i<=list.size();i++){
//			//num=mainTrainBasicInfoDao.getNumofMainTrain(list.get(i).getUnitID());
//			list1.add(num);
//			
//		}
//		return list1;
//		
//	}
	
	public void insert(){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		t321_Bean.setTime(new Date()) ;
		t321_Bean.setCheckState(Constants.WAIT_CHECK);
		//这还没确定,设置填报者的职工号与部门号
		//UserInfo userinfo = (UserInfo)getSession().getAttribute("userinfo") ;
		//t321_Bean.setFillTeaID(userinfo.getTeaID()) ;
		boolean flag = t321_Service.insert(t321_Bean) ;
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
		
//		System.out.println("輸出輸出輸出");
		
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
		String pages = t321_Service.auditingData(cond, null, Integer.parseInt(page), Integer.parseInt(rows)) ;
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
		System.out.println("++++++++++++++++++++++++++++++");
		boolean flag = false;
		
		int tag = 0;
		//获得该条数据审核状态
		//System.out.println("seq:"+t321_Bean.getSeqNumber());
		int state = t321_Service.getCheckState(t321_Bean.getSeqNumber());
		//System.out.println(state);
		
		//如果审核状态是待审核，则直接修改
		if(state == Constants.WAIT_CHECK){
			t321_Bean.setCheckState(Constants.WAIT_CHECK);
			flag = t321_Service.update(t321_Bean) ;
			if(flag) tag = 1;
		}
		//如果是审核不通过，则修改该条数据，并将审核状态调节为待审核，同时删除该条数据在checkInfo表的信息
		if(state == Constants.NOPASS_CHECK){
			System.out.println("getCheck");
			t321_Bean.setCheckState(Constants.WAIT_CHECK);
			boolean flag1 = t321_Service.update(t321_Bean) ;
			boolean flag2 = check_services.delete("T321",t321_Bean.getSeqNumber());
			if(flag1&&flag2){
				flag = true;
				tag = 2;
			}
		}

		//boolean flag = t321_Service.update(t321_Bean) ;
		PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
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
	
	/**  修改某条数据的审核状态  */
	public void updateCheck(){
		HttpServletResponse response = ServletActionContext.getResponse();
	
		boolean flag = t321_Service.updateCheck(this.getSeqNum(),this.getCheckNum());
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
	
		boolean flag = t321_Service.checkAll();
		
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
	
	/**  根据数据的id删除数据  */
	public void deleteCoursesByIds(){
		System.out.println("ids=" + ids) ;
		boolean flag = t321_Service.deleteCoursesByIds(ids) ;
		//删除审核不通过信息
		check_services.delete("T321", ids);
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
		UserinfoBean userBean = (UserinfoBean) getRequest().getSession().getAttribute("userinfo") ;
		String sheetName = null;
		List<T321_Bean> list = null;
		
		if("111".equals(userBean.getRoleID())){
			String year = (String)getRequest().getSession().getAttribute("allYear") ;
			list = t321_Service.totalList(year,Constants.PASS_CHECK);
			sheetName = "表3-2-1大类培养基本情况表（教务处）";
		}else{					
			list = t321_Service.totalList(this.getSelectYear(),Constants.PASS_CHECK);					
			sheetName = this.excelName;
		}


		try {		
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("大类名称");columns.add("大类代码");columns.add("分流时间");
			columns.add("包含校内专业名称");columns.add("校内专业代码");columns.add("所属单位");
			columns.add("单位号");

			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("MainClassName", 1);maplist.put("MainClassID", 2);maplist.put("ByPassTime", 3);maplist.put("MajorNameInSch", 4);
			maplist.put("MajorID", 5);maplist.put("UnitName", 6);maplist.put("UnitID", 7);
			
			
			//inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());
			inputStream = new ByteArrayInputStream(t321Excel.batchExport(list, sheetName, maplist, columns).toByteArray());
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



	public String getSelectYear() {
		return selectYear;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}

	public int getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}
	



	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}


	public T321Excel getT321Excel() {
		return t321Excel;
	}

	public void setT321Excel(T321Excel t321Excel) {
		this.t321Excel = t321Excel;
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

	public T321_Service getT321_Service() {
		return t321_Service;
	}


	public void setT321_Service(T321_Service t321Service) {
		t321_Service = t321Service;
	}


	public T321_Bean getT321_Bean() {
		return t321_Bean;
	}


	public void setT321_Bean(T321_Bean t321Bean) {
		t321_Bean = t321Bean;
	}


	public static void main(String args[]){
		String match = "[\\d]+" ;
		System.out.println("23gfhf4".matches(match)) ;
	}
}
