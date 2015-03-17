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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import cn.nit.bean.table5.T513_Bean;
import cn.nit.dao.table5.T513_DAO;
import cn.nit.excel.imports.table5.T513Excel;


import cn.nit.pojo.table5.S5102POJO;
import cn.nit.pojo.table5.T513POJO;
import cn.nit.service.table5.T513Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

public class T513Action {
	
	/**  表T513的数据库操作类  */
	private T513_DAO t513Dao = new T513_DAO() ;
	
	private T513Excel t513Excel=new T513Excel();

	/**  表513的Service类  */
	private T513Service t513Ser = new T513Service() ;
	
	/**  表513的Bean实体类  */
	private T513_Bean t513Bean = new T513_Bean() ;
	
	/**excel导出名字*/
	private String excelName; //
	

	/**导出选择年份*/
	private String selectYear;
	

	/**  待审核数据的查询的序列号  */
	private Integer seqNum ;

	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
//查询出所有
	
	public void auditingData() throws Exception{
		System.out.println("+++++++++++++++++++");
		HttpServletResponse response = ServletActionContext.getResponse() ;		
		System.out.println(this.getSelectYear());
		List<T513POJO> list=null;
		if(this.getSelectYear()!=null){
			list = t513Ser.getYearInfo(this.getSelectYear());
		}
//		System.out.println("AcTION:list的长度:"+list.size());
		
		//System.out.println(this.getSelectYear());
		//System.out.println(list.size());
		JSON json = JSONSerializer.toJSON(list) ;
		PrintWriter out = null ;
		//System.out.println(json.toString());
		try {
			//设置输出内容的格式为json
			response.setContentType("application/json; charset=UTF-8") ;
			out = response.getWriter() ;
			//设置数据的内容的编码格式
			String outPrint = URLDecoder.decode(json.toString(), "UTF-8") ;
			out.print(outPrint) ;
			out.flush() ;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}	
	
	/**  编辑数据  */
	public void edit(){
		t513Bean.setTime(TimeUtil.changeDateY(this.getSelectYear()));
		
		boolean flag = t513Ser.update(t513Bean) ;		
//		boolean flag0 = T285_Service.update(sumBean) ;
		
		PrintWriter out = null ;
	
		try{
			response.setContentType("text/html; charset=UTF-8") ;
			out = response.getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"修改成功!!!\"}") ;
			}else{
				out.print("{\"state\":true,data:\"修改失败!!!\"}") ;
			}
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"系统错误，请联系管理员!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	
	
	/**数据导出
	 * @throws IOException */
	public InputStream getInputStream() throws IOException{
		
		List<T513POJO>  list = t513Dao.totalList(this.getSelectYear());
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
				String sheetName=this.excelName;	
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
		           ws.addCell(new Label(1, 2, "分类", wcf)); 
		           ws.addCell(new Label(2, 2, "应评课程总门次数", wcf)); 
		           ws.addCell(new Label(3, 2, "已评课程门次数", wcf)); 
		           ws.addCell(new Label(4, 2, "覆盖比例（%）", wcf)); 
		           ws.addCell(new Label(5, 2, "优（90分及以上）", wcf)); 
		           ws.addCell(new Label(7, 2, "良好（89-75分）", wcf)); 
		           ws.addCell(new Label(9, 2, "中（74-60分）", wcf)); 
		           ws.addCell(new Label(11, 2, "差（60分以下）", wcf)); 
		           ws.mergeCells(0, 2, 0, 3);
		           ws.mergeCells(1, 2, 1, 3);
		           ws.mergeCells(2, 2, 2, 3);
		           ws.mergeCells(3, 2, 3, 3);
		           ws.mergeCells(4, 2, 4, 3);
		           ws.mergeCells(5, 2, 6, 2);
		           ws.mergeCells(7, 2, 8, 2);
		           ws.mergeCells(9, 2, 10, 2);
		           ws.mergeCells(11, 2, 12, 2);
		           
		           
		           ws.addCell(new Label(5, 3, "门次数", wcf)); 
		           ws.addCell(new Label(6, 3, "比例（%）", wcf)); 
		           ws.addCell(new Label(7, 3, "门次数", wcf));
		           ws.addCell(new Label(8, 3, "比例（%）", wcf)); 
		           ws.addCell(new Label(9, 3, "门次数", wcf));
		           ws.addCell(new Label(10, 3, "比例（%）", wcf)); 
		           ws.addCell(new Label(11, 3, "门次数", wcf));
		           ws.addCell(new Label(12, 3, "比例（%）", wcf)); 
		    
		           

		           
		           int n = list.size();
		           int j = 4;
		            int seq = 1;//序号
		           if(list!=null && list.size()>0){
		        	   
		        	   for(int i = 0 ; i<n;i++){
		        		       T513POJO  pojo = list.get(i);
			        		   ws.addCell(new Label(0, j, pojo.getItem(), wcf1)); 
//			       
			        		   ws.addCell(new Label(1, j, pojo.getCategory(), wcf1)); 
			        		   ws.addCell(new Label(2, j, ""+pojo.getShouldASCSNum(), wcf1)); 
			        		   ws.addCell(new Label(3, j, ""+pojo.getHavedASCSNum(), wcf1)); 
			        		   ws.addCell(new Label(4, j, ""+pojo.getCoverRatio(), wcf1)); 
			        		   ws.addCell(new Label(5, j, ""+pojo.getExcellentNum(), wcf1)); 
			        		   ws.addCell(new Label(6, j, ""+pojo.getExcellentRatio(), wcf1)); 
			        		   ws.addCell(new Label(7, j, ""+pojo.getGoodNum(), wcf1)); 
			        		   ws.addCell(new Label(8, j, ""+pojo.getGoodRatio(), wcf1)); 
			        		   ws.addCell(new Label(9, j, ""+pojo.getAvgNum(), wcf1)); 
			        		   ws.addCell(new Label(10, j, ""+pojo.getAvgRatio(), wcf1)); 
			        		   ws.addCell(new Label(11, j, ""+pojo.getPoorNum(), wcf1)); 
			        		   ws.addCell(new Label(12, j, ""+pojo.getPoorRatio(), wcf1)); 
			         
			        		   j++;
			        	   }
		        	   ws.mergeCells(0, 4, 0, 5);
		        	   ws.mergeCells(0, 6, 0, 7);
		        	   ws.mergeCells(0, 8, 0, 9);
		        	   ws.mergeCells(0, 10, 0, 11);
			        	  
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
	
	public T513_Bean getT513Bean() {
		return t513Bean;
	}

	public void setT533Bean(T513_Bean t513Bean) {
		this.t513Bean = t513Bean;
	}

	
	public Integer getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(Integer seqNum) {
		this.seqNum = seqNum;
	}

	
	public String getSelectYear() {
		return selectYear;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
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


}
