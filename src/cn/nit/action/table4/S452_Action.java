package cn.nit.action.table4;

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

import cn.nit.bean.table4.S452_Bean;
import cn.nit.dao.table4.S452_Dao;
import cn.nit.service.table4.S452_Service;


public class S452_Action {
	
	
	private S452_Service s452_Service=new S452_Service();
	
	private S452_Bean s452_Bean=new S452_Bean();
	
//	private S452_Dao s452_Dao=new S452_Dao();
	
	/**  哪一年数据  */
	private String selectYear;

	/**  导出的excelName名 */
	private String excelName ;
	

	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	//查询出所有
	
	public void loadInfo() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse() ;		
		
		List<S452_Bean> list = s452_Service.getYearInfo(this.getSelectYear());

		//System.out.println(this.getSelectYear());
		//System.out.println(list.size());
		
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
		List<S452_Bean> list = s452_Service.totalList(this.getSelectYear());
		
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
		           WritableSheet ws = wwb.createSheet(sheetName , 0);        // 创建一个工作表
		
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
		           
		           ws.addCell(new Label(0,2,"序号",wcf));
		           ws.addCell(new Label(1,2,"教学单位",wcf));
		           ws.addCell(new Label(2,2,"单位号",wcf));
		           ws.addCell(new Label(3,2,"1.培训类型",wcf));
		           ws.addCell(new Label(7,2,"2.培训时长",wcf));
		           ws.addCell(new Label(10,2,"3.培训地点",wcf));
		           ws.addCell(new Label(3,3,"到行业培训",wcf));
		           ws.addCell(new Label(4,3,"攻读博士学位",wcf));
		           ws.addCell(new Label(5,3,"攻读硕士学位",wcf));
		           ws.addCell(new Label(6,3,"其他",wcf));
		           ws.addCell(new Label(7,3,"一个月内",wcf));
		           ws.addCell(new Label(8,3,"一个月~三个月",wcf));
		           ws.addCell(new Label(9,3,"三个月以上",wcf));
		           ws.addCell(new Label(10,3,"境内",wcf));
		           ws.addCell(new Label(11,3,"境外",wcf));
		           
		     
		           ws.addCell(new Label(0,4,list.get(0).getTeaUnit(),wcf));
		           ws.addCell(new Label(3,4,list.get(0).getIndustryTrain()+"",wcf1));
		           ws.addCell(new Label(4,4,list.get(0).getDoctorTrain()+"",wcf1));
		           ws.addCell(new Label(5,4,list.get(0).getMasterTrain()+"",wcf1));
		           ws.addCell(new Label(6,4,list.get(0).getOtherTrain()+"",wcf1));
		           ws.addCell(new Label(7,4,list.get(0).getOneMonth()+"",wcf1));
		           ws.addCell(new Label(8,4,list.get(0).getOneToThreeMonth()+"",wcf1));
		           ws.addCell(new Label(9,4,list.get(0).getAboveThreeMonth()+"",wcf1));
		           ws.addCell(new Label(10,4,list.get(0).getInPlace()+"",wcf1));
		           ws.addCell(new Label(11,4,list.get(0).getOutPlace()+"",wcf1));
 
		           
		           for(int i=1;i<list.size();i++){
		        	   ws.addCell(new Label(0,4+i,i+"",wcf1));
			           ws.addCell(new Label(1,4+i,list.get(i).getTeaUnit(),wcf1));
			           ws.addCell(new Label(2,4+i,list.get(i).getUnitID(),wcf1));
			           ws.addCell(new Label(3,4+i,list.get(i).getIndustryTrain()+"",wcf1));
			           ws.addCell(new Label(4,4+i,list.get(i).getDoctorTrain()+"",wcf1));
			           ws.addCell(new Label(5,4+i,list.get(i).getMasterTrain()+"",wcf1));
			           ws.addCell(new Label(6,4+i,list.get(i).getOtherTrain()+"",wcf1));
			           ws.addCell(new Label(7,4+i,list.get(i).getOneMonth()+"",wcf1));
			           ws.addCell(new Label(8,4+i,list.get(i).getOneToThreeMonth()+"",wcf1));
			           ws.addCell(new Label(9,4+i,list.get(i).getAboveThreeMonth()+"",wcf1));
			           ws.addCell(new Label(10,4+i,list.get(i).getInPlace()+"",wcf1));
			           ws.addCell(new Label(11,4+i,list.get(i).getOutPlace()+"",wcf1));
		           }
		           



		           ws.mergeCells(0, 2, 0, 3);
		           ws.mergeCells(1, 2, 1, 3);
		           ws.mergeCells(2, 2, 2, 3);
		           ws.mergeCells(3, 2, 6, 2);
		           ws.mergeCells(7, 2, 9, 2);
		           ws.mergeCells(10, 2, 11, 2);
		           ws.mergeCells(0, 4, 2, 4);
		           

 
		             

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





	public S452_Service getS452_Service() {
		return s452_Service;
	}

	public void setS452_Service(S452_Service s452Service) {
		s452_Service = s452Service;
	}

	public S452_Bean getS452_Bean() {
		return s452_Bean;
	}

	public void setS452_Bean(S452_Bean s452Bean) {
		s452_Bean = s452Bean;
	}

//	public S452_Dao getS452_Dao() {
//		return s452_Dao;
//	}
//
//	public void setS452_Dao(S452_Dao s452Dao) {
//		s452_Dao = s452Dao;
//	}

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
