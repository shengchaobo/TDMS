package cn.nit.action.table1;

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

import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table1.T11Bean;
import cn.nit.bean.table1.T151Bean;
import cn.nit.bean.table1.T16Bean;
import cn.nit.dao.table1.T16DAO;
import cn.nit.excel.imports.table1.T16Excel;
import cn.nit.pojo.table1.T16POJO;
import cn.nit.service.table1.T16Service;


public class T16Action {
	
	/**  表16的Service类  */
	private T16Service t16Ser = new T16Service() ;
	
	/**  表16的Bean实体类  */
	private T16Bean t16Bean = new T16Bean() ;
	
	/**  表16的DAO类  */
	private T16DAO t16Dao = new T16DAO() ;
	
	/**  表16的Excel类  */
	private T16Excel t16Excel = new T16Excel() ;
	
	/**excel导出名字*/
	private String excelName; //
	
	/**数据库导出数据年份*/
	private String selectYear;
	
	public String getSelectYear() {
		return selectYear;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}

	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	/**  待审核数据的查询的序列号  */
	private int seqNum ;
//	
//	/**  待审核数据查询的起始时间  */
//	private Date startTime ;
//	
//	/**  待审核数据查询的结束时间  */
//	private Date endTime ;
//	
//	/**  数据的SeqNumber编号  */
//	private String ids ;
//	
//	/**  当前查询的是第几页  */
//	private String page ;
//	
//	/**每页显示的条数  */
//	private String rows ;
//	
	/**  逐条插入数据  */
	public void insert(){
//		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		t16Bean.setTime(new Date()) ;
		//这还没确定,设置填报者的职工号与部门号
//		UserRoleBean userinfo = (UserRoleBean)getSession().getAttribute("userinfo") ;
//		undergraCSBaseTea.setFillTeaID(userinfo.getTeaID()) ;
		boolean flag = t16Ser.insert(t16Bean) ;
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
		Date currentTi=new Date();
		String str=currentTi.toString();
		String Year=str.substring(str.length()-4, str.length()) ;
		
		String pages = t16Ser.auditingData(Year) ;
//		System.out.println("pages="+pages);
		PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
//			if(pages=="")
//			{
//				out.print("{success:false,errorMsg:'还未录入数据！请添加shu'}");
//			}else
//			{
				out.print(pages) ;
//			}
				
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
		 
//		System.out.println(t16Bean.getSeqNumber());
//		System.out.println(t16Bean.getContents());
//		System.out.println(t16Bean.getItem());
//		System.out.println(t16Bean.getNote());
		
		
		t16Bean.setTime(new Date()) ;
		boolean flag = t16Ser.update(t16Bean) ;
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
	
	public InputStream getInputStream(){

		InputStream inputStream = null ;

		try {
			
			List<T16POJO> list=new ArrayList<T16POJO>(); 
//            Date time=new Date();
//            String time1=time.toString();
//            String year=time1.substring(time1.length()-4, time1.length());
            list=t16Dao.forExcel(this.selectYear);
            inputStream = new ByteArrayInputStream(t16Excel.writeExcel(list).toByteArray());

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

	public T16Bean getT16Bean() {
		return t16Bean;
	}

	public void setT16Bean(T16Bean t16Bean) {
		this.t16Bean = t16Bean;
	}

	public void setSeqNum(int seqNum){
		this.seqNum = seqNum ;
	}
	
	

}
