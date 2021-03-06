package cn.nit.action.table3;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

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

import cn.nit.bean.table3.S31_Bean;
import cn.nit.dao.table3.S31_DAO;




import cn.nit.pojo.table3.S31POJO;
import cn.nit.service.table3.S31_Service;
import cn.nit.util.JsonUtil;


public class S31_Action {
	
private S31_Service s31_Service = new S31_Service() ;
	
	private S31_Bean s31_Bean = new S31_Bean() ;
	
//	private S31_DAO  s31_DAO=new S31_DAO();

	
//	private S31Excel s31Excel = new S31Excel() ;
	

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
		
		S31_Bean bean = s31_Service.getYearInfo(this.getSelectYear()) ;
		
		//private JSONObject jsonObj;
		bean.setTime(null);
		String json = JsonUtil.beanToJson(bean);
		
		PrintWriter out = null ;

		if(bean.getDocStationOne()==-1||bean.getJuniorMajor()==-1||bean.getPostdocStation()==-1||bean.getSumMajor()==-1){
			response.setContentType("text/html;charset=UTF-8") ;
			out = response.getWriter() ;
			out.println( "{\"data\":\"该统计表数据不全，请填写相关数据后再进行统计。\"}"); 
		}else{
			try {	
				System.out.println(json) ;
				response.setContentType("application/json;charset=UTF-8") ;
				out = response.getWriter() ;
				out.print(json) ;
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(out != null){
					out.flush() ;
					out.close() ;
				}
			}
		}
	}

	
	
	
	
	public InputStream getInputStream() throws IOException{
		

		System.out.println(this.getSelectYear());
		S31_Bean bean = s31_Service.exportData(this.getSelectYear());
		
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
		           ws.addCell(new Label(0, 3, "1.博士后流动站（个）", wcf)); 
		           ws.addCell(new Label(0, 4, "2.博士学位授权一级学科点", wcf)); 
		           ws.addCell(new Label(0, 5, "3.博士学位授权二级学科点（不含一级覆盖）", wcf)); 
		           ws.addCell(new Label(0, 6, "4.硕士学位授权一级学科点", wcf)); 
		           ws.addCell(new Label(0, 7, "5.硕士学位授权二级学科点（不含一级覆盖）", wcf)); 
		           ws.addCell(new Label(0, 8, "6.本科专业（个）", wcf));  
		           ws.addCell(new Label(0, 10, "7.专科专业（各）", wcf)); 
		           ws.addCell(new Label(1, 8, "总数", wcf)); 
		           ws.addCell(new Label(1, 9, "其中：新专业", wcf)); 


		           ws.mergeCells(0, 2, 1, 2);
		           ws.mergeCells(0, 3, 1, 3);
		           ws.mergeCells(0, 4, 1, 4);
		           ws.mergeCells(0, 5, 1, 5);
		           ws.mergeCells(0, 6, 1, 6);
		           ws.mergeCells(0, 7, 1, 7);
		           ws.mergeCells(0, 10, 1, 10);
		           ws.mergeCells(0, 8, 0, 9);
		           
		           
		           ws.addCell(new Label(2, 3, ""+bean.getPostdocStation(), wcf1)); 
		           ws.addCell(new Label(2, 4, ""+bean.getDocStationOne(), wcf1));  
		           ws.addCell(new Label(2, 5, ""+bean.getDocStationTwo(), wcf1)); 
		           ws.addCell(new Label(2, 6, ""+bean.getMasterStationOne(), wcf1)); 
		           ws.addCell(new Label(2, 7, ""+bean.getMasterStationTwo(), wcf1)); 
		           ws.addCell(new Label(2, 8, ""+bean.getSumMajor(), wcf1));
		           ws.addCell(new Label(2, 9, ""+bean.getNewMajor(), wcf1)); 
		           ws.addCell(new Label(2, 10, ""+bean.getJuniorMajor(), wcf1)); 
 
		             

		          wwb.write();
		          wwb.close();

		        } catch (IOException e){
		        } catch (RowsExceededException e){
		        } catch (WriteException e){}
		        
		}
		return new ByteArrayInputStream(fos.toByteArray());
		
	}


	public String execute() throws Exception{
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
	
	public S31_Service getS31_Service() {
		return s31_Service;
	}




	public void setS31_Service(S31_Service s31Service) {
		s31_Service = s31Service;
	}




	public S31_Bean getS31_Bean() {
		return s31_Bean;
	}




	public void setS31_Bean(S31_Bean s31Bean) {
		s31_Bean = s31Bean;
	}




	public static void main(String args[]){
		String match = "[\\d]+" ;
		System.out.println("23gfhf4".matches(match)) ;
	}





	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}





	public String getSelectYear() {
		return selectYear;
	}





	public void setExcelName(String excelName) {
		this.excelName = excelName;
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
