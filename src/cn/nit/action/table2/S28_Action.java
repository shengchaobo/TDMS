package cn.nit.action.table2;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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

import cn.nit.bean.table2.S28_Bean;
import cn.nit.bean.table2.T281_Bean;
import cn.nit.bean.table2.T282_Bean;
import cn.nit.bean.table2.T283_Bean;
import cn.nit.bean.table2.T284_Bean;
import cn.nit.bean.table2.T285_Bean;
import cn.nit.service.table2.S28_Service;
import cn.nit.service.table2.T281_Service;
import cn.nit.service.table2.T282_Service;
import cn.nit.service.table2.T283_Service;
import cn.nit.service.table2.T284_Service;
import cn.nit.service.table2.T285_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.JsonUtil;
import cn.nit.util.TimeUtil;
import cn.nit.util.ToBeanUtil;


public class S28_Action {
	
	private S28_Service S28_services = new S28_Service();
	private T281_Service T281_services = new T281_Service();
	private T282_Service T282_services = new T282_Service();
	private T283_Service T283_services = new T283_Service();
	private T284_Service T284_services = new T284_Service();
	private T285_Service T285_services = new T285_Service();
	//private T285_Service T285_services = new T285_Service();	暂不写
	
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
		S28_Bean bean = new S28_Bean();
				
		T281_Bean bean281 = T281_services.getYearInfo(this.getSelectYear());
		T282_Bean bean282 = T282_services.getYearInfo(this.getSelectYear());
		T283_Bean bean283 = T283_services.getYearInfo(this.getSelectYear());
		T284_Bean bean284 = T284_services.getYearInfo(this.getSelectYear());
		T285_Bean bean285 = T285_services.findSumBean("全校合计：", this.getSelectYear());
		
		//T285_Bean bean285 = T285_services.getYearInfo(this.getSelectYear());
		
		if((bean281 == null) || (bean282 == null) || (bean283 == null) || (bean284 == null) || (bean285 == null)){	
			flag0 = false;
			System.out.println("bean281-5empty");
		}else{
			bean.setFixedAsset(bean281.getSumFixedAsset()
					+bean282.getSumFixedAsset()
					+bean283.getSumFixedAsset()
					+bean284.getSumFixedAsset());
			bean.setPlantAsset(bean285.getSumEquAsset());
			bean.setNewAddAsset(bean285.getNewAddAsset());
		}
		

		
		//数据不为空相加
		boolean flag = false;
		String json = null;
		if(flag0 == true){
			flag = S28_services.save(bean,this.getSelectYear());
			bean.setTime(null);
			json = JsonUtil.beanToJson(bean);
			System.out.println(json) ;
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
		S28_Bean bean = S28_services.getYearInfo(this.getSelectYear());
		
	    ByteArrayOutputStream fos = null;
		
		if(bean==null){
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
		           ws.addCell(new Label(2, 2, "内容", wcf)); 
		           ws.addCell(new Label(0, 3, "1.固定资产总值（万元）", wcf)); 
		           ws.addCell(new Label(0, 4, "其中：教学、科研仪器设备资产", wcf));  
		           ws.addCell(new Label(1, 4, "总值", wcf)); 
		           ws.addCell(new Label(1, 5, "其中：当年新增值", wcf)); 
		           ws.mergeCells(0, 2, 1, 2);
		           ws.mergeCells(0, 3, 1, 3);
		           ws.mergeCells(0, 4, 0, 5);
		           		           
		           ws.addCell(new Label(2, 3, bean.getFixedAsset().toString(), wcf1)); 
		           ws.addCell(new Label(2, 4, bean.getPlantAsset().toString(), wcf1));  
		           ws.addCell(new Label(2, 5, bean.getNewAddAsset().toString(), wcf1));
		             

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
		System.out.println("excelName=============" + this.getExcelName()) ;
		return "success" ;
	}
	
	public String getExcelName() {
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