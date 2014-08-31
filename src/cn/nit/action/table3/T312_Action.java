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

import cn.nit.bean.table3.T312_Bean;
import cn.nit.dao.table3.T312_DAO;
import cn.nit.excel.imports.table3.T312Excel;
import cn.nit.service.table3.T312_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;





public class T312_Action {

	private T312_Service docAndGraStaSer = new T312_Service() ;
	
	private T312_Bean docAndGraStaBean = new T312_Bean() ;
	
	/**  表181的Dao类  */
	private T312_DAO t312_DAO = new T312_DAO();
	
	/**  表181的Excel实体类  */
	private T312Excel t312Excel = new T312Excel() ;
	
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
	
	public void insert(){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		docAndGraStaBean.setTime(new Date()) ;
		//这还没确定,设置填报者的职工号与部门号
		//UserInfo userinfo = (UserInfo)getSession().getAttribute("userinfo") ;
		//docAndGraStaBean.setFillTeaID(userinfo.getTeaID()) ;
		boolean flag = docAndGraStaSer.insert(docAndGraStaBean) ;
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
		
//		System.out.println("輸出輸出輸出");
		
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
		String pages = docAndGraStaSer.auditingData(cond, null, Integer.parseInt(page), Integer.parseInt(rows)) ;
	

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

		boolean flag = docAndGraStaSer.update(docAndGraStaBean) ;
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
		boolean flag = docAndGraStaSer.deleteCoursesByIds(ids) ;
		PrintWriter out = null ;
		System.out.println("1111111");
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
			
			List<T312_Bean> list = t312_DAO.totalList();
			
			String sheetName = this.excelName;
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("名称");columns.add("代码");columns.add("所属单位");columns.add("单位号");
			columns.add("类型");

			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("StaName", 1);maplist.put("StaID", 2);maplist.put("UnitName", 3);maplist.put("UnitID", 4);
			maplist.put("StaType", 5);
			
			//inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());

			inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist, columns).toByteArray());
			inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());

		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}

		return inputStream ;
	}
	

	public T312_DAO getT312_DAO() {
		return t312_DAO;
	}

	public void setT312_DAO(T312_DAO t312DAO) {
		t312_DAO = t312DAO;
	}

	public T312Excel getT312Excel() {
		return t312Excel;
	}

	public void setT312Excel(T312Excel t312Excel) {
		this.t312Excel = t312Excel;
	}

	public Integer getSeqNum() {
		return seqNum;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public String getIds() {
		return ids;
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
		
	public T312_Bean getDocAndGraStaBean() {
		return docAndGraStaBean;
	}

	public void setDocAndGraStaBean(T312_Bean docAndGraStaBean) {
		this.docAndGraStaBean = docAndGraStaBean;
	}
	
	
	public T312_Service getDocAndGraStaSer() {
		return docAndGraStaSer;
	}

	public void setDocAndGraStaSer(T312_Service docAndGraStaSer) {
		this.docAndGraStaSer = docAndGraStaSer;
	}

	public void setSeqNum(Integer seqNum){
		this.seqNum = seqNum ;
	}
	
	public void setStartTime(Date startTime){
		this.startTime = startTime ;
	}
	
	public void setEndTime(Date endTime){
		this.endTime = endTime ;
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
