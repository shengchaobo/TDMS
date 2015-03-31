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

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.table4.T441_Bean;
import cn.nit.bean.table6.T655_Bean;
import cn.nit.bean.table6.T656_Bean;
import cn.nit.constants.Constants;

import cn.nit.dao.table6.T656_Dao;
import cn.nit.dbconnection.DBConnection;
import cn.nit.excel.imports.table6.T656_Excel;

import cn.nit.service.CheckService;
import cn.nit.service.table6.T656_Service;
import cn.nit.util.DAOUtil;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

/**
 * 待完成！！！！！！！
 * 
 * @author Yuan
 */
public class T656_Action {

	/** 表的Service类 */
	private T656_Service T656_service = new T656_Service();
	/** 表的Excel类 */
	private T656_Excel T656_Excel = new T656_Excel();
	
	private CheckService check_services = new CheckService();

	/** 表的Bean实体类 */
	T656_Bean T656_bean = new T656_Bean();
	
//	T656_Dao T656_dao = new T656_Dao();


	private String excelName; //excel导出名字
	
	/**  审核状态显示判别标志  */
	private int checkNum ;
	
	
	/**  哪一年数据  */
	private String selectYear;
	
	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;

	

	/** 为界面加载数据 */
	public void loadInfo() throws Exception {
		System.out.println("++++++++++++++++++++++");

		HttpServletResponse response = ServletActionContext.getResponse() ;		
		
		List<T656_Bean> list = T656_service.getYearInfo(this.getSelectYear());
		
		//System.out.println(this.getSelectYear());
		//System.out.println(list.size());
		JSON json = JSONSerializer.toJSON(list) ;
		PrintWriter out = null ;
		//System.out.println(json.toString());
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


	/** 编辑数据 */
	public void edit() {
		System.out.println("--------------------------");
		int tag = 1;
		boolean flag0 = false;
		boolean flag1 = false;
		if(T656_bean.getTeaUnit().equals("全校合计")){
			//System.out.println("修改合计");
			T656_Bean sumBean = T656_service.findBySeqNum(T656_bean.getSeqNumber());
			//首次编辑待审核
			if(sumBean.getCheckState() == 0){
				//sumBean.setCheckState(Constants.WAIT_CHECK);
				T656_bean.setCheckState(Constants.WAIT_CHECK);
			}
			//审核不通过再编辑待审核
			else if(sumBean.getCheckState() == Constants.NOPASS_CHECK){
				T656_bean.setCheckState(Constants.WAIT_CHECK);
				//sumBean.setCheckState(Constants.WAIT_CHECK);
				int year = Integer.parseInt(this.getSelectYear());
				check_services.delete("T656", year);
				tag = 2;
			}
			T656_bean.setTime(sumBean.getTime());
//			System.out.println(T656_bean.getCheckState());
			flag0 = true;
			flag1 = T656_service.update(T656_bean) ;
		}
		else{
//			System.out.println("未修改合计");
			//被修改的那条数据
			T656_Bean oldBean = T656_service.findBySeqNum(T656_bean.getSeqNumber());
			String teaUnitName = "全校合计";
			//找到合计数据
			T656_Bean sumBean = T656_service.findSumBean(teaUnitName,this.getSelectYear());
			//首次编辑待审核
			if(sumBean.getCheckState() == 0){
				sumBean.setCheckState(Constants.WAIT_CHECK);
			}
			//审核不通过再编辑待审核
			else if(sumBean.getCheckState() == Constants.NOPASS_CHECK){
				sumBean.setCheckState(Constants.WAIT_CHECK);
				int year = Integer.parseInt(this.getSelectYear());
				check_services.delete("T656", year);
				tag = 2;
			}
			
			T656_bean.setTime(oldBean.getTime());
			flag0 = T656_service.update(T656_bean) ;		
			flag1 = T656_service.update(sumBean) ;
		}
		
		PrintWriter out = null ;
	
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(tag == 1){
				if(flag0&&flag1){
					out.print("{\"state\":true,data:\"编辑成功!!!\"}") ;
				}else{
					out.print("{\"state\":true,data:\"编辑成功!!!\"}") ;
				}				
			}
			else if(tag == 2){
				if(flag0&&flag1){
					out.print("{\"state\":true,data:\"修改成功!!!\",tag:2}") ;
				}else{
					out.print("{\"state\":true,data:\"修改成功!!!\"}") ;
				}		
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
		boolean flag = T656_service.updateCheck(this.getSelectYear(), UnitName, this.getCheckNum());
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
		UserinfoBean userBean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		String sheetName = null;
		List<T656_Bean> list = null;
		
		if("111".equals(userBean.getRoleID())){
			String year = (String)request.getSession().getAttribute("allYear") ;
			list = T656_service.totalList(year,Constants.PASS_CHECK);
			sheetName = "表6-5-6学习成果-全国计算机等级考试（信息工程学院）";
		}else{					
			list = T656_service.totalList(this.getSelectYear(),Constants.PASS_CHECK);						
			sheetName = this.excelName;
		}

		InputStream inputStream = null ;
		ByteArrayOutputStream fos = new ByteArrayOutputStream();
		try {

			List<String> columns = new ArrayList<String>();
			columns.add("序号");columns.add("教学单位");columns.add("单位号");columns.add("全国高校计算机等级考试累计通过率（%）");
			//columns.add("备注");
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);maplist.put("teaUnit", 1);maplist.put("unitId", 2);maplist.put("nationNCREPassRate", 3);
			//maplist.put("note", 4);
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
		                	
		                	
		                		ws.addCell(new Label(i, 2, columns.get(i), wcf));
//		                		ws.mergeCells(i, 2, i, 2);
		                	                			                	
		                }  
		                
		            }
		            //判断表中是否有数据  
		            if (list != null && list.size() > 0) {  
		                    //循环写入表中数据  
		                	BeanWrapperImpl wrapper = new BeanWrapperImpl() ;
		                	int i=0;  
		                	for(Object obj : list){  
		                		wrapper.setWrappedInstance(obj) ;  
		                        //循环输出map中的子集：既列值                         
		                        for(String column:maplist.keySet()){
		                        	
		                        	if(column.equals("SeqNum")){
		                        		ws.addCell(new Label(0,i+3,""+(i-1),wcf1)); 
		                        		continue;
		                        	}
		                        	                        	
		        					String type = wrapper.getPropertyType(column).toString() ;
//		        					System.out.println(type +"-test：" + column);

		        					//判断插入数据的类型，并赋�?
		        					if(type.endsWith("String")){
		        						if(wrapper.getPropertyValue(column) == null){
		        							continue;
		        						}
		        						if(((String) wrapper.getPropertyValue(column)).equals("全校合计")){
		        							ws.addCell(new Label(maplist.get(column).intValue()-1,i+3,(String) wrapper.getPropertyValue(column),wcf1));
		        							ws.mergeCells(0,i+3,2,i+3);
		        						}else{
		        							ws.addCell(new Label(maplist.get(column).intValue(),i+3,(String) wrapper.getPropertyValue(column),wcf1));
		        						}
		        						
		        					}else if(type.endsWith("String")){
		        						ws.addCell(new Label(maplist.get(column).intValue(),i+3,(String) wrapper.getPropertyValue(column).toString(),wcf1));
		        					}
		        					else if(type.endsWith("double")||type.endsWith("Double")){
		        						ws.addCell(new Label(maplist.get(column).intValue(),i+3,(String) wrapper.getPropertyValue(column).toString(),wcf1));
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

	public String execute() throws Exception{
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

	public T656_Service getT656_service() {
		return T656_service;
	}

	public void setT656_service(T656_Service t631Service) {
		T656_service = t631Service;
	}

	public T656_Bean getT656_bean() {
		return T656_bean;
	}

	public void setT656_bean(T656_Bean T656Bean) {
		T656_bean = T656Bean;
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

	public int getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}

	public String getSelectYear() {
		return selectYear;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}

	public static void main(String args[]) {
		String match = "[\\d]+";
		System.out.println("23gfhf4".matches(match));
	}




}
