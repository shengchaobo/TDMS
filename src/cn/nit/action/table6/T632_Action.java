package cn.nit.action.table6;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table6.T621_Bean;
import cn.nit.bean.table6.T631_Bean;
import cn.nit.bean.table6.T632_Bean;
import cn.nit.dao.table6.T632_Dao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.service.table6.T632_Service;
import cn.nit.util.DAOUtil;
import cn.nit.util.ExcelUtil;

/**
 * 待完成！！！！！！！
 * 
 * @author Yuan
 */
public class T632_Action {

	/** �?32的Service�?*/
	private T632_Service T632_service = new T632_Service();

	/** �?32的Bean实体�?*/
	T632_Bean T632_bean = new T632_Bean();
	
	T632_Dao T632_dao = new T632_Dao();

	/** 待审核数据的查询的序列号 */
	private int seqNum;

	/** 待审核数据查询的起始时间 */
	private Date startTime;

	/** 待审核数据查询的结束时间 */
	private Date endTime;
	
	private String excelName; //excel导出名字
	
	//待查询的专业名称
	private String searchItem;

	/** 数据的SeqNumber编号 */
	private String ids;

	/** 当前查询的是第几*/
	private String page;

	/** 每页显示的条*/
	private String rows;
	
	/**所属教学单*/
	private String fromTeaUnit;
	
	/**专业名称*/
	private String majorName;

