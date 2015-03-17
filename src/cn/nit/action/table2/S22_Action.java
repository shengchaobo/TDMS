package cn.nit.action.table2;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;


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

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table1.S15_Bean;
import cn.nit.bean.table2.S22_Bean;
import cn.nit.bean.table2.T22_Bean;
import cn.nit.bean.table2.T231_Bean;
import cn.nit.service.table1.S15Service;
import cn.nit.service.table2.S22_Service;
import cn.nit.service.table2.T22_Service;
import cn.nit.service.table2.T231_Service;
import cn.nit.service.table2.T251_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.JsonUtil;
import cn.nit.util.TimeUtil;
import cn.nit.util.ToBeanUtil;


public class S22_Action {
	
	private S22_Service S22_services = new S22_Service();
	private T231_Service T231_services = new T231_Service();
	private T22_Service T22_services = new T22_Service();
	private T251_Service T251_services = new T251_Service();
	private S15Service S15_services = new S15Service();
	
	private S22_Bean S22_bean = new S22_Bean();	
	
	/**  哪一年数据  */
	private String selectYear; //删除的id
	
	/**  导出的excelName名 */
	private String excelName ;
	
	/**  前台获数据 */
	private String data ;
	
	//save的字段
	private String fields;
	
	
	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	//查询出所有
	public void loadInfo() throws Exception{
		
		HttpServletResponse response = ServletActionContext.getResponse() ;	
		
		boolean flag0 = true; 
		S22_Bean bean = new S22_Bean();
				
		T231_Bean bean231 = T231_services.getYearInfo(this.getSelectYear());
		if(bean231 == null){	
			flag0 = false;
			System.out.println("bean231empty");
		}else{
			bean.setClassrmArea(bean231.getClassrmArea());
		}
		
		T22_Bean bean22 = T22_services.getYearInfo(this.getSelectYear());
		if(bean22 == null){
			flag0 = false;
			System.out.println("bean22empty");
		}else{
			bean.setLibArea(bean22.getLibArea());
			bean.setPhyArea(bean22.getGymArea());
			bean.setHallArea(bean22.getHallArea());
			bean.setSumAdminArea(bean22.getAdmOfficeArea());
		}
		
		double trainArea = T251_services.getTrainArea(this.getSelectYear());
		bean.setLabArea(trainArea);
		System.out.println("trainArea:" + trainArea);
		
		S15_Bean bean15 = S15_services.loadData(this.getSelectYear()) ;
		if(bean15 == null){	
			flag0 = false;
			System.out.println("bean15empty");
		}else{
			bean.setResArea(bean15.getSumResArea());
		}
		
		//数据不为空相加
		boolean flag = false;
		String json = null;
		if(flag0 == true){
			bean.setSumTeaArea(bean.getClassrmArea()+bean.getLibArea()+bean.getLabArea()+bean.getPhyArea()+bean.getHallArea()+bean.getResArea());
			flag = S22_services.save(bean,this.getSelectYear());
			//转化为前台所需要json
			bean.setTime(null);
			json = JsonUtil.beanToJson(bean);
		}
				
		
		PrintWriter out = null ;

		if(flag0 == false){
			response.setContentType("text/html;charset=UTF-8") ;
			out = response.getWriter() ;
			out.print("{\"data\":\"该统计表数据不全，请填写相关数据后再进行统计!!!\"}") ;
			System.out.println("统计数据不全");
		}
		else if(flag == false ){
			System.out.println("统计数据保存失败");
			response.setContentType("text/html;charset=UTF-8") ;
			out = response.getWriter() ;
			out.println("{\"data\":\"统计数据保存失败\"}"); 
		}
		else{
			try {				
				System.out.println(json) ;
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
	
		
	public InputStream getInputStream() throws Exception{

		System.out.println(this.getSelectYear());
		S22_Bean bean = S22_services.getYearInfo(this.getSelectYear());
		
	    ByteArrayOutputStream fos = null;
		
		if(bean==null){
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
		           
		           ws.addCell(new Label(0, 2, "项目", wcf)); 
		           ws.addCell(new Label(1, 2, "内容", wcf)); 
		           ws.addCell(new Label(0, 3, "1.教学科研及辅助用房（平方米）", wcf)); 
		           ws.addCell(new Label(0, 4, "其中：教室", wcf));  
		           ws.addCell(new Label(0, 5, "图书馆", wcf)); 
		           ws.addCell(new Label(0, 6, "实验室、实习场所", wcf)); 
		           ws.addCell(new Label(0, 7, "专用科研用房", wcf)); 
		           ws.addCell(new Label(0, 8, "体育馆", wcf)); 
		           ws.addCell(new Label(0, 9, "会堂", wcf)); 
		           ws.addCell(new Label(0, 10, "2.行政用房（平方米）", wcf)); 
		           
		           		           
		           ws.addCell(new Label(1, 3, bean.getSumTeaArea().toString(), wcf1)); 
		           ws.addCell(new Label(1, 4, bean.getClassrmArea().toString(), wcf1));  
		           ws.addCell(new Label(1, 5, bean.getLibArea().toString(), wcf1)); 
		           ws.addCell(new Label(1, 6, bean.getLabArea().toString(), wcf1)); 
		           ws.addCell(new Label(1, 7, bean.getResArea().toString(), wcf1)); 
		           ws.addCell(new Label(1, 8, bean.getPhyArea().toString(), wcf1)); 
		           ws.addCell(new Label(1, 9, bean.getHallArea().toString(), wcf1)); 
		           ws.addCell(new Label(1, 10, bean.getSumAdminArea().toString(), wcf1)); 
		             

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
	

	public S22_Bean getS22_bean() {
		return S22_bean;
	}

	public void setS22_bean(S22_Bean S22Bean) {
		S22_bean = S22Bean;
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

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}

	public String getSelectYear() {
		return selectYear;
	}



	public void setData(String data) {
		this.data = data;
	}



	public String getData() {
		return data;
	}



	public void setFields(String fields) {
		this.fields = fields;
	}



	public String getFields() {
		return fields;
	}
}