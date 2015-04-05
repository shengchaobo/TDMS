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

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.table3.T322_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.table3.T322_DAO;
import cn.nit.excel.imports.table3.T322Excel;
import cn.nit.pojo.table3.T322POJO;
import cn.nit.service.CheckService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table3.T322_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;



public class T322_Action {
	
	private T322_Service t322_Service = new T322_Service() ;
	
	private T322_Bean t322_Bean = new T322_Bean() ;
	

//	private T322_DAO t322_DAO = new T322_DAO();
	

	private T322Excel t322Excel = new T322Excel() ;
	
	private CheckService check_services = new CheckService();
	
	/**  部门管理Service类  */
	private DiDepartmentService deSer = new DiDepartmentService() ;
	
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
	
	/**  审核通过数据按年时间查询  */
	private String queryYear ;
	
	public String getQueryYear() {
		return queryYear;
	}

	public void setQueryYear(String queryYear) {
		this.queryYear = queryYear;
	}

	
	
	/**  审核状态显示判别标志  */
	private int checkNum ;
	
	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;


	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void insert(){
		UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String fillUnitID = bean.getUnitID();
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		t322_Bean.setFillUnitID(fillUnitID);
		/**设置数据审核状态*/
		t322_Bean.setCheckState(Constants.WAIT_CHECK);
		t322_Bean.setTime(new Date());
		System.out.println(t322_Bean.getTime());
		t322_Bean.setPraCSHour((int)t322_Bean.getPraCredit()*16);
		t322_Bean.setTotalCSHour(t322_Bean.getRequireCShour()+t322_Bean.getOptionCSHour()+t322_Bean.getPraCSHour());
		t322_Bean.setTotalCredit(t322_Bean.getRequireCredit()+t322_Bean.getOptionCredit()+t322_Bean.getPraCredit()+t322_Bean.getOutClassCredit());
		boolean flag = t322_Service.insert(t322_Bean) ;
		PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
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
		
		//具体教学单位
		UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String fillUnitID;
		String tempUnitID = bean.getUnitID().substring(0,1);
		
		if("3".equals(tempUnitID)){
			fillUnitID = bean.getUnitID();
		}else{
			fillUnitID = null;
		}
		
		String pages = t322_Service.auditingData(cond, fillUnitID, Integer.parseInt(page), Integer.parseInt(rows)) ;
	
		System.out.println(pages);
		PrintWriter out = null ;
		
		
		try{
			getResponse().setContentType("application/json; charset=UTF-8") ;
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
		int state = t322_Service.getCheckState(t322_Bean.getSeqNumber());
		System.out.println("test"+state);
		//如果审核状态是待审核，则直接修改
		if(state == Constants.WAIT_CHECK){
			System.out.println("test"+state);
			t322_Bean.setCheckState(Constants.WAIT_CHECK);
			t322_Bean.setPraCSHour((int)t322_Bean.getPraCredit()*16);
			t322_Bean.setTotalCSHour(t322_Bean.getRequireCShour()+t322_Bean.getOptionCSHour()+t322_Bean.getPraCSHour());
			t322_Bean.setTotalCredit(t322_Bean.getRequireCredit()+t322_Bean.getOptionCredit()+t322_Bean.getPraCredit()+t322_Bean.getOutClassCredit());
			flag = t322_Service.update(t322_Bean) ;
			if(flag) tag = 1;
		}
		//如果是审核不通过，则修改该条数据，并将审核状态调节为待审核，同时删除该条数据在checkInfo表的信息
		if(state == Constants.NOPASS_CHECK){
			t322_Bean.setCheckState(Constants.WAIT_CHECK);
			t322_Bean.setPraCSHour((int)t322_Bean.getPraCredit()*16);
			t322_Bean.setTotalCSHour(t322_Bean.getRequireCShour()+t322_Bean.getOptionCSHour()+t322_Bean.getPraCSHour());
			t322_Bean.setTotalCredit(t322_Bean.getRequireCredit()+t322_Bean.getOptionCredit()+t322_Bean.getPraCredit()+t322_Bean.getOutClassCredit());
			boolean flag1 = t322_Service.update(t322_Bean) ;
			boolean flag2 = check_services.delete("T322",t322_Bean.getSeqNumber());
			if(flag1&&flag2){
				flag = true;
				tag = 2;
			}
		}

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
	
	/**  根据数据的id删除数据  */
	public void deleteCoursesByIds(){
		System.out.println("ids=" + ids) ;
		boolean flag = t322_Service.deleteCoursesByIds(ids) ;
		
		//删除审核不通过信息
		check_services.delete("T322", ids);
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
	
		boolean flag = t322_Service.updateCheck(this.getSeqNum(),this.getCheckNum());
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
	
		boolean flag = t322_Service.checkAll();
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
		InputStream inputStream = null ;
		UserinfoBean userBean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String sheetName = null;
		List<T322_Bean> list = null;
		
		if("111".equals(userBean.getRoleID())){
			String year = (String)request.getSession().getAttribute("allYear") ;
			list = t322_Service.totalList("111",year,Constants.PASS_CHECK);
			sheetName = "表4-4-1专业带头人（教学单位-教务处）";
		}else{			
			String fillUnitID = userBean.getUnitID();			
			list = t322_Service.totalList(fillUnitID,this.getSelectYear(),Constants.PASS_CHECK);						
			sheetName = this.excelName;
		}

		try {
			

			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("专业名称");columns.add("专业代码");columns.add("代码版本");
			columns.add("校内专业名称");columns.add("校内专业代码");
			columns.add("专业方向名称");columns.add("专业方向号");columns.add("专业设置时间");columns.add("批文号");columns.add("学制");columns.add("学位授予门类");
			columns.add("开始招生时间");columns.add("招生状态");columns.add("停止招生时间");columns.add("是否新办专业");
			columns.add("专业特色简述");columns.add("专业培养目标简述");
			columns.add("批准建设年度");columns.add("建设批文号");
			columns.add("级别");columns.add("类型");columns.add("领域、方向");columns.add("建设负责人");columns.add("教工号");columns.add("验收时间");
			columns.add("验收批文号");columns.add("学校经费(万元)");columns.add("教育部经费(万元)");columns.add("首次通过认证时间");columns.add("认证时间");columns.add("批文号");
			columns.add("认证结果");columns.add("有效期(起)");columns.add("有效期(起)");columns.add("认证机构");columns.add("总学时数");columns.add("必修课学时数");
			columns.add("选修课学时数");columns.add("课内教学学时数");columns.add("实验教学学时数");columns.add("集中性实践教学环节学时数");columns.add("总学分数");columns.add("必修课学分数");
			columns.add("选修课学分数");columns.add("课内教学学分数");columns.add("实验教学学分数");columns.add("集中实践教学环节学分数");columns.add("课外科技活动学分数");

			

			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);maplist.put("MajorName", 1);maplist.put("MajorID", 2);
			maplist.put("MajorVersion", 3);
			maplist.put("SchMajorName", 4);maplist.put("SchMajorID", 5);
			maplist.put("MajorField", 6);maplist.put("MajorFieldID", 7);
			maplist.put("MajorSetTime", 8);maplist.put("MajorAppvlID", 9);maplist.put("MajorDurition", 10);
			maplist.put("MajorDegreeType", 11);maplist.put("MajorAdmisTime", 12);maplist.put("MajorState", 13);
			maplist.put("StopAdmisTime", 14);maplist.put("IsNewMajor", 15);
			maplist.put("MajorFeature", 16);maplist.put("MajorPurpose", 17);
			maplist.put("AppvlYear", 18);maplist.put("BuildAppvlID", 19);maplist.put("MajorLevel", 20);maplist.put("Type", 21);
			maplist.put("Field", 22);maplist.put("Leader", 23);maplist.put("TeaID", 24);maplist.put("CheckTime", 25);
			maplist.put("CheckAppvlID", 26);maplist.put("SchExp",27);
			maplist.put("EduMinistryExp", 28);maplist.put("FirstAppvlTime", 29);maplist.put("AppvlTime", 30);maplist.put("AppvlID", 31);maplist.put("AppvlResult", 32);maplist.put("FromTime",33);
			maplist.put("EndTime", 34);maplist.put("AppvlAuth", 35);maplist.put("TotalCSHour", 36);maplist.put("RequireCShour", 37);maplist.put("OptionCSHour", 38);maplist.put("InClassCSHour", 39);
			maplist.put("ExpCSHour", 40);maplist.put("PraCSHour", 41);maplist.put("TotalCredit", 42);maplist.put("RequireCredit", 43);maplist.put("OptionCredit", 44);maplist.put("InClassCredit", 45);
			maplist.put("ExpCredit", 46);maplist.put("PraCredit", 47);maplist.put("OutClassCredit", 48);

			inputStream = new ByteArrayInputStream(T322Excel.exportExcel(list, sheetName, maplist, columns).toByteArray());
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

	public T322_Service getT322_Service() {
		return t322_Service;
	}

	public void setT322_Service(T322_Service t322Service) {
		t322_Service = t322Service;
	}

	public T322_Bean getT322_Bean() {
		return t322_Bean;
	}

	public void setT322_Bean(T322_Bean t322Bean) {
		t322_Bean = t322Bean;
	}


	public T322Excel getT322Excel() {
		return t322Excel;
	}

	public void setT322Excel(T322Excel t322Excel) {
		this.t322Excel = t322Excel;
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
