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
import cn.nit.bean.table6.T66_Bean;
import cn.nit.constants.Constants;
import cn.nit.service.table6.T66_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.JsonUtil;
import cn.nit.util.TimeUtil;
import cn.nit.util.ToBeanUtil;


public class T66_Action {
	
	private T66_Service T66_services = new T66_Service();
	
	private T66_Bean T66_bean = new T66_Bean();	
	
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
		
		T66_Bean bean = T66_services.getYearInfo(this.getSelectYear()) ;
		
		String json = null;
		boolean flag = false;
		if(bean != null){
			bean.setTime(null);
			json = JsonUtil.beanToJson(bean);
			flag = true;
		}
		
		
		PrintWriter out = null ;
		
		
		
		if(flag == false){
//			System.out.print("无该年数据!!!");
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

	/**  修改某条数据的审核状态  */
	public void updateCheck(){
		
		HttpServletResponse response = ServletActionContext.getResponse();
	
		boolean flag = T66_services.updateCheck(this.getSelectYear(),this.getCheckNum());
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
	
	//保存
	public void save(){
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++") ;
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String tempData = this.getData();
		//System.out.println(tempData);
		
		
		T66_Bean bean  = ToBeanUtil.toBean(tempData, T66_Bean.class);
										
		boolean flag = T66_services.save(bean,this.getSelectYear(),this.getFields());
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
		
		UserinfoBean userBean = (UserinfoBean) request.getSession().getAttribute("userinfo") ;
		T66_Bean bean = null;
		String sheetName = null;
		
		if("111".equals(userBean.getRoleID())){
			String year = (String)request.getSession().getAttribute("allYear") ;
			bean = T66_services.getYearInfo(year,Constants.PASS_CHECK);
			sheetName = "表6-6 学生社团（团委）";
		}else{
			bean = T66_services.getYearInfo(this.getSelectYear(),Constants.PASS_CHECK);
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
		           
		           ws.addCell(new Label(0, 0, sheetName, wcf)); 
		           ws.mergeCells(0, 0, 1, 0);
		           
		           ws.addCell(new Label(0, 2, "项目", wcf)); 
		           ws.addCell(new Label(2, 2, "内容", wcf)); 
		           ws.addCell(new Label(0, 3, "1.学生社团（个）", wcf)); 
		           ws.addCell(new Label(0, 9, "2.参与人次数（人次）", wcf)); 
		           ws.addCell(new Label(1, 3, "总数", wcf));
		           ws.addCell(new Label(1, 4, "其中：科技类", wcf));
		           ws.addCell(new Label(1, 5, "人文社会类", wcf));
		           ws.addCell(new Label(1, 6, "体育类", wcf));
		           ws.addCell(new Label(1, 7, "	文艺类", wcf));
		           ws.addCell(new Label(1, 8, " 其他", wcf));
		           ws.addCell(new Label(1, 9, " 总数", wcf));
		           ws.addCell(new Label(1, 10, "其中：科技类", wcf));
		           ws.addCell(new Label(1, 11, "人文社会类", wcf));
		           ws.addCell(new Label(1, 12, "体育类", wcf));
		           ws.addCell(new Label(1, 13, "文艺类", wcf));
		           ws.addCell(new Label(1, 14, "其他", wcf));  
		           ws.mergeCells(0, 2, 1, 2);
		           ws.mergeCells(0, 3, 0, 8);
		           ws.mergeCells(0, 9, 0, 14);
		           	if(bean!=null)	{           
		           ws.addCell(new Label(2, 3, bean.getStuClubSum().toString(), wcf1));
		           ws.addCell(new Label(2, 4,bean.getStuClubSciNum().toString(), wcf1));
		           ws.addCell(new Label(2, 5,bean.getStuClubHumanNum().toString(), wcf1));
		           ws.addCell(new Label(2, 6, bean.getStuClubSportNum().toString(), wcf1));
		           ws.addCell(new Label(2, 7, bean.getStuClubArtNum().toString(), wcf1));
		           ws.addCell(new Label(2, 8,bean.getOtherStuClub().toString(), wcf1));
		           ws.addCell(new Label(2, 9, bean.getJoinStuSum().toString(), wcf1));
		           ws.addCell(new Label(2, 10, bean.getJoinClubSciNum().toString(), wcf1));
		           ws.addCell(new Label(2, 11, bean.getJoinClubHumanNum().toString(), wcf1));
		           ws.addCell(new Label(2, 12, bean.getJoinClubSportNum().toString(), wcf1));
		           ws.addCell(new Label(2, 13, bean.getJoinClubArtNum().toString(), wcf1));
		           ws.addCell(new Label(2, 14, bean.getJoinOtherClub().toString(), wcf1));  
		             
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
	

	public T66_Bean getT66_bean() {
		return T66_bean;
	}

	public void setT66_bean(T66_Bean T66Bean) {
		T66_bean = T66Bean;
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