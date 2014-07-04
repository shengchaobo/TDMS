package cn.nit.action.table1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table1.A15Bean;
import cn.nit.bean.table1.S15Bean;
import cn.nit.bean.table1.T11Bean;
import cn.nit.dao.table1.S15DAO;
import cn.nit.excel.imports.table1.S15Excel;
import cn.nit.service.table1.S15Service;

public class S15Action {
	
	/**S15的service類*/
	private S15Service s15Ser=new S15Service();
	
	/**S15的Bean類*/
	private S15Bean s15Bean=new S15Bean();
	
	/**S15的Excel類*/
	private S15Excel s15Excel=new S15Excel();
	
	/**导出数据选择年份*/
	private String selectYear;
	
	
	public String getSelectYear() {
		return selectYear;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}

	/**S15的Dao類*/
	private S15DAO s15Dao=new S15DAO();
		
		/**
		 * 输出json格式数据
		 * */
		public void autidingdata(){
	
			System.out.println("=======================");
			Date time=new Date();
			String date=time.toString();
			String year=date.substring(date.length()-4);
			String info =s15Ser.autidingdata(year) ;
			PrintWriter out = null ;
	
			try{
				getResponse().setContentType("text/html; charset=UTF-8") ;
				out = getResponse().getWriter() ;
				out.print(info) ;
			}catch(Exception e){
				e.printStackTrace() ;
				return ;
			}finally{
				if(out != null){
					out.close() ;
				}
			}
		}
		
//		/**Excel表導出*/
//		public InputStream getInputStream(){
//
//			InputStream inputStream = null ;
//
//			try {
//				
//				List<S15Bean> list=new ArrayList<S15Bean>(); 
////	            Date time=new Date();
////	            String time1=time.toString();
////	            String year=time1.substring(time1.length()-4, time1.length());
//	            list=s15Dao.forExcel(this.selectYear);
//	            inputStream = new ByteArrayInputStream(s15Excel.writeExcel(list).toByteArray());
//				
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				return null ;
//			}
//
//			return inputStream ;
//		}
		