	/** 逐条插入数据 */
	public void insert() {
		System.out
				.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		T632_bean.setSumGoOnHighStudyNum(T632_bean.getRecommendGraNum()+T632_bean.getExamGraInSch()+T632_bean.getExamGraOutSch()+T632_bean.getAbroadNum());//统计生成-应届升学总人�?
		T632_bean.setExamGraEnrollNum(T632_bean.getExamGraInSch()+T632_bean.getExamGraOutSch());//统计生成-考研录取总人�?
		T632_bean.setGoOnHighStudy(T632_bean.getSumGoOnHighStudyNum());//引用生成--升学
		
		T632_bean.setSumEmployNum(T632_bean.getGovermentNum()+T632_bean.getPubInstiNum()+T632_bean.getEnterpriseNum()+T632_bean.getForceNum()
				+T632_bean.getFlexibleEmploy()+T632_bean.getNationItemEmploy()+T632_bean.getOtherEmploy()+T632_bean.getGoOnHighStudy());//统计生成-应届就业总人�?
		
		boolean flag = T632_service.insert(T632_bean);
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

	/** 为界面加载数�?*/
	public void loadData() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		
		
		// private JSONObject jsonObj;
		
		String cond = "1=1";
		if(this.getSearchItem()!= null){
			cond += " and MajorName LIKE '" + this.getSearchItem() + "%'";
			System.out.println(cond);
		}
		List<T632_Bean> list = T632_service.getPageInfoList(cond,null,this.getRows(), this.getPage());
		String TeaInfoJson = this.toBeJson(list, T632_service.getTotal(cond,null));

		PrintWriter out = null;

		if (TeaInfoJson == null) {
			return;
		} else {
			try {

				System.out.println(TeaInfoJson);
				response.setContentType("application/json;charset=UTF-8");
				out = response.getWriter();
				out.print(TeaInfoJson);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.flush();
					out.close();
				}
			}
		}
	}

	// 将分页系统的总数以及当前页的list转化一个json传页面显�?
	private String toBeJson(List<T632_Bean> list, int total) throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();

		JSONObject testjson = new JSONObject();
		testjson.accumulate("total", total);
		testjson.accumulate("rows", list);

		String json = testjson.toString();
		System.out.println(json);
		return json;
	}


	/** 编辑数据 */
	public void edit() {
		
		T632_bean.setSumGoOnHighStudyNum(T632_bean.getRecommendGraNum()+T632_bean.getExamGraInSch()+T632_bean.getExamGraOutSch()+T632_bean.getAbroadNum());//统计生成-应届升学总人�?
		T632_bean.setExamGraEnrollNum(T632_bean.getExamGraInSch()+T632_bean.getExamGraOutSch());//统计生成-考研录取总人�?
		T632_bean.setGoOnHighStudy(T632_bean.getSumGoOnHighStudyNum());//引用生成--升学
		
		T632_bean.setSumEmployNum(T632_bean.getGovermentNum()+T632_bean.getPubInstiNum()+T632_bean.getEnterpriseNum()+T632_bean.getForceNum()
				+T632_bean.getFlexibleEmploy()+T632_bean.getNationItemEmploy()+T632_bean.getOtherEmploy()+T632_bean.getGoOnHighStudy());//统计生成-应届就业总人�?

		boolean flag = T632_service.update(T632_bean);
		PrintWriter out = null;

		try {
			out = getResponse().getWriter();
			if (flag) {
				out.print("{\"state\":true,data:\"编辑成功!!!\"}");
			} else {
				out.print("{\"state\":true,data:\"编辑失败!!!\"}");
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			out.print("{\"state\":false,data:\"系统错误，请联系管理�?!!\"}");
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/** 根据数据的id删除数据 */
	public void deleteByIds() {
		System.out.println("ids=" +this.getIds());
		boolean flag = T632_service.deleteItemsByIds(ids);
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
			out.print("{\"state\":false,data:\"系统错误，请联系管理�?!!\"}");
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	public InputStream getInputStream() {

		InputStream inputStream = null ;
		
		try {
/*			response.reset();
			response.addHeader("Content-Disposition", "attachment;fileName="
                      + java.net.URLEncoder.encode(excelName,"UTF-8"));*/
			
			List<T632_Bean> list = T632_dao.getAllList("1=1", null);
						
			String sheetName = this.getExcelName();
			
			List<String> columns = new ArrayList<String>();
			
		
			
			columns.add("序号");
			columns.add("教学单位");
			columns.add("单位");
			columns.add("专业名称");
			columns.add("专业代码");
			columns.add("应届就业总人");
			columns.add("政府机构就业人数");
			columns.add("事业单位就业人数");
			columns.add("企业就业人数");
			columns.add("部队人数");
			columns.add("灵活就业人数");
			columns.add("升学人数");
			columns.add("参加国家地方项目就业人数");
			columns.add("其他人数");
			
			columns.add("应届升学总人");
			columns.add("免试推荐研究生人");
			columns.add("考研报名人数");
			columns.add("考研录取总人");
			columns.add("考取本校人数");
			columns.add("考取外校人数");
			columns.add("出国（境）留学人");
			
			columns.add("时间");
			columns.add("备注");
			

			Map<String,Integer> maplist = new HashMap<String,Integer>();
		

			
			maplist.put("seqNumber", 0);
			maplist.put("teaUnit", 1);
			maplist.put("unitId", 2);
			maplist.put("majorName", 3);
			maplist.put("majorId", 4);
			
			maplist.put("sumEmployNum", 5);
			maplist.put("govermentNum", 6);
			maplist.put("pubInstiNum", 7);
			maplist.put("enterpriseNum", 8);
			maplist.put("forceNum", 9);
			maplist.put("flexibleEmploy", 10);
			maplist.put("goOnHighStudy", 11);
			maplist.put("nationItemEmploy", 12);
			maplist.put("otherEmploy", 13);
			
			maplist.put("sumGoOnHighStudyNum", 14);
			maplist.put("recommendGraNum", 15);
			maplist.put("examGraApplyNum", 16);
			maplist.put("examGraEnrollNum", 17);
			maplist.put("examGraInSch", 18);
			maplist.put("examGraOutSch", 19);
			maplist.put("abroadNum", 20);
						
			maplist.put("time", 21);
			maplist.put("note", 22);
				
			inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}

		return inputStream ;
	}

	public String execute() throws Exception {

		getResponse().setContentType("application/octet-stream;charset=UTF-8");
		return "success";
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

	public UserRoleBean getUserinfo() {
		return (UserRoleBean) getSession().getAttribute("userinfo");
	}



	public int getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(int seqNum) {
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

	public T632_Service getT632_service() {
		return T632_service;
	}

	public void setT632_service(T632_Service t631Service) {
		T632_service = t631Service;
	}

	public T632_Bean getT632_bean() {
		return T632_bean;
	}

	public void setT632_bean(T632_Bean t632Bean) {
		T632_bean = t632Bean;
	}

	public String getSearchItem() {
		return searchItem;
	}

	public void setSearchItem(String searchItem) {
		this.searchItem = searchItem;
	}

	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	public static void main(String args[]) {
		String match = "[\\d]+";
		System.out.println("23gfhf4".matches(match));
	}




}
