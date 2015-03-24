package cn.nit.action.table4;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import cn.nit.bean.table4.T42_Bean;
import cn.nit.bean.table4.T461_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.table4.T42_Dao;
import cn.nit.dao.table4.T461_Dao;
import cn.nit.service.CheckService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table4.T461_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;


public class T461_Action {
	
	private String rows; //每页显示的记录数
	
	private String page; //当前第几页
	
	private String param; //荣誉类型
	
	private T461_Service T461_services = new T461_Service();
	
	private CheckService check_services = new CheckService();
	
	/**  部门管理Service类  */
	private DiDepartmentService deSer = new DiDepartmentService() ;
	
	private T461_Bean T461_bean = new T461_Bean();
	
//	private T461_Dao T461_dao = new T461_Dao();
	
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
	public void loadHonorInfo() throws Exception{
		
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
		 
		
		String fillUnitID = null;
		if("6".equals(this.getParam())){
			//用于466表区别各教学单位
			//插入教学单位
			UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
			String tempUnitID = bean.getUnitID().substring(0,1);
			if("3".equals(tempUnitID)){
				fillUnitID = bean.getUnitID();
			}
		}
		
		List<T461_Bean> list = T461_services.getPagehonorList(cond, fillUnitID, this.getRows(), this.getPage(), this.getParam()) ;
		String TeaInfoJson = this.toBeJson(list,T461_services.getTotal(cond, fillUnitID, this.getParam()));
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
	private String toBeJson(List<T461_Bean> list, int total) throws Exception{
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

		T461_bean.setTime(new Date());
		
		//插入审核状态
		T461_bean.setCheckState(Constants.WAIT_CHECK);
		
		if("6".equals(this.getParam())){
			//插入教学单位
			UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
			String fillUnitID = bean.getUnitID();
			T461_bean.setFillUnitID(fillUnitID);
			
			String unitName = deSer.getName(fillUnitID);
			T461_bean.setFromTeaUnit(unitName);
			T461_bean.setUnitId(fillUnitID);
		}

		
		boolean flag = T461_services.insert(T461_bean);
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
		
		int tag = 0;
		//获得该条数据审核状态
		int state = T461_services.getCheckState(T461_bean.getSeqNumber());
		//如果审核状态是待审核，则直接修改
		if(state == Constants.WAIT_CHECK){
			System.out.println("test"+state);
			T461_bean.setCheckState(Constants.WAIT_CHECK);
			flag = T461_services.update(T461_bean) ;
			if(flag) tag = 1;
		}
		//如果是审核不通过，则修改该条数据，并将审核状态调节为待审核，同时删除该条数据在checkInfo表的信息
		if(state == Constants.NOPASS_CHECK){
			T461_bean.setCheckState(Constants.WAIT_CHECK);
			boolean flag1 = T461_services.update(T461_bean) ;
			boolean flag2 = false;
			if("1".equals(this.getParam())){
				//删除审核不通过信息
				flag2 = check_services.delete("T461",T461_bean.getSeqNumber());
			}
			else if("2".equals(this.getParam())){
				//删除审核不通过信息
				flag2 = check_services.delete("T462",T461_bean.getSeqNumber());
			}
			else if("3".equals(this.getParam())){
				//删除审核不通过信息
				flag2 = check_services.delete("T463",T461_bean.getSeqNumber());
			}
			else if("4".equals(this.getParam())){
				//删除审核不通过信息
				flag2 = check_services.delete("T464",T461_bean.getSeqNumber());
			}
			else if("5".equals(this.getParam())){
				//删除审核不通过信息
				flag2 = check_services.delete("T465",T461_bean.getSeqNumber());
			}
			else if("6".equals(this.getParam())){
				//删除审核不通过信息
				flag2 = check_services.delete("T466",T461_bean.getSeqNumber());
			}
			
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
				if("1".equals(this.getParam())){
					//删除审核不通过信息
					out.print("{\"state\":true,data:\"修改成功!!!\",tag:2,tableID:\"T461\"}") ;
				}
				else if("2".equals(this.getParam())){
					//删除审核不通过信息
					out.print("{\"state\":true,data:\"修改成功!!!\",tag:2,tableID:\"T462\"}") ;
				}
				else if("3".equals(this.getParam())){
					//删除审核不通过信息
					out.print("{\"state\":true,data:\"修改成功!!!\",tag:2,tableID:\"T463\"}") ;
				}
				else if("4".equals(this.getParam())){
					//删除审核不通过信息
					out.print("{\"state\":true,data:\"修改成功!!!\",tag:2,tableID:\"T464\"}") ;
				}
				else if("5".equals(this.getParam())){
					//删除审核不通过信息
					out.print("{\"state\":true,data:\"修改成功!!!\",tag:2,tableID:\"T465\"}") ;
				}
				else if("6".equals(this.getParam())){
					//删除审核不通过信息
					out.print("{\"state\":true,data:\"修改成功!!!\",tag:2,tableID:\"T466\"}") ;
				}
				
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
		System.out.println("ids=" + this.getIds()) ;
		boolean flag = T461_services.deleteByIds(ids) ;
		if("1".equals(this.getParam())){
			//删除审核不通过信息
			check_services.delete("T461", ids);
		}
		else if("2".equals(this.getParam())){
			//删除审核不通过信息
			check_services.delete("T462", ids);
		}
		else if("3".equals(this.getParam())){
			//删除审核不通过信息
			check_services.delete("T463", ids);
		}
		else if("4".equals(this.getParam())){
			//删除审核不通过信息
			check_services.delete("T464", ids);
		}
		else if("5".equals(this.getParam())){
			//删除审核不通过信息
			check_services.delete("T465", ids);
		}
		else if("6".equals(this.getParam())){
			//删除审核不通过信息
			check_services.delete("T466", ids);
		}
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
	
		boolean flag = T461_services.updateCheck(this.getSeqNum(),this.getCheckNum());
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
	
		boolean flag = T461_services.checkAll(this.getParam());
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
		
		try {
			
			//用于466表区别各教学单位
			//插入教学单位
			UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
			String fillUnitID = null;
			
			if(this.getParam() != "6"){
				fillUnitID = bean.getUnitID();
			}
			
			List<T461_Bean> list = T461_services.totalList(this.getParam(),fillUnitID,this.getSelectYear(),Constants.PASS_CHECK);
						
			String sheetName = this.excelName;
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("姓名");columns.add("教工号");columns.add("所属教学单位");columns.add("单位号");columns.add("类别");
			columns.add("获奖级别");columns.add("授予单位");columns.add("获奖时间");
			columns.add("批文号");columns.add("其他参与教师人数");columns.add("其他成员");columns.add("备注");
			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("name", 1);maplist.put("teaId", 2);maplist.put("fromTeaUnit", 3);maplist.put("unitId", 4);
			maplist.put("awardType", 5);maplist.put("awardLevel", 6);maplist.put("awardFromUnit", 7);maplist.put("gainAwardTime", 8);
			maplist.put("appvlId", 9);maplist.put("otherTeaNum", 10);maplist.put("otherTeaInfo", 11);maplist.put("note", 12);
						
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
	
	public T461_Bean getT461_bean() {
		return T461_bean;
	}

	public void setT461_bean(T461_Bean T461Bean) {
		T461_bean = T461Bean;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getParam() {
		return param;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	public void setSeqNum(Integer seqNum) {
		this.seqNum = seqNum;
	}

	public Integer getSeqNum() {
		return seqNum;
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