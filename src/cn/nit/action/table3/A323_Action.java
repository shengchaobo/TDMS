package cn.nit.action.table3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

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
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table3.A323_Bean;
import cn.nit.dao.table3.A323_DAO;
import cn.nit.service.table3.A323_Service;

public class A323_Action {
	
	
	
	private A323_Service a323_Service=new A323_Service();
	
	private A323_Bean a323_Bean=new A323_Bean();
	
//	private A323_DAO a323_Dao=new A323_DAO();
	
	/**  哪一年数据  */
	private String selectYear;

	/**  导出的excelName名 */
	private String excelName ;
	

	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	//查询出所有
	
	public void loadInfo() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse() ;		
		
		List<A323_Bean> list = a323_Service.getYearInfo(this.getSelectYear());		
		boolean flag = true;
		JSON json = null;
		if(list.size()==0){
			flag = false;
		}else{
			 json = JSONSerializer.toJSON(list) ;
			 System.out.println(json.toString());
		}		
		PrintWriter out = null ;		
		try {
			
			if(flag){
				//设置输出内容的格式为json
				response.setContentType("application/json; charset=UTF-8") ;				
				out = response.getWriter() ;
				//设置数据的内容的编码格式
				String outPrint = URLDecoder.decode(json.toString(), "UTF-8") ;
				out.print(outPrint) ;
			}else{
				response.setContentType("text/html; charset=UTF-8") ;
				out = response.getWriter() ;
				out.print("[{\"data\":\"该统计表数据不全，请填写相关数据后再进行统计!!!\"}]") ;
				System.out.println("统计数据不全");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.flush() ;
				out.close() ;
			}
		}
	}
	
	
	public InputStream getInputStream() throws IOException{
		

		System.out.println(this.getSelectYear());
		List<A323_Bean> list = a323_Service.totalList(this.getSelectYear());
		
	    ByteArrayOutputStream fos = null;
		
		if(list.isEmpty()){
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
		           ws.mergeCells(0, 0, 3, 0);
		           
		           ws.addCell(new Label(0, 2, "序号", wcf)); 
		           ws.addCell(new Label(1, 2, "专业名称", wcf)); 
		           ws.addCell(new Label(2, 2, "专业代码", wcf)); 
		           ws.addCell(new Label(3, 2, "1.各类课程学时结构（%）", wcf)); 
		           ws.addCell(new Label(8, 2, "2.各类课程学分结构（%）", wcf)); 
		           ws.addCell(new Label(3, 3, "必修课学时", wcf));
		           ws.addCell(new Label(4, 3, "选修课学时", wcf)); 
		           ws.addCell(new Label(5, 3, "理论教学学时", wcf)); 
		           ws.addCell(new Label(6, 3, "实验教学学时", wcf)); 
		           ws.addCell(new Label(7, 3, "集中实践教学环节学时", wcf)); 
		           ws.addCell(new Label(8, 3, "必修课学分", wcf)); 
		           ws.addCell(new Label(9, 3, "选修课学分", wcf)); 
		           ws.addCell(new Label(10, 3, "理论教学学分", wcf)); 
		           ws.addCell(new Label(11, 3, "实验教学学分", wcf)); 
		           ws.addCell(new Label(12, 3, "集中实践教学环节学分", wcf)); 
		           ws.addCell(new Label(13, 3, "课外科技活动学分", wcf));	
		           ws.mergeCells(0, 2, 0, 3);
		           ws.mergeCells(1, 2, 1, 3);
		           ws.mergeCells(2, 2, 2, 3);
		           ws.mergeCells(3, 2, 7, 2);
		           ws.mergeCells(8, 2, 13, 2);
		           int i = 0,count = 1;
		           while(i<list.size()){
		        	   if(list.get(i).getUnitID().equals("")){
		        		   count = 1;
			        	   ws.addCell(new Label(0, 4+i,list.get(i).getTeaUnit(), wcf));
			        	   ws.addCell(new Label(3, 4+i,list.get(i).getRequireHour()+"", wcf1));
			        	   ws.addCell(new Label(4, 4+i,list.get(i).getOptionHour()+"", wcf1));
			        	   ws.addCell(new Label(5, 4+i,list.get(i).getInClassHour()+"", wcf1));
			        	   ws.addCell(new Label(6, 4+i,list.get(i).getExpHour()+"", wcf1));
			        	   ws.addCell(new Label(7, 4+i,list.get(i).getPraHour()+"", wcf1));
			        	   ws.addCell(new Label(8, 4+i,list.get(i).getRequireCredit()+"", wcf1));
			        	   ws.addCell(new Label(9, 4+i,list.get(i).getOptionCredit()+"", wcf1));
			        	   ws.addCell(new Label(10, 4+i,list.get(i).getInClassCredit()+"", wcf1));
			        	   ws.addCell(new Label(11, 4+i,list.get(i).getExpCredit()+"", wcf1));
			        	   ws.addCell(new Label(12, 4+i,list.get(i).getPraCredit()+"", wcf1));
			        	   ws.addCell(new Label(13, 4+i,list.get(i).getOutClassCredit()+"", wcf1));
				           ws.mergeCells(0, 4+i, 2, 4+i);
			        	   i++;
			        	   
		        	   }else{
			        	   ws.addCell(new Label(0, 4+i,count+"", wcf));
			        	   ws.addCell(new Label(1, 4+i,list.get(i).getTeaUnit(), wcf1));
			        	   ws.addCell(new Label(2, 4+i,list.get(i).getUnitID()+"", wcf1));
			        	   ws.addCell(new Label(3, 4+i,list.get(i).getRequireHour()+"", wcf1));
			        	   ws.addCell(new Label(4, 4+i,list.get(i).getOptionHour()+"", wcf1));
			        	   ws.addCell(new Label(5, 4+i,list.get(i).getInClassHour()+"", wcf1));
			        	   ws.addCell(new Label(6, 4+i,list.get(i).getExpHour()+"", wcf1));
			        	   ws.addCell(new Label(7, 4+i,list.get(i).getPraHour()+"", wcf1));
			        	   ws.addCell(new Label(8, 4+i,list.get(i).getRequireCredit()+"", wcf1));
			        	   ws.addCell(new Label(9, 4+i,list.get(i).getOptionCredit()+"", wcf1));
			        	   ws.addCell(new Label(10, 4+i,list.get(i).getInClassCredit()+"", wcf1));
			        	   ws.addCell(new Label(11, 4+i,list.get(i).getExpCredit()+"", wcf1));
			        	   ws.addCell(new Label(12, 4+i,list.get(i).getPraCredit()+"", wcf1));
			        	   ws.addCell(new Label(13, 4+i,list.get(i).getOutClassCredit()+"", wcf1));
			        	   count++;
			        	   i++;
		        	   }
		           }
		           	             

		          wwb.write();
		          wwb.close();

		        } catch (IOException e){
		        } catch (RowsExceededException e){
		        } catch (WriteException e){}
		        
		}
		return new ByteArrayInputStream(fos.toByteArray());
		
	}
	

	
	public String execute() throws Exception{
		request.setCharacterEncoding("UTF-8") ;
		System.out.println("excelName=============" + this.excelName) ;
		return "success" ;
	}

	public String getSelectYear() {
		return selectYear;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
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

	

	public A323_Service getA323_Service() {
		return a323_Service;
	}


	public void setA323_Service(A323_Service a323Service) {
		a323_Service = a323Service;
	}


	public A323_Bean getA323_Bean() {
		return a323_Bean;
	}


	public void setA323_Bean(A323_Bean a323Bean) {
		a323_Bean = a323Bean;
	}



	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}
