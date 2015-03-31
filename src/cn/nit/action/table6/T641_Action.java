package cn.nit.action.table6;


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

import cn.nit.bean.UserinfoBean;
import cn.nit.bean.table2.T21_Bean;
import cn.nit.bean.table6.T641_Bean;
import cn.nit.constants.Constants;
import cn.nit.service.table6.T641_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.JsonUtil;
import cn.nit.util.TimeUtil;
import cn.nit.util.ToBeanUtil;


public class T641_Action {
	
	private T641_Service T641_services = new T641_Service();
	
	private T641_Bean T641_bean = new T641_Bean();	
	
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
		
		T641_Bean bean = T641_services.getYearInfo(this.getSelectYear()) ;
		
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
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String tempData = this.getData();
		//System.out.println(tempData);
		
		
		T641_Bean bean  = ToBeanUtil.toBean(tempData, T641_Bean.class);
										
		boolean flag = T641_services.save(bean,this.getSelectYear(),this.getFields());
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
	
		boolean flag = T641_services.updateCheck(this.getSelectYear(),this.getCheckNum());
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
		
		UserinfoBean userBean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		T641_Bean bean = null;
		String sheetName = null;
		
		if("111".equals(userBean.getRoleID())){
			String year = (String)request.getSession().getAttribute("allYear") ;
			bean = T641_services.getYearInfo(year,Constants.PASS_CHECK);
			sheetName = "表6-4-1本科生奖贷补（学工处）";
		}else{
			bean = T641_services.getYearInfo(this.getSelectYear(),Constants.PASS_CHECK);
			sheetName = this.excelName;
		}

						
		    ByteArrayOutputStream fos = null;
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
		           
		           ws.addCell(new Label(0, 0,sheetName, wcf)); 
		           ws.mergeCells(0, 0, 1, 0);
		           
		           ws.addCell(new Label(0, 2, "项目", wcf)); 
		           ws.addCell(new Label(1, 2, "资助金额（万元）", wcf)); 
		           ws.addCell(new Label(2, 2, "资助学生数（人次", wcf)); 
		           ws.addCell(new Label(0, 3, "总计", wcf)); 
		           ws.addCell(new Label(0, 4, "1.政府奖、助学金", wcf)); 
		           ws.addCell(new Label(0, 5, "2.社会奖、助学金", wcf));
		           ws.addCell(new Label(0, 6, "3.学校奖学金", wcf));
		           ws.addCell(new Label(0, 7, "4.国家助学贷款", wcf));
		           ws.addCell(new Label(0, 8, "5.勤工助学金", wcf));
		           ws.addCell(new Label(0, 9, "6.减免学费", wcf));
		           ws.addCell(new Label(0, 10, "7.临时困难补助", wcf));

		          if(bean!=null) {		           
		           ws.addCell(new Label(1, 3, bean.getSumAidFund().toString(), wcf1)); 
		           ws.addCell(new Label(1, 4, bean.getGovAidFund().toString(), wcf1)); 
		           ws.addCell(new Label(1, 5, bean.getSocialAidFund().toString(), wcf1));
		           ws.addCell(new Label(1, 6, bean.getSchAidFund().toString(), wcf1));
		           ws.addCell(new Label(1, 7, bean.getNationAidFund().toString(), wcf1));
		           ws.addCell(new Label(1, 8, bean.getWorkStudyFund().toString(), wcf1));
		           ws.addCell(new Label(1, 9, bean.getTuitionWaiberFund().toString(), wcf1));
		           ws.addCell(new Label(1, 10, bean.getTempFund().toString(), wcf1));
		           
		           ws.addCell(new Label(2, 3, bean.getSumAidNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 4, bean.getGovAidNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 5, bean.getSocialAidNum().toString(), wcf1));
		           ws.addCell(new Label(2, 6, bean.getSchAidNum().toString(), wcf1));
		           ws.addCell(new Label(2, 7, bean.getNationAidNum().toString(), wcf1));
		           ws.addCell(new Label(2, 8, bean.getWorkStudyNum().toString(), wcf1));
		           ws.addCell(new Label(2, 9, bean.getTuitionWaiberNum().toString(), wcf1));
		           ws.addCell(new Label(2, 10, bean.getTempNum().toString(), wcf1));
		          }

		          wwb.write();
		          wwb.close();

		        } catch (IOException e){
		        } catch (RowsExceededException e){
		        } catch (WriteException e){}
		        
		
		return new ByteArrayInputStream(fos.toByteArray());
	}
	
	public String execute() throws Exception{
		response.setContentType("text/html;charset=utf-8"); 
		System.out.println("excelName=============" + this.excelName) ;
		return "success" ;
	}
	

	public T641_Bean getT641_bean() {
		return T641_bean;
	}

	public void setT641_bean(T641_Bean T641Bean) {
		T641_bean = T641Bean;
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



	public int getCheckNum() {
		return checkNum;
	}



	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}



	public void setFields(String fields) {
		this.fields = fields;
	}



	public String getFields() {
		return fields;
	}
}