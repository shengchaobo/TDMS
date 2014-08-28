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

import cn.nit.bean.table2.T292_Bean;
import cn.nit.service.table2.T292_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.JsonUtil;
import cn.nit.util.TimeUtil;
import cn.nit.util.ToBeanUtil;


public class T292_Action {
	
	private T292_Service T292_services = new T292_Service();
	
	private T292_Bean T292_bean = new T292_Bean();	
	
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
		
		T292_Bean bean = T292_services.getYearInfo(this.getSelectYear()) ;
		
		String json = null;
		boolean flag = false;
		if(bean != null){
			bean.setTime(null);
			json = JsonUtil.beanToJson(bean);
			flag = true;
		}
		
		
		PrintWriter out = null ;
		
		
		
		if(flag == false){
			System.out.print("无该年数据!!!");
			response.setContentType("text/html;charset=UTF-8") ;
			out = response.getWriter() ;
			out.print("{\"data\":\"无该年数据!!!\"}");  
		}else{
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


	
	//保存
	public void save(){
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String tempData = this.getData();
		//System.out.println(tempData);
		
		
		T292_Bean bean  = ToBeanUtil.toBean(tempData, T292_Bean.class);
										
		boolean flag = T292_services.save(bean,this.getSelectYear(),this.getFields());
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
		
	public InputStream getInputStream() throws Exception{

		System.out.println(this.getSelectYear());
		T292_Bean bean = T292_services.getYearInfo(this.getSelectYear());
		
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
		           
		           ws.addCell(new Label(0, 2, "1.学校教育经费支出总额（万元）", wcf)); 
		           ws.addCell(new Label(0, 3, "2.其中本科教育经费支出（万元）", wcf)); 
		           ws.addCell(new Label(1, 3, "支出总计", wcf)); 
		           ws.addCell(new Label(1, 4, "教学日常运行支出", wcf));  
		           ws.addCell(new Label(1, 5, "教学改革支出", wcf)); 
		           ws.addCell(new Label(1, 6, "课程建设支出", wcf)); 
		           ws.addCell(new Label(1, 7, "专业建设支出", wcf)); 
		           ws.addCell(new Label(1, 8, "教材建设支出", wcf)); 
		           ws.addCell(new Label(1, 9, "实践教学支出", wcf)); 
		           ws.addCell(new Label(2, 9, "总数", wcf));
		           ws.addCell(new Label(2, 10, "其中：实验经费支出", wcf));
		           ws.addCell(new Label(2, 11, "其中：实习经费支出", wcf));
		           ws.addCell(new Label(2, 12, "其中：校外", wcf));
		           ws.addCell(new Label(1, 13, "学生活动经费支出", wcf)); 
		           ws.addCell(new Label(1, 14, "教师培训进修专项经费支出", wcf)); 
		           ws.addCell(new Label(1, 15, "其他教学专项", wcf)); 
		           
		           ws.mergeCells(0, 2, 2, 2);
		           ws.mergeCells(0, 3, 0, 15);
		           ws.mergeCells(1, 3, 2, 3);
		           ws.mergeCells(1, 4, 2, 4);
		           ws.mergeCells(1, 5, 2, 5);
		           ws.mergeCells(1, 6, 2, 6);
		           ws.mergeCells(1, 7, 2, 7);
		           ws.mergeCells(1, 8, 2, 8);
		           ws.mergeCells(1, 9, 1, 12);
		           ws.mergeCells(1, 13, 2, 13);
		           ws.mergeCells(1, 14, 2, 14);
		           ws.mergeCells(1, 15, 2, 15);
		           		
		           ws.addCell(new Label(3, 2, bean.getSchTeaExpTotal().toString(), wcf1)); 
		           ws.addCell(new Label(3, 3, bean.getUndergraTeaExpTotal().toString(), wcf1)); 
		           ws.addCell(new Label(3, 4, bean.getDayTeaExp().toString(), wcf1));  
		           ws.addCell(new Label(3, 5, bean.getTeaReformExp().toString(), wcf1)); 
		           ws.addCell(new Label(3, 6, bean.getMajorExp().toString(), wcf1)); 
		           ws.addCell(new Label(3, 7, bean.getMajorExp().toString(), wcf1)); 
		           ws.addCell(new Label(3, 8, bean.getTextbookExp().toString(), wcf1));  
		           ws.addCell(new Label(3, 9, bean.getPraTeaExpTotal().toString(), wcf1));
		           ws.addCell(new Label(3, 10, bean.getExpTeaExp().toString(), wcf1));
		           ws.addCell(new Label(3, 11, bean.getPraTeaExp().toString(), wcf1));
		           ws.addCell(new Label(3, 12, bean.getOutSchPraExp().toString(), wcf1));
		           ws.addCell(new Label(3, 13, bean.getStuActExp().toString(), wcf1)); 
		           ws.addCell(new Label(3, 14, bean.getTeaTrainExp().toString(), wcf1)); 
		           ws.addCell(new Label(3, 15, bean.getOtherTeaExp().toString(), wcf1)); 
		             

		          wwb.write();
		          wwb.close();

		        } catch (IOException e){
		        } catch (RowsExceededException e){
		        } catch (WriteException e){}
		        
		}
		return new ByteArrayInputStream(fos.toByteArray());
	}
	
	public String execute() throws Exception{
		response.setContentType("text/html;charset=utf-8"); 
		System.out.println("excelName=============" + this.excelName) ;
		return "success" ;
	}
	

	public T292_Bean getT292_bean() {
		return T292_bean;
	}

	public void setT292_bean(T292_Bean T292Bean) {
		T292_bean = T292Bean;
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