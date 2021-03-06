package cn.nit.action.table4;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

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

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table4.A441_Bean;
import cn.nit.dao.table4.A441_Dao;
import cn.nit.service.table4.A441_Service;
import cn.nit.util.JsonUtil;

public class A441_Action {
	
	private A441_Service a441_Service=new A441_Service();
	
	private A441_Bean a441_Bean=new A441_Bean();
	
//	private A441_Dao a441_Dao=new A441_Dao();
	
	/**  哪一年数据  */
	private String selectYear;

	/**  导出的excelName名 */
	private String excelName ;
	

	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	//查询出所有
	
	public void loadInfo() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse() ;		
		PrintWriter out = null ;
		A441_Bean bean=a441_Service.getYearInfo(this.getSelectYear());
		boolean flag = false;
		String json = null;
		
		if(bean == null){
			response.setContentType("text/html;charset=UTF-8") ;
			out = response.getWriter() ;
			out.print("{\"data\":\"该统计表数据不全，请填写相关数据后再进行统计!!!\"}") ;
			System.out.println("统计数据不全");
		}else{			
			flag = a441_Service.save(bean,this.getSelectYear());
			bean.setTime(null);
			json = JsonUtil.beanToJson(bean);
			System.out.println(json) ;			
		}	
		if(flag == false ){
			System.out.println("统计数据保存失败");
			response.setContentType("text/html;charset=UTF-8") ;
			out = response.getWriter() ;
			out.println("{\"data\":\"统计数据保存失败\"}"); 
		}else{
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
		

	}
	

