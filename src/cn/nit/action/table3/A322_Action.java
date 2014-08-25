package cn.nit.action.table3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
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

import cn.nit.bean.table3.A322_Bean;
import cn.nit.dao.table3.A322_DAO;
import cn.nit.service.table3.A322_Service;
import cn.nit.util.JsonUtil;

public class A322_Action {
	
	private A322_Service a322_Service=new A322_Service();
	
	private A322_Bean a322_Bean=new A322_Bean();
	
	private A322_DAO a322_Dao=new A322_DAO();
	
	/**  哪一年数据  */
	private String selectYear;

	/**  导出的excelName名 */
	private String excelName ;
	

	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	//查询出所有
	
	public void loadInfo() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse() ;		
		
		List<A322_Bean> list = a322_Service.getYearInfo(this.getSelectYear());		
		boolean flag = true;
		JSON json = null;
		if(list.size()==0){
			flag = false;
		}else{
			 json = JSONSerializer.toJSON(list) ;
			 System.out.println(json.toString());
		}		
		PrintWriter out = null ;		
		try {
			
			if(flag){
				//设置输出内容的格式为json
				response.setContentType("application/json; charset=UTF-8") ;				
				out = response.getWriter() ;
				//设置数据的内容的编码格式
				String outPrint = URLDecoder.decode(json.toString(), "UTF-8") ;
				out.print(outPrint) ;
			}else{
				response.setContentType("text/html; charset=UTF-8") ;
				out = response.getWriter() ;
				out.print("[{\"data\":\"该统计表数据不全，请填写相关数据后再进行统计!!!\"}]") ;
				System.out.println("统计数据不全");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.flush() ;
				out.close() ;
			}
		}
	}
	
	
	public InputStream getInputStream() throws IOException{
		

		System.out.println(this.getSelectYear());
		List<A322_Bean> list = a322_Dao.totalList(this.getSelectYear());
		
	    ByteArrayOutputStream fos = null;
		
		if(list.isEmpty()){
			PrintWriter out = null ;
			response.setContentType("text/html;charset=utf-8") ;
			out = response.getWriter() ;
			out.print("后台传入的数据为空") ;
			System.out.println("后台传入的数据为空");
			return null;
		}else{
			String sheetName = this.getExcelName();
						
		    WritableWorkbook wwb;
		    try {    
		           fos = new ByteArrayOutputStream();
		           wwb = Workbook.createWorkbook(fos);
		           WritableSheet ws = wwb.createSheet("A3-2-2", 0);        // 创建一个工作表
		
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
		           ws.mergeCells(0, 0, 3, 0);
		           
		           ws.addCell(new Label(0, 2, "序号", wcf)); 
		           ws.addCell(new Label(1, 2, "教学单位", wcf)); 
		           ws.addCell(new Label(2, 2, "单位号", wcf)); 
		           ws.addCell(new Label(3, 2, "本科专业数（个）", wcf)); 
		           ws.addCell(new Label(4, 2, "优势专业占专业总数比例（%）", wcf)); 
		           ws.addCell(new Label(4, 3, "合计", wcf)); 
		           ws.addCell(new Label(5, 3, "国际级", wcf)); 
		           ws.addCell(new Label(6, 3, "国家级", wcf)); 
		           ws.addCell(new Label(7, 3, "省部级", wcf)); 
		           ws.addCell(new Label(8, 3, "市级", wcf)); 
		           ws.addCell(new Label(9, 3, "校级", wcf)); 
		           ws.addCell(new Label(0, 4, "全校合计", wcf)); 
		           ws.addCell(new Label(3, 4, ""+list.get(0).getFieldNum(), wcf1)); 
		           ws.addCell(new Label(4, 4, ""+list.get(0).getSum()+"%", wcf1)); 
		           ws.addCell(new Label(5, 4, ""+list.get(0).getInternationRatio()+"%", wcf1)); 
		           ws.addCell(new Label(6, 4, ""+list.get(0).getNationRatio()+"%", wcf1)); 
		           ws.addCell(new Label(7, 4, ""+list.get(0).getProviRatio()+"%", wcf1)); 
		           ws.addCell(new Label(8, 4, ""+list.get(0).getCityRatio()+"%", wcf1)); 
		           ws.addCell(new Label(9, 4, ""+list.get(0).getSchoolRatio()+"%", wcf1));  
		           
		           for(int i=1;i<list.size();i++){
		        	   ws.addCell(new Label(0, 4+i,""+i, wcf));
		        	   ws.addCell(new Label(1, 4+i,list.get(i).getTeaUnit(), wcf));
		        	   ws.addCell(new Label(2, 4+i,""+list.get(i).getUnitID(), wcf1));
		        	   ws.addCell(new Label(3, 4+i,""+list.get(i).getFieldNum(), wcf1));
		        	   ws.addCell(new Label(4, 4+i,""+list.get(i).getSum()+"%", wcf1));
		        	   ws.addCell(new Label(5, 4+i,""+list.get(i).getInternationRatio()+"%", wcf1));
		        	   ws.addCell(new Label(6, 4+i,""+list.get(i).getNationRatio()+"%", wcf1));
		        	   ws.addCell(new Label(7, 4+i,""+list.get(i).getProviRatio()+"%", wcf1));
		        	   ws.addCell(new Label(8, 4+i,""+list.get(i).getCityRatio()+"%", wcf1));
		        	   ws.addCell(new Label(9, 4+i,""+list.get(i).getSchoolRatio()+"%", wcf1));
		           }
		           



		           ws.mergeCells(0, 2, 0, 3);
		           ws.mergeCells(1, 2, 1, 3);
		           ws.mergeCells(2, 2, 2, 3);
		           ws.mergeCells(3, 2, 3, 3);
		           ws.mergeCells(4, 2, 9, 2);
		           ws.mergeCells(0, 4, 2, 4);
		           

 
		             

		          wwb.write();
		          wwb.close();

		        } catch (IOException e){
		        } catch (RowsExceededException e){
		        } catch (WriteException e){}
		        
		}
		return new ByteArrayInputStream(fos.toByteArray());
		
	}
	

	
	public String execute() throws Exception{
		request.setCharacterEncoding("UTF-8") ;
		System.out.println("excelName=============" + excelName) ;
		return "success" ;
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

	

	public A322_Service getA322_Service() {
		return a322_Service;
	}


	public void setA322_Service(A322_Service a322Service) {
		a322_Service = a322Service;
	}


	public A322_Bean getA322_Bean() {
		return a322_Bean;
	}


	public void setA322_Bean(A322_Bean a322Bean) {
		a322_Bean = a322Bean;
	}


	public A322_DAO getA322_Dao() {
		return a322_Dao;
	}


	public void setA322_Dao(A322_DAO a322Dao) {
		a322_Dao = a322Dao;
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


	
	
}
