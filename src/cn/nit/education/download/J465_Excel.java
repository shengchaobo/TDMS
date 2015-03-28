package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

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
import cn.nit.bean.table4.T410_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.table4.T410_Dao;
import cn.nit.dao.table4.T49_Dao;

public class J465_Excel {
	
	public static boolean export_J465(String path,String year){
		
		T410_Dao T410_dao = new T410_Dao();
		T49_Dao T49_dao = new T49_Dao();
//		Calendar a = Calendar.getInstance();
//		String year = String.valueOf(a.get(Calendar.YEAR));
		T410_Bean bean = T410_dao.totalList(year, Constants.PASS_CHECK);
		int bianZhu = T49_dao.getNum(year,"ComplileBookNum");
		int bianXie = T49_dao.getNum(year,"WriteBookNum");
		
	    ByteArrayOutputStream fos = null;
		
	    String sheetName = "J-4-6-5教师出版著作（自然年）";
					
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
	           ws.addCell(new Label(0, 2, "总数（本、册）", wcf)); 
	           ws.addCell(new Label(0, 3, "专著", wcf)); 
	           ws.addCell(new Label(0, 4, "译著", wcf)); 
	           ws.addCell(new Label(0, 5, "编著", wcf)); 
	           ws.addCell(new Label(0, 6, "其他", wcf)); 
          
	           if(bean!=null){

		           ws.addCell(new Label(1, 2, bean.getPatentNum().toString(), wcf1)); 
		           ws.addCell(new Label(1, 3, bean.getTreatises().toString(), wcf1));
		           ws.addCell(new Label(1, 4, bean.getTranslation().toString(), wcf1));
		           ws.addCell(new Label(1, 5, String.valueOf(bianZhu), wcf1));
		           ws.addCell(new Label(1, 6, String.valueOf(bianXie), wcf1));
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
								
				File file = new File(path,"J-4-6-5教师出版著作.xls");
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
