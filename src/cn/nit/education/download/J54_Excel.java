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
import cn.nit.bean.table5.T54_Bean;
import cn.nit.service.table5.T54_Service;
import cn.nit.util.ExcelUtil;

public class J54_Excel {
	
	public static boolean export_J54(String path){
		
		T54_Service T54_services = new T54_Service();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		String year = dateFormat.format(new Date());
		T54_Bean bean = T54_services.getYearInfo(year);
		
	    ByteArrayOutputStream fos = null;
		
	    String sheetName = "J-5-4课外活动、讲座（学年）";
					
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
	           ws.addCell(new Label(0, 3, "1.文化、学术讲座数（个）", wcf)); 
	           ws.addCell(new Label(0, 7, "2.本科生课外科技、文化活动项目（个）", wcf)); 
	           ws.addCell(new Label(1, 3, "总数", wcf)); 
	           ws.addCell(new Label(1, 4, "  其中：校级", wcf));  
	           ws.addCell(new Label(1, 5, "  其中：院（系）级", wcf)); 
	           ws.addCell(new Label(1, 6, "总数", wcf)); 
	           ws.addCell(new Label(1, 7, "  其中：国家大学生创新性实验计划项目", wcf)); 
	           ws.addCell(new Label(1, 8, "  其中：省部级项目", wcf)); 
	           ws.addCell(new Label(1, 9, "  其中：学校项目", wcf)); 


	           ws.mergeCells(0, 2, 1, 2);
	           ws.mergeCells(0, 3, 0, 6);
	           ws.mergeCells(0, 7, 0, 9);
	           		           
	           if(bean!=null){
		           ws.addCell(new Label(2, 3, bean.getLectureSumNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 4, bean.getSchLecture().toString(), wcf1));  
		           ws.addCell(new Label(2, 5, bean.getCollegeLecture().toString(), wcf1)); 
		           ws.addCell(new Label(2, 6, bean.getActItemSumNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 7, bean.getNationActItem().toString(), wcf1)); 
		           ws.addCell(new Label(2, 8, bean.getProviActItem().toString(), wcf1)); 
		           ws.addCell(new Label(2, 9, bean.getSchActItem().toString(), wcf1)); 
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
								
				File file = new File(path,"J-5-4课外活动、讲座.xls");
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
	
	public static void main(String arg[]){
		  String path = "D:\\";
		  J54_Excel excel = new J54_Excel();
		  boolean flag = excel.export_J54(path);
		  if(flag){
			  System.out.println("成功！");
		  }else{
			  System.out.println("不成功！");
		  }
	}
}
