package cn.nit.action.table2;


import java.io.ByteArrayInputStream;
import java.io.IOException;
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

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.table2.T251_Bean;
import cn.nit.bean.table4.T441_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.table2.T251_Dao;
import cn.nit.service.CheckService;
import cn.nit.service.table2.T251_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;


public class T251_Action {
	
	private String rows; //每页显示的记录数
	
	private String page; //当前第几页
	
	private T251_Service T251_services = new T251_Service();
	
	private CheckService check_services = new CheckService();
	
	private T251_Bean T251_bean = new T251_Bean();
	
//	private T251_Dao T251_dao = new T251_Dao();
	
	/**  待审核数据的要删除的序列集  */
	private String ids; //删除的id
	
	/**  待审核数据的查询的序列号  */
	private Integer seqNum ;
	
	/**  待审核数据查询的起始时间  */
	private Date startTime ;
	
	/**  待审核数据查询的结束时间  */
	private Date endTime ;
	
	/**  下载的excelName  */
	private String excelName ;
	
	/**  审核状态显示判别标志  */
	private int checkNum ;
	
	/**  导出时间  */
	private String selectYear ;
	
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
	
	//查询出所有
	public void loadPlaceInfo() throws Exception{
		
		HttpServletResponse response = ServletActionContext.getResponse() ;	
				
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
			//System.out.println(this.getCheckNum()+"test");
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
		
		List<T251_Bean> list = T251_services.getPageMajorTeaList(cond, null, this.getRows(), this.getPage()) ;
		String TeaInfoJson = this.toBeJson(list,T251_services.getTotal(cond, null));
		//private JSONObject jsonObj;
		
		PrintWriter out = null ;

		if(TeaInfoJson == null){			
			return ;
		}else{
			try {
				
				System.out.println(TeaInfoJson) ;
				response.setContentType("application/json;charset=UTF-8") ;
				out = response.getWriter() ;
				out.print(TeaInfoJson) ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(out != null){
					out.flush() ;
					out.close() ;
				}
			}
		}
	}

    //将分页系统的总数以及当前页的list转化一个json传页面显示
	private String toBeJson(List<T251_Bean> list, int total) throws Exception{
		// TODO Auto-generated method stub
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		 

		JSONObject testjson = new JSONObject();
		testjson.accumulate("total", total);
		testjson.accumulate("rows", list);
				
        String json = testjson.toString();
        System.out.println(json) ;
		return json;
	}
	
	//插入一个新的教职工
	public void insert(){
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		HttpServletResponse response = ServletActionContext.getResponse();
		
		//插入时间
		T251_bean.setTime(new Date());
		System.out.println("YEAR++++++："+T251_bean.getBuildTimeYM());
		
		//插入审核状态
		T251_bean.setCheckState(Constants.WAIT_CHECK);
				
		boolean flag = T251_services.insert(T251_bean);
		PrintWriter out = null ;
		
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
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
	
	
	/**  编辑数据  */
	public void edit(){
		
		boolean flag = false;
		System.out.println("buileTime:"+T251_bean.getBuildTimeYM());
		
		int tag = 0;
		//获得该条数据审核状态
		int state = T251_services.getCheckState(T251_bean.getSeqNumber());
		
		//如果审核状态是待审核，则直接修改
		if(state == Constants.WAIT_CHECK){
			T251_bean.setCheckState(Constants.WAIT_CHECK);
			flag = T251_services.update(T251_bean) ;
			if(flag) tag = 1;
		}
		//如果是审核不通过，则修改该条数据，并将审核状态调节为待审核，同时删除该条数据在checkInfo表的信息
		if(state == Constants.NOPASS_CHECK){
			T251_bean.setCheckState(Constants.WAIT_CHECK);
			boolean flag1 = T251_services.update(T251_bean) ;
			boolean flag2 = check_services.delete("T251",T251_bean.getSeqNumber());
			if(flag1&&flag2){
				flag = true;
				tag = 2;
			}
		}
		
		PrintWriter out = null ;
	
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
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
	public void deleteByIds(){
		//System.out.println("ids=" + this.getIds()) ;
		boolean flag = T251_services.deleteByIds(ids) ;
		
		//删除审核不通过信息
		check_services.delete("T251", ids);
		
		PrintWriter out = null ;
		
		try{
						
			response.setContentType("application/json; charset=UTF-8") ;
			out = response.getWriter() ;			
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
	
		boolean flag = T251_services.updateCheck(this.getSeqNum(),this.getCheckNum());
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
	
		boolean flag = T251_services.checkAll();
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

	
	public InputStream getInputStream() throws UnsupportedEncodingException{
		
		InputStream inputStream = null ;
		UserinfoBean userBean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String sheetName = null;
		List<T251_Bean> list  = null;
		
		if("111".equals(userBean.getRoleID())){
			String year = (String)request.getSession().getAttribute("allYear") ;
			list = T251_services.totalList(year,Constants.PASS_CHECK);
			sheetName = "表2-5-1本科实验、实习、实训场所-基本情况（设备处）";
		}else{			
			String fillUnitID = userBean.getUnitID();			
			list =T251_services.totalList(this.getSelectYear(),Constants.PASS_CHECK);					
			sheetName = this.excelName;
		}

		
		try {
/*			response.reset();
			response.addHeader("Content-Disposition", "attachment;fileName="
                      + java.net.URLEncoder.encode(excelName,"UTF-8"));*/
			
			//List<T251_Bean> list = T251_services.totalList(this.getSelectYear(),Constants.PASS_CHECK);
						
			//String sheetName = this.excelName;
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("实验中心名称");columns.add("所属教学单位");columns.add("教学单位号");columns.add("下属实验室名称");
			columns.add("创建时间");columns.add("地点");columns.add("台件数量");columns.add("金额（元）");
			columns.add("面积（平方米）");columns.add("其中当年新增面积（平方米）");columns.add("性质");
			columns.add("面向专业");
			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("expCenterName", 1);maplist.put("teaUnit", 2);maplist.put("teaUnitID", 3);maplist.put("labName", 4);
			maplist.put("buildTimeYM", 5);maplist.put("place", 6);maplist.put("machNum", 7);maplist.put("money", 8);
			maplist.put("area", 9);maplist.put("newAddArea", 10);maplist.put("nature", 11);maplist.put("forMajor", 12);
						
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
	
	public String getRows() {
		return rows;
	}


	public void setRows(String rows) {
		this.rows = rows;
	}


	public String getPage() {
		return page;
	}


	public void setPage(String page) {
		this.page = page;
	}
	
	public T251_Bean getT251_bean() {
		return T251_bean;
	}

	public void setT251_bean(T251_Bean T251Bean) {
		T251_bean = T251Bean;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setSeqNum(Integer seqNum) {
		this.seqNum = seqNum;
	}

	public Integer getSeqNum() {
		return seqNum;
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

	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}

	public int getCheckNum() {
		return checkNum;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}

	public String getSelectYear() {
		return selectYear;
	}
}