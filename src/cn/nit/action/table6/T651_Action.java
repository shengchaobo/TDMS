package cn.nit.action.table6;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.table4.T441_Bean;
import cn.nit.bean.table6.T611_Bean;
import cn.nit.bean.table6.T612_Bean;
import cn.nit.bean.table6.T613_Bean;
import cn.nit.bean.table6.T614_Bean;
import cn.nit.bean.table6.T615_Bean;
import cn.nit.bean.table6.T617_Bean;
import cn.nit.bean.table6.T621_Bean;
import cn.nit.bean.table6.T622_Bean;
import cn.nit.bean.table6.T631_Bean;
import cn.nit.bean.table6.T632_Bean;
import cn.nit.bean.table6.T641_Bean;
import cn.nit.bean.table6.T651_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.table6.T611_Dao;
import cn.nit.dao.table6.T612_Dao;
import cn.nit.dao.table6.T613_Dao;
import cn.nit.dao.table6.T614_Dao;
import cn.nit.dao.table6.T615_Dao;
import cn.nit.dao.table6.T617_Dao;
import cn.nit.dao.table6.T622_Dao;
import cn.nit.dao.table6.T632_Dao;
import cn.nit.dao.table6.T641_Dao;
import cn.nit.dao.table6.T651_Dao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.service.CheckService;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.table6.T611_Service;
import cn.nit.service.table6.T612_Service;
import cn.nit.service.table6.T613_Service;
import cn.nit.service.table6.T614_Service;
import cn.nit.service.table6.T615_Service;
import cn.nit.service.table6.T617_Service;
import cn.nit.service.table6.T622_Service;
import cn.nit.service.table6.T632_Service;
import cn.nit.service.table6.T641_Service;
import cn.nit.service.table6.T651_Service;
import cn.nit.util.DAOUtil;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

/**
 * 待完成！！！！！！！
 * 
 * @author Yuan
 */
public class T651_Action {

	/** 表的Service类 */
	private T651_Service T651_service = new T651_Service();
	
	/**  部门管理Service类  */
	private DiDepartmentService deSer = new DiDepartmentService() ;
	
	
	private CheckService check_services = new CheckService();

	/** 表的Bean实体类 */
	T651_Bean T651_bean = new T651_Bean();
	
//	T651_Dao T651_dao = new T651_Dao();

	/** 待审核数据的查询的序列号 */
	private Integer seqNum;

	/** 待审核数据查询的起始时间 */
	private Date startTime;

	/** 待审核数据查询的结束时间 */
	private Date endTime;
	
	private String excelName; //excel导出名字
	
	//待查询的专业名称
	private String searchItem;

	/** 数据的SeqNumber编号 */
	private String ids;

	/** 当前查询的是第几页 */
	private String page;

	/** 每页显示的条数 */
	private String rows;
	
	/**所属教学单位*/
	private String fromTeaUnit;
	
	/**专业名称*/
	private String majorName;
	
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
	private int checkFlag ;
	
	
	public int getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(int checkFlag) {
		this.checkFlag = checkFlag;
	}

	
	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	UserinfoBean bean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
	String fillUnitID = bean.getUnitID();

