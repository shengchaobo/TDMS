package cn.nit.action.table1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table1.T11Bean;
import cn.nit.bean.table1.T151Bean;
import cn.nit.bean.table4.T410_Bean;
import cn.nit.dao.table1.T11DAO;
import cn.nit.excel.imports.table1.T11Excel;

import cn.nit.service.table1.T11Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

/**
 * 
 * @author lenovo
 */
public class T11Action {

	/**  表11的Service类  */
	private T11Service t11Ser = new T11Service() ;
	
	/**  表11的dao类  */
	private T11DAO t11Dao = new T11DAO() ;
	
	/**  表11的Bean实体类  */
	private T11Bean t11Bean = new T11Bean() ;
	
	/**  表11的Excel实体类  */
	private T11Excel t11Excel = new T11Excel() ;
	
	/** 接收年份*/
	private String Year ;
	
	/**导出选择年份*/
	private String selectYear;
	
	/**excel名稱*/
	private String excelName;
	


	/**  逐条插入数据  */
	public void insert(){
//		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		t11Bean.setTime(new Date()) ;
//		System.out.println(t151Bean.getResInsID());
//		System.out.println(t151Bean.getResInsName());
		//这还没确定,设置填报者的职工号与部门号
//		UserRoleBean userinfo = (UserRoleBean)getSession().getAttribute("userinfo") ;
//		undergraCSBaseTea.setFillTeaID(userinfo.getTeaID()) ;
		
		boolean flag = t11Ser.insert(t11Bean) ;
		PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
//			getResponse().setHeader("Content-type", "text/html");  
			out = getResponse().getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"录入成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"录入失败!!!\"}") ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"录入失败!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
		out.flush() ;
	}

	/**  为界面加载数据  */
	public void auditingData(){
		
		Date date=new Date();
		String cuYear=date.toString();
		String year=cuYear.substring(cuYear.length()-4, cuYear.length());
		
		String pages = t11Ser.auditingData(year) ;
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
	

	/**  编辑数据  */
	public void edit(){
		
		t11Bean.setTime(new Date()) ;
		System.out.println(this.Year);
		Date ti=TimeUtil.changeDateY(this.Year);
		t11Bean.setSch_BeginTime(ti);
//		System.out.println(t11Bean.getTime());
		boolean flag = t11Ser.update(t11Bean) ;
		PrintWriter out = null ;
		
		try{
			out = getResponse().getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"修改成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"修改失败!!!\"}") ;
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

//	/**利用模板導入*/
//	public InputStream getInputStream(){
//
//		InputStream inputStream = null ;
//
//		try {
//			
//			List<T11Bean> list=new ArrayList<T11Bean>(); 
////            Date time=new Date();
////            String time1=time.toString();
////            String year=time1.substring(time1.length()-4, time1.length());
//            list=t11Dao.forExcel(this.selectYear);
 //          inputStream = new ByteArrayInputStream(t11Excel.writeExcel(list).toByteArray());
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
	
			T11Bean bean =t11Dao.forExcel(this.selectYear).get(0);
			
		    ByteArrayOutputStream fos = null;
		
			if(bean==null){
				PrintWriter out = null ;
				getResponse().setContentType("text/html; charset=UTF-8") ;
				out = getResponse().getWriter() ;
				out.print("后台传入的数据为空!!!") ;
				System.out.println("后台传入的数据为空");
			}else{
	//			String sheetName = this.getExcelName();
					String sheetName="表1-1学校基本信息（党院办）";	
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
			           ws.mergeCells(0, 0, 4, 0);
			             
			           ws.addCell(new Label(0, 1, "联系方式", wcf)); 
			           ws.addCell(new Label(0, 7, "学校概况", wcf)); 
			           ws.addCell(new Label(0, 18, "学校地址", wcf)); 
			           ws.addCell(new Label(1, 1, "1.学校地址", wcf)); 
			           ws.addCell(new Label(1, 2, "2.学校办公电话号码", wcf)); 
			           ws.addCell(new Label(1, 3, "3.学校办公传真号码", wcf)); 
			           ws.addCell(new Label(1, 4, "4.学校填报负责人", wcf)); 
			           ws.addCell(new Label(1, 7, "5.学校名称", wcf)); 
			           ws.addCell(new Label(1, 8, "6.代码", wcf)); 
			           ws.addCell(new Label(1, 9, "7.英文名称", wcf)); 
			           ws.addCell(new Label(1, 10, "8.办学类型", wcf)); 
			           ws.addCell(new Label(1, 11, "9.学校性质", wcf)); 
			           ws.addCell(new Label(1, 12, "10.举办者", wcf)); 
			           ws.addCell(new Label(1, 13, "11.主管部门", wcf)); 
			           ws.addCell(new Label(1, 14, "12.学校网址", wcf)); 
			           ws.addCell(new Label(1, 15, "13.招生批次", wcf)); 
			           ws.addCell(new Label(1, 16, "14.开办本科教育年份", wcf));
			           ws.addCell(new Label(1, 17, "15.多媒体反映", wcf)); 
			           ws.addCell(new Label(1, 18, "16.校区名称", wcf));
			           ws.addCell(new Label(2,4,"姓名",wcf));
			           ws.addCell(new Label(2,5,"联系电话",wcf));
			           ws.addCell(new Label(2,6,"联系电子邮箱",wcf));
			           ws.addCell(new Label(2,18,"瑶湖校区",wcf));
			           ws.addCell(new Label(2,19,"彭桥校区",wcf));
			           
			           ws.mergeCells(0, 1, 0, 5);
			           ws.mergeCells(0, 7, 0, 10);
			           ws.mergeCells(1, 4, 0, 2);
			           ws.mergeCells(1, 18, 0, 1);
			           ws.mergeCells(1, 1, 1, 0);
			           ws.mergeCells(1, 2, 1, 0);
			           ws.mergeCells(1, 3, 1, 0);
			           ws.mergeCells(1, 7, 1, 0);
			           ws.mergeCells(1, 8, 1, 0);
			           ws.mergeCells(1, 9, 1, 0);
			           ws.mergeCells(1, 10, 1, 0);
			           ws.mergeCells(1, 11, 1, 0);
			           ws.mergeCells(1, 12, 1, 0);
			           ws.mergeCells(1, 13, 1, 0);
			           ws.mergeCells(1, 14, 1, 0);
			           ws.mergeCells(1, 15, 1, 0);
			           ws.mergeCells(1, 16, 1, 0);
			           ws.mergeCells(1, 17, 1, 0);
			           
			          
			           ws.addCell(new Label(3, 1, bean.getSchAddress().toString(), wcf1)); 
			           ws.addCell(new Label(3, 2, bean.getSchTel().toString(), wcf1));
			           ws.addCell(new Label(3, 3, bean.getSchFax().toString(), wcf1));
			           ws.addCell(new Label(3, 4, bean.getSchFillerName().toString(), wcf1));
			           ws.addCell(new Label(3, 5, bean.getSchFillerTel().toString(), wcf1));
			           ws.addCell(new Label(3, 6, bean.getSchFillerEmail().toString(), wcf1)); 
			           ws.addCell(new Label(3, 7, bean.getSchName().toString(), wcf1));
			           ws.addCell(new Label(3, 8, bean.getSchID().toString(), wcf1));
			           ws.addCell(new Label(3, 9, bean.getSchEnName().toString(), wcf1));
			           ws.addCell(new Label(3, 10, bean.getSchType().toString(), wcf1));
			           ws.addCell(new Label(3, 11, bean.getSchQuality().toString(), wcf1));
			           ws.addCell(new Label(3, 12, bean.getSchBuilder().toString(), wcf1));
			           ws.addCell(new Label(3, 13, bean.getMajDept().toString(), wcf1));
			           ws.addCell(new Label(3, 14, bean.getSchUrl().toString(), wcf1));
			           ws.addCell(new Label(3, 15, bean.getAdmissonBatch().toString(), wcf1));
			           ws.addCell(new Label(3, 16, TimeUtil.changeFormat5(bean.getSch_BeginTime()), wcf1));
			           ws.addCell(new Label(3, 17, bean.getMediaUrl().toString(), wcf1));
			           ws.addCell(new Label(3, 18, bean.getYaohuSchAdd().toString(), wcf1));
			           ws.addCell(new Label(3, 19, bean.getPengHuSchAdd().toString(), wcf1));
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

	public T11Bean getT11Bean() {
		return t11Bean;
	}

	public void setT11Bean(T11Bean t11Bean) {
		this.t11Bean = t11Bean;
	}
	public String getSelectYear() {
		return selectYear;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		this.Year = year;
	}
	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
	
	public static void main(String args[]){
		String match = "[\\d]+" ;
		System.out.println("23gfhf4".matches(match)) ;
	}
}
