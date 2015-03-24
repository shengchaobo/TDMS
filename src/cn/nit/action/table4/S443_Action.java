package cn.nit.action.table4;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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



import cn.nit.bean.table4.S443_Bean;
import cn.nit.dao.table4.S443_Dao;
import cn.nit.service.table4.S443_Service;
import cn.nit.util.JsonUtil;

public class S443_Action {
	
	
	
	private S443_Service s443_Service=new S443_Service();
	
	private S443_Bean s443_Bean=new S443_Bean();
	
//	private S443_Dao s443_Dao=new S443_Dao();
	
	/**  哪一年数据  */
	private String selectYear;

	/**  导出的excelName名 */
	private String excelName ;
	

	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	//查询出所有
	
	public void loadInfo() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse() ;		
		
		List<S443_Bean> list = s443_Service.getYearInfo(this.getSelectYear());

		//System.out.println(this.getSelectYear());
		//System.out.println(list.size());
		
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
		List<S443_Bean> list = s443_Service.totalList(this.getSelectYear());
		
	    ByteArrayOutputStream fos = null;
		
		if(list.isEmpty()){
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
		           WritableSheet ws = wwb.createSheet(sheetName , 0);        // 创建一个工作表
		
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
		           
		           ws.addCell(new Label(0,2,"序号",wcf));
		           ws.addCell(new Label(1,2,"类型",wcf));
		           ws.addCell(new Label(2,2,"人次数",wcf));
		           ws.addCell(new Label(0,3,list.get(0).getTalentType(),wcf));
		           ws.addCell(new Label(2,3,list.get(0).getTalentNum()+"",wcf1));
 
		           
		           for(int i=1;i<list.size();i++){
		        	   ws.addCell(new Label(0, 3+i,""+i, wcf));
		        	   ws.addCell(new Label(1, 3+i,list.get(i).getTalentType(), wcf1));
		        	   ws.addCell(new Label(2, 3+i,list.get(i).getTalentNum()+"", wcf1));

		           }
		           



		           ws.mergeCells(0, 3, 1, 3);

		           

 
		             

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

	public S443_Service getS443_Service() {
		return s443_Service;
	}

	public void setS443_Service(S443_Service s443Service) {
		s443_Service = s443Service;
	}

	public S443_Bean getS443_Bean() {
		return s443_Bean;
	}

	public void setS443_Bean(S443_Bean s443Bean) {
		s443_Bean = s443Bean;
	}

//	public S443_Dao getS443_Dao() {
//		return s443_Dao;
//	}
//
//	public void setS443_Dao(S443_Dao s443Dao) {
//		s443_Dao = s443Dao;
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
