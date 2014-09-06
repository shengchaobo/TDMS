package cn.nit.action.table3;

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
import cn.nit.bean.table3.T322_Bean;
import cn.nit.dao.table3.T322_DAO;
import cn.nit.excel.imports.table3.T322Excel;
import cn.nit.service.table3.T322_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;



public class T322_Action {
private T322_Service t322_Service = new T322_Service() ;
	
	private T322_Bean t322_Bean = new T322_Bean() ;
	

	private T322_DAO t322_DAO = new T322_DAO();
	

	private T322Excel t322Excel = new T322Excel() ;
	
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
		t322_Bean.setTime(new Date()) ;
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
		UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String fillUnitID = bean.getUnitID();

		
		
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

		t322_Bean.setTime(new Date());
		
		
		t322_Bean.setPraCSHour((int)t322_Bean.getPraCredit()*16);
		t322_Bean.setTotalCSHour(t322_Bean.getRequireCShour()+t322_Bean.getOptionCSHour()+t322_Bean.getPraCSHour());
		t322_Bean.setTotalCredit(t322_Bean.getRequireCredit()+t322_Bean.getOptionCredit()+t322_Bean.getPraCredit()+t322_Bean.getOutClassCredit());
		boolean flag = t322_Service.update(t322_Bean) ;

		PrintWriter out = null ;
		
		try{
			out = getResponse().getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"修改成功!!!\"}") ;
			}else{
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
		UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String fillUnitID = bean.getUnitID();

		InputStream inputStream = null ;

		try {
			
			List<T322_Bean> list = t322_DAO.totalList(fillUnitID);
			
			String sheetName = this.excelName;
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("专业名称");columns.add("专业代码");columns.add("代码版本");
			columns.add("专业方向名称");columns.add("专业方向号");columns.add("专业设置时间");columns.add("批文号");columns.add("学制");columns.add("学位授予门类");
			columns.add("开始招生时间");columns.add("招生状态");columns.add("停止招生时间");columns.add("是否新办专业");columns.add("批准建设年度");columns.add("建设批文号");
			columns.add("级别");columns.add("类型");columns.add("领域、方向");columns.add("建设负责人");columns.add("教工号");columns.add("验收时间");
			columns.add("验收批文号");columns.add("学校经费(万元)");columns.add("教育部经费(万元)");columns.add("首次通过认证时间");columns.add("认证时间");columns.add("批文号");
			columns.add("认证结果");columns.add("有效期(起)");columns.add("有效期(起)");columns.add("认证机构");columns.add("总学时数");columns.add("必修课学时数");
			columns.add("选修课学时数");columns.add("课内教学学时数");columns.add("实验教学学时数");columns.add("集中性实践教学环节学时数");columns.add("总学分数");columns.add("必修课学分数");
			columns.add("选修课学分数");columns.add("课内教学学分数");columns.add("实验教学学分数");columns.add("集中实践教学环节学分数");columns.add("课外科技活动学分数");

			

			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);maplist.put("MajorName", 1);maplist.put("MajorID", 2);maplist.put("MajorVersion", 3);maplist.put("MajorField", 4);maplist.put("MajorFieldID", 5);
			maplist.put("MajorSetTime", 6);maplist.put("MajorAppvlID", 7);maplist.put("MajorDurition", 8);maplist.put("MajorDegreeType", 9);maplist.put("MajorAdmisTime", 10);maplist.put("MajorState", 11);
			maplist.put("StopAdmisTime", 12);maplist.put("IsNewMajor", 13);maplist.put("AppvlYear", 14);maplist.put("BuildAppvlID", 15);maplist.put("MajorLevel", 16);maplist.put("Type", 17);
			maplist.put("Field", 18);maplist.put("Leader", 19);maplist.put("TeaID", 20);maplist.put("CheckTime", 21);maplist.put("CheckAppvlID", 22);maplist.put("SchExp",23);
			maplist.put("EduMinistryExp", 24);maplist.put("FirstAppvlTime", 25);maplist.put("AppvlTime", 26);maplist.put("AppvlID", 27);maplist.put("AppvlResult", 28);maplist.put("FromTime",29);
			maplist.put("EndTime", 30);maplist.put("AppvlAuth", 31);maplist.put("TotalCSHour", 32);maplist.put("RequireCShour", 33);maplist.put("OptionCSHour", 34);maplist.put("InClassCSHour", 35);
			maplist.put("ExpCSHour", 36);maplist.put("PraCSHour", 37);maplist.put("TotalCredit", 38);maplist.put("RequireCredit", 39);maplist.put("OptionCredit", 40);maplist.put("InClassCredit", 41);
			maplist.put("ExpCredit", 42);maplist.put("PraCredit", 43);maplist.put("OutClassCredit", 44);

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

	public T322_DAO getT322_DAO() {
		return t322_DAO;
	}

	public void setT322_DAO(T322_DAO t322DAO) {
		t322_DAO = t322DAO;
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

	public String getSelectYear() {
		return selectYear;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}

	







}
