package cn.nit.action.table1;

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
import cn.nit.bean.table1.T12_Bean;
import cn.nit.bean.table1.T13_Bean;
import cn.nit.dao.table1.T13DAO;
import cn.nit.excel.imports.table1.T13Excel;
import cn.nit.service.table1.T12Service;
import cn.nit.service.table1.T13Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

public class T13_Action {
	
	
	/**  表13的Service类  */
	private T12Service t12Ser = new T12Service() ;
	
	/**  表13的Bean实体类  */
	private T13_Bean t13Bean = new T13_Bean() ;
	
//	/**  表13的DAO类  */
//	private T13DAO t13Dao = new T13DAO() ;
	
	/**  表13的Excel实体类  */
	private T13Excel t13Excel = new T13Excel() ;
	
	/**excel导出名字*/
	private String excelName; //
	
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
	
	/**  待审核数据的查询的序列号  */
	private String unitID ;
	
	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	/**  逐条插入数据  */
	
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
			
			if(this.getUnitID()!= null&&!this.getUnitID().equals("")){
				cond = " where UnitID LIKE '" + this.getUnitID() + "%'";
//				System.out.println(cond);
			}


			String pages = t12Ser.auditingData(cond, "20", Integer.parseInt(page), Integer.parseInt(rows)) ;
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


	
	/**数据导出*/
	public InputStream getInputStream(){
		
//        System.out.println("年份："+this.Year);
		InputStream inputStream = null ;
		

		UserinfoBean userBean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		List<T12_Bean> list   = null;
		String sheetName = null;
		
		if("111".equals(userBean.getRoleID())){
			String year = (String)request.getSession().getAttribute("allYear") ;
			list =  t12Ser.totalList1();
			sheetName = "表1-2学校行政单位（党院办）";
		}else{
			list =  t12Ser.totalList1();
			sheetName = this.excelName;
		}


		try {
			
			//List<T12_Bean> list = t12Ser.totalList1();
			
			//String sheetName = this.excelName;
			
			List<String> columns = new ArrayList<String>();
			columns.add("科研单位名称");columns.add("单位号");
			columns.add("单位负责人");columns.add("教工号");
			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("UnitID", 0);
			maplist.put("UnitName", 1);
			maplist.put("Leader", 2);
			maplist.put("TeaID", 3);
			
			//inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());
			inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}
//        System.out.println(inputStream);
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


	public T13_Bean getT13Bean() {
		return t13Bean;
	}

	public void setT12Bean(T13_Bean t13Bean) {
		this.t13Bean = t13Bean;
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

	public String getUnitID() {
		return unitID;
	}

	public void setUnitID(String unitID) {
		this.unitID = unitID;
	}
	

}
