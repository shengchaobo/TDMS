package cn.nit.action.table1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import cn.nit.bean.table1.T16Bean;
import cn.nit.dao.table1.T16DAO;
import cn.nit.excel.imports.table1.T16Excel;
import cn.nit.pojo.table1.T16POJO;
import cn.nit.service.table1.T16Service;
import cn.nit.util.TimeUtil;
import cn.nit.util.ToBeanUtil;


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
	//save的字段
	private String fields;
	
	/**要删除数据的年份*/
	private String year;
	

	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getFields() {
		return fields;
	}


	public void setFields(String fields) {
		this.fields = fields;
	}
	
	/**  前台获数据 */
	private String data ;


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}
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

	/**  为界面加载数据  
	 * @throws Exception */
	public void loadInfo() throws Exception{
		
//		System.out.println("輸出輸出輸出");
//		Date currentTi=new Date();
//		String str=currentTi.toString();
//		String Year=str.substring(str.length()-4, str.length()) ;
		HttpServletResponse response = ServletActionContext.getResponse() ;	
		String pages = t16Ser.auditingData(this.getSelectYear()) ;
//		System.out.println("pages="+pages);
		PrintWriter out = null ;
		
		if(pages == null){
			System.out.println("no data");
			response.setContentType("text/html;charset=UTF-8") ;
			out = response.getWriter() ;
			out.println( "<script language='javascript'>window.alert('无该年数据');</script>" ); 
		}else{
			System.out.println("have data");
			try {				
//				System.out.println(json) ;
				response.setContentType("application/json;charset=UTF-8") ;
				out = response.getWriter() ;
				out.print(pages) ;
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
	
	/**编辑保存数据*/
	public void save(){
		
//		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String tempData = this.getData();
		System.out.println(tempData);
//		System.out.println("tempDate:"+tempData);
		//System.out.println(tempData);
				
//		T16POJO pojo  = this.toBean(tempData, T16POJO.class);
//		System.out.println("fields:"+this.getFields());	
		System.out.println(this.getFields());
		boolean flag = t16Ser.save(tempData,this.getSelectYear(),this.getFields());
	
		PrintWriter out = null ;
		
		try{
			response.setContentType("application/json; charset=UTF-8") ;
			out = response.getWriter() ;
			if(flag){
				out.print("{\"mesg\":\"success\"}") ;
			}else{
				out.print("{\"mesg\":\"fail\"}") ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"保存失败!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
		out.flush() ;
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
	
	/**  根据数据的year删除数据  */
	public void deleteByYear(){
		
		System.out.println("year=" + year) ;
		boolean flag = t16Ser.deleteByYear(year);
		PrintWriter out = null ;
		
		try{
			out = getResponse().getWriter() ;
			
			if(flag){
				out.print("{\"mesg\":\"success\"}") ;
			}else{
				out.print("{\"mesg\":\"fail\"}") ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"系统错误，请联系管理员!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	/**导出
	 * @throws Exception */
	public InputStream getInputStream() throws Exception{

		System.out.println(this.getSelectYear());

		List<T16POJO> list = t16Ser.forExcel(this.getSelectYear());
		
	    ByteArrayOutputStream fos = null;
	    
	
		if(list==null){
			PrintWriter out = null ;
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			out.print("后台传入的数据为空!!!") ;
			System.out.println("后台传入的数据为空");
		}else{
//			String sheetName = this.getExcelName();
				String sheetName="表1-6办学指导思想（党院办）";	
		    WritableWorkbook wwb;
		    try {    
		           fos = new ByteArrayOutputStream();
		           wwb = Workbook.createWorkbook(fos);
		           WritableSheet ws = wwb.createSheet(sheetName, 0);        // 创建一个工作表

		
		            //    设置单元格的文字格式
		           WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.BOLD,false,
		                    UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
		           WritableCellFormat wcf = new WritableCellFormat(wf);
		           wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
		           wcf.setAlignment(Alignment.CENTRE);
		           wcf.setBorder(Border.ALL, BorderLineStyle.THIN,
						     jxl.format.Colour.BLACK); 
		           wcf.setAlignment(jxl.write.Alignment.CENTRE);
		           ws.setRowView(1, 500);
		     
		           //设置格式
				   WritableCellFormat wcf1 = new WritableCellFormat();
				   wcf1.setBorder(Border.ALL, BorderLineStyle.THIN,
						     jxl.format.Colour.BLACK); 
		           
		           ws.addCell(new Label(0, 0, sheetName, wcf)); 
		           ws.mergeCells(0, 0, 2, 0);
		             
		           ws.addCell(new Label(0, 2, "项目", wcf)); 
		           ws.addCell(new Label(1, 2, "内容", wcf)); 
		           ws.addCell(new Label(2, 2, "备注", wcf));  
		   

		           if(list!=null && list.size()>0){
		        	   System.out.println("导出");
		        	  
		        		   T16POJO pojo = list.get(0);
			        		   ws.addCell(new Label(0, 3, pojo.getItem1(), wcf1)); 
			        		   ws.addCell(new Label(1, 3, pojo.getContents1(), wcf1)); 
			        		   ws.addCell(new Label(2, 3, pojo.getNote1(), wcf1)); 
			        		   ws.addCell(new Label(0, 4, pojo.getItem2(), wcf1)); 
			        		   ws.addCell(new Label(1, 4, pojo.getContents2(), wcf1)); 
			        		   ws.addCell(new Label(2, 4, pojo.getNote2(), wcf1)); 
			           }else{
		        	   System.out.println("后台传入的数据为空");
		           }
		          wwb.write();
		          wwb.close();

		        } catch (IOException e){
		        } catch (RowsExceededException e){
		        } catch (WriteException e){}
		        
		}
		return new ByteArrayInputStream(fos.toByteArray());
	}
	

//	public InputStream getInputStream(){
//
//		InputStream inputStream = null ;
//
//		try {
//			
//			List<T16POJO> list=new ArrayList<T16POJO>(); 
////            Date time=new Date();
////            String time1=time.toString();
////            String year=time1.substring(time1.length()-4, time1.length());
//            list=t16Dao.forExcel(this.getSelectYear());
//            inputStream = new ByteArrayInputStream(t16Excel.writeExcel(list).toByteArray());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null ;
//		}
//		return inputStream ;
//	}
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
