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
import org.springframework.beans.BeanWrapperImpl;

import cn.nit.bean.table6.T611_Bean;
import cn.nit.bean.table6.T612_Bean;
import cn.nit.bean.table6.T613_Bean;
import cn.nit.bean.table6.T614_Bean;
import cn.nit.bean.table6.T615_Bean;
import cn.nit.bean.table6.T617_Bean;
import cn.nit.bean.table6.T621_Bean;
import cn.nit.bean.table6.T631_Bean;
import cn.nit.bean.table6.T632_Bean;
import cn.nit.dao.table6.T611_Dao;
import cn.nit.dao.table6.T612_Dao;
import cn.nit.dao.table6.T613_Dao;
import cn.nit.dao.table6.T614_Dao;
import cn.nit.dao.table6.T615_Dao;
import cn.nit.dao.table6.T617_Dao;
import cn.nit.dao.table6.T632_Dao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.service.CheckService;
import cn.nit.service.table6.T611_Service;
import cn.nit.service.table6.T612_Service;
import cn.nit.service.table6.T613_Service;
import cn.nit.service.table6.T614_Service;
import cn.nit.service.table6.T615_Service;
import cn.nit.service.table6.T617_Service;
import cn.nit.service.table6.T632_Service;
import cn.nit.util.DAOUtil;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

/**
 * 待完成！！！！！！！
 * 
 * @author Yuan
 */
public class T617_Action {

	/** 表的Service类 */
	private T617_Service T617_service = new T617_Service();
	
	private CheckService check_services = new CheckService();

	/** 表的Bean实体类 */
	T617_Bean T617_bean = new T617_Bean();
	
//	T617_Dao T617_dao = new T617_Dao();

	/**  哪一年数据  */
	private String selectYear;

	/**  导出的excelName名 */
	private String excelName ;
	
	/**  待审核数据的要删除的序列集  */
	private String ids; //删除的id
	
