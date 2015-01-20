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

import cn.nit.bean.table5.T532Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.table5.T532DAO;
import cn.nit.excel.imports.table5.T532Excel;

import cn.nit.service.CheckService;
import cn.nit.service.table5.T532Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

public class T532Action {

	
	/**  表T531的数据库操作类  */
	private T532DAO t532Dao = new T532DAO() ;
	
	private T532Excel t532Excel=new T532Excel();

	/**  表532的Service类  */
	private T532Service t532Ser = new T532Service() ;
	
	private CheckService check_services = new CheckService();
	
	/**  表532的Bean实体类  */
	private T532Bean t532Bean = new T532Bean() ;
	
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
	
	/**  逐条插入数据  */
	public void insert(){
//		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		t532Bean.setTime(new Date()) ;
		//插入审核状态
		t532Bean.setCheckState(Constants.WAIT_CHECK);
//		t533Bean.setFillUnitID("3001");
//		System.out.println(t532Bean.getCenterName());
//		System.out.println(t532Bean.getFromSubject());
//		System.out.println(t532Bean.getCenterLevel());
//		System.out.println(t532Bean.getFromTeaUnit());
//		这还没确定,设置填报者的职工号与部门号
//		UserRoleBean userinfo = (UserRoleBean)getSession().getAttribute("userinfo") ;
//		undergraCSBaseTea.setFillTeaID(userinfo.getTeaID()) ;
		
		boolean flag = t532Ser.insert(t532Bean) ;
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
				
				cond = conditions.toString();
			}


			String pages = t532Ser.auditingData(cond, null, Integer.parseInt(page), Integer.parseInt(rows)) ;

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
			int state = t532Ser.getCheckState(t532Bean.getSeqNumber());
			
			//如果审核状态是待审核，则直接修改
			if(state == Constants.WAIT_CHECK){
				t532Bean.setCheckState(Constants.WAIT_CHECK);
				flag = t532Ser.update(t532Bean) ;
				if(flag) tag = 1;
			}
			//如果是审核不通过，则修改该条数据，并将审核状态调节为待审核，同时删除该条数据在checkInfo表的信息
			if(state == Constants.NOPASS_CHECK){
				t532Bean.setCheckState(Constants.WAIT_CHECK);
				boolean flag1 = t532Ser.update(t532Bean) ;
				boolean flag2 = check_services.delete("T532",t532Bean.getSeqNumber());
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
	
	/**  修改某条数据的审核状态  */
	public void updateCheck(){
		HttpServletResponse response = ServletActionContext.getResponse();
	
		boolean flag = t532Ser.updateCheck(this.getSeqNum(),this.getCheckNum());
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
	
		boolean flag = t532Ser.checkAll();
	
		
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
//		System.out.println("ids=" + ids) ;
		boolean flag = t532Ser.deleteCoursesByIds(ids) ;
		//删除审核不通过信息
		check_services.delete("T532", ids);
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
		
//        System.out.println("年份："+this.Year);
		InputStream inputStream = null ;

		try {
			System.out.println(this.getSelectYear());
			List<T532Bean> list = t532Dao.totalList(this.getSelectYear(),Constants.PASS_CHECK);
			
			String sheetName = this.excelName;
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("中心名称");columns.add("所属学科");columns.add("学科代码");
			columns.add("级别");columns.add("所属教学单位");
			columns.add("单位号");columns.add("中心主任");
			columns.add("教工号");columns.add("职称");columns.add("设立时间");columns.add("建设批文号");
			columns.add("验收时间");columns.add("验收批文号");
			columns.add("有效期（年）");columns.add("经费(万元)");columns.add("备注");
			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("CenterName", 1);maplist.put("FromSubject", 2);maplist.put("SubjectID", 3);
			maplist.put("CenterLevel", 4);maplist.put("FromTeaUnit", 5);
			maplist.put("UnitID", 6);maplist.put("CenterLeader", 7);
			maplist.put("TeaID", 8);maplist.put("TeaTitle", 9);maplist.put("BuildTime", 10);maplist.put("BuildAppvlID", 11);
			maplist.put("ReceptTime", 12);maplist.put("ReceptAppvlID", 13);
			maplist.put("ValidTime", 14);maplist.put("Fund", 15);maplist.put("Note", 16);
			//inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());
			inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}
        System.out.println(inputStream);
		return inputStream ;
	}
	

	public String execute() throws Exception{

		getResponse().setContentType("application/octet-stream;charset=UTF-8") ;
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

	public T532Bean getT532Bean() {
		return t532Bean;
	}

	public void setT532Bean(T532Bean t532Bean) {
		this.t532Bean = t532Bean;
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
