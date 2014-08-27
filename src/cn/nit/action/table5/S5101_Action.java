package cn.nit.action.table5;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
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
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table5.S5101_Bean;
import cn.nit.dao.table5.S5101_DAO;
import cn.nit.pojo.table5.S5101POJO;
import cn.nit.service.table5.S5101_Service;

public class S5101_Action {
	
	/**  表s5101的数据库操作类  */
	private S5101_DAO s5101Dao = new S5101_DAO() ;


	/**  表s5101的Service类  */
	private S5101_Service s5101Ser = new S5101_Service() ;
	
	/**  表S5101的Bean实体类  */
	private S5101_Bean s5101Bean = new S5101_Bean() ;
	
	/**excel导出名字*/
	private String excelName; //
	

	/**导出选择年份*/
	private String selectYear;


	/**
	 * 显示数据
	 * */
	public void loadInfo() throws Exception{
		
		
		HttpServletResponse response = ServletActionContext.getResponse() ;		
		
		List<S5101POJO> list =s5101Ser.loadInfo(this.getSelectYear());
//		List<S512_Bean> list=s512Ser.loadInfo(this.getSelectYear();
		System.out.println("year:"+this.getSelectYear());
		
		boolean flag = true;
		JSON json = null;
		
		if(list.size()==0){
			flag = false;
		}
		else{
			json = JSONSerializer.toJSON(list) ;
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
			}
			else{
				System.out.println("hello");
				response.setContentType("text/html; charset=UTF-8") ;
				out = response.getWriter() ;
				out.print("[{\"data\":\"该统计表数据不全，请填写相关数据后再进行统计!!!\"}]") ;
				System.out.println("统计数据不全");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
					if(out != null){
					out.flush() ;
				    out.close() ;
				}
			}
	}

	
	/**excel导出*/
	public InputStream getInputStream() throws Exception{
		
		System.out.println(this.getSelectYear());

		List<S5101POJO>  list = s5101Ser.getAll(this.getSelectYear());
//		System.out.println("S52 de "+list.size());
		
	    ByteArrayOutputStream fos = null;
	    
	
		if(list==null){
			PrintWriter out = null ;
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			out.print("后台传入的数据为空!!!") ;
			System.out.println("后台传入的数据为空");
		}else{
//			String sheetName = this.getExcelName();
				String sheetName="S-5-1-1本科课程库信息统计(按课程性质统计)";	
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
		           wcf.setAlignment(jxl.write.Alignment.CENTRE);
		           ws.setRowView(1, 500);
		     
		           //设置格式
				   WritableCellFormat wcf1 = new WritableCellFormat();
				   wcf1.setBorder(Border.ALL, BorderLineStyle.THIN,
						     jxl.format.Colour.BLACK); 
		           
		           ws.addCell(new Label(0, 0, sheetName, wcf)); 
		           ws.mergeCells(0, 0, 3, 0);
		             
		           ws.addCell(new Label(0, 2, "项目", wcf)); 
		           ws.addCell(new Label(1, 2, "理论课（含实践）", wcf)); 
		           ws.addCell(new Label(3, 2, "理论课（不含实践）", wcf)); 
		           ws.addCell(new Label(5, 2, "集中性实践环节", wcf)); 
		           ws.addCell(new Label(7, 2, "实验课", wcf)); 
		           
		           ws.addCell(new Label(1, 3, "门数（门）", wcf)); 
		           ws.addCell(new Label(2, 3, "比例（%）", wcf)); 
		           ws.addCell(new Label(3, 3, "门数（门）", wcf));
		           ws.addCell(new Label(4, 3, "比例（%）", wcf)); 
		           ws.addCell(new Label(5, 3, "门数（门）", wcf));
		           ws.addCell(new Label(6, 3, "比例（%）", wcf)); 
		           ws.addCell(new Label(7, 3, "门数（门）", wcf));
		           ws.addCell(new Label(8, 3, "比例（%）", wcf)); 
		           
		           ws.mergeCells(0, 2, 0, 3);
		           ws.mergeCells(1, 2, 2, 2);
		           ws.mergeCells(3, 2, 4, 2);
		           ws.mergeCells(5, 2, 6, 2);
		           ws.mergeCells(7, 2, 8, 2);
		           

		           
		           int n = list.size();
		           int j = 4;
		            int seq = 1;//序号
		           if(list!=null && list.size()>0){
		        	   
		        	   for(int i = 0 ; i<n;i++){
		        		   S5101POJO  pojo = list.get(i);
			        		   ws.addCell(new Label(0, j, pojo.getItem(), wcf1)); 
//			       
			        		   ws.addCell(new Label(1, j, ""+pojo.getTheoPraNum(), wcf1)); 
			        		   ws.addCell(new Label(2, j, ""+pojo.getTheoPraRatio()+"%", wcf1)); 
			        		   ws.addCell(new Label(3, j, ""+pojo.getInClassNum(), wcf1)); 
			        		   ws.addCell(new Label(4, j, ""+pojo.getInClassRatio()+"%", wcf1)); 
			        		   ws.addCell(new Label(5, j, ""+pojo.getPraNum(), wcf1)); 
			        		   ws.addCell(new Label(6, j, ""+pojo.getPraRatio()+"%", wcf1)); 
			        		   ws.addCell(new Label(7, j, ""+pojo.getExpNum(), wcf1)); 
			        		   ws.addCell(new Label(8, j, ""+pojo.getExpRatio()+"%", wcf1)); 
			         
			        		   j++;
			        	   }
			        	  
			           }else{
		        	   System.out.println("后台传入的数据为空");
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

		getResponse().setContentType("application/octet-stream;charset=UTF-8") ;
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
	
	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	public S5101_Bean getS5101Bean() {
		return s5101Bean;
	}


	public void setS5101Bean(S5101_Bean s5101Bean) {
		this.s5101Bean = s5101Bean;
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
	public String getSelectYear() {
		return selectYear;
	}
	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}
}
