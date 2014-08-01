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
import cn.nit.bean.table3.S322_Bean;
import cn.nit.dao.table3.S322_DAO;
import cn.nit.service.table3.S322_Service;
import cn.nit.util.JsonUtil;

public class S322_Action {
	
	private S322_Service s322_Service=new S322_Service();
	
	private S322_Bean s322_Bean=new S322_Bean();
	
	private S322_DAO s322_Dao=new S322_DAO();
	
	/**  哪一年数据  */
	private String selectYear;

	/**  导出的excelName名 */
	private String excelName ;
	

	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	//查询出所有
	
	public void loadInfo() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse() ;		
		
		List<S322_Bean> list=s322_Service.getYearInfo(this.getSelectYear());
		
		System.out.println(this.getSelectYear());
		System.out.println(list.size());
		JSON json = JSONSerializer.toJSON(list) ;
		PrintWriter out = null ;
		System.out.println(json.toString());
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
		List<S322_Bean> list = s322_Dao.totalList(this.getSelectYear());
		
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
		           ws.addCell(new Label(1, 2, "教学单位", wcf)); 
		           ws.addCell(new Label(2, 2, "单位号", wcf)); 
		           ws.addCell(new Label(3, 2, "已通过专业认证（评估）的本科专业名称", wcf)); 
		           ws.addCell(new Label(4, 2, "专业代码", wcf)); 
		           ws.addCell(new Label(5, 2, "认证时间", wcf)); 
		           ws.addCell(new Label(6, 2, "有效期(起)", wcf)); 
		           ws.addCell(new Label(7, 2, "有效期(止)", wcf)); 
		           ws.addCell(new Label(8, 2, "认证机构", wcf));            
		           for(int i=0;i<list.size();i++){
		        	   ws.addCell(new Label(0, 3+i,""+(i+1), wcf));
		        	   ws.addCell(new Label(1, 3+i,list.get(i).getTeaUnit(), wcf));
		        	   ws.addCell(new Label(2, 3+i,""+list.get(i).getUnitID(), wcf1));
		        	   ws.addCell(new Label(3, 3+i,""+list.get(i).getPassedMajor(), wcf1));
		        	   ws.addCell(new Label(4, 3+i,""+list.get(i).getMajorID(), wcf1));
		        	   ws.addCell(new Label(5, 3+i,""+list.get(i).getAssessTime(), wcf1));
		        	   ws.addCell(new Label(6, 3+i,""+list.get(i).getValidityBegin(), wcf1));
		        	   ws.addCell(new Label(7, 3+i,""+list.get(i).getValidityEnd(), wcf1));
		        	   ws.addCell(new Label(8, 3+i,""+list.get(i).getAssessOrg(), wcf1));
		           }

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

	public S322_Service getS322_Service() {
		return s322_Service;
	}

	public void setS322_Service(S322_Service s322Service) {
		s322_Service = s322Service;
	}

	public S322_Bean getS322_Bean() {
		return s322_Bean;
	}

	public void setS322_Bean(S322_Bean s322Bean) {
		s322_Bean = s322Bean;
	}

	public S322_DAO getS322_Dao() {
		return s322_Dao;
	}

	public void setS322_Dao(S322_DAO s322Dao) {
		s322_Dao = s322Dao;
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
