package cn.nit.education.download;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import cn.nit.bean.table2.T21_Bean;
import cn.nit.bean.table3.S31_Bean;
import cn.nit.dao.table3.S31_DAO;
import cn.nit.service.table2.T21_Service;
import cn.nit.service.table3.S31_Service;

public class J311_Excel {
	
	public static boolean export_J311(String path,String year){
		
		
		S31_Service s31_Service = new S31_Service();
		S31_DAO s31_Dao = new S31_DAO();
//		SimpleDateFormat  dateFormat = new SimpleDateFormat ("yyyy");
//		String year = dateFormat.format(new Date());
		S31_Bean bean = s31_Dao.exportData(year);
		if(bean==null){
			bean = s31_Service.getYearInfo(year);
		}
		
	    ByteArrayOutputStream fos = null;
		

	    String sheetName = "J-3-1-1学科建设（时点）";
						
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
		           
		           if(bean!=null){
		        	   ws.addCell(new Label(2, 3, ""+bean.getPostdocStation(), wcf1)); 
			           ws.addCell(new Label(2, 4, ""+bean.getDocStationOne(), wcf1));  
			           ws.addCell(new Label(2, 5, ""+bean.getDocStationTwo(), wcf1)); 
			           ws.addCell(new Label(2, 6, ""+bean.getMasterStationOne(), wcf1)); 
			           ws.addCell(new Label(2, 7, ""+bean.getMasterStationTwo(), wcf1)); 
			           ws.addCell(new Label(2, 8, ""+bean.getSumMajor(), wcf1));
			           ws.addCell(new Label(2, 9, ""+bean.getNewMajor(), wcf1)); 
			           ws.addCell(new Label(2, 10, ""+bean.getJuniorMajor(), wcf1)); 
		           }
		          
 
		             

		          wwb.write();
		          wwb.close();

		        } catch (IOException e){
		        } catch (RowsExceededException e){
		        } catch (WriteException e){}
		        
		
				try {
					
					File file = new File(path,"J-3-1-1学科建设.xls");
					FileOutputStream fileOutputStream  = new FileOutputStream(file);
					
					//写到文件中
					fileOutputStream.write(fos.toByteArray());
					
					fos.close();
					fileOutputStream.close();
					
					return true;
				} catch (Exception e) {	
					e.printStackTrace();
					return false;
				}
		        		
	}
	
	public static void main (String args[]){
		export_J311("E:","2014");
	}
	
	


}
