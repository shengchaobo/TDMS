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

import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table1.A15Bean;
import cn.nit.bean.table1.S18Bean;
import cn.nit.bean.table1.T11Bean;
import cn.nit.dao.table1.A15DAO;
import cn.nit.excel.imports.table1.A15Excel;
import cn.nit.service.table1.A15Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

public class A15Action {
	

	/**  表A15的Service类  */
	private A15Service a15Ser = new A15Service() ;
	
	/**  表A15的Bean实体类  */
	private A15Bean a15Bean = new A15Bean() ;
	

	/**  表A15的DAO类  */
	private A15DAO a15Dao = new A15DAO() ;
	
	/**  表A15的Excel类  */
	private A15Excel a15Excel = new A15Excel() ;

	/**数据导出年份*/
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
		
		String pages = a15Ser.autidingdata(year);
//		System.out.println("pages:"+pages);
		PrintWriter out = null ;
//		boolean flag=false;
//		if(pages!=null){
//			flag=true;
//		}
//		
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
//			List<A15Bean> list=new ArrayList<A15Bean>(); 
////            Date time=new Date();
////            String time1=time.toString();
////            String year=time1.substring(time1.length()-4, time1.length());
//            list=a15Dao.forExcel(this.selectYear);
//            inputStream = new ByteArrayInputStream(a15Excel.writeExcel(list).toByteArray());
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

		A15Bean bean =a15Dao.forExcel(this.selectYear).get(0);
		
	    ByteArrayOutputStream fos = null;
	
		if(bean==null){
			PrintWriter out = null ;
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			out.print("后台传入的数据为空!!!") ;
			System.out.println("后台传入的数据为空");
		}else{
//			String sheetName = this.getExcelName();
				String sheetName="A-1-5科研机构";	
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
		           ws.addCell(new Label(1, 1, "个数（个）", wcf));
		           ws.addCell(new Label(2, 1, "所占比例（%）", wcf));
		           ws.addCell(new Label(0,2,"1.国家级科研机构",wcf));
		           ws.addCell(new Label(0,3,"2.省部级科研机构",wcf));
		           ws.addCell(new Label(0,4,"3.市级科研机构",wcf));
		           ws.addCell(new Label(0,5,"4.校级科研机构",wcf));
		           ws.addCell(new Label(0,6,"5.总计",wcf));
		           ws.addCell(new Label(2,6,"/",wcf));
		           
		          
		           ws.addCell(new Label(1, 2, ""+bean.getNationResNum(), wcf1)); 
		           ws.addCell(new Label(2, 2,this.toStr(bean.getNationResRatio()) , wcf1));
		           ws.addCell(new Label(1, 3, ""+bean.getProviResNum(), wcf1));
		           ws.addCell(new Label(2, 3, this.toStr(bean.getProviResRatio()), wcf1));
		           ws.addCell(new Label(1, 4,""+bean.getCityResNum(), wcf1));
		           ws.addCell(new Label(2, 4, this.toStr(bean.getCityResRatio()), wcf1)); 
		           ws.addCell(new Label(1, 5,""+bean.getSchResNum(), wcf1));
		           ws.addCell(new Label(2, 5, this.toStr(bean.getSchResRatio()), wcf1));
		           ws.addCell(new Label(1, 6, ""+bean.getSumResNum(), wcf1));
		           
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

	public A15Bean getA15Bean() {
		return a15Bean;
	}

	public void setA15Bean(A15Bean a15Bean) {
		this.a15Bean = a15Bean;
	}
	
	public String toStr(double num){
		double n=num*100;
		String str1=""+n;
		String str=str1+"%";
		return str;
	}
	
	public static  void main(String arg[]){
		A15Action action=new A15Action();
		String str=action.toStr(0.23);
		System.out.println(str);
	}

	

}
