package cn.nit.action.table6;


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

import cn.nit.bean.table6.T614_Bean;
import cn.nit.service.table6.T614_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.JsonUtil;
import cn.nit.util.TimeUtil;
import cn.nit.util.ToBeanUtil;


public class T614_Action {
	
	private T614_Service T614_services = new T614_Service();
	
	private T614_Bean T614_bean = new T614_Bean();	
	
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
		
		T614_Bean bean = T614_services.getYearInfo(this.getSelectYear()) ;
		
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
		
		
		T614_Bean bean  = ToBeanUtil.toBean(tempData, T614_Bean.class);
										
		boolean flag = T614_services.save(bean,this.getSelectYear(),this.getFields());
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
		T614_Bean bean = T614_services.getYearInfo(this.getSelectYear());
		
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
		           
		           ws.addCell(new Label(0, 2, "分类", wcf)); 
		           ws.addCell(new Label(1, 2, "上学年人数（个）", wcf)); 
		           ws.addCell(new Label(2, 2, "本学年人数（个）", wcf)); 
		           ws.addCell(new Label(0, 3, "6.普通预科生数", wcf)); 
		           ws.addCell(new Label(0, 4, "7.进修生数", wcf)); 
		           ws.addCell(new Label(0, 5, "8.成人脱产学生数", wcf)); 
		           ws.addCell(new Label(0, 6, "9.夜大（业余）学生数", wcf)); 
		           ws.addCell(new Label(0, 7, "10.函授学生数", wcf)); 
		           ws.addCell(new Label(0, 8, "11.网络学生数", wcf)); 
		           ws.addCell(new Label(0, 9, "12.自考学生数", wcf));

		           		           
		           ws.addCell(new Label(1, 3, bean.getPreppyLastYearNum().toString(), wcf1)); 
		           ws.addCell(new Label(1, 4, bean.getAdvStuLastYearNum().toString(), wcf1));
		           ws.addCell(new Label(1, 5, bean.getAdultLastYearNum().toString(), wcf1)); 
		           ws.addCell(new Label(1, 6, bean.getNightUniLastYearNum().toString(), wcf1)); 
		           ws.addCell(new Label(1, 7, bean.getCorrespdCoLastYearNum().toString(), wcf1)); 
		           ws.addCell(new Label(1, 8, bean.getNetStuLastYearNum().toString(), wcf1)); 
		           ws.addCell(new Label(1, 9, bean.getSelfStudyLastYearNum().toString(), wcf1));
		         
		           ws.addCell(new Label(2, 3, bean.getPreppyThisYearNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 4, bean.getAdvStuThisYearNum().toString(), wcf1));
		           ws.addCell(new Label(2, 5, bean.getAdultThisYearNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 6, bean.getNightUniThisYearNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 7, bean.getCorrespdThisYearNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 8, bean.getNetStuThisYearNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 9, bean.getSelfStudyThisYearNum().toString(), wcf1));
		           
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
	

	public T614_Bean getT614_bean() {
		return T614_bean;
	}

	public void setT614_bean(T614_Bean T614Bean) {
		T614_bean = T614Bean;
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