package cn.nit.action.table1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.table1.T11_Bean;
import cn.nit.bean.table1.T16_Bean;
import cn.nit.bean.table2.T21_Bean;
import cn.nit.dao.table1.T16DAO;
import cn.nit.excel.imports.table1.T16Excel;
import cn.nit.pojo.table1.T16POJO;
import cn.nit.service.table1.T16Service;
import cn.nit.util.TimeUtil;
import cn.nit.util.ToBeanUtil;


public class T16_Action {
	
	/**  表16的Service类  */
	private T16Service t16Ser = new T16Service() ;
	
	/**  表16的Bean实体类  */
	private T16_Bean t16Bean = new T16_Bean() ;
	
//	/**  表16的DAO类  */
//	private T16DAO t16Dao = new T16DAO() ;
	
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
	
	/**  前台获数据 */
	private String data ;

	/**  待审核数据的查询的序列号  */
	private int seqNum ;
	
	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;


	/**  为界面加载数据  
	 * @throws Exception */
	public void loadInfo() throws Exception{
		
//		System.out.println("輸出輸出輸出");
//		Date currentTi=new Date();
//		String str=currentTi.toString();
//		String Year=str.substring(str.length()-4, str.length()) ;
		HttpServletResponse response = ServletActionContext.getResponse() ;	
//		System.out.println(this.getSelectYear());
		String pages = t16Ser.auditingData(this.getSelectYear()) ;
		boolean flag = false;
		if(pages!=null){
			flag= true;
		}
//		System.out.println("pages="+pages);
		PrintWriter out = null ;
		
		if(flag == false){
			System.out.print("无该年数据!!!");
			response.setContentType("text/html;charset=UTF-8") ;
			out = response.getWriter() ;
			out.print("{\"data\":\"无该年数据!!!\"}"); 
		}else{
			try {
//				System.out.println(pages) ;
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
//		System.out.println("tempData:"+tempData);

//		System.out.println("fields:"+this.getFields());	
//		System.out.println("this.getFields():"+this.getFields());
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
	
	
	//复制往年数据
	public void copy(){
//		System.out.println("ccccccccccccccccccccccccccccccccccc");
		HttpServletResponse response = ServletActionContext.getResponse();
		
		//得到需要复制的年份的数据
		List<T16_Bean> list = t16Ser.getBean() ;
		Date newYear = TimeUtil.changeDateY(this.getSelectYear());
		//System.out.println("year:"+this.getSelectYear());
		//System.out.println(newYear);
		//设置时间
		T16_Bean bean1 = list.get(0);
		bean1.setTime(newYear);
		T16_Bean bean2 = list.get(1);
		bean2.setTime(newYear);
		List<T16_Bean> newlist= new ArrayList<T16_Bean>();
		newlist.add(bean1);newlist.add(bean2);
		//插入
		boolean flag = t16Ser.insert(newlist);
		//System.out.println(flag);
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
			out.print("{\"state\":false,data:\"导入失败!!!\"}") ;
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
		
//		System.out.println("year=" + year) ;
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

//		System.out.println(this.getSelectYear());
		
		UserinfoBean userBean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		List<T16POJO> list =null;
		String sheetName = null;
		
		if("111".equals(userBean.getRoleID())){
			String year = (String)request.getSession().getAttribute("allYear") ;
			list = t16Ser.forExcel(year);
			sheetName = "表1-6办学指导思想（党院办）";
		}else{
			list = t16Ser.forExcel(this.getSelectYear());
			sheetName = this.excelName;
		}

		//List<T16POJO> list = t16Ser.forExcel(this.getSelectYear());
		
	    ByteArrayOutputStream fos = null;
	    
//	
//		if(list==null){
//			PrintWriter out = null ;
//			getResponse().setContentType("text/html; charset=UTF-8") ;
//			out = getResponse().getWriter() ;
//			out.print("后台传入的数据为空!!!") ;
//			System.out.println("后台传入的数据为空");
//		}else{
//			String sheetName = this.getExcelName();
				//String sheetName=this.excelName;	
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
		          // ws.addCell(new Label(2, 2, "备注", wcf));  
		   

		           if(list!=null ){
		        	   if(list.size()!=0){
				        	  
		        		   T16POJO pojo = list.get(0);
			        		   ws.addCell(new Label(0, 3, pojo.getItem1(), wcf1)); 
			        		   ws.addCell(new Label(1, 3, pojo.getContents1(), wcf1)); 
			        		   //ws.addCell(new Label(2, 3, pojo.getNote1(), wcf1)); 
			        		   ws.addCell(new Label(0, 4, pojo.getItem2(), wcf1)); 
			        		   ws.addCell(new Label(1, 4, pojo.getContents2(), wcf1)); 
			        		   //ws.addCell(new Label(2, 4, pojo.getNote2(), wcf1)); 
		        	   	}
			           }
		          wwb.write();
		          wwb.close();

		        } catch (IOException e){
		        } catch (RowsExceededException e){
		        } catch (WriteException e){}
		        
//		}
		return new ByteArrayInputStream(fos.toByteArray());
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


	public T16_Bean getT16Bean() {
		return t16Bean;
	}

	public void setT16Bean(T16_Bean t16Bean) {
		this.t16Bean = t16Bean;
	}

	public void setSeqNum(int seqNum){
		this.seqNum = seqNum ;
	}
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
	
	

}
