package cn.nit.action.table6;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanWrapperImpl;

import cn.nit.bean.table6.T616_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.table6.T616_Dao;
import cn.nit.service.CheckService;
import cn.nit.service.table6.T616_Service;
import cn.nit.util.JsonUtil;

public class T616_Action {
	
	private T616_Service T616_Service = new T616_Service();
	
	private CheckService check_services = new CheckService();
	
	private T616_Bean T616_bean = new T616_Bean();
	
	private T616_Dao T616_Dao = new T616_Dao();
	
	/**  哪一年数据  */
	private String selectYear;

	/**  导出的excelName名 */
	private String excelName ;

	/**  审核状态显示判别标志  */
	private int checkNum ;
	
	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	//查询出所有
	
	public void loadInfo() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse() ;		
		
		List<T616_Bean> list = T616_Service.getYearInfo(this.getSelectYear());
		
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
	
	
	/**  编辑数据  */
	public void edit(){

		
		int tag = 1;
		T616_Bean oldBean = T616_Service.findBySeqNum(T616_bean.getSeqNumber());
		String stuType = "总计";
		T616_Bean sumBean = T616_Service.findSumBean(stuType,this.getSelectYear());
		//首次编辑待审核
		if(sumBean.getCheckState() == 0){
			sumBean.setCheckState(Constants.WAIT_CHECK);
		}
		//审核不通过再编辑待审核  变审核状态为待审核  删除审核不通过原因
		else if(sumBean.getCheckState() == Constants.NOPASS_CHECK){
			sumBean.setCheckState(Constants.WAIT_CHECK);
			int year = Integer.parseInt(this.getSelectYear());
			check_services.delete("T616", year);
			tag = 2;
		}
		sumBean.setSumGraNum(sumBean.getSumGraNum()+(T616_bean.getSumGraNum()-oldBean.getSumGraNum()));
		sumBean.setGraOutNum(sumBean.getGraOutNum()+(T616_bean.getGraOutNum()-oldBean.getGraOutNum()));
		sumBean.setGraHongNum(sumBean.getGraHongNum()+(T616_bean.getGraHongNum()-oldBean.getGraHongNum()));
		sumBean.setGraAoNum(sumBean.getGraAoNum()+(T616_bean.getGraAoNum()-oldBean.getGraAoNum()));
		sumBean.setGraTaiNum(sumBean.getGraTaiNum()+(T616_bean.getGraTaiNum()-oldBean.getGraTaiNum()));
		sumBean.setSumDegreeNum(sumBean.getSumDegreeNum()+(T616_bean.getSumDegreeNum()-oldBean.getSumDegreeNum()));
		sumBean.setDegreeOutNum(sumBean.getDegreeOutNum()+(T616_bean.getDegreeOutNum()-oldBean.getDegreeOutNum()));
		sumBean.setDegreeHongNum(sumBean.getDegreeHongNum()+(T616_bean.getDegreeHongNum()-oldBean.getDegreeHongNum()));
		sumBean.setDegreeAoNum(sumBean.getDegreeAoNum()+(T616_bean.getDegreeAoNum()-oldBean.getDegreeAoNum()));
		sumBean.setDegreeTaiNum(sumBean.getDegreeTaiNum()+(T616_bean.getDegreeTaiNum()-oldBean.getDegreeTaiNum()));
		sumBean.setSumAdmisNum(sumBean.getSumAdmisNum()+(T616_bean.getSumAdmisNum()-oldBean.getSumAdmisNum()));
		sumBean.setAdmisOutNum(sumBean.getAdmisOutNum()+(T616_bean.getAdmisOutNum()-oldBean.getAdmisOutNum()));
		sumBean.setAdmisHongNum(sumBean.getAdmisHongNum()+(T616_bean.getAdmisHongNum()-oldBean.getAdmisHongNum()));
		sumBean.setAdmisAoNum(sumBean.getAdmisAoNum()+(T616_bean.getAdmisAoNum()-oldBean.getAdmisAoNum()));
		sumBean.setAdmisTaiNum(sumBean.getAdmisTaiNum()+(T616_bean.getAdmisTaiNum()-oldBean.getAdmisTaiNum()));
		sumBean.setSumInSchNum(sumBean.getSumInSchNum()+(T616_bean.getSumInSchNum()-oldBean.getSumInSchNum()));
		sumBean.setInSchOutNum(sumBean.getInSchOutNum()+(T616_bean.getInSchOutNum()-oldBean.getInSchOutNum()));
		sumBean.setInSchHongNum(sumBean.getInSchHongNum()+(T616_bean.getInSchHongNum()-oldBean.getInSchHongNum()));
		sumBean.setInSchAoNum(sumBean.getInSchAoNum()+(T616_bean.getInSchAoNum()-oldBean.getInSchAoNum()));
		sumBean.setInSchTaiNum(sumBean.getInSchTaiNum()+(T616_bean.getInSchTaiNum()-oldBean.getInSchTaiNum()));
		
		//更新编辑的数据和总计数据
		T616_bean.setTime(oldBean.getTime());
		boolean flag0 = T616_Service.update(T616_bean) ;		
		boolean flag1 = T616_Service.update(sumBean) ;
		
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
	
	
//	/**  编辑数据  */
//	public void edit(){
//
//		T616_Bean oldBean = T616_Service.findBySeqNum(T616_bean.getSeqNumber());
//		String stuTypeName = "总计";
//		T616_Bean sumBean = T616_Service.findSumBean(stuTypeName,this.getSelectYear());
//		
//		sumBean.setSumGraNum(sumBean.getSumGraNum()+(T616_bean.getSumGraNum()-oldBean.getSumGraNum()));
//		sumBean.setGraOutNum(sumBean.getGraOutNum()+(T616_bean.getGraOutNum()-oldBean.getGraOutNum()));
//		sumBean.setGraHongNum(sumBean.getGraHongNum()+(T616_bean.getGraHongNum()-oldBean.getGraHongNum()));
//		sumBean.setGraAoNum(sumBean.getGraAoNum()+(T616_bean.getGraAoNum()-oldBean.getGraAoNum()));
//		sumBean.setGraTaiNum(sumBean.getGraTaiNum()+(T616_bean.getGraTaiNum()-oldBean.getGraTaiNum()));
//		sumBean.setSumDegreeNum(sumBean.getSumDegreeNum()+(T616_bean.getSumDegreeNum()-oldBean.getSumDegreeNum()));
//		sumBean.setDegreeOutNum(sumBean.getDegreeOutNum()+(T616_bean.getDegreeOutNum()-oldBean.getDegreeOutNum()));
//		sumBean.setDegreeHongNum(sumBean.getDegreeHongNum()+(T616_bean.getDegreeHongNum()-oldBean.getDegreeHongNum()));
//		sumBean.setDegreeAoNum(sumBean.getDegreeAoNum()+(T616_bean.getDegreeAoNum()-oldBean.getDegreeAoNum()));
//		sumBean.setDegreeTaiNum(sumBean.getDegreeTaiNum()+(T616_bean.getDegreeTaiNum()-oldBean.getDegreeTaiNum()));
//		sumBean.setSumAdmisNum(sumBean.getSumAdmisNum()+(T616_bean.getSumAdmisNum()-oldBean.getSumAdmisNum()));
//		sumBean.setAdmisOutNum(sumBean.getAdmisOutNum()+(T616_bean.getAdmisOutNum()-oldBean.getAdmisOutNum()));
//		sumBean.setAdmisHongNum(sumBean.getAdmisHongNum()+(T616_bean.getAdmisHongNum()-oldBean.getAdmisHongNum()));
//		sumBean.setAdmisAoNum(sumBean.getAdmisAoNum()+(T616_bean.getAdmisAoNum()-oldBean.getAdmisAoNum()));
//		sumBean.setAdmisTaiNum(sumBean.getAdmisTaiNum()+(T616_bean.getAdmisTaiNum()-oldBean.getAdmisTaiNum()));
//		sumBean.setSumInSchNum(sumBean.getSumInSchNum()+(T616_bean.getSumInSchNum()-oldBean.getSumInSchNum()));
//		sumBean.setInSchOutNum(sumBean.getInSchOutNum()+(T616_bean.getInSchOutNum()-oldBean.getInSchOutNum()));
//		sumBean.setInSchHongNum(sumBean.getInSchHongNum()+(T616_bean.getInSchHongNum()-oldBean.getInSchHongNum()));
//		sumBean.setInSchAoNum(sumBean.getInSchAoNum()+(T616_bean.getInSchAoNum()-oldBean.getInSchAoNum()));
//		sumBean.setInSchTaiNum(sumBean.getInSchTaiNum()+(T616_bean.getInSchTaiNum()-oldBean.getInSchTaiNum()));
//		
//		T616_bean.setTime(oldBean.getTime());
//		boolean flag = T616_Service.update(T616_bean) ;		
//		boolean flag0 = T616_Service.update(sumBean) ;
//		
//		PrintWriter out = null ;
//	
//		try{
//			response.setContentType("text/html; charset=UTF-8") ;
//			out = response.getWriter() ;
//			if(flag&&flag0){
//				out.print("{\"state\":true,data:\"修改成功!!!\"}") ;
//			}else{
//				out.print("{\"state\":true,data:\"修改失败!!!\"}") ;
//			}
//			out.flush() ;
//		}catch(Exception e){
//			e.printStackTrace() ;
//			out.print("{\"state\":false,data:\"系统错误，请联系管理员!!!\"}") ;
//		}finally{
//			if(out != null){
//				out.close() ;
//			}
//		}
//	}
	
	
	/**  修改某条数据的审核状态  */
	public void updateCheck(){
		
		HttpServletResponse response = ServletActionContext.getResponse();
		String UnitName = "总计";
		boolean flag = T616_Service.updateCheck(this.getSelectYear(), UnitName, this.getCheckNum());
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
	
	
	public T616_Service getT616_Service() {
		return T616_Service;
	}


	public void setT616_Service(T616_Service t616Service) {
		T616_Service = t616Service;
	}


	public CheckService getCheck_services() {
		return check_services;
	}


	public void setCheck_services(CheckService checkServices) {
		check_services = checkServices;
	}


	public T616_Dao getT616_Dao() {
		return T616_Dao;
	}


	public void setT616_Dao(T616_Dao t616Dao) {
		T616_Dao = t616Dao;
	}


	public int getCheckNum() {
		return checkNum;
	}


	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}


	public HttpServletResponse getResponse() {
		return response;
	}


	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}


	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public InputStream getInputStream(){
		System.out.println(this.getSelectYear());
		InputStream inputStream = null ;
		ByteArrayOutputStream fos = new ByteArrayOutputStream();
		try {
			List<T616_Bean> list=T616_Dao.totalList(this.getSelectYear());
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
			columns.add("1.毕（结）业生数（人）");columns.add("2.授予学位数（人）");columns.add("3.招生数（人）");columns.add("4.在校生数（人）");
			columns.add("小计");columns.add("国外");columns.add("香港");columns.add("澳门");columns.add("台湾");
			columns.add("小计");columns.add("国外");columns.add("香港");columns.add("澳门");columns.add("台湾");
			columns.add("小计");columns.add("国外");columns.add("香港");columns.add("澳门");columns.add("台湾");
			columns.add("小计");columns.add("国外");columns.add("香港");columns.add("澳门");columns.add("台湾");
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("StuType", 0);maplist.put("SumGraNum", 1);maplist.put("GraOutNum", 2);maplist.put("GraOutNum", 3);
			maplist.put("GraAoNum", 4);maplist.put("GraTaiNum", 5);maplist.put("SumDegreeNum", 6);
			maplist.put("DegreeOutNum", 7);
			maplist.put("DegreeHongNum", 8);maplist.put("DegreeAoNum", 9);maplist.put("DegreeTaiNum",10);maplist.put("SumAdmisNum", 11);
			maplist.put("AdmisHongNum", 12);maplist.put("AdmisAoNum", 13);maplist.put("AdmisOutNum", 14);
			maplist.put("AdmisTaiNum", 15);
			maplist.put("SumInSchNum", 16);maplist.put("InSchHongNum", 17);maplist.put("InSchAoNum", 18);
			maplist.put("InSchTaiNum", 19);	maplist.put("InSchOutNum", 20);
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
					ws.addCell(new Label(0,2,"",wcf));
					ws.mergeCells(0, 0, 1, 0);
					ws.mergeCells(0, 2, 0, 3);
				
					
		            //判断一下表头数组是否有数据    
		            if (columns != null && columns.size() > 0) {  
		            	ws.addCell(new Label(1, 2, columns.get(0), wcf));
		            	ws.mergeCells(1, 2, 5,2);
		            	ws.addCell(new Label(6, 2, columns.get(1), wcf));
		            	ws.mergeCells(6, 2, 10,2);
		            	ws.addCell(new Label(11, 2, columns.get(2), wcf));
		            	ws.mergeCells(11, 2, 15,2);
		            	ws.addCell(new Label(16, 2, columns.get(3), wcf));
		            	ws.mergeCells(16, 2, 20,2);
		            	
		            	int k=1;
		            	for(int i=4;i <columns.size(); i++){
		            		 int j = k%5;
		            		 switch (j){
		            		  case 1: ws.addCell(new Label(k, 3, columns.get(i), wcf));break;
		            		  case 2: ws.addCell(new Label(k, 3, columns.get(i), wcf));break;
		            		  case 3: ws.addCell(new Label(k, 3, columns.get(i), wcf));break;
		            		  case 4: ws.addCell(new Label(k, 3, columns.get(i), wcf));break;
		            		  case 0: ws.addCell(new Label(k, 3, columns.get(i), wcf));break;
		            		  
		            		 }
		            		 k++;
		            	}
		            	
		            	
//		                //循环写入表头  
//		                for (int i = 0; i < columns.size(); i++) {  
//		  
//		                    /* 
//		                     * 添加单元格(Cell)内容addCell() 
//		                     * 添加Label对象Label() 
//		                     * 数据的类型有很多种、在这里你需要什么类型就导入什么类型 
//		                     * 如：jxl.write.DateTime 、jxl.write.Number、jxl.write.Label 
//		                     * Label(i, 0, columns[i], wcf) 
//		                     * 其中i为列、0为行、columns[i]为数据、wcf为样式 
//		                     * 合起来就是说将columns[i]添加到第一行(行、列下标都是从0开始)第i列、样式为什么"色"内容居中 
//		                     */ 
//		                		                			                	
//		                }  
		                
		            }
		            
		            //判断表中是否有数据  
		            int j=4;
		            if (list != null && list.size() > 0) {  
		            	for(int i =0;i<list.size();i++){
		            		T616_Bean bean = list.get(i);
		            		int k=0;
		            		ws.addCell(new Label(k, j, bean.getStuType(), wcf1));
		            		k+=1;
		            		ws.addCell(new Label(k, j, bean.getSumGraNum()+"", wcf1));
		            		k+=1;ws.addCell(new Label(k, j, bean.getGraOutNum()+"", wcf1));
		            		k+=1;ws.addCell(new Label(k, j, bean.getGraHongNum()+"", wcf1));
		            		k+=1;ws.addCell(new Label(k, j, bean.getGraAoNum()+"", wcf1));
		            		k+=1;ws.addCell(new Label(k, j, bean.getGraTaiNum()+"", wcf1));
		            		k+=1;
		            		ws.addCell(new Label(k, j, bean.getSumDegreeNum()+"", wcf1));
		            		k+=1;ws.addCell(new Label(k, j, bean.getDegreeOutNum()+"", wcf1));
		            		k+=1;ws.addCell(new Label(k, j, bean.getDegreeHongNum()+"", wcf1));
		            		k+=1;ws.addCell(new Label(k, j, bean.getDegreeAoNum()+"", wcf1));
		            		k+=1;ws.addCell(new Label(k, j, bean.getDegreeTaiNum()+"", wcf1));
		            		k+=1;
		            		ws.addCell(new Label(k, j, bean.getSumAdmisNum()+"", wcf1));
		            		k+=1;ws.addCell(new Label(k, j, bean.getAdmisHongNum()+"", wcf1));
		            		k+=1;ws.addCell(new Label(k, j, bean.getAdmisAoNum()+"", wcf1));
		            		k+=1;ws.addCell(new Label(k, j, bean.getAdmisOutNum()+"", wcf1));
		            		k+=1;ws.addCell(new Label(k, j, bean.getAdmisTaiNum()+"", wcf1));
		            		k+=1;
		            		ws.addCell(new Label(k, j, bean.getSumInSchNum()+"", wcf1));
		            		k+=1;ws.addCell(new Label(k, j, bean.getInSchHongNum()+"", wcf1));
		            		k+=1;ws.addCell(new Label(k, j, bean.getInSchAoNum()+"", wcf1));
		            		k+=1;ws.addCell(new Label(k, j, bean.getInSchTaiNum()+"", wcf1));
		            		k+=1;ws.addCell(new Label(k, j, bean.getInSchOutNum()+"", wcf1));
		            		k+=1;
		            		j++;
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
		request.setCharacterEncoding("UTF-8") ;
		System.out.println("excelName=============" + this.excelName) ;
		return "success" ;
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

	public T616_Bean getT616_bean() {
		return T616_bean;
	}

	public void setT616_bean(T616_Bean T616Bean) {
		T616_bean = T616Bean;
	}

	public static void main(String arg[]){
		T616_Action s=new T616_Action();
		 		
	}
	
	
}