package cn.nit.action.table6;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table6.T631_Bean;
import cn.nit.constants.Constants;

import cn.nit.dao.table6.T631_Dao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.service.CheckService;
import cn.nit.service.table6.T631_Service;
import cn.nit.util.DAOUtil;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

/**
 * 待完成！！！！！！！
 * 
 * @author Yuan
 */
public class T631_Action {

	/** T631的Service*/
	private T631_Service T631_service = new T631_Service();
	
	private CheckService check_services = new CheckService();

	/** T631的Bean*/
	T631_Bean T631_bean = new T631_Bean();
	
	
//	private T631_Dao T631_dao = new T631_Dao();
	
	
	/**  哪一年数据  */
	private String selectYear;

	/**  导出的excelName名 */
	private String excelName ;
	
	/**  待审核数据的要删除的序列集  */
	private String ids; //删除的id
	
	/**  审核状态显示判别标志  */
	private int checkNum ;

	
	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;


	/** 逐条插入数据 */
	public void loadInfo() {
		List<T631_Bean> list=T631_service.getYearInfo(this.getSelectYear());
		JSON json = JSONSerializer.toJSON(list) ;
		PrintWriter out = null ;

		try {
			//设置输出内容的格式为json
			response.setContentType("application/json; charset=UTF-8") ;
			out = response.getWriter() ;
			//设置数据的内容的编码格式
			String outPrint = URLDecoder.decode(json.toString(), "UTF-8") ;
			out.print(outPrint) ;
			out.flush() ;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	
	//插入一个新的信息
	public void insert(){
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		HttpServletResponse response = ServletActionContext.getResponse();
		
		//插入时间
		String year = this.getSelectYear();
		boolean flag = T631_service.insert(T631_bean, year);
		PrintWriter out = null ;
		
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
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
	
	/**  编辑数据  */
	public void edit(){
		int flag = T631_service.update(T631_bean,this.getSelectYear()) ;
		if(flag == 2){
			int year = Integer.parseInt(this.getSelectYear());
			check_services.delete("T631", year);
		}
		
		PrintWriter out = null ;
	
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(flag == 2){
				//out.print("{\"state\":true,data:\"修改成功!!!\"}") ;
				out.print("{\"state\":true,data:\"修改成功!!!\",tag:2}") ;
			}else if(flag == 1){
				out.print("{\"state\":true,data:\"修改成功!!!\"}") ;
			}else{
				out.print("{\"state\":true,data:\"修改失败!!!\"}") ;
			}			
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"系统错误，请联系管理员!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
		out.flush() ;
	}
	
	
	/**  根据数据的id删除数据  */
	public void deleteByIds(){
		System.out.println("ids=" + this.getIds()) ;
		boolean flag = T631_service.deleteByIds(ids, this.getSelectYear()) ;
		//删除审核不通过信息
		int year = Integer.parseInt(this.getSelectYear());
		check_services.delete("T631", year);
		PrintWriter out = null ;
		
		try{			
			response.setContentType("application/json; charset=UTF-8") ;
			out = response.getWriter() ;			
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
	
	
	/**  修改某条数据的审核状态  */
	public void updateCheck(){
		
		HttpServletResponse response = ServletActionContext.getResponse();
		String TeaUnit = "全校合计";
		boolean flag = T631_service.updateCheck(this.getSelectYear(), TeaUnit, this.getCheckNum());
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

	public InputStream getInputStream() {

		InputStream inputStream = null ;
		ByteArrayOutputStream fos = new ByteArrayOutputStream();
		
		try {
/*			response.reset();
			response.addHeader("Content-Disposition", "attachment;fileName="
                      + java.net.URLEncoder.encode(excelName,"UTF-8"));*/
			
			List<T631_Bean> list = T631_service.totalList(this.getSelectYear(),Constants.PASS_CHECK);
			if(list==null){
				if(list.size()==0){
					PrintWriter out = null ;
					response.setContentType("text/html;charset=utf-8") ;
					out = response.getWriter() ;
					out.print("后台传入的数据为空") ;
					System.out.println("后台传入的数据为空");
					return null;
				}
			}
			if(list!=null){

				
				String sheetName = this.excelName;
				
				List<String> columns = new ArrayList<String>();
				
				columns.add("序号");columns.add("教学单位");columns.add("单位号");
				columns.add("专业名称");columns.add("专业代码");columns.add("应届毕业生数");
				columns.add("应届生中未按时毕业数");columns.add("授予学位");
//				columns.add("时间");
//				columns.add("备注");
				

				Map<String,Integer> maplist = new HashMap<String,Integer>();
				
				maplist.put("seqNumber", 0);maplist.put("teaUnit", 1);maplist.put("unitId", 2);
				maplist.put("majorName", 3);maplist.put("majorId", 4);maplist.put("thisYearGraduNum", 5);
				maplist.put("thisYearNotGraduNum", 6);maplist.put("awardDegreeNum", 7);
//				maplist.put("time", 8);
//				maplist.put("note", 9);
				
				WritableWorkbook wwb;
				try{
					
					 fos = new ByteArrayOutputStream();
			            wwb = Workbook.createWorkbook(fos);
			            WritableSheet ws = wwb.createSheet(sheetName, 0);        // 创建一个工作表

			            //    设置表头的文字格式
			            
			            WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.BOLD,false,
			                    UnderlineStyle.NO_UNDERLINE,Colour.BLACK);    
			            WritableCellFormat wcf = new WritableCellFormat(wf);
			            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			            wcf.setAlignment(Alignment.CENTRE);
			            wcf.setBorder(Border.ALL, BorderLineStyle.THIN,
				        		     jxl.format.Colour.BLACK);
			            
			            //    设置内容单无格的文字格式
			            WritableFont wf1 = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false,
				                    UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
			            WritableCellFormat wcf1 = new WritableCellFormat(wf1);       
			            wcf1.setVerticalAlignment(VerticalAlignment.CENTRE);
			            wcf1.setAlignment(Alignment.CENTRE);
			            wcf1.setBorder(Border.ALL, BorderLineStyle.THIN,
				        		     jxl.format.Colour.BLACK);
			            ws.setRowView(1, 500);
						//第一行存表名
						ws.addCell(new Label(0, 0, sheetName, wcf)); 
						ws.mergeCells(0, 0, 1, 0);
						
						//写表头
						if(columns != null && columns.size() > 0){
							for(int i =0;i<columns.size();i++){
								ws.addCell(new Label(i, 2, columns.get(i), wcf));
							}
						}
						
						//向表中写数据
						int k=3;//从第4行开始写数据,第3行为全校合计数
						for(int j=0;j<list.size();j++){
							T631_Bean bean1 =  list.get(j);
							if(j==0){
								ws.addCell(new Label(0,3, bean1.getTeaUnit(), wcf1));
								ws.mergeCells(0, 3, 4, 3);
								ws.addCell(new Label(5, 3, bean1.getThisYearGraduNum()+"", wcf1));
								ws.addCell(new Label(6, 3, bean1.getThisYearNotGraduNum()+"", wcf1));
								ws.addCell(new Label(7, 3, bean1.getAwardDegreeNum()+"", wcf1));
								
							}else{
								ws.addCell(new Label(0, k,j+"", wcf1));
								ws.addCell(new Label(1, k, bean1.getTeaUnit(), wcf1));
								ws.addCell(new Label(2, k, bean1.getUnitId(), wcf1));
								ws.addCell(new Label(3, k, bean1.getMajorName(), wcf1));
								ws.addCell(new Label(4, k, bean1.getMajorId(), wcf1));
								ws.addCell(new Label(5, k, bean1.getThisYearGraduNum()+"", wcf1));
								ws.addCell(new Label(6, k, bean1.getThisYearNotGraduNum()+"", wcf1));
								ws.addCell(new Label(7, k, bean1.getAwardDegreeNum()+"", wcf1));
							}
							k++;
						}
						    wwb.write();
				            wwb.close();

				} catch (IOException e){
		        } catch (RowsExceededException e){
		        } catch (WriteException e){}
				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}

		inputStream = new ByteArrayInputStream(fos.toByteArray());
		return inputStream ;
	}
						

	public String execute() throws Exception{
		request.setCharacterEncoding("UTF-8") ;
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





	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}



	public T631_Service getT631_service() {
		return T631_service;
	}

	public void setT631_service(T631_Service t631Service) {
		T631_service = t631Service;
	}

	public T631_Bean getT631_bean() {
		return T631_bean;
	}

	public void setT631_bean(T631_Bean t631Bean) {
		T631_bean = t631Bean;
	}



	public CheckService getCheck_services() {
		return check_services;
	}

	public void setCheck_services(CheckService checkServices) {
		check_services = checkServices;
	}

//	public T631_Dao getT631_dao() {
//		return T631_dao;
//	}
//
//	public void setT631_dao(T631_Dao t631Dao) {
//		T631_dao = t631Dao;
//	}

	public String getSelectYear() {
		return selectYear;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}

	public int getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
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

	public static void main(String args[]) {
		String match = "[\\d]+";
		System.out.println("23gfhf4".matches(match));
	}




}
