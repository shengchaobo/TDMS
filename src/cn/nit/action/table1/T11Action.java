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
import cn.nit.bean.table5.T54_Bean;
import cn.nit.dao.table1.T11DAO;
import cn.nit.excel.imports.table1.T11Excel;

import cn.nit.pojo.table1.T11POJO;
import cn.nit.service.table1.T11Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.JsonUtil;
import cn.nit.util.TimeUtil;
import cn.nit.util.ToBeanUtil;

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
	
	//save的字段
	private String fields;
	

	public String getFields() {
		return fields;
	}


	public void setFields(String fields) {
		this.fields = fields;
	}
	
	/**  前台获数据 */
	private String data ;


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	//查询出所有
	public void loadInfo() throws Exception{
		System.out.println("nnnnnnnn");
		
		HttpServletResponse response = ServletActionContext.getResponse() ;		
		
		T11POJO pojo = t11Ser.loadData(this.getSelectYear()) ;
		
		//private JSONObject jsonObj;
		pojo.setTime(null);
		String json = JsonUtil.beanToJson(pojo);
		
		PrintWriter out = null ;

		if(pojo == null){
			System.out.println("no data");
			response.setContentType("text/html;charset=UTF-8") ;
			out = response.getWriter() ;
			out.println( "<script language='javascript'>window.alert('无该年数据');</script>" ); 
		}else{
			System.out.println("have data");
			try {				
//				System.out.println(json) ;
				response.setContentType("application/json;charset=UTF-8") ;
				out = response.getWriter() ;
				out.print(json) ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(out != null){
					out.flush() ;
					out.close() ;
				}
			}
		}
	}
	

	//保存
	public void save(){
		
//		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String tempData = this.getData();
		//System.out.println(tempData);
				
		T11Bean bean  = ToBeanUtil.toBean(tempData, T11Bean.class);
										
		boolean flag = t11Ser.save(bean,this.getSelectYear(),this.getFields());
		PrintWriter out = null ;
		
		try{
			response.setContentType("application/json; charset=UTF-8") ;
			out = response.getWriter() ;
			if(flag){
				out.print("{\"mesg\":\"success\"}") ;
			}else{
				out.print("{\"mesg\":\"fail\"}") ;
			}
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"保存失败!!!\"}") ;
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
		
		T11POJO pojo = t11Ser.auditingData(year) ;
		String json = JsonUtil.beanToJson(pojo);
		
		PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			out.print(json) ;
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
			           wcf.setAlignment(jxl.write.Alignment.LEFT);
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
			             
			           ws.addCell(new Label(0, 2, "联系方式", wcf)); 
			           ws.addCell(new Label(0, 8, "学校概况", wcf)); 
			           ws.addCell(new Label(0, 19, "校区地址", wcf));  
			           ws.addCell(new Label(1, 2, "1.学校地址", wcf)); 
			           ws.addCell(new Label(1, 3, "2.学校办公电话号码", wcf)); 
			           ws.addCell(new Label(1, 4, "3.学校办公传真号码", wcf)); 
			           ws.addCell(new Label(1, 5, "4.学校填报负责人", wcf)); 
			           ws.addCell(new Label(1, 8, "5.学校名称", wcf)); 
			           ws.addCell(new Label(1, 9, "6.代码", wcf)); 
			           ws.addCell(new Label(1, 10, "7.英文名称", wcf)); 
			           ws.addCell(new Label(1, 11, "8.办学类型", wcf)); 
			           ws.addCell(new Label(1, 12, "9.学校性质", wcf)); 
			           ws.addCell(new Label(1, 13, "10.举办者", wcf)); 
			           ws.addCell(new Label(1, 14, "11.主管部门", wcf)); 
			           ws.addCell(new Label(1, 15, "12.学校网址", wcf)); 
			           ws.addCell(new Label(1, 16, "13.招生批次", wcf)); 
			           ws.addCell(new Label(1, 17, "14.开办本科教育年份", wcf));
			           ws.addCell(new Label(1, 18, "15.多媒体反映", wcf)); 
			           ws.addCell(new Label(1, 19, "16.校区名称", wcf));
			           ws.addCell(new Label(2,5,"姓名",wcf));
			           ws.addCell(new Label(2,6,"联系电话",wcf));
			           ws.addCell(new Label(2,7,"联系电子邮箱",wcf));
			           ws.addCell(new Label(2,19,"瑶湖校区",wcf));
			           ws.addCell(new Label(2,20,"彭桥校区",wcf));
			           
			           ws.mergeCells(0, 2, 0, 7);
			           ws.mergeCells(0, 8, 0, 18);
			           ws.mergeCells(0, 19, 0, 20);
			           ws.mergeCells(1, 2, 2, 2);
			           ws.mergeCells(1, 3, 2, 3);
			           ws.mergeCells(1, 4, 2, 4);
			           ws.mergeCells(1, 5, 1, 7);
			           ws.mergeCells(1, 19, 1, 20);
			           ws.mergeCells(1, 8, 2, 8);
			           ws.mergeCells(1, 9, 2, 9);
			           ws.mergeCells(1, 10, 2, 10);
			           ws.mergeCells(1, 11, 2, 11);
			           ws.mergeCells(1, 12, 2, 12);
			           ws.mergeCells(1, 13, 2, 13);
			           ws.mergeCells(1, 14, 2, 14);
			           ws.mergeCells(1, 15, 2, 15);
			           ws.mergeCells(1, 16, 2, 16);
			           ws.mergeCells(1, 17, 2, 17);
			           ws.mergeCells(1, 18, 2, 18);
			           
			           
			          
			           ws.addCell(new Label(3, 2, bean.getSchAddress().toString(), wcf1)); 
			           ws.addCell(new Label(3, 3, bean.getSchTel().toString(), wcf1));
			           ws.addCell(new Label(3, 4, bean.getSchFax().toString(), wcf1));
			           ws.addCell(new Label(3, 5, bean.getSchFillerName().toString(), wcf1));
			           ws.addCell(new Label(3, 6, bean.getSchFillerTel().toString(), wcf1));
			           ws.addCell(new Label(3, 7, bean.getSchFillerEmail().toString(), wcf1)); 
			           ws.addCell(new Label(3, 8, bean.getSchName().toString(), wcf1));
			           ws.addCell(new Label(3, 9, bean.getSchID().toString(), wcf1));
			           ws.addCell(new Label(3, 10, bean.getSchEnName().toString(), wcf1));
			           ws.addCell(new Label(3, 11, bean.getSchType().toString(), wcf1));
			           ws.addCell(new Label(3, 12, bean.getSchQuality().toString(), wcf1));
			           ws.addCell(new Label(3, 13, bean.getSchBuilder().toString(), wcf1));
			           ws.addCell(new Label(3, 14, bean.getMajDept().toString(), wcf1));
			           ws.addCell(new Label(3, 15, bean.getSchUrl().toString(), wcf1));
			           ws.addCell(new Label(3, 16, bean.getAdmissonBatch().toString(), wcf1));
			           ws.addCell(new Label(3, 17, TimeUtil.changeFormat5(bean.getSch_BeginTime()), wcf1));
			           ws.addCell(new Label(3, 18, bean.getMediaUrl().toString(), wcf1));
			           ws.addCell(new Label(3, 19, bean.getYaohuSchAdd().toString(), wcf1));
			           ws.addCell(new Label(3, 20, bean.getPengHuSchAdd().toString(), wcf1));
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
