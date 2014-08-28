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

import cn.nit.bean.table4.S46_Bean;
import cn.nit.dao.table4.S461_Dao;
import cn.nit.service.table4.S461_Service;


public class S461_Action {
	
	
	
	

	private S461_Service s461_Service=new S461_Service();
	
	private S46_Bean s46_Bean=new S46_Bean();
	
	private S461_Dao s461_Dao=new S461_Dao();
	
	/**  哪一年数据  */
	private String selectYear;

	/**  导出的excelName名 */
	private String excelName ;
	

	HttpServletResponse response = ServletActionContext.getResponse() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	
	//查询出所有
	
	public void loadInfo() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse() ;		
		
		List<S46_Bean> list = s461_Service.getYearInfo(this.getSelectYear());

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
		List<S46_Bean> list = s461_Dao.totalList(this.getSelectYear());
		
	    ByteArrayOutputStream fos = null;
		
		if(list == null){
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
		           ws.mergeCells(0, 0, 8, 0);
		           
		           ws.addCell(new Label(0,2,"项目",wcf));
		           ws.addCell(new Label(1,2,"教学名师",wcf));
		           ws.addCell(new Label(2,2,"师德先进个人",wcf));
		           ws.addCell(new Label(3,2,"研究与创作奖",wcf));
		           ws.addCell(new Label(4,2,"学生思政队伍工作成果奖",wcf));
		           ws.addCell(new Label(5,2,"优秀教师",wcf));
		           ws.addCell(new Label(6,2,"优秀工作者",wcf));
		           ws.addCell(new Label(7,2,"教学活动月获奖",wcf));
		           ws.addCell(new Label(8,2,"其他",wcf));
		           
		           
	        	   ws.addCell(new Label(0,3,"合计",wcf1));
		           ws.addCell(new Label(1,3,list.get(0).getFameTeaAward()+"",wcf1));
		           ws.addCell(new Label(2,3,list.get(0).getAdvanceTeaAward()+"",wcf1));
		           ws.addCell(new Label(3,3,list.get(0).getWorkAward()+"",wcf1));
		           ws.addCell(new Label(4,3,list.get(0).getStuWordAward()+"",wcf1));
		           ws.addCell(new Label(5,3,list.get(0).getOutstdTeaAward()+"",wcf1));
		           ws.addCell(new Label(6,3,list.get(0).getOutWorkAward()+"",wcf1));
		           ws.addCell(new Label(7,3,list.get(0).getTeathAward()+"",wcf1));
		           ws.addCell(new Label(8,3,list.get(0).getOtherAward()+"",wcf1));

 
		           
		           for(int i=1;i<list.size();i++){
		        	   ws.addCell(new Label(0,3+i,list.get(i).getItem(),wcf1));
			           ws.addCell(new Label(1,3+i,list.get(i).getFameTeaAward()+"",wcf1));
			           ws.addCell(new Label(2,3+i,list.get(i).getAdvanceTeaAward()+"",wcf1));
			           ws.addCell(new Label(3,3+i,list.get(i).getWorkAward()+"",wcf1));
			           ws.addCell(new Label(4,3+i,list.get(i).getStuWordAward()+"",wcf1));
			           ws.addCell(new Label(5,3+i,list.get(i).getOutstdTeaAward()+"",wcf1));
			           ws.addCell(new Label(6,3+i,list.get(i).getOutWorkAward()+"",wcf1));
			           ws.addCell(new Label(7,3+i,list.get(i).getTeathAward()+"",wcf1));
			           ws.addCell(new Label(8,3+i,list.get(i).getOtherAward()+"",wcf1));
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


	public S461_Service getS461_Service() {
		return s461_Service;
	}

	public void setS461_Service(S461_Service s461Service) {
		s461_Service = s461Service;
	}

	public S46_Bean getS46_Bean() {
		return s46_Bean;
	}

	public void setS46_Bean(S46_Bean s46Bean) {
		s46_Bean = s46Bean;
	}

	public S461_Dao getS461_Dao() {
		return s461_Dao;
	}

	public void setS461_Dao(S461_Dao s461Dao) {
		s461_Dao = s461Dao;
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
