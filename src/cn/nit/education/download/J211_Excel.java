package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.nit.bean.table2.T2101_Bean;
import cn.nit.bean.table2.T2102_Bean;
import cn.nit.bean.table2.T2103_Bean;
import cn.nit.service.table2.T2101_Service;
import cn.nit.service.table2.T2102_Service;
import cn.nit.service.table2.T2103_Service;

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


public class J211_Excel {
	
	public static boolean export_J211(String path, String year){
		
/*		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		String year = dateFormat.format(new Date());*/

		T2101_Service T2101_services = new T2101_Service();
		T2101_Bean bean1 = T2101_services.getYearInfo(year);
		
		T2102_Service T2102_services = new T2102_Service();
		T2102_Bean bean2 = T2102_services.getYearInfo(year);
		
		T2103_Service T2103_services = new T2103_Service();
		T2103_Bean bean3 = T2103_services.getYearInfo(year);
		
		
	    ByteArrayOutputStream fos = null;
		
	    String sheetName = "J-2-11素质教育基地、职业资质培训等情况（学年） ";
					
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
	           ws.addCell(new Label(1, 2, "内容", wcf)); 
	           ws.addCell(new Label(0, 3, "1.大学生素质拓展活动数（次）", wcf)); 
	           ws.addCell(new Label(0, 4, "2.大学生职业资质培训数（人次）", wcf)); 
	           ws.addCell(new Label(0, 5, "3.开设的职业生涯规划及创业教育指导课程数（个）", wcf)); 
	           ws.addCell(new Label(0, 6, "4.素质教育基地数（个）", wcf)); 
	           
	           if(bean1!=null && bean2!=null && bean3!=null){
		           ws.addCell(new Label(1, 3, bean1.getQuaEduItemNum().toString(), wcf1)); 
		           ws.addCell(new Label(1, 6, bean1.getQuaEduBaseNum().toString(), wcf1)); 	
		           ws.addCell(new Label(1, 5, bean2.getCourseNum().toString(), wcf1));
		           ws.addCell(new Label(1, 4, bean3.getStuProfTrainNum().toString(), wcf1)); 
	           }
//	           if(bean2!=null){
//	        	   ws.addCell(new Label(1, 5, bean2.getCourseNum().toString(), wcf1));            
//	           }	           
//	           if(bean3!=null){
//		           ws.addCell(new Label(1, 4, bean3.getStuProfTrainNum().toString(), wcf1)); 		              
//	           }
	           
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
								
				File file = new File(path,"J-2-11素质教育基地、职业资质培训等情况.xls");
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
