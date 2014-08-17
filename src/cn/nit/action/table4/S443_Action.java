package cn.nit.action.table4;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;

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

import org.apache.struts2.ServletActionContext;


import cn.nit.bean.table4.S443_Bean;
import cn.nit.dao.table4.S443_Dao;
import cn.nit.service.table4.S443_Service;
import cn.nit.util.JsonUtil;

public class S443_Action {
	
	
	
	private S443_Service s443_Service=new S443_Service();
	
	private S443_Bean s443_Bean=new S443_Bean();
	
	private S443_Dao s443_Dao=new S443_Dao();
	
	/**  哪一年数据  */
	private String selectYear;

	/**  导出的excelName名 */
	private String excelName ;
	

	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	//查询出所有
	
	public void loadInfo() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse() ;		
		
		S443_Bean bean=s443_Service.getData(this.getSelectYear());
		
		
		boolean flag = false;
		String json = null;
		if(bean!=null){
			flag = s443_Service.save(bean,this.getSelectYear());
			bean.setTime(null);
			json = JsonUtil.beanToJson(bean);
			System.out.println(json) ;
		}		
		PrintWriter out = null ;
		if(flag == false ){
			System.out.println("统计数据保存失败");
			response.setContentType("text/html;charset=UTF-8") ;
			out = response.getWriter() ;
			out.println("{\"data\":\"统计数据保存失败\"}"); 
		}else{

		

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
		

	}
	

	public InputStream getInputStream() throws Exception{

		System.out.println(this.getSelectYear());
		S443_Bean bean = s443_Service.getYearInfo(this.getSelectYear());
		
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
		           ws.mergeCells(0, 0, 2, 0);
		           
		           ws.addCell(new Label(0, 2, "序号", wcf)); 
		           ws.addCell(new Label(1, 2, "类型", wcf)); 
		           ws.addCell(new Label(2, 2, "人次数", wcf)); 
		           ws.addCell(new Label(0, 3, "合计", wcf));  
		           ws.addCell(new Label(0, 4, "1", wcf)); 
		           ws.addCell(new Label(1, 4, "中国科学院院士", wcf)); 
		           ws.addCell(new Label(0, 5, "2", wcf)); 
		           ws.addCell(new Label(1, 5, "中国工程院院士", wcf)); 
		           ws.addCell(new Label(0, 6, "3", wcf)); 
		           ws.addCell(new Label(1, 6, "引进海外高层次人才“千人计划”入选者", wcf)); 
		           ws.addCell(new Label(0, 7, "4", wcf)); 
		           ws.addCell(new Label(1, 7, "长江学者特聘教授", wcf)); 
		           ws.addCell(new Label(0, 8, "5", wcf)); 
		           ws.addCell(new Label(1, 8, "国家杰出青年科学基金资助者", wcf)); 
		           ws.addCell(new Label(0, 9, "6", wcf)); 
		           ws.addCell(new Label(1, 9, "省部级突出贡献专家", wcf)); 
		           ws.addCell(new Label(0, 10, "7", wcf)); 
		           ws.addCell(new Label(1, 10, "新世纪优秀人才", wcf)); 
		           ws.addCell(new Label(0, 11, "8", wcf)); 
		           ws.addCell(new Label(1, 11, "教育部高校青年教师获奖者", wcf)); 
		           ws.addCell(new Label(0, 12, "9", wcf)); 
		           ws.addCell(new Label(1, 12, "省级高层次人才", wcf)); 
		           ws.addCell(new Label(0, 13, "10", wcf)); 
		           ws.addCell(new Label(1, 13, "青年“千人计划”入选者", wcf)); 


		           
		           ws.addCell(new Label(2, 3, bean.getSumTalent()+"", wcf1)); 
		           ws.addCell(new Label(2, 4, bean.getScienceTalent()+"", wcf1)); 
		           ws.addCell(new Label(2, 5, bean.getEngneerTalent()+"", wcf1)); 
		           ws.addCell(new Label(2, 6, bean.getOverseasTalent()+"", wcf1)); 
		           ws.addCell(new Label(2, 7, bean.getYangtzeTalent()+"", wcf1)); 
		           ws.addCell(new Label(2, 8, bean.getYouthTalent()+"", wcf1)); 
		           ws.addCell(new Label(2, 9, bean.getExpertTalent()+"", wcf1)); 
		           ws.addCell(new Label(2, 10, bean.getExcellentTalent()+"", wcf1)); 
		           ws.addCell(new Label(2, 11, bean.getYouthTeaTalent()+"", wcf1)); 
		           ws.addCell(new Label(2, 12, bean.getHighLevelTalent()+"", wcf1)); 
		           ws.addCell(new Label(2, 13, bean.getYouthOverseas()+"", wcf1)); 
 		      
		           ws.mergeCells(0, 3, 1, 3);

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
		System.out.println("excelName=============" + excelName) ;
		return "success" ;
	}

	public String getSelectYear() {
		return selectYear;
	}

	public void setSelectYear(String selectYear) {
		this.selectYear = selectYear;
	}

	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}



	public S443_Service getS443_Service() {
		return s443_Service;
	}


	public void setS443_Service(S443_Service s443Service) {
		s443_Service = s443Service;
	}


	public S443_Bean getS443_Bean() {
		return s443_Bean;
	}


	public void setS443_Bean(S443_Bean s443Bean) {
		s443_Bean = s443Bean;
	}


	public S443_Dao getS443_Dao() {
		return s443_Dao;
	}


	public void setS443_Dao(S443_Dao s443Dao) {
		s443_Dao = s443Dao;
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
