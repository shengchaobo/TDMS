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
import cn.nit.action.table2.T21_Action;
import cn.nit.bean.table2.T21_Bean;
import cn.nit.service.table2.T21_Service;
import cn.nit.util.ExcelUtil;

public class J21_Excel {

	public static boolean export_J21(String path, String year){
		
		T21_Service T21_services = new T21_Service();
/*		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		String year = dateFormat.format(new Date());*/

		T21_Bean bean = T21_services.getYearInfo(year);
		
	
	    ByteArrayOutputStream fos = null;
		
	    String sheetName = "J-2-1占地与建筑面积（时点）";
					
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
	           ws.addCell(new Label(0, 3, "1.占地面积(平方米)", wcf)); 
	           ws.addCell(new Label(0, 10, "2.总建筑面积(平方米)", wcf)); 
	           ws.addCell(new Label(1, 3, "总占地面积", wcf)); 
	           ws.addCell(new Label(1, 4, "学校产权", wcf));  
	           ws.addCell(new Label(1, 5, "  其中：绿化用地", wcf)); 
	           ws.addCell(new Label(1, 6, "非学校产权", wcf)); 
	           ws.addCell(new Label(1, 7, "  其中：绿化用地", wcf)); 
	           ws.addCell(new Label(1, 8, "  其中：独立使用", wcf)); 
	           ws.addCell(new Label(1, 9, "       共同使用", wcf)); 
	           ws.addCell(new Label(1, 10, "总建筑面积", wcf)); 
	           ws.addCell(new Label(1, 11, "学校产权", wcf)); 
	           ws.addCell(new Label(1, 12, "非学校产权", wcf)); 
	           ws.addCell(new Label(1, 13, "  其中：独立使用", wcf)); 
	           ws.addCell(new Label(1, 14, "        共同使用", wcf)); 

	           ws.mergeCells(0, 2, 1, 2);
	           ws.mergeCells(0, 3, 0, 9);
	           ws.mergeCells(0, 10, 0, 14);
	           		           
	           if(bean!=null){
		           ws.addCell(new Label(2, 3, bean.getSumArea().toString(), wcf1)); 
		           ws.addCell(new Label(2, 4, bean.getSchProArea().toString(), wcf1));  
		           ws.addCell(new Label(2, 5, bean.getGreenArea().toString(), wcf1)); 
		           ws.addCell(new Label(2, 6, bean.getNotSchProArea().toString(), wcf1)); 
		           ws.addCell(new Label(2, 7, bean.getGreenAreaNotInSch().toString(), wcf1)); 
		           ws.addCell(new Label(2, 8, bean.getOnlyUseArea().toString(), wcf1)); 
		           ws.addCell(new Label(2, 9, bean.getCoUseArea().toString(), wcf1)); 
		           ws.addCell(new Label(2, 10, bean.getSumCoverArea().toString(), wcf1)); 
		           ws.addCell(new Label(2, 11, bean.getSchProCovArea().toString(), wcf1)); 
		           ws.addCell(new Label(2, 12, bean.getNotSchProCovArea().toString(), wcf1)); 
		           ws.addCell(new Label(2, 13, bean.getOnlyUseCovArea().toString(), wcf1)); 
		           ws.addCell(new Label(2, 14, bean.getCoUseCovArea().toString(), wcf1)); 
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
								
				File file = new File(path,"J-2-1占地与建筑面积.xls");
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
