package cn.nit.action.table3;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.nit.dao.table3.T313_DAO;
import cn.nit.excel.imports.table3.T313Excel;
import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table3.T313_Bean;
import cn.nit.service.table3.T313_Service;
import cn.nit.util.ExcelUtil;



public class T313_Action {
	
private T313_Service discipSer = new T313_Service() ;
	
	private T313_Bean discipBean = new T313_Bean() ;
	
	private T313_DAO t313_DAO=new T313_DAO();
	

	private T313Excel t313Excel = new T313Excel() ;
	
	/**excel导出名字*/
	private String excelName; //
	
	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
	
	
	/**  待审核数据的查询的序列号  */
	private int seqNum ;
	
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
	
	public void insert(){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		discipBean.setTime(new Date()) ;
		//这还没确定,设置填报者的职工号与部门号
		//UserInfo userinfo = (UserInfo)getSession().getAttribute("userinfo") ;
		//discipBean.setFillTeaID(userinfo.getTeaID()) ;
		boolean flag = discipSer.insert(discipBean) ;
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
		
		String conditions = (String) getSession().getAttribute("auditingConditions") ;
		String pages = discipSer.auditingData(conditions, null, Integer.parseInt(page), Integer.parseInt(rows)) ;
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
	
	public T313_Service getDiscipSer() {
		return discipSer;
	}


	public void setDiscipSer(T313_Service discipSer) {
		this.discipSer = discipSer;
	}


	/**  生成查询条件  （查询数据） */
	public void auditingConditions(){
		
		String sqlConditions = discipSer.gernateAuditingConditions(seqNum, startTime, endTime) ;
		getSession().setAttribute("auditingConditions", sqlConditions) ;
		PrintWriter out = null ;
		
		try{
			out = getResponse().getWriter() ;
			out.print("{\"state\":true,data:\"查询失败!!!\"}") ;
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"查询失败!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	/**  编辑数据  */
	public void edit(){

//		System.out.println("插入数据");
		discipBean.setTime(new Date());

		boolean flag = discipSer.update(discipBean) ;
		PrintWriter out = null ;
		
		try{
			out = getResponse().getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"删除成功!!!\"}") ;
			}else{
				out.print("{\"state\":true,data:\"删除失败!!!\"}") ;
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
		boolean flag = discipSer.deleteCoursesByIds(ids) ;
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
			
			List<T313_Bean> list = t313_DAO.totalList();
			
			String sheetName = this.getExcelName();
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("重点学科名称");columns.add("学科代码");columns.add("所属教学单位");
			columns.add("单位号");columns.add("学科门类");columns.add("国家一级");columns.add("国家二级");columns.add("国家重点");
			columns.add("省部一级");columns.add("省部二级");columns.add("市级");columns.add("校级");columns.add("备注");
			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("DiscipName", 1);maplist.put("DiscipID", 2);maplist.put("UnitName", 3);maplist.put("UnitID", 4);
			maplist.put("DiscipType", 5);maplist.put("NationLevelOne", 6);maplist.put("NationLevelTwo", 7);maplist.put("NationLevelKey", 8);
			maplist.put("ProvinceLevelOne", 9);maplist.put("ProvinceLevelTwo", 10);maplist.put("CityLevel", 11);maplist.put("SchLevel", 12);
			maplist.put("Note", 13);
			
			
			
			//inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());
			inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist, columns).toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}

		return inputStream ;
	}
	
	


	public String execute() throws Exception{

		getResponse().setContentType("application/octet-stream;charset=UTF-8") ;
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
	
	public UserRoleBean getUserinfo(){
		return (UserRoleBean)getSession().getAttribute("userinfo") ;
	}



	public void setSeqNum(int seqNum){
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

	public void setPage(String page){
		this.page = page ;
	}
	
	public void setRows(String rows){
		this.rows = rows ;
	}
	
	public T313_Bean getDiscipBean() {
		return discipBean;
	}


	public void setDiscipBean(T313_Bean discipBean) {
		this.discipBean = discipBean;
	}


	public static void main(String args[]){
		String match = "[\\d]+" ;
		System.out.println("23gfhf4".matches(match)) ;
	}

}
