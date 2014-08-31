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

import cn.nit.bean.table2.T293_Bean;
import cn.nit.service.table2.T293_Service;
import cn.nit.service.table2.T294_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.JsonUtil;
import cn.nit.util.TimeUtil;
import cn.nit.util.ToBeanUtil;


public class T293_Action {
	
	private T293_Service T293_services = new T293_Service();
	
	private T294_Service T294_services = new T294_Service();
	
	private T293_Bean T293_bean = new T293_Bean();	
	
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
		
		double donaMoney = T294_services.getYearSumDona(this.getSelectYear());
		
		
		T293_Bean bean = T293_services.getYearInfo(this.getSelectYear()) ;	

		String json = null;
		boolean flag = false;
		if(bean != null){
			bean.setSumIncome(bean.getSumIncome()+(donaMoney-bean.getDonation()));
			bean.setSumOtherIncome(bean.getSumOtherIncome()+(donaMoney-bean.getDonation()));
			bean.setDonation(donaMoney);			
			//更新捐赠收入
			boolean flag1 = T293_services.update(bean,this.getSelectYear());
			if(flag1==false){
				System.out.println("更新捐赠总收入失败");
			}
			
			//将time设为null，是方便转为前台所需要的json格式
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
		
		
		T293_Bean bean  = ToBeanUtil.toBean(tempData, T293_Bean.class);
										
		boolean flag = T293_services.save(bean,this.getSelectYear(),this.getFields());
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
		T293_Bean bean = T293_services.getYearInfo(this.getSelectYear());
		
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
		           
		           ws.addCell(new Label(0, 2, "1.学校教育经费收入总额（万元）", wcf)); 
		           ws.addCell(new Label(0, 3, "2.其中本科教育事业收入（万元）", wcf)); 
		           ws.addCell(new Label(1, 3, "收入总计", wcf)); 
		           ws.addCell(new Label(1, 4, "本科生生均拨款总额", wcf)); 
		           ws.addCell(new Label(2, 4, "总数", wcf));
		           ws.addCell(new Label(2, 5, "国家", wcf));
		           ws.addCell(new Label(2, 6, "地方", wcf));
		           ws.addCell(new Label(1, 7, "本科生学费收入", wcf)); 
		           ws.addCell(new Label(1, 8, "本科教改专项拨款", wcf)); 
		           ws.addCell(new Label(0, 9, "3.其他教育事业收入（万元）", wcf));
		           ws.addCell(new Label(1, 9, "收入总计", wcf)); 
		           ws.addCell(new Label(1, 10, "经常性预算内教育事业费拨款", wcf)); 		           
		           ws.addCell(new Label(2, 10, "总数", wcf));
		           ws.addCell(new Label(2, 11, "国家", wcf));
		           ws.addCell(new Label(2, 12, "地方", wcf));
		           ws.addCell(new Label(1, 13, "学费收入", wcf)); 
		           ws.addCell(new Label(2, 13, "总数", wcf));
		           ws.addCell(new Label(2, 14, "各类研究生", wcf));
		           ws.addCell(new Label(2, 15, "高职高专", wcf));
		           ws.addCell(new Label(2, 16, "网络与继续教育", wcf));
		           ws.addCell(new Label(1, 17, "社会捐赠收入", wcf)); 
		           ws.addCell(new Label(1, 18, "其他教育事业收入", wcf)); 
		           
		           ws.mergeCells(0, 2, 2, 2);
		           ws.mergeCells(0, 3, 0, 8);
		           ws.mergeCells(1, 4, 1, 6);
		           ws.mergeCells(1, 7, 2, 7);
		           ws.mergeCells(1, 8, 2, 8);
		           ws.mergeCells(0, 9, 0, 18);
		           ws.mergeCells(1, 9, 2, 9);
		           ws.mergeCells(1, 10, 1, 12);
		           ws.mergeCells(1, 13, 1, 16);
		           ws.mergeCells(1, 17, 2, 17);
		           ws.mergeCells(1, 18, 2, 18);
		           
		           		
		           ws.addCell(new Label(3, 2, bean.getSumIncome().toString(), wcf1)); 
		           ws.addCell(new Label(3, 3, bean.getSumUndergraIncome().toString(), wcf1)); 
		           ws.addCell(new Label(3, 4, bean.getAllocateFund().toString(), wcf1));  
		           ws.addCell(new Label(3, 5, bean.getNationFund().toString(), wcf1)); 
		           ws.addCell(new Label(3, 6, bean.getLocalFund().toString(), wcf1)); 
		           ws.addCell(new Label(3, 7, bean.getUndergraTuition().toString(), wcf1)); 
		           ws.addCell(new Label(3, 8, bean.getEduReformFund().toString(), wcf1));  
		           ws.addCell(new Label(3, 9, bean.getSumOtherIncome().toString(), wcf1));
		           ws.addCell(new Label(3, 10, bean.getOtherAllocateFund().toString(), wcf1));
		           ws.addCell(new Label(3, 11, bean.getOtherNationFund().toString(), wcf1));
		           ws.addCell(new Label(3, 12, bean.getOtherLocalFund().toString(), wcf1));
		           ws.addCell(new Label(3, 13, bean.getOtherTuition().toString(), wcf1)); 
		           ws.addCell(new Label(3, 14, bean.getGraTuition().toString(), wcf1)); 
		           ws.addCell(new Label(3, 15, bean.getJuniorTuition().toString(), wcf1)); 
		           ws.addCell(new Label(3, 16, bean.getNetTeaTuition().toString(), wcf1)); 
		           ws.addCell(new Label(3, 17, bean.getDonation().toString(), wcf1)); 
		           ws.addCell(new Label(3, 18, bean.getOtherIncome().toString(), wcf1)); 

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
	

	public T293_Bean getT293_bean() {
		return T293_bean;
	}

	public void setT293_bean(T293_Bean T293Bean) {
		T293_bean = T293Bean;
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