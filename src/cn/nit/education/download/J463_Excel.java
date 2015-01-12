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
import cn.nit.dao.table4.T410_Dao;

public class J463_Excel {
	
	
	public static boolean export_J463(String path){
		
		T410_Dao T410_dao = new T410_Dao();
		Calendar a = Calendar.getInstance();
		String year = String.valueOf(a.get(Calendar.YEAR));
		T410_Bean bean = T410_dao.totalList(year);
		
	    ByteArrayOutputStream fos = null;
		
	    String sheetName = "J-4-6-3教师最近一届科研成果奖数（时点）";
					
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
	           ws.addCell(new Label(0, 2, "总数（项） ", wcf)); 
	           ws.addCell(new Label(1, 4, "国家级", wcf)); 
	           ws.addCell(new Label(1, 5, "省部级", wcf)); 
	           ws.addCell(new Label(0, 4, "其中", wcf)); 

	           ws.mergeCells(0, 2, 1, 3);
	           ws.mergeCells(2, 2, 2, 3);
	           ws.mergeCells(0, 4, 0, 5);	           
	           		           
	           if(bean!=null){
		           ws.addCell(new Label(2, 2, bean.getResAwardNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 4, bean.getNationResAward().toString(), wcf1));
		           ws.addCell(new Label(2, 5, bean.getProviResAward().toString(), wcf1));
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
								
				File file = new File(path,"J-4-6-3教师最近一届科研成果奖数.xls");
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
	
	
	public static void main(String args[]){
		String path = "E:/test";
		boolean flag = J463_Excel.export_J463(path);
		if(flag){
		System.out.println("成功");
		}else{
			System.out.println("失败");
		}
	}


}
