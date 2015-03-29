package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import cn.nit.dao.table4.J461_Dao;
import cn.nit.pojo.table4.J461POJO;
import cn.nit.service.table2.T21_Service;
import cn.nit.util.ExcelUtil;

public class J461_Excel {	
	
	public static boolean export_J461(String path, String year){
		
	    J461_Dao J461_dao = new J461_Dao();
	    J461POJO pojo = J461_dao.totalList(year);
		
	    ByteArrayOutputStream fos = null;
		
	    String sheetName = "J-4-6-1教师所获荣誉概况（时点）";
					
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
	            
/*		            for(int i=1;i< ws.getColumns(); i++){
		            	System.out.println(ws.getColumns());
			            CellView cellView = new CellView();  
			            cellView.setAutosize(true); //设置自动大小    
			            ws.setColumnView(1, cellView);//根据内容自动设置列宽    			            
		            }*/

	           ws.addCell(new Label(0, 0, sheetName, wcf)); 
	           ws.mergeCells(0, 0, 1, 0);
	           
	           ws.addCell(new Label(0, 2, "教学名师（人）", wcf)); 
	           ws.addCell(new Label(3, 2, "教学团队（个）", wcf)); 
	           ws.addCell(new Label(6, 2, "全国师德先进个人累计数（人）", wcf)); 
	           ws.addCell(new Label(0, 3, "累计数", wcf)); 
	           ws.addCell(new Label(1, 3, "其中", wcf)); 
	           ws.addCell(new Label(3, 3, "累计数", wcf));  
	           ws.addCell(new Label(4, 3, "其中", wcf)); 
	           ws.addCell(new Label(1, 4, "国家级", wcf)); 
	           ws.addCell(new Label(2, 4, "省部级", wcf)); 
	           ws.addCell(new Label(4, 4, "国家级", wcf)); 
	           ws.addCell(new Label(5, 4, "省部级", wcf)); 

	           ws.mergeCells(0, 2, 2, 2);
	           ws.mergeCells(3, 2, 5, 2);
	           ws.mergeCells(6, 2, 6, 4);
	           
	           ws.mergeCells(0, 3, 0, 4);
	           ws.mergeCells(1, 3, 2, 3);
	           ws.mergeCells(3, 3, 3, 4);
	           ws.mergeCells(4, 3, 5, 3);
	           		           
	           if(pojo!=null){
		           ws.addCell(new Label(0, 5, pojo.getSum1()+"", wcf1)); 
		           ws.addCell(new Label(1, 5, pojo.getCountry1()+"", wcf1));  
		           ws.addCell(new Label(2, 5, pojo.getProvi1()+"", wcf1)); 
		           ws.addCell(new Label(3, 5, pojo.getSum2()+"", wcf1)); 
		           ws.addCell(new Label(4, 5, pojo.getCountry2()+"", wcf1)); 
		           ws.addCell(new Label(5, 5, pojo.getProvi2()+"", wcf1)); 
		           ws.addCell(new Label(6, 5, pojo.getSum3()+"", wcf1)); 

	           }	             
	          wwb.write();
	          wwb.close();

	        } catch (IOException e){
	        	return false;
	        } catch (RowsExceededException e){
	        	return false;
	        } catch (WriteException e){
	        	return false;
	        }
	        
			try {
								
				File file = new File(path,"J-4-6-1教师所获荣誉概况.xls");
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

}