	public InputStream getInputStream() throws Exception{

		System.out.println(this.getSelectYear());
		A441_Bean bean = a441_Service.getData(this.getSelectYear());
		
	    ByteArrayOutputStream fos = null;
		
		if(bean==null){
			PrintWriter out = null ;
			response.setContentType("text/html;charset=utf-8") ;
			out = response.getWriter() ;
			out.print("后台传入的数据为空") ;
			System.out.println("后台传入的数据为空");
			return null;
		}else{
			String sheetName = this.excelName;
						
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
		           ws.setRowView(1, 500);
		           
		            //    设置内容单无格的文字格式
		           WritableFont wf1 = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false,
		                    UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
		            WritableCellFormat wcf1 = new WritableCellFormat(wf1);        
		            wcf1.setVerticalAlignment(VerticalAlignment.CENTRE);
		            wcf1.setAlignment(Alignment.CENTRE);
		            wcf1.setBorder(Border.ALL, BorderLineStyle.THIN,
			        		     jxl.format.Colour.BLACK);
		           
		           ws.addCell(new Label(0, 0, sheetName, wcf)); 
		           ws.mergeCells(0, 0, 2, 0);
		           
		           ws.addCell(new Label(0, 2, "项目", wcf)); 
		           ws.addCell(new Label(1, 2, "内容", wcf)); 
		           ws.addCell(new Label(0, 3, "1.职称结构", wcf)); 
		           ws.addCell(new Label(1, 3, "正高级", wcf));  
		           ws.addCell(new Label(3, 3, "副高级", wcf)); 
		           ws.addCell(new Label(5, 3, "中级", wcf)); 
		           ws.addCell(new Label(7, 3, "初级及以下", wcf)); 
		           ws.addCell(new Label(1, 4, "人数", wcf)); 
		           ws.addCell(new Label(2, 4, "比例", wcf)); 
		           ws.addCell(new Label(3, 4, "人数", wcf)); 
		           ws.addCell(new Label(4, 4, "比例", wcf)); 
		           ws.addCell(new Label(5, 4, "人数", wcf)); 
		           ws.addCell(new Label(6, 4, "比例", wcf)); 
		           ws.addCell(new Label(7, 4, "人数", wcf)); 
		           ws.addCell(new Label(8, 4, "比例", wcf)); 
		           
		           ws.addCell(new Label(1, 5, bean.getSeniorNum()+"", wcf1)); 
		           ws.addCell(new Label(2, 5, bean.getSeniorRatio()+"%", wcf1));  
		           ws.addCell(new Label(3, 5, bean.getSubSenior()+"", wcf1));
		           ws.addCell(new Label(4, 5, bean.getSubSeniorRatio()+"%", wcf1)); 
		           ws.addCell(new Label(5, 5, bean.getMiddleNum()+"", wcf1));  
		           ws.addCell(new Label(6, 5, bean.getMiddleRatio()+"%", wcf1));
		           ws.addCell(new Label(7, 5, bean.getPrimaryNum()+"", wcf1)); 
		           ws.addCell(new Label(8, 5, bean.getPrimaryRatio()+"%", wcf1));  

		           ws.addCell(new Label(0, 6, "2.学位结构", wcf)); 
		           ws.addCell(new Label(1, 6, "博士", wcf));  
		           ws.addCell(new Label(3, 6, "硕士", wcf)); 
		           ws.addCell(new Label(5, 6, "学士", wcf)); 
		           ws.addCell(new Label(7, 6, "无学位", wcf)); 
		           ws.addCell(new Label(1, 7, "人数", wcf)); 
		           ws.addCell(new Label(2, 7, "比例", wcf)); 
		           ws.addCell(new Label(3, 7, "人数", wcf)); 
		           ws.addCell(new Label(4, 7, "比例", wcf)); 
		           ws.addCell(new Label(5, 7, "人数", wcf)); 
		           ws.addCell(new Label(6, 7, "比例", wcf)); 
		           ws.addCell(new Label(7, 7, "人数", wcf)); 
		           ws.addCell(new Label(8, 7, "比例", wcf)); 
		           
		           ws.addCell(new Label(1, 8, bean.getDoctorNum()+"", wcf1)); 
		           ws.addCell(new Label(2, 8, bean.getDoctorRatio()+"%", wcf1));  
		           ws.addCell(new Label(3, 8, bean.getMasterNum()+"", wcf1));
		           ws.addCell(new Label(4, 8, bean.getMasterRatio()+"%", wcf1)); 
		           ws.addCell(new Label(5, 8, bean.getBachelorNum()+"", wcf1));  
		           ws.addCell(new Label(6, 8, bean.getBachelorRatio()+"%", wcf1));
		           ws.addCell(new Label(7, 8, bean.getNotDegreeNum()+"", wcf1)); 
		           ws.addCell(new Label(8, 8, bean.getNotDegreeRatio()+"%", wcf1)); 
		           
		           ws.addCell(new Label(0, 9, "3.年龄结构", wcf)); 
		           ws.addCell(new Label(1, 9, "35岁及以下", wcf));  
		           ws.addCell(new Label(3, 9, "36~45岁", wcf)); 
		           ws.addCell(new Label(5, 9, "46~55岁", wcf)); 
		           ws.addCell(new Label(7, 9, "56岁及以上", wcf)); 
		           ws.addCell(new Label(1, 10, "人数", wcf)); 
		           ws.addCell(new Label(2, 10, "比例", wcf)); 
		           ws.addCell(new Label(3, 10, "人数", wcf)); 
		           ws.addCell(new Label(4, 10, "比例", wcf)); 
		           ws.addCell(new Label(5, 10, "人数", wcf)); 
		           ws.addCell(new Label(6, 10, "比例", wcf)); 
		           ws.addCell(new Label(7, 10, "人数", wcf)); 
		           ws.addCell(new Label(8, 10, "比例", wcf)); 
		           
		           ws.addCell(new Label(1, 11, bean.getBelow35Num()+"", wcf1)); 
		           ws.addCell(new Label(2, 11, bean.getBelow35Ratio()+"%", wcf1));  
		           ws.addCell(new Label(3, 11, bean.getIn36To45Num()+"", wcf1));
		           ws.addCell(new Label(4, 11, bean.getIn36To45Ratio()+"%", wcf1)); 
		           ws.addCell(new Label(5, 11, bean.getIn46To55Num()+"", wcf1));  
		           ws.addCell(new Label(6, 11, bean.getIn46To55Ratio()+"%", wcf1));
		           ws.addCell(new Label(7, 11, bean.getAbove56Num()+"", wcf1)); 
		           ws.addCell(new Label(8, 11, bean.getAbove56Ratio()+"%", wcf1)); 
		           
		           ws.addCell(new Label(0, 12, "4.学缘结构", wcf)); 
		           ws.addCell(new Label(1, 12, "本校", wcf));  
		           ws.addCell(new Label(3, 12, "外校（境内）", wcf)); 
		           ws.addCell(new Label(5, 12, "外校（境外）", wcf)); 
		           ws.addCell(new Label(1, 13, "人数", wcf)); 
		           ws.addCell(new Label(2, 13, "比例", wcf)); 
		           ws.addCell(new Label(3, 13, "人数", wcf)); 
		           ws.addCell(new Label(4, 13, "比例", wcf)); 
		           ws.addCell(new Label(5, 13, "人数", wcf)); 
		           ws.addCell(new Label(6, 13, "比例", wcf)); 

		           
		           ws.addCell(new Label(1, 14, bean.getThisSchNum()+"", wcf1)); 
		           ws.addCell(new Label(2, 14, bean.getThisSchRatio()+"%", wcf1));  
		           ws.addCell(new Label(3, 14, bean.getOutSchInNum()+"", wcf1));
		           ws.addCell(new Label(4, 14, bean.getOutSchInRatio()+"%", wcf1)); 
		           ws.addCell(new Label(5, 14, bean.getOutSchOutNum()+"", wcf1));  
		           ws.addCell(new Label(6, 14, bean.getOutSchOutRatio()+"%", wcf1));
		           


		           
		           

		           
		           ws.mergeCells(1, 2, 8, 2);
		           ws.mergeCells(0, 3, 0, 5);
		           ws.mergeCells(1, 3, 2, 3);
		           ws.mergeCells(3, 3, 4, 3);
		           ws.mergeCells(5, 3, 6, 3);
		           ws.mergeCells(7, 3, 8, 3);
		           
		           ws.mergeCells(0, 6, 0, 8);
		           ws.mergeCells(1, 6, 2, 6);
		           ws.mergeCells(3, 6, 4, 6);
		           ws.mergeCells(5, 6, 6, 6);
		           ws.mergeCells(7, 6, 8, 6);
		           
		           ws.mergeCells(0, 9, 0, 11);
		           ws.mergeCells(1, 9, 2, 9);
		           ws.mergeCells(3, 9, 4, 9);
		           ws.mergeCells(5, 9, 6, 9);
		           ws.mergeCells(7, 9, 8, 9);
		           
		           ws.mergeCells(0, 12, 0, 14);
		           ws.mergeCells(1, 12, 2, 12);
		           ws.mergeCells(3, 12, 4, 12);
		           ws.mergeCells(5, 12, 6, 12);
		           

		           

		           		           

		             

		          wwb.write();
		          wwb.close();

		        } catch (IOException e){
		        } catch (RowsExceededException e){
		        } catch (WriteException e){}
		        
		}
		return new ByteArrayInputStream(fos.toByteArray());
	}
	
	
	public String execute() throws Exception{
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



	public A441_Service getA441_Service() {
		return a441_Service;
	}


	public void setA441_Service(A441_Service a441Service) {
		a441_Service = a441Service;
	}


	public A441_Bean getA441_Bean() {
		return a441_Bean;
	}


	public void setA441_Bean(A441_Bean a441Bean) {
		a441_Bean = a441Bean;
	}


//	public A441_Dao getA441_Dao() {
//		return a441_Dao;
//	}
//
//
//	public void setA441_Dao(A441_Dao a441Dao) {
//		a441_Dao = a441Dao;
//	}


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

}
