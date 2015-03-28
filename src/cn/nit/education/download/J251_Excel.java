package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.nit.bean.table2.T22_Bean;
import cn.nit.bean.table2.T241_Bean;
import cn.nit.service.table2.T22_Service;
import cn.nit.service.table2.T241_Service;


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


public class J251_Excel {
	
	public static boolean export_J251(String path,String year){
		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
//		String year = dateFormat.format(new Date());
		T241_Service T241_services = new T241_Service();				
		T241_Bean bean = T241_services.getYearInfo(year);
		
		T22_Service T22_services = new T22_Service();
		T22_Bean bean1 = T22_services.getYearInfo(year);
		
	    ByteArrayOutputStream fos = null;
		
	    String sheetName = "J-2-5-1图书馆（时点）";
					
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
	           ws.addCell(new Label(2, 2, "内容", wcf)); 
	           ws.addCell(new Label(0, 3, "1.数量(个)", wcf)); 
	           ws.addCell(new Label(0, 4, "2.阅览室座位数（个）", wcf)); 
	           
	           ws.addCell(new Label(0, 5, "3.纸质图书", wcf)); 
	           ws.addCell(new Label(1, 5, "总量", wcf)); 
	           ws.addCell(new Label(0, 6, "4.纸质期刊", wcf)); 
	           ws.addCell(new Label(1, 6, "数量（份）", wcf));  
	           ws.addCell(new Label(1, 7, "种类（种）", wcf)); 
	           ws.addCell(new Label(0, 8, "5.电子图书", wcf)); 
	           ws.addCell(new Label(1, 8, "数量（种）", wcf)); 
	           ws.addCell(new Label(1, 9, "其中：中文数量（种）", wcf)); 
	           ws.addCell(new Label(1, 10, "其中：外文数量（种）", wcf)); 
	           ws.addCell(new Label(0, 11, "6.电子期刊", wcf)); 
	           ws.addCell(new Label(1, 11, "期刊种类（种）", wcf)); 
	           ws.addCell(new Label(0, 12, "7.数据库", wcf)); 
	           ws.addCell(new Label(1, 12, "数量（个）", wcf)); 
	           ws.mergeCells(0, 2, 1, 2);	           
	           ws.mergeCells(0, 6, 0, 7);
	           ws.mergeCells(0, 8, 0, 10);
	           
	           ws.mergeCells(1, 3, 2, 3);
	           ws.mergeCells(1, 4, 2, 4);
	           
	           if(bean1!=null){
		           ws.addCell(new Label(1, 3, bean1.getLibNum().toString(), wcf1)); 
		           ws.addCell(new Label(1, 4, bean1.getLibRoomSitNum().toString(), wcf1)); 	   
	           }
	           	        	           		           
	           if(bean!=null){
		           ws.addCell(new Label(2, 5, bean.getPaperBookNum().toString(), wcf1));  
		           ws.addCell(new Label(2, 6, bean.getPaperJonalNum().toString(), wcf1));  
		           ws.addCell(new Label(2, 7, bean.getPaperJonalType().toString(), wcf1)); 
		           ws.addCell(new Label(2, 8, bean.getDigBookType().toString(), wcf1)); 
		           ws.addCell(new Label(2, 9, bean.getChiDigBookType().toString(), wcf1)); 
		           ws.addCell(new Label(2, 10, bean.getForDigBookType().toString(), wcf1)); 
		           ws.addCell(new Label(2, 11, bean.getDigJonalType().toString(), wcf1)); 
		           ws.addCell(new Label(2, 12, bean.getDatabaseNum().toString(), wcf1)); 		             
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
								
				File file = new File(path,"J-2-5-1图书馆.xls");
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