		public InputStream getInputStream() throws Exception{
			
			System.out.println(this.getSelectYear());

			S15Bean bean =s15Dao.forExcel(this.selectYear).get(0);
			
		    ByteArrayOutputStream fos = null;
		
			if(bean==null){
				PrintWriter out = null ;
				getResponse().setContentType("text/html; charset=UTF-8") ;
				out = getResponse().getWriter() ;
				out.print("后台传入的数据为空!!!") ;
				System.out.println("后台传入的数据为空");
			}else{
//				String sheetName = this.getExcelName();
					String sheetName="S-1-5科研机构";	
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
			           
//			            //    设置内容单无格的文字格式
//			           WritableFont wf1 = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false,
//			                    UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
//			            WritableCellFormat wcf1 = new WritableCellFormat(wf1);        
//			            wcf1.setVerticalAlignment(VerticalAlignment.CENTRE);
//			            wcf1.setAlignment(Alignment.CENTRE);
//			            wcf1.setBorder(Border.ALL, BorderLineStyle.THIN,
//				        		     jxl.format.Colour.BLACK);
			           //设置格式
					   WritableCellFormat wcf1 = new WritableCellFormat();
					   wcf1.setBorder(Border.ALL, BorderLineStyle.THIN,
							     jxl.format.Colour.BLACK); 
			           
			           ws.addCell(new Label(0, 0, sheetName, wcf)); 
			           ws.mergeCells(0, 0, 1, 0);
			             
			           ws.addCell(new Label(0, 1, "项目", wcf)); 
			           ws.addCell(new Label(1, 1, "数量（个）", wcf));
			           ws.addCell(new Label(2, 1, "专业科研用房面积（平方米）", wcf));
			           ws.addCell(new Label(0,2,"总计",wcf));
			           ws.addCell(new Label(0,3,"1.国家实验室",wcf));
			           ws.addCell(new Label(0,4,"2.国家重点实验室",wcf));
			           ws.addCell(new Label(0,5,"3.国家工程（技术）研究中心",wcf));
			           ws.addCell(new Label(0,6,"4.其他国家级科研机构",wcf));
			           ws.addCell(new Label(0,7,"5.省、部级设置的研究所（院、中心）",wcf));
			           ws.addCell(new Label(0,8,"6.省、部级设置的实验室",wcf));
			           ws.addCell(new Label(0,9,"7.其他省级科研机构",wcf));
			           ws.addCell(new Label(0,10,"8.人文科学重点研究基地",wcf));
			           ws.addCell(new Label(0,13,"9.市级科研机构",wcf));
			           ws.addCell(new Label(0,14,"10.教学单位科研实验室",wcf));
			           ws.addCell(new Label(0,15,"11.其他校级科研机构",wcf));
			           ws.addCell(new Label(1,10,"9.市级科研机构",wcf));
			           ws.addCell(new Label(1,11,"10.教学单位科研实验室",wcf));
			           ws.addCell(new Label(1,12,"11.其他校级科研机构",wcf));
			           
			           ws.mergeCells(0, 1, 1, 0);
			           ws.mergeCells(0, 2, 1, 0);
			           ws.mergeCells(0, 3, 1, 0);
			           ws.mergeCells(0, 4, 1, 0);
			           ws.mergeCells(0, 5, 1, 0);
			           ws.mergeCells(0, 6, 1, 0);
			           ws.mergeCells(0, 7, 1, 0);
			           ws.mergeCells(0, 8, 1, 0);
			           ws.mergeCells(0, 9, 1, 0);
			           ws.mergeCells(0, 13, 1, 0);
			           ws.mergeCells(0, 14, 1, 0);
			           ws.mergeCells(0, 15, 1, 0);
			           ws.mergeCells(0, 10, 0, 2);
			           
			           ws.addCell(new Label(2, 2, ""+bean.getSumResNum(), wcf1)); 
			           ws.addCell(new Label(3, 2,""+bean.getSumResArea() , wcf1));
			           ws.addCell(new Label(2, 3, ""+bean.getNationResNum(), wcf1)); 
			           ws.addCell(new Label(3, 3,""+bean.getNationResArea() , wcf1));
			           ws.addCell(new Label(2, 4, ""+bean.getNationKeyResNum(), wcf1)); 
			           ws.addCell(new Label(3, 4,""+bean.getNationKeyResArea() , wcf1));
			           ws.addCell(new Label(2, 5, ""+bean.getNationEnginResNum(), wcf1)); 
			           ws.addCell(new Label(3, 5,""+bean.getNationEnginResArea() , wcf1));
			           ws.addCell(new Label(2, 6, ""+bean.getOtherNationResNum(), wcf1)); 
			           ws.addCell(new Label(3, 6,""+bean.getOtherNationResArea() , wcf1));
			           ws.addCell(new Label(2, 7, ""+bean.getProviResNum(), wcf1)); 
			           ws.addCell(new Label(3, 7,""+bean.getProviResArea() , wcf1));
			           ws.addCell(new Label(2, 8, ""+bean.getProviLabNum(), wcf1)); 
			           ws.addCell(new Label(3, 8,""+bean.getProviLabArea() , wcf1));
			           ws.addCell(new Label(2, 9, ""+bean.getOtherProviResNum(), wcf1)); 
			           ws.addCell(new Label(3, 9,""+bean.getOtherSchResArea() , wcf1));
			           ws.addCell(new Label(2, 10, ""+bean.getHumanResSumNum(), wcf1)); 
			           ws.addCell(new Label(3, 10,""+bean.getHumanResSumArea() , wcf1));
			           ws.addCell(new Label(2, 11, ""+bean.getHumanNationResNum(), wcf1)); 
			           ws.addCell(new Label(3, 11,""+bean.getHumanNationResArea() , wcf1));
			           ws.addCell(new Label(2, 12, ""+bean.getHumanProviResNum(), wcf1)); 
			           ws.addCell(new Label(3, 12,""+bean.getHumanProviResArea() , wcf1));
			           ws.addCell(new Label(2, 13, ""+bean.getCityResNum(), wcf1)); 
			           ws.addCell(new Label(3, 13,""+bean.getCityResArea() , wcf1));
			           ws.addCell(new Label(2, 14, ""+bean.getTeaUnitResNum(), wcf1)); 
			           ws.addCell(new Label(3, 14,""+bean.getTeaUnitResArea() , wcf1));
			           ws.addCell(new Label(2, 15, ""+bean.getOtherSchResNum(), wcf1)); 
			           ws.addCell(new Label(3, 15,""+bean.getOtherSchResArea() , wcf1));
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
		
		public HttpServletRequest getRequest(){
			return ServletActionContext.getRequest() ;
		}
	
		public HttpSession getSession(){
			return getRequest().getSession() ;
		}
	
		public HttpServletResponse getResponse(){
			return ServletActionContext.getResponse() ;
		}


}
