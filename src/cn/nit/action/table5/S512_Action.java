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

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
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

import cn.nit.bean.table1.S17Bean;
import cn.nit.bean.table5.S512_Bean;
import cn.nit.dao.table5.S512_DAO;
import cn.nit.excel.imports.table5.S512Excel;
import cn.nit.service.table5.S512_Service;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;



public class S512_Action {
	
	/**  表s512的数据库操作类  */
	private S512_DAO s512Dao = new S512_DAO() ;
	
	private S512Excel s512Excel=new S512Excel();

	/**  表s512的Service类  */
	private S512_Service s512Ser = new S512_Service() ;
	
	/**  表s512的Bean实体类  */
	private S512_Bean s512Bean = new S512_Bean() ;
	
	/**excel导出名字*/
	private String excelName; //
	

	/**导出选择年份*/
	private String selectYear;

	
//查询出所有
	
	public void loadInfo() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse() ;		
		
		List<S512_Bean> list = new ArrayList<S512_Bean>();
//		List<S512_Bean> list=s512Ser.loadInfo(this.getSelectYear());
		if(s512Ser.loadInfo(this.getSelectYear())!=null){
			list = s512Ser.loadInfo(this.getSelectYear());
		}
		System.out.println("year:"+this.getSelectYear());

		JSON json = JSONSerializer.toJSON(list) ;
		System.out.println(json);
		PrintWriter out = null ;
		boolean flag = false;
		//System.out.println(json.toString());
		
//		if(list != null){
//			flag = true;
//			System.out.println("有数据");
//		}
		
		System.out.println(flag);

//		if(flag == false){
//			System.out.println("gello1");
//			System.out.print("无该年数据！！！");
//			response.setContentType("text/html;charset=UTF-8");
//			out = response.getWriter();
//			out.print("{\"data\":\"无该年数据！！！\"}");
//		}else{
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

	
	
public InputStream getInputStream() throws Exception{
		
		System.out.println(this.getSelectYear());

		List<S512_Bean>  list = s512Dao.totalList(this.getSelectYear());
		
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
		             
		           ws.addCell(new Label(0, 2, "序号", wcf)); 
		           ws.addCell(new Label(1, 2, "开课单位", wcf)); 
		           ws.addCell(new Label(2, 2, "单位号", wcf));
		           ws.addCell(new Label(3, 2, "1.本科课程门次数", wcf));
		           ws.addCell(new Label(5, 2, "2.主讲本科课程的教师", wcf));
		           ws.addCell(new Label(11, 2, "3.授课情况", wcf));
		           ws.addCell(new Label(3,3,"总数",wcf));
		           ws.addCell(new Label(4,3,"其中：小班授课",wcf));
		           ws.addCell(new Label(5,3,"总人数（人）",wcf));
		           ws.addCell(new Label(6,3,"其中",wcf));
		           ws.addCell(new Label(11,3,"由教授授课的课程门次（门次）",wcf));
		           ws.addCell(new Label(12,3,"由副教授授课的课程门次（门次）",wcf));
		           ws.addCell(new Label(6,4,"符合岗位资格（人）",wcf));
		           ws.addCell(new Label(7,4,"教授（人）",wcf));
		           ws.addCell(new Label(8,4,"副教授（人）",wcf));
		           ws.addCell(new Label(9,4,"为低年级授课的教授（人） ",wcf));
		           ws.addCell(new Label(10,4,"为低年级授课的副教授（人",wcf));
		           
		           //合并单元格
		           ws.mergeCells(0, 2, 0, 4);//序号
		           ws.mergeCells(1, 2, 1, 4);//开课单位
		           ws.mergeCells(2, 2, 2, 4);//单位号
		           ws.mergeCells(3, 2, 4, 2);//1.本科课程门次数
		           ws.mergeCells(5, 2, 10, 2);//2.主讲本科课程的教师
		           ws.mergeCells(11, 2, 12, 2);//3.授课情况

		           ws.mergeCells(3, 3, 3, 4);//总数
		           ws.mergeCells(4, 3, 4, 4);//其中：小班授课
		           ws.mergeCells(5, 3, 5, 4);//总人数（人）
		           ws.mergeCells(6, 3, 10, 3);//其中 
		           ws.mergeCells(11, 3, 11, 4);//由教授授课的课程门次（门次）
		           ws.mergeCells(12, 3, 12, 4);//由副教授授课的课程门次（门次）
		           
		           int n = list.size();
		           int j = 6;
		            int seq = 1;
		           if(list!=null && list.size()>0){
		        	   
		        	   for(int i = 0 ; i<n;i++){
			        	   S512_Bean  bean = list.get(i);
			        	   if(i == 0){
			        		   ws.addCell(new Label(0,5,""+seq,wcf1));
			        		   ws.addCell(new Label(1, 5, ""+bean.getTeaUnit(), wcf1));
			        		   ws.mergeCells(1, 5, 2, 5);
			        		   ws.addCell(new Label(3, 5, ""+bean.getSumCS(), wcf1)); 
			        		   ws.addCell(new Label(4, 5, ""+bean.getSmallCSNum(), wcf1)); 
			        		   ws.addCell(new Label(5, 5, ""+bean.getSumTeaNum(), wcf1)); 
			        		   ws.addCell(new Label(6, 5, ""+bean.getQuqlifyTea(), wcf1)); 
			        		   ws.addCell(new Label(7, 5, ""+bean.getProfessor(), wcf1)); 
			        		   ws.addCell(new Label(8, 5, ""+bean.getViceProfessor(), wcf1)); 
			        		   ws.addCell(new Label(9, 5, ""+bean.getJuniorTea(), wcf1)); 
			        		   ws.addCell(new Label(10, 5, ""+bean.getJuniorViceProf(), wcf1)); 
			        		   ws.addCell(new Label(11, 5, ""+bean.getCSProfNum(), wcf1)); 
			        		   ws.addCell(new Label(12, 5, ""+bean.getCSViceProfNum(), wcf1));  
			        	   }
			        	   else if(i>0){
			        		   ws.addCell(new Label(0, j,""+seq,wcf1));
			        		   ws.addCell(new Label(1, j, bean.getTeaUnit(), wcf1));
			        		   ws.addCell(new Label(2, j, bean.getUnitID(), wcf1));
			        		   ws.addCell(new Label(3, j, ""+bean.getSumCS(), wcf1)); 
			        		   ws.addCell(new Label(4, j, ""+bean.getSmallCSNum(), wcf1)); 
			        		   ws.addCell(new Label(5, j, ""+bean.getSumTeaNum(), wcf1)); 
			        		   ws.addCell(new Label(6, j, ""+bean.getQuqlifyTea(), wcf1)); 
			        		   ws.addCell(new Label(7, j, ""+bean.getProfessor(), wcf1)); 
			        		   ws.addCell(new Label(8, j, ""+bean.getViceProfessor(), wcf1)); 
			        		   ws.addCell(new Label(9, j, ""+bean.getJuniorTea(), wcf1)); 
			        		   ws.addCell(new Label(10, j, ""+bean.getJuniorViceProf(), wcf1)); 
			        		   ws.addCell(new Label(11, j, ""+bean.getCSProfNum(), wcf1)); 
			        		   ws.addCell(new Label(12, j, ""+bean.getCSViceProfNum(), wcf1));  
			        		   j++;
			        	   }
			        	   seq++;
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

	public S512_Bean getS512Bean() {
		return s512Bean;
	}


	public void setS512Bean(S512_Bean s512Bean) {
		this.s512Bean = s512Bean;
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
