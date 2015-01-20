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

import cn.nit.bean.table2.T27_Bean;
import cn.nit.service.table2.T27_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.JsonUtil;
import cn.nit.util.TimeUtil;
import cn.nit.util.ToBeanUtil;


public class T27_Action {
	
	private T27_Service T27_services = new T27_Service();
	
	private T27_Bean T27_bean = new T27_Bean();	
	
	/**  哪一年数据  */
	private String selectYear; //删除的id
	
	/**  导出的excelName名 */
	private String excelName ;
	
	/**  前台获数据 */
	private String data ;
	
	//save的字段
	private String fields;
	
	/**  审核状态显示判别标志  */
	private int checkNum ;
	
	
	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	//查询出所有
	public void loadInfo() throws Exception{
		
		HttpServletResponse response = ServletActionContext.getResponse() ;		
		
		T27_Bean bean = T27_services.getYearInfo(this.getSelectYear()) ;
		
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
				//System.out.println(json) ;
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
		
		
		T27_Bean bean  = ToBeanUtil.toBean(tempData, T27_Bean.class);
										
		boolean flag = T27_services.save(bean,this.getSelectYear(),this.getFields());
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
	
	/**  修改某条数据的审核状态  */
	public void updateCheck(){
		
		HttpServletResponse response = ServletActionContext.getResponse();
	
		boolean flag = T27_services.updateCheck(this.getSelectYear(),this.getCheckNum());
		PrintWriter out = null ;
		
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"修改审核状态成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"修改审核状态失败!!!\"}") ;
			}
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"修改审核状态失败!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
		
	public InputStream getInputStream() throws Exception{

		System.out.println(this.getSelectYear());
		T27_Bean bean = T27_services.getYearInfo(this.getSelectYear());
		
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
		           ws.addCell(new Label(2, 2, "内容", wcf)); 
		           ws.addCell(new Label(0, 3, "1.校园网主干带宽（Mbps）", wcf)); 
		           ws.addCell(new Label(0, 4, "2.校园网出口带宽（Mbps）", wcf)); 
		           ws.addCell(new Label(0, 5, "3.网络接入信息点数量（个)", wcf)); 
		           ws.addCell(new Label(0, 6, "4.通达情况", wcf));  
		           ws.addCell(new Label(0, 9, "5.网络存储空间", wcf)); 
		           ws.addCell(new Label(0, 12, "6.网络教学平台", wcf));
		           ws.addCell(new Label(0, 13, "7.教学管理信息系统", wcf));
		           ws.addCell(new Label(1, 6, "教室数（间）", wcf)); 
		           ws.addCell(new Label(1, 7, "学生宿舍数（间）", wcf)); 
		           ws.addCell(new Label(1, 8, "教学、科研与管理办公室数（间）", wcf)); 
		           ws.addCell(new Label(1, 9, "总量(TB)", wcf)); 
		           ws.addCell(new Label(1, 10, "教职工平均存储空间（MB）", wcf)); 
		           ws.addCell(new Label(1, 11, "学生平均存储空间（MB）", wcf)); 
		           ws.addCell(new Label(1, 12, "链接地址", wcf)); 
		           ws.addCell(new Label(1, 13, "链接地址", wcf)); 

		           ws.mergeCells(0, 2, 1, 2);
		           ws.mergeCells(0, 3, 1, 3);
		           ws.mergeCells(0, 4, 1, 4);
		           ws.mergeCells(0, 5, 1, 5);
		           ws.mergeCells(0, 6, 0, 8);
		           ws.mergeCells(0, 9, 0, 11);
		           		           
		           ws.addCell(new Label(2, 3, bean.getMainBw().toString(), wcf1)); 
		           ws.addCell(new Label(2, 4, bean.getExitBw().toString(), wcf1));  
		           ws.addCell(new Label(2, 5, bean.getAccessPoinum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 6, bean.getClassrmNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 7, bean.getDormiNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 8, bean.getOfficeNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 9, bean.getSumMemSpace().toString(), wcf1)); 
		           ws.addCell(new Label(2, 10, bean.getAvgTeaMemSpace().toString(), wcf1)); 
		           ws.addCell(new Label(2, 11, bean.getAvgStuMemSpace().toString(), wcf1)); 
		           ws.addCell(new Label(2, 12, bean.getWebTeahingUrl().toString(), wcf1)); 
		           ws.addCell(new Label(2, 12, bean.getTeaManageUrl().toString(), wcf1)); 
		             

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
	

	public T27_Bean getT27_bean() {
		return T27_bean;
	}

	public void setT27_bean(T27_Bean T27Bean) {
		T27_bean = T27Bean;
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



	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}



	public int getCheckNum() {
		return checkNum;
	}
}