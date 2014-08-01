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

import cn.nit.bean.other.UserRoleBean;
import cn.nit.bean.table5.S5302_Bean;
import cn.nit.dao.table5.S5302_DAO;
import cn.nit.pojo.table5.S5302POJO;
import cn.nit.service.table5.S5302_Service;


public class S5302_Action {
	

	/**  表s5302的数据库操作类  */
	private S5302_DAO s5302Dao = new S5302_DAO() ;
	
//	private S512Excel s512Excel=new S512Excel();

	/**  表s5302的Service类  */
	private S5302_Service s5302Ser = new S5302_Service() ;
	
	/**  表S5302的Bean实体类  */
	private S5302_Bean s5302Bean = new S5302_Bean() ;
	
	/**excel导出名字*/
	private String excelName; //
	

	/**导出选择年份*/
	private String selectYear;


	/**
	 * 显示数据
	 * */
	public void loadInfo() throws Exception{
		
		System.out.println();
		HttpServletResponse response = ServletActionContext.getResponse() ;		
		
		List<S5302POJO> list = new ArrayList<S5302POJO>();
//		List<S512_Bean> list=s512Ser.loadInfo(this.getSelectYear());
		if(s5302Ser.loadInfo(this.getSelectYear())!=null){
			list = s5302Ser.loadInfo(this.getSelectYear());
		}
//		System.out.println("year:"+this.getSelectYear());
//		System.out.println(list.size());

		JSON json = JSONSerializer.toJSON(list) ;
		PrintWriter out = null ;

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
//		}
	
	}

	
	/**excel导出*/
	public InputStream getInputStream() throws Exception{
		
		System.out.println(this.getSelectYear());

		List<S5302POJO>  list = s5302Ser.loadInfo(this.getSelectYear());
//		System.out.println("S5302 de "+list.size());
		
	    ByteArrayOutputStream fos = null;
	    
	
		if(list==null){
			PrintWriter out = null ;
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			out.print("后台传入的数据为空!!!") ;
			System.out.println("后台传入的数据为空");
		}else{
//			String sheetName = this.getExcelName();
				String sheetName="S-5-3-2人才培养模式创新实验项目统计(按教学单位统计项目数)";	
		    WritableWorkbook wwb;
		    try {    
		           fos = new ByteArrayOutputStream();
		           wwb = Workbook.createWorkbook(fos);
		           WritableSheet ws = wwb.createSheet(sheetName, 0);        // 创建一个工作表
//		           
//		           List<String> colums = new ArrayList<String>() ;
//		            columns.add("序号");
//					columns.add("开课单位");columns.add("单位号");columns.add("1.本科课程门次数");columns.add("2.主讲本科课程的教师");
//					columns.add("3.授课情况");columns.add("总数");columns.add("其中：小班授课");columns.add("总人数（人）");
//					columns.add("开设年份");columns.add("专业科研用房面积（平方米）");columns.add("备注");
//
//					
//					Map<String,Integer> maplist = new HashMap<String,Integer>();
//					maplist.put("SeqNum", 0);
//					maplist.put("ResInsName", 1);maplist.put("ResInsID", 2);maplist.put("Type", 3);maplist.put("BuildCondition", 4);
//					maplist.put("BiOpen", 5);maplist.put("OpenCondition", 6);maplist.put("TeaUnit", 7);maplist.put("UnitID", 8);
//					maplist.put("BeginYear", 9);maplist.put("HouseArea", 10);maplist.put("Note", 11);
		
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
		           ws.addCell(new Label(1, 2, "级别", wcf)); 
		           ws.addCell(new Label(1, 3, "国际级", wcf));
		           ws.addCell(new Label(2, 3, "国家级", wcf));
		           ws.addCell(new Label(3, 3, "省部级", wcf));
		           ws.addCell(new Label(4, 3, "市级", wcf));
		           ws.addCell(new Label(5, 3, "校级", wcf));
		           ws.mergeCells(0, 2, 0, 3);
		           ws.mergeCells(1, 2, 5, 2);
		           

		           
		           int n = list.size();
		           int j = 4;
		            int seq = 1;//序号
		           if(list!=null && list.size()>0){
		        	   
		        	   for(int i = 0 ; i<n;i++){
		        		   S5302POJO  pojo = list.get(i);
			        	    ws.addCell(new Label(0,j,pojo.getItem(),wcf1));
			        		ws.addCell(new Label(1, j, ""+pojo.getInternation(), wcf1)); 
			        		ws.addCell(new Label(2, j, ""+pojo.getNation(), wcf1)); 
			        		ws.addCell(new Label(3, j, ""+pojo.getProvi(), wcf1)); 
			        	    ws.addCell(new Label(4, j, ""+pojo.getCity(), wcf1)); 
			        		ws.addCell(new Label(5, j, ""+pojo.getSchool(), wcf1)); 
			         
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
	
	public UserRoleBean getUserinfo(){
		return (UserRoleBean)getSession().getAttribute("userinfo") ;
	}

	
	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	public S5302_Bean getS5302Bean() {
		return s5302Bean;
	}


	public void setS5302Bean(S5302_Bean s5302Bean) {
		this.s5302Bean = s5302Bean;
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
