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

import cn.nit.dao.table1.T18DAO;
import cn.nit.dao.table3.T321_DAO;

import cn.nit.excel.imports.table3.T321Excel;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.bean.di.DiDepartmentBean;


import cn.nit.bean.other.UserRoleBean;



import cn.nit.bean.table3.T321_Bean;





import cn.nit.service.table3.T321_Service;
import cn.nit.util.ExcelUtil;



public class T321_Action {
	

	
	
	private T321_Service t321_Service = new T321_Service() ;
	
	int num=0;
	
	private T321_Bean t321_Bean = new T321_Bean() ;
	
	/**  表181的Dao类  */
	private T321_DAO t321_DAO = new T321_DAO() ;
	
	/**  表181的Excel实体类  */
	private T321Excel t321Excel = new T321Excel() ;
	
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
	
//	public List<Integer> getNumofMainTrain(){
//		List<Integer> list1=new ArrayList<Integer> ();
//		List<DiDepartmentBean> list = diDepartmentSer.getList() ;
//		for(int i=0;i<=list.size();i++){
//			//num=mainTrainBasicInfoDao.getNumofMainTrain(list.get(i).getUnitID());
//			list1.add(num);
//			
//		}
//		return list1;
//		
//	}
	
	public void insert(){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		t321_Bean.setTime(new Date()) ;
		//这还没确定,设置填报者的职工号与部门号
		//UserInfo userinfo = (UserInfo)getSession().getAttribute("userinfo") ;
		//t321_Bean.setFillTeaID(userinfo.getTeaID()) ;
		boolean flag = t321_Service.insert(t321_Bean) ;
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
		String pages = t321_Service.auditingData(conditions, null, Integer.parseInt(page), Integer.parseInt(rows)) ;
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
	



	public T321_Service getT321_Service() {
		return t321_Service;
	}


	public void setT321_Service(T321_Service t321Service) {
		t321_Service = t321Service;
	}


	public T321_Bean getT321_Bean() {
		return t321_Bean;
	}


	public void setT321_Bean(T321_Bean t321Bean) {
		t321_Bean = t321Bean;
	}


	/**  生成查询条件  （查询数据） */
	public void auditingConditions(){
		
		String sqlConditions = t321_Service.gernateAuditingConditions(seqNum, startTime, endTime) ;
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
		t321_Bean.setTime(new Date());

		boolean flag = t321_Service.update(t321_Bean) ;
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
		boolean flag = t321_Service.deleteCoursesByIds(ids) ;
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
			
			List<T321_Bean> list = t321_DAO.totalList();
			
			String sheetName = this.getExcelName();
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("大类名称");columns.add("大类代码");columns.add("分流时间");
			columns.add("包含校内专业名称");columns.add("校内专业代码");columns.add("所属单位");
			columns.add("单位号");columns.add("备注");

			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("MainClassName", 1);maplist.put("MainClassID", 2);maplist.put("ByPassTime", 3);maplist.put("MajorNameInSch", 4);
			maplist.put("MajorID", 5);maplist.put("UnitName", 6);maplist.put("UnitID", 7);maplist.put("Note", 8);
			
			
			//inputStream = new ByteArrayInputStream(ExcelUtil.exportExcel(list, sheetName, maplist,columns).toByteArray());
			inputStream = new ByteArrayInputStream(t321Excel.batchExport(list, sheetName, maplist, columns).toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}

		return inputStream ;
	}
	

//	
//	columns.add("序号");
//	columns.add("大类名称");columns.add("大类代码");columns.add("分流时间");
//	columns.add("包含校内专业名称");columns.add("校内专业代码");columns.add("所属单位");
//	columns.add("单位号");columns.add("备注");
//
//	
//	Map<String,Integer> maplist = new HashMap<String,Integer>();
//	maplist.put("SeqNum", 0);
//	maplist.put("MainClassName", 1);maplist.put("MainClassID", 2);maplist.put("ByPassTime", 3);maplist.put("MajorNameInSch", 4);
//	maplist.put("MajorID", 5);maplist.put("UnitName", 6);maplist.put("UnitID", 7);maplist.put("Note", 8);
//	

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
	



	public static void main(String args[]){
		String match = "[\\d]+" ;
		System.out.println("23gfhf4".matches(match)) ;
	}
}
