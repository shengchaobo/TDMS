package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import cn.nit.bean.table2.T22_Bean;
import cn.nit.service.table2.T22_Service;

public class J22_Excel {
	
	public static boolean export_J22(String path){
		T22_Service T22_services = new T22_Service();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		String year = dateFormat.format(new Date());
		T22_Bean bean = T22_services.getYearInfo(year);
		
	    ByteArrayOutputStream fos = null;
		
	    String sheetName = "J-2-2教学行政用房（时点）";
					
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
	           
	           ws.addCell(new Label(0, 2, "项目", wcf)); 
	           ws.addCell(new Label(1, 2, "面积‌‌（平方米）", wcf)); 
	           ws.addCell(new Label(2, 2, "数量（个", wcf)); 
	           ws.addCell(new Label(0, 3, "1.行政办公用房", wcf)); 
	           ws.addCell(new Label(0, 4, "2.图书馆", wcf));  
	           ws.addCell(new Label(0, 5, "3.图书馆阅览室座位数", wcf)); 
	           ws.addCell(new Label(0, 6, "4.博物馆", wcf)); 
	           ws.addCell(new Label(0, 7, "5.校史馆", wcf)); 
	           ws.addCell(new Label(0, 8, "6.体育馆", wcf)); 
	           ws.addCell(new Label(0, 9, "7.运动场", wcf)); 
	           ws.addCell(new Label(0, 10, "8.学生活动中心", wcf)); 
	           ws.addCell(new Label(0, 11, "9.会堂", wcf)); 
	           ws.addCell(new Label(0, 12, "10.学生食堂", wcf)); 
	           ws.addCell(new Label(0, 13, "11.学生宿舍", wcf)); 
	           ws.addCell(new Label(0, 14, "12.其他", wcf)); 
	
	           ws.mergeCells(0, 2, 1, 2);
	           ws.mergeCells(0, 3, 0, 9);
	           ws.mergeCells(0, 10, 0, 14);
	           		           
	           if(bean!=null){
		           ws.addCell(new Label(1, 3, bean.getAdmOfficeArea().toString(), wcf1)); 
		           ws.addCell(new Label(1, 4, bean.getLibArea().toString(), wcf1));  
		           ws.addCell(new Label(1, 5, "/", wcf1)); 
		           ws.addCell(new Label(1, 6, bean.getMuseumArea().toString(), wcf1)); 
		           ws.addCell(new Label(1, 7, bean.getSchHisHallArea().toString(), wcf1)); 
		           ws.addCell(new Label(1, 8, bean.getGymArea().toString(), wcf1)); 
		           ws.addCell(new Label(1, 9, bean.getSportArea().toString(), wcf1)); 
		           ws.addCell(new Label(1, 10, bean.getStuCenterArea().toString(), wcf1)); 
		           ws.addCell(new Label(1, 11, bean.getHallArea().toString(), wcf1)); 
		           ws.addCell(new Label(1, 12, bean.getStuCanteenArea().toString(), wcf1)); 
		           ws.addCell(new Label(1, 13, bean.getStuDormiArea().toString(), wcf1)); 
		           ws.addCell(new Label(1, 14, bean.getOtherArea().toString(), wcf1)); 

		           ws.addCell(new Label(2, 3, "/", wcf1)); 
		           ws.addCell(new Label(2, 4, bean.getLibNum().toString(), wcf1));  
		           ws.addCell(new Label(2, 5, bean.getLibRoomSitNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 6, bean.getMuseumNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 7, bean.getSchHisHallNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 8, bean.getGymNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 9, bean.getSportNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 10, bean.getStuCenterNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 11, bean.getHallNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 12, bean.getStuCanteenNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 13, bean.getStuDormiNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 14, bean.getOtherNum().toString(), wcf1)); 
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
								
				File file = new File(path,"J-2-2教学行政用房.xls");
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