	/** 逐条插入数据 */
	public void insert() {
		System.out
				.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//		//插入时间
//		T651_bean.setTime(new Date());
		//插入审核状态
		T651_bean.setCheckState(Constants.WAIT_CHECK);
		T651_bean.setFillUnitID(fillUnitID);
		String teaUnit = deSer.getName(fillUnitID);
		T651_bean.setTeaUnit(teaUnit);
		T651_bean.setUnitId(fillUnitID);
		boolean flag = T651_service.insert(T651_bean);
		PrintWriter out = null;

		try {
			getResponse().setContentType("text/html; charset=UTF-8");
			// getResponse().setHeader("Content-type", "text/html");
			out = getResponse().getWriter();
			if (flag) {
				out.print("{\"state\":true,data:\"录入成功!!!\"}");
			} else {
				out.print("{\"state\":false,data:\"录入失败!!!\"}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.print("{\"state\":false,data:\"录入失败!!!\"}");
		} finally {
			if (out != null) {
				out.close();
			}
		}
		out.flush();
	}

	/** 为界面加载数据 */
	public void loadData() throws Exception {
		
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
			
			String tempfield1 = bean.getUnitID().substring(0, 1);
			String tempfield = bean.getUnitID();
			if(!"3".equals(tempfield1)){
				tempfield=null;
			}
			
//			System.out.println("---------------");
//			System.out.println(tempfield);
			List<T651_Bean> list = T651_service.getPageInfoList(cond, tempfield, this.getRows(), this.getPage()) ;
			String TeaInfoJson = this.toBeJson(list,T651_service.getTotal(cond, tempfield));
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

	// 将分页系统的总数以及当前页的list转化一个json传页面显示
	private String toBeJson(List<T651_Bean> list, int total) throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();

		JSONObject testjson = new JSONObject();
		testjson.accumulate("total", total);
		testjson.accumulate("rows", list);

		String json = testjson.toString();
//		System.out.println(json);
		return json;
	}


	/** 编辑数据 */
	public void edit() {
//		System.out.println(T651_bean.getAwardFromUnit());
//		System.out.println(T651_bean.getAwardGrade());
//		System.out.println(T651_bean.getAwardItem());
//		System.out.println(T651_bean.getAwardLevel());
//		System.out.println(T651_bean.getAwardStuName());
//		//System.out.println(T651_bean.getCheckState());
//		System.out.println(T651_bean.getCompetiName());
//		System.out.println(T651_bean.getCompetiType());
//		System.out.println(T651_bean.getFillUnitID());
//		System.out.println(T651_bean.getGuideTeaName());
//		System.out.println(T651_bean.getNote());
//		System.out.println(T651_bean.getSeqNumber());
//		System.out.println(T651_bean.getTeaUnit());
//		System.out.println(T651_bean.getUnitId());
//		System.out.println(T651_bean.getAwardStuNum());
//		System.out.println(T651_bean.getAwardTime());
//		System.out.println(T651_bean.getGuideTeaNum());
//		System.out.println(T651_bean.getTime());
//		
		
		
		boolean flag = false;
		
		int tag = 0;
		//获得该条数据审核状态
		int state = T651_service.getCheckState(T651_bean.getSeqNumber());
//		System.out.println("==========");
//		System.out.println(state);
//		System.out.println("test"+state);
		//如果审核状态是待审核，则直接修改
		if(state == Constants.WAIT_CHECK){
//			System.out.println("test"+state);
			T651_bean.setCheckState(Constants.WAIT_CHECK);
			flag = T651_service.update(T651_bean) ;
			if(flag) tag = 1;
		}
		//如果是审核不通过，则修改该条数据，并将审核状态调节为待审核，同时删除该条数据在checkInfo表的信息
		if(state == Constants.NOPASS_CHECK){
//			System.out.println("审核："+state);
			T651_bean.setCheckState(Constants.WAIT_CHECK);
			boolean flag1 = T651_service.update(T651_bean) ;
			boolean flag2 = check_services.delete("T651",T651_bean.getSeqNumber());
			if(flag1&&flag2){
				flag = true;
				tag = 2;
			}
		}
		PrintWriter out = null;

		try {
			out = getResponse().getWriter();
			if(tag == 1){
				out.print("{\"state\":true,data:\"修改成功!!!\"}") ;
			}
			else if(tag == 2){
				out.print("{\"state\":true,data:\"修改成功!!!\",tag:2}") ;
			}
			else{
				out.print("{\"state\":true,data:\"修改失败!!!\"}") ;
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			out.print("{\"state\":false,data:\"系统错误，请联系管理员!!!\"}");
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	/**  修改某条数据的审核状态  */
	public void updateCheck(){
		HttpServletResponse response = ServletActionContext.getResponse();
	
		boolean flag = T651_service.updateCheck(this.getSeqNum(),this.getCheckNum());
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
	
		boolean flag = T651_service.checkAll();
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
	

	/** 根据数据的id删除数据 */
	public void deleteByIds() {
//		System.out.println("ids=" +this.getIds());
		boolean flag = T651_service.deleteItemsByIds(ids);
		//删除审核不通过信息
		check_services.delete("T651", ids);
		PrintWriter out = null;

		try {
			out = getResponse().getWriter();

			if (flag) {
				out.print("{\"state\":true,data:\"数据删除成功!!!\"}");
			} else {
				out.print("{\"state\":false,data:\"数据删除失败!!!\"}");
			}

			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			out.print("{\"state\":false,data:\"系统错误，请联系管理员!!!\"}");
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	public InputStream getInputStream() {
		InputStream inputStream = null ;
		UserinfoBean userBean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String sheetName = null;
		List<T651_Bean> list = null;
		
		if("111".equals(userBean.getRoleID())){
			String year = (String)request.getSession().getAttribute("allYear") ;
			list = T651_service.totalList("111",year,Constants.PASS_CHECK);
			sheetName = "表6-5-1学习成果—本科生竞赛获奖情况（教学单位-团委）";
		}else{			
			String fillUnitID = userBean.getUnitID();			
			list = T651_service.totalList(fillUnitID,this.getSelectYear(),Constants.PASS_CHECK);						
			sheetName = this.excelName;
		}
		
		try {
				
			List<String> columns = new ArrayList<String>();
						
			columns.add("序号");
			columns.add("教学单位");
			columns.add("单位号");
			columns.add("竞赛类型");
			columns.add("赛事名称");
			columns.add("获奖项目");
			columns.add("级别");
			columns.add("等级");
			columns.add("授予单位");
			columns.add("获奖时间");
			columns.add("获奖学生姓名");
			columns.add("获奖学生数");
			columns.add("指导教师");
			columns.add("指导教师数");
			
			columns.add("填写单位");
			columns.add("时间");
			columns.add("备注");
			

			Map<String,Integer> maplist = new HashMap<String,Integer>();
		
			maplist.put("seqNumber", 0);
			maplist.put("teaUnit", 1);
			maplist.put("unitId", 2);
			maplist.put("competiType", 3);
			maplist.put("competiName", 4);
			
			maplist.put("awardItem", 5);
			maplist.put("awardLevel", 6);
			maplist.put("awardGrade", 7);
			maplist.put("awardFromUnit", 8);
			maplist.put("awardTime", 9);
			maplist.put("awardStuName", 10);
			maplist.put("awardStuNum", 11);
			maplist.put("guideTeaName", 12);
			maplist.put("guideTeaNum", 13);
			
			maplist.put("fillUnitID", 14);
			maplist.put("time", 15);
			maplist.put("note", 16);
				
			inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}

		return inputStream ;
	}

	public String execute() throws Exception {

		System.out.println("excelName=============" + this.excelName) ;
		return "success" ;
	}

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpSession getSession() {
		return getRequest().getSession();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
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

	public String getFromTeaUnit() {
		return fromTeaUnit;
	}

	public void setFromTeaUnit(String fromTeaUnit) {
		this.fromTeaUnit = fromTeaUnit;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public T651_Service getT651_service() {
		return T651_service;
	}

	public void setT651_service(T651_Service t631Service) {
		T651_service = t631Service;
	}

	public T651_Bean getT651_bean() {
		return T651_bean;
	}

	public void setT651_bean(T651_Bean T651Bean) {
		T651_bean = T651Bean;
	}

	public String getSearchItem() {
		return searchItem;
	}

	public void setSearchItem(String searchItem) {
		this.searchItem = searchItem;
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

	public static void main(String args[]) {
		String match = "[\\d]+";
		System.out.println("23gfhf4".matches(match));
	}




}
