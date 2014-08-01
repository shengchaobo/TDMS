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
import cn.nit.bean.table3.S321_Bean;
import cn.nit.dao.table3.S321_DAO;
import cn.nit.service.table3.S321_Service;
import cn.nit.util.JsonUtil;

public class S321_Action {
	
	private S321_Service s321_Service=new S321_Service();
	
	private S321_Bean s321_Bean=new S321_Bean();
	
	private S321_DAO s321_Dao=new S321_DAO();
	
	/**  哪一年数据  */
	private String selectYear;

	/**  导出的excelName名 */
	private String excelName ;
	

	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	//查询出所有
	
	public void loadInfo() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse() ;		
		
		List<S321_Bean> list=s321_Service.getYearInfo(this.getSelectYear());
		
		System.out.println(this.getSelectYear());
		System.out.println(list.size());
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
	
	
	public InputStream getInputStream() throws IOException{
		

		System.out.println(this.getSelectYear());
		List<S321_Bean> list = s321_Dao.totalList(this.getSelectYear());
		
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
		           WritableSheet ws = wwb.createSheet("A3-2-1", 0);        // 创建一个工作表
		
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
		           ws.mergeCells(0, 0, 1, 0);
		           
		           ws.addCell(new Label(0, 2, "序号", wcf)); 
		           ws.addCell(new Label(1, 2, "类型", wcf)); 
		           ws.addCell(new Label(2, 2, "优势专业数（个）", wcf)); 
		           ws.addCell(new Label(2, 3, "合计", wcf)); 
		           ws.addCell(new Label(3, 3, "国际级", wcf)); 
		           ws.addCell(new Label(4, 3, "国家级", wcf)); 
		           ws.addCell(new Label(5, 3, "省部级", wcf)); 
		           ws.addCell(new Label(6, 3, "市级", wcf)); 
		           ws.addCell(new Label(7, 3, "校级", wcf)); 
		           ws.addCell(new Label(0, 4, "全校合计", wcf)); 
		           ws.addCell(new Label(2, 4, ""+list.get(0).getSum(), wcf1)); 
		           ws.addCell(new Label(3, 4, ""+list.get(0).getInternation(), wcf1)); 
		           ws.addCell(new Label(4, 4, ""+list.get(0).getNation(), wcf1)); 
		           ws.addCell(new Label(5, 4, ""+list.get(0).getProvi(), wcf1)); 
		           ws.addCell(new Label(6, 4, ""+list.get(0).getCity(), wcf1)); 
		           ws.addCell(new Label(7, 4, ""+list.get(0).getSchool(), wcf1)); 
		           
		           for(int i=1;i<list.size();i++){
		        	   ws.addCell(new Label(0, 4+i,""+i, wcf));
		        	   ws.addCell(new Label(1, 4+i,list.get(i).getFieldType(), wcf));
		        	   ws.addCell(new Label(2, 4+i,""+list.get(i).getSum(), wcf1));
		        	   ws.addCell(new Label(3, 4+i,""+list.get(i).getInternation(), wcf1));
		        	   ws.addCell(new Label(4, 4+i,""+list.get(i).getNation(), wcf1));
		        	   ws.addCell(new Label(5, 4+i,""+list.get(i).getProvi(), wcf1));
		        	   ws.addCell(new Label(6, 4+i,""+list.get(i).getCity(), wcf1));
		        	   ws.addCell(new Label(7, 4+i,""+list.get(i).getSchool(), wcf1));
		           }
		           



		           ws.mergeCells(0, 2, 0, 3);
		           ws.mergeCells(1, 2, 1, 3);
		           ws.mergeCells(2, 2, 7, 2);
		           ws.mergeCells(0, 4, 1, 4);
		           

 
		             

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

	public S321_Service getS321_Service() {
		return s321_Service;
	}

	public void setS321_Service(S321_Service s321Service) {
		s321_Service = s321Service;
	}

	public S321_Bean getS321_Bean() {
		return s321_Bean;
	}

	public void setS321_Bean(S321_Bean s321Bean) {
		s321_Bean = s321Bean;
	}

	public S321_DAO getS321_Dao() {
		return s321_Dao;
	}

	public void setS321_Dao(S321_DAO s321Dao) {
		s321_Dao = s321Dao;
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
