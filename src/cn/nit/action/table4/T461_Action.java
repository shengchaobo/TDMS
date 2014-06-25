package cn.nit.action.table4;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table4.T42_Bean;
import cn.nit.bean.table4.T461_Bean;
import cn.nit.dao.table4.T42_Dao;
import cn.nit.dao.table4.T461_Dao;
import cn.nit.service.table4.T461_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;


public class T461_Action {
	
	private String rows; //每页显示的记录数
	
	private String page; //当前第几页
	
	private String param; //页面传递参数
	
	private T461_Service T461_services = new T461_Service();
	
	private T461_Bean T461_bean = new T461_Bean();
	
	private T461_Dao T461_dao = new T461_Dao();
	
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



	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	
	//查询出所有
	public void loadHonorInfo() throws Exception{
		
		HttpServletResponse response = ServletActionContext.getResponse() ;	
		
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
		
		//用于466表区别各教学单位
		String fillUnitID ;
		if(this.getParam() != "6"){
			fillUnitID = null;
		}else{
			fillUnitID = null;
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

		boolean flag = T461_services.update(T461_bean) ;
		PrintWriter out = null ;
	
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
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
	public void deleteByIds(){
		System.out.println("ids=" + this.getIds()) ;
		boolean flag = T461_services.deleteByIds(ids) ;
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
	
	public InputStream getInputStream() throws UnsupportedEncodingException{

		InputStream inputStream = null ;
		
		try {
/*			response.reset();
			response.addHeader("Content-Disposition", "attachment;fileName="
                      + java.net.URLEncoder.encode(excelName,"UTF-8"));*/
			
			List<T461_Bean> list = T461_dao.totalList(this.getParam());
						
			String sheetName = this.getExcelName();
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("姓名");columns.add("教工号");columns.add("所属教学单位");columns.add("单位号");columns.add("类别");
			columns.add("获奖级别");columns.add("授予单位");columns.add("获奖时间");
			columns.add("批文号");columns.add("其他参与教师人数");columns.add("其他成员");columns.add("备注");
			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("name", 1);maplist.put("teaId", 2);maplist.put("fromTeaUnit", 3);maplist.put("unitId", 4);
			maplist.put("awardType", 5);maplist.put("awardLevel", 6);maplist.put("awardFromUnit", 7);maplist.put("gainAwardTime", 8);
			maplist.put("appvlId", 9);maplist.put("otherTeaNum", 10);maplist.put("otherTeaInfo", 11);maplist.put("note", 11);
						
			inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}

		return inputStream ;
	}
	
	public String execute() throws Exception{
		request.setCharacterEncoding("UTF-8") ;
		System.out.println("excelName=============" + excelName) ;
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
		return excelName;
	}
}