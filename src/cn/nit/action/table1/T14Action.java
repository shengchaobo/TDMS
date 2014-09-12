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

import cn.nit.bean.table1.T12Bean;
import cn.nit.bean.table1.T14Bean;
import cn.nit.dao.table1.T14DAO;
import cn.nit.excel.imports.table1.T14Excel;
import cn.nit.service.table1.T12Service;
import cn.nit.service.table1.T14Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

public class T14Action {
	

	/**  表14的Service类  */
	private T12Service t12Ser = new T12Service() ;
	
	/**  表14的Bean实体类  */
	private T14Bean t14Bean = new T14Bean() ;
	
	/**  表14的Dao类  */
	private T14DAO t14Dao = new T14DAO() ;
	
	/**  表14的Excel实体类  */
	private T14Excel t14Excel = new T14Excel() ;
	
	
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
	
	/**  为界面加载数据  */
	public void auditingData(){
			
			System.out.println("輸出輸出輸出");
			
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


			String pages = t12Ser.auditingData(cond, "30", Integer.parseInt(page), Integer.parseInt(rows)) ;
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

//	/**  为界面加载数据  */
//	public void auditingData(){
//		
//		Date date=new Date();
//		String cuYear=date.toString();
//		String year=cuYear.substring(cuYear.length()-4, cuYear.length());
//		
//		String pages = t12Ser.auditingData() ;
//		PrintWriter out = null ;
//		
//		try{
//			getResponse().setContentType("text/html; charset=UTF-8") ;
//			out = getResponse().getWriter() ;
//			out.print(pages) ;
//		}catch(Exception e){
//			e.printStackTrace() ;
//			return ;
//		}finally{
//			if(out != null){
//				out.close() ;
//			}
//		}
//	}
	
	/**数据导出*/
	public InputStream getInputStream(){
		
//        System.out.println("年份："+this.Year);
		InputStream inputStream = null ;

		try {
			
			List<T12Bean> list = t14Dao.totalList();
			
			String sheetName = this.excelName;
			
			List<String> columns = new ArrayList<String>();
			columns.add("教学单位名称");columns.add("单位号");
			columns.add("单位负责人");columns.add("备注");

			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("UnitName", 0);maplist.put("UnitID", 1);
			maplist.put("Leader", 2);
			maplist.put("Note", 3);
			
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


	public T14Bean getT14Bean() {
		return t14Bean;
	}

	public void setT14Bean(T14Bean t14Bean) {
		this.t14Bean = t14Bean;
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
