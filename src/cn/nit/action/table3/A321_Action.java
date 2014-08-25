package cn.nit.action.table3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
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
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table3.A321_Bean;


import cn.nit.dao.table3.A321_DAO;
import cn.nit.excel.imports.table3.A321Excel;




import cn.nit.pojo.table3.A321POJO;
import cn.nit.service.table3.A321_Service;
import cn.nit.util.ExcelUtil;

public class A321_Action {
	
	
	private A321_Service a321_Service = new A321_Service() ;
		
		private A321_Bean a321_Bean = new A321_Bean() ;
		
		private A321_DAO a321_DAO=new A321_DAO();
	
		
		private A321Excel a321Excel = new A321Excel() ;
		
		
		/**  哪一年数据  */
		private String selectYear; //删除的id
		
		/**  导出的excelName名 */
		private String excelName ;

		HttpServletResponse response = ServletActionContext.getResponse() ;
		HttpServletRequest request = ServletActionContext.getRequest() ;
		


		



		

		
		public String getSelectYear() {
			return selectYear;
		}




		public void setSelectYear(String selectYear) {
			this.selectYear = selectYear;
		}




//		/**  为界面加载数据  */
//	public void auditingData(){
//			
//
//
//		
//			List<A321_Bean> list = new ArrayList<A321_Bean>();
//			
//			list = a321_Service.auditingData(this.getSelectYear()) ;
//			PrintWriter out = null ;
//			JSON json=JSONSerializer.toJSON(list) ;
//			String jsonStr=json.toString();
//			
//			try{
//				getResponse().setContentType("text/html; charset=UTF-8") ;
//				out = getResponse().getWriter() ;
//				out.print(list) ;
//			}catch(Exception e){
//				e.printStackTrace() ;
//				return ;
//			}finally{
//				if(out != null){
//					out.close() ;
//				}
//			}
//		}
	
	public void auditingData() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse() ;		
		
		List<A321_Bean> list = a321_Service.auditingData(this.getSelectYear());		
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



		
		public A321_Service getA321_Service() {
			return a321_Service;
		}




		public void setA321_Service(A321_Service a321Service) {
			a321_Service = a321Service;
		}




		public A321_Bean getA321_Bean() {
			return a321_Bean;
		}




		public void setA321_Bean(A321_Bean a321Bean) {
			a321_Bean = a321Bean;
		}
		
		

		
		public InputStream getInputStream() throws IOException{
			

			System.out.println(this.getSelectYear());
			List<A321_Bean> list = a321_DAO.totalList(this.getSelectYear());
			
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
			           ws.addCell(new Label(1, 2, "学位授予门类", wcf)); 
			           ws.addCell(new Label(2, 2, "专业数（个）", wcf)); 
			           ws.addCell(new Label(3, 2, "所占比例（%）", wcf)); 
			           ws.addCell(new Label(0, 4, "全校合计", wcf)); 
			           ws.addCell(new Label(2, 4, ""+list.get(0).getTotalNum(), wcf1)); 
			           ws.addCell(new Label(3, 4, "/", wcf));
			           for(int i=0;i<list.size();i++){
			        	   ws.addCell(new Label(0, 5+i,""+(i+1), wcf));
			        	   ws.addCell(new Label(1, 5+i,list.get(i).getDisClass(), wcf));
			        	   ws.addCell(new Label(2, 5+i,""+list.get(i).getFieldNum(), wcf1));
			        	   
			        	   ws.addCell(new Label(3, 5+i,list.get(i).getArtRatio()+"%", wcf1));
			           }
			           



			           ws.mergeCells(0, 2, 0, 3);
			           ws.mergeCells(1, 2, 1, 3);
			           ws.mergeCells(2, 2, 2, 3);
			           ws.mergeCells(3, 2, 3, 3);
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

			getResponse().setContentType("application/octet-stream;charset=UTF-8") ;
			return "success" ;
		}
		public A321_DAO getA321_DAO() {
			return a321_DAO;
		}




		public void setA321_DAO(A321_DAO a321DAO) {
			a321_DAO = a321DAO;
		}




		public A321Excel getA321Excel() {
			return a321Excel;
		}




		public void setA321Excel(A321Excel a321Excel) {
			this.a321Excel = a321Excel;
		}




		public String getExcelName() {
			return excelName;
		}




		public void setExcelName(String excelName) {
			this.excelName = excelName;
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
		

		public static void main(String args[]){
			String match = "[\\d]+" ;
			System.out.println("23gfhf4".matches(match)) ;
		}


}
