package cn.nit.action.table1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.ByteArrayInputStream;

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

import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table1.A15Bean;
import cn.nit.bean.table1.S17Bean;
import cn.nit.bean.table1.T11Bean;
import cn.nit.bean.table1.T17Bean;
import cn.nit.dao.table1.S17DAO;
import cn.nit.excel.imports.table1.S17Excel;
import cn.nit.excel.imports.table1.T11Excel;
import cn.nit.service.table1.S17Service;
import cn.nit.util.ExcelUtil;

public class S17Action {
	

	/**  表S17的Service类  */
	private S17Service s17Ser = new S17Service() ;
	
	/**  表S17的Bean实体类  */
	private S17Bean s17Bean = new S17Bean() ;
	
	/**  表17的DAO类  */
	private S17DAO s17Dao = new S17DAO() ;
	
	/**  表17的Excel实体类  */
	private S17Excel s17Excel = new S17Excel() ;
	
	/**导出数据选择年份*/
	private String selectYear;
	

	public String getSelectYear() {
		return selectYear;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}

	/**  为界面加载数据  */
	public void auditingData(){
		
//		System.out.println("=========");
		Date date=new Date();
		String cuYear=date.toString();
		String year=cuYear.substring(cuYear.length()-4, cuYear.length());
		
		String pages = s17Ser.autidingdata(year);
//		System.out.println("pages:"+pages);
		PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			out.print(pages) ;
		}catch(Exception e){
			e.printStackTrace() ;
			return ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
//	public InputStream getInputStream(){
//
//		InputStream inputStream = null ;
//
//		try {
//			
//			List<S17Bean> list=new ArrayList<S17Bean>(); 
////            Date time=new Date();
////            String time1=time.toString();
////            String year=time1.substring(time1.length()-4, time1.length());
//            list=s17Dao.forExcel(this.selectYear);
//            inputStream = new ByteArrayInputStream(s17Excel.writeExcel(list).toByteArray());
//			
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null ;
//		}
//
//		return inputStream ;
//	}
	
	
	public InputStream getInputStream() throws Exception{
		
		System.out.println(this.getSelectYear());

		S17Bean bean =s17Dao.forExcel(this.selectYear).get(0);
		
	    ByteArrayOutputStream fos = null;
	
		if(bean==null){
			PrintWriter out = null ;
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			out.print("后台传入的数据为空!!!") ;
			System.out.println("后台传入的数据为空");
		}else{
//			String sheetName = this.getExcelName();
				String sheetName="S-1-7校友会";	
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
		           
//		            //    设置内容单无格的文字格式
//		           WritableFont wf1 = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false,
//		                    UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
//		            WritableCellFormat wcf1 = new WritableCellFormat(wf1);        
//		            wcf1.setVerticalAlignment(VerticalAlignment.CENTRE);
//		            wcf1.setAlignment(Alignment.CENTRE);
//		            wcf1.setBorder(Border.ALL, BorderLineStyle.THIN,
//			        		     jxl.format.Colour.BLACK);
		           //设置格式
				   WritableCellFormat wcf1 = new WritableCellFormat();
				   wcf1.setBorder(Border.ALL, BorderLineStyle.THIN,
						     jxl.format.Colour.BLACK); 
		           
		           ws.addCell(new Label(0, 0, sheetName, wcf)); 
		           ws.mergeCells(0, 0, 1, 0);
		             
		           ws.addCell(new Label(0, 1, "项目", wcf)); 
		           ws.addCell(new Label(2, 1, "内容", wcf));
		           ws.addCell(new Label(0,2,"校友会（个）",wcf));
		           ws.addCell(new Label(1,2,"总数",wcf));
		           ws.addCell(new Label(1,3,"其中：境内",wcf));
		           ws.addCell(new Label(1,4,"境外",wcf));
		           
		           ws.mergeCells(0, 1, 1, 0);
		           ws.mergeCells(0, 2, 0, 2);
		           
		          
		           ws.addCell(new Label(2, 2, ""+bean.getSumSchFriNum(), wcf1)); 
		           ws.addCell(new Label(2, 3,""+bean.getInlandNum() , wcf1));
		           ws.addCell(new Label(2, 4, ""+bean.getOutlandNum(), wcf1));
		           
		           
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
	
	public UserRoleBean getUserinfo(){
		return (UserRoleBean)getSession().getAttribute("userinfo") ;
	}

	public S17Bean getS17Bean() {
		return s17Bean;
	}

	public void setS17Bean(S17Bean s17Bean) {
		this.s17Bean = s17Bean;
	}

}