	/**  审核状态显示判别标志  */
	private int checkNum ;
	

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	/** 逐条插入数据 */
	public void loadInfo() {
		System.out
				.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	
		List<T617_Bean> list=T617_service.getYearInfo(this.getSelectYear());
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
				
		int juniorStuSumNum = T617_bean.getJuniorOneStuNum()+
								T617_bean.getJuniorTwoStuNum()+T617_bean.getJuniorThreeStuNum();
		T617_bean.setJuniorStuSumNum(juniorStuSumNum);
		boolean flag = T617_service.insert(T617_bean, year);
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
		
		int juniorStuSumNum = T617_bean.getJuniorOneStuNum()+
							  T617_bean.getJuniorTwoStuNum()+T617_bean.getJuniorThreeStuNum();
		T617_bean.setJuniorStuSumNum(juniorStuSumNum);
		int flag = T617_service.update(T617_bean,this.getSelectYear()) ;
		if(flag == 2){
			int year = Integer.parseInt(this.getSelectYear());
			check_services.delete("T617", year);
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
//		System.out.println("ids=" + this.getIds()) ;
		boolean flag = T617_service.deleteByIds(ids, this.getSelectYear()) ;
		//删除审核不通过信息
		int year = Integer.parseInt(this.getSelectYear());
		check_services.delete("T617", year);
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
		String UnitName = "全校合计";
		boolean flag = T617_service.updateCheck(this.getSelectYear(), UnitName, this.getCheckNum());
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

	public InputStream getInputStream(){
		
		InputStream inputStream = null ;
		ByteArrayOutputStream fos = new ByteArrayOutputStream();
		try {
			List<T617_Bean> list=T617_service.totalList(this.getSelectYear());
			if(list.size()==0){
				PrintWriter out = null ;
				response.setContentType("text/html;charset=utf-8") ;
				out = response.getWriter() ;
				out.print("后台传入的数据为空") ;
				System.out.println("后台传入的数据为空");
				return null;
			}
			String sheetName = this.excelName;
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");columns.add("教学单位");columns.add("单位号");columns.add("专业名称");
			columns.add("专业代码");columns.add("专业方向名称");columns.add("在校生数(人)");columns.add("总人数");
			columns.add("一年级");columns.add("二年级");columns.add("三年级");
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);maplist.put("teaUnit", 1);maplist.put("unitId", 2);maplist.put("majorName", 3);
			maplist.put("majorId", 4);maplist.put("majorFieldName", 5);maplist.put("juniorStuSumNum", 6);maplist.put("juniorOneStuNum", 7);
			maplist.put("juniorTwoStuNum", 8);maplist.put("juniorThreeStuNum", 9);
			WritableWorkbook wwb;
		    try {    
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
					
					//取出第一行总计信息
					T617_Bean bean = list.get(0);					
					ws.addCell(new Label(0, 4, "全校合计", wcf));  
					ws.mergeCells(0, 4, 5, 4);
					ws.addCell(new Label(6, 4, bean.getJuniorStuSumNum().toString(), wcf1));  
					ws.addCell(new Label(7, 4, bean.getJuniorOneStuNum().toString(), wcf1));  
					ws.addCell(new Label(8, 4, bean.getJuniorTwoStuNum().toString(), wcf1));  
					ws.addCell(new Label(9, 4, bean.getJuniorThreeStuNum().toString(), wcf1));  
					
		            //判断一下表头数组是否有数据    
		            if (columns != null && columns.size() > 0) {  		 
		  
		                    //循环写入表头  
		                    for (int i = 0; i < columns.size(); i++) {  
		      
		                        /* 
		                         * 添加单元格(Cell)内容addCell() 
		                         * 添加Label对象Label() 
		                         * 数据的类型有很多种、在这里你需要什么类型就导入什么类型 
		                         * 如：jxl.write.DateTime 、jxl.write.Number、jxl.write.Label 
		                         * Label(i, 0, columns[i], wcf) 
		                         * 其中i为列、0为行、columns[i]为数据、wcf为样式 
		                         * 合起来就是说将columns[i]添加到第一行(行、列下标都是从0开始)第i列、样式为什么"色"内容居中 
		                         */ 
		                    	if(i<6){
		                    		 ws.addCell(new Label(i, 2, columns.get(i), wcf)); 
		                    		 ws.mergeCells(i, 2, i, 3);
		                    	}
		                    	if(i==6){
		                    		 ws.addCell(new Label(i, 2, columns.get(i), wcf)); 
		                    		 ws.mergeCells(i, 2, i+3, 2);
		                    	}
		                    	if(i>6){
		                    		ws.addCell(new Label(i-1, 3, columns.get(i), wcf)); 
		                    	}
		                    } 	                			                	                
		            }
		            
		            //判断表中是否有数据  
		            if (list != null && list.size() > 0) {  
		                    //循环写入表中数据  
		                	BeanWrapperImpl wrapper = new BeanWrapperImpl() ;
		                	int i=0;  
		                	for(Object obj : list){
		                		if(i == 0) {
		                			i++;
		                			continue;
		                		}
		                		wrapper.setWrappedInstance(obj) ;  
		                        //循环输出map中的子集：既列值                         
		                        for(String column:maplist.keySet()){
		                        	
		                        	if(column.equals("SeqNum")){
		                        		ws.addCell(new Label(0,i+4,""+i,wcf1)); 
		                        		continue;
		                        	}
		                        	                        	
		        					String type = wrapper.getPropertyType(column).toString() ;
//		        					System.out.println(type +"-test：" + column);

		        					//判断插入数据的类型，并赋�?
		        					if(type.endsWith("String")){
		        						ws.addCell(new Label(maplist.get(column).intValue(),i+4,(String) wrapper.getPropertyValue(column),wcf1));		        						
		        					}else if(type.endsWith("int")||type.endsWith("Integer")){
		        						ws.addCell(new Label(maplist.get(column).intValue(),i+4,(String) wrapper.getPropertyValue(column).toString(),wcf1));
		        					}else if(type.endsWith("double")||type.endsWith("Double")){
		        						ws.addCell(new Label(maplist.get(column).intValue(),i+4,(String) wrapper.getPropertyValue(column).toString(),wcf1));
		        					}else{
		        						throw new Exception("自行添加对应类型" + type) ;
		        					}                       	                         	
		                    }
		                    i++;
		                }
		            }else{
		            	System.out.println("后台传入的数据为空");
		            }

		            wwb.write();
		            wwb.close();

		        } catch (IOException e){
		        } catch (RowsExceededException e){
		        } catch (WriteException e){}
		        
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null ;
		}
		inputStream = new ByteArrayInputStream(fos.toByteArray());
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

	

	public T617_Service getT617_service() {
		return T617_service;
	}

	public void setT617_service(T617_Service t631Service) {
		T617_service = t631Service;
	}

	public T617_Bean getT617_bean() {
		return T617_bean;
	}

	public void setT617_bean(T617_Bean T617Bean) {
		T617_bean = T617Bean;
	}


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
