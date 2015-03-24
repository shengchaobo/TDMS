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
import cn.nit.bean.table1.T151_Bean;
import cn.nit.bean.table1.T181_Bean;
import cn.nit.constants.Constants;
import cn.nit.excel.imports.table1.T181Excel;
import cn.nit.service.CheckService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table1.T181Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;


public class T181Action {
	
	/**  表181的Service类  */
	private T181Service t181Ser = new T181Service() ;
	
	/**  表181的Bean实体类  */
	private T181_Bean t181Bean = new T181_Bean() ;
	
	
	/**  表181的Excel实体类  */
	private T181Excel t181Excel = new T181Excel() ;
	
	/**  审核  */
	private CheckService check_services = new CheckService();
	
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
	
	/**  审核状态显示判别标志  */
	private int checkNum ;
	
	/**  导出/导入年份  */
	private String selectYear ;
	
	/**  审核通过数据按年时间查询  */
	private String queryYear ;
	
	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	/**  部门管理Service类  */
	private DiDepartmentService deSer = new DiDepartmentService() ;
	//正在登陆的用户信息
	UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
	String fillUnitID = bean.getUnitID();
	
	/**  逐条插入数据  */
	public void insert(){
//		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		t181Bean.setTime(new Date()) ;
		t181Bean.setFillDept(fillUnitID);//教务处
		//插入审核状态
		t181Bean.setCheckState(Constants.WAIT_CHECK);


		boolean flag = t181Ser.insert(t181Bean) ;
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
					if(this.getQueryYear() != null){
						conditions.append(" and Time like '" + this.queryYear + "%'");
					}else{
						 Calendar now = Calendar.getInstance();  
						 this.setQueryYear(now.get(Calendar.YEAR)+"");
						 conditions.append(" and Time like '" + this.queryYear + "%'");
					}
				}else if(this.getCheckNum() == (Constants.NOPASS_CHECK)){
					conditions.append(" and CheckState=" + this.getCheckNum()) ;
				}else if(this.getCheckNum() == (Constants.NO_CHECK)){
					conditions.append(" and CheckState!=" + Constants.PASS_CHECK) ;
				}
				
				cond = conditions.toString();
			}
			
			
			String tempUnitID = bean.getUnitID();
			if(!tempUnitID.equals("1012")){
				tempUnitID = null;
			}

			String pages = t181Ser.auditingData(cond, "1012", Integer.parseInt(page), Integer.parseInt(rows)) ;
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
		int state = t181Ser.getCheckState(t181Bean.getSeqNumber());
		
		//如果审核状态是待审核，则直接修改
		if(state == Constants.WAIT_CHECK){
			t181Bean.setCheckState(Constants.WAIT_CHECK);
			flag = t181Ser.update(t181Bean) ;
			if(flag) tag = 1;
		}
		//如果是审核不通过，则修改该条数据，并将审核状态调节为待审核，同时删除该条数据在checkInfo表的信息
		if(state == Constants.NOPASS_CHECK){
			t181Bean.setCheckState(Constants.WAIT_CHECK);
			boolean flag1 = t181Ser.update(t181Bean) ;
			boolean flag2 = check_services.delete("T181",t181Bean.getSeqNumber());
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
			}else{
				out.print("{\"state\":true,data:\"修改失败!!!\"}") ;
			}
			out.flush() ;
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
	
		boolean flag = t181Ser.updateCheck(this.getSeqNum(),this.getCheckNum());
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
	
		boolean flag = t181Ser.checkAll();
		
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
		
		boolean flag = t181Ser.deleteCoursesByIds(ids) ;
		//删除审核不通过信息
		check_services.delete("T181", ids);
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

		try {

			List<T181_Bean> list = t181Ser.totalList("1012",this.getSelectYear(),Constants.PASS_CHECK );
			
			String sheetName = this.excelName;
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("合作机构名称");columns.add("合作机构类型");columns.add("合作机构级别");columns.add("签订协议时间");
			columns.add("我方单位");columns.add("单位号");columns.add("我方单位级别");columns.add("备注");

			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("CooperInsName", 1);maplist.put("CooperInsType", 2);maplist.put("CooperInsLevel", 3);maplist.put("SignedTime", 4);
			maplist.put("UnitName", 5);maplist.put("UnitID", 6);maplist.put("UnitLevel", 7);maplist.put("Note", 8);
			
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

	public T181_Bean getT181Bean() {
		return t181Bean;
	}

	public void setT181Bean(T181_Bean t181Bean) {
		this.t181Bean = t181Bean;
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
	
	

	public String getQueryYear() {
		return queryYear;
	}

	public void setQueryYear(String queryYear) {
		this.queryYear = queryYear;
	}

	public static void main(String args[]){
		String match = "[\\d]+" ;
		System.out.println("23gfhf4".matches(match)) ;
	}

}
