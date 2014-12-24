package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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

import cn.nit.dao.table2.T27_Dao;
import cn.nit.dao.table5.S52_DAO;
import cn.nit.util.ExcelUtil;

public class J72_Excel {
	
	public static boolean export_J72(String path){
		
		T27_Dao T27_dao = new T27_Dao();
		S52_DAO S52_dao = new S52_DAO();
		Calendar a = Calendar.getInstance();
		String year = String.valueOf(a.get(Calendar.YEAR));
		String webTeahingUrl = T27_dao.getString(year,"WebTeahingUrl");
		String teaManageUrl = T27_dao.getString(year,"TeaManageUrl");
		int num = S52_dao.getNum(year,"网络课程");
	    ByteArrayOutputStream fos = null;
		
	    String sheetName = "J-7-2本科教学信息化（时点）";
					
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
	           ws.mergeCells(0, 0, 2, 0);
	           ws.addCell(new Label(0, 2, "项目", wcf)); 
	           ws.addCell(new Label(1, 2, "内容", wcf)); 
	           ws.mergeCells(1, 2, 2, 2);
	           ws.addCell(new Label(0, 3, "教学管理信息系统", wcf)); 
	           ws.addCell(new Label(0, 4, "网络教学平台", wcf)); 
	           ws.addCell(new Label(0, 5, "网络课程数量", wcf)); 
	           if(teaManageUrl == null){
	        	   ws.addCell(new Label(1, 3,"无", wcf1)); 
	           }else{
	        	   ws.addCell(new Label(1, 3, "有", wcf1)); 
	           }
		       ws.addCell(new Label(2, 3, teaManageUrl, wcf1));
		       if(webTeahingUrl == null){
		    	   ws.addCell(new Label(1, 4,"无", wcf1));
		       }else{
		    	   ws.addCell(new Label(1, 4, "有", wcf1));
		       }
		       
		       ws.addCell(new Label(2, 4, webTeahingUrl, wcf1));
		       ws.addCell(new Label(1, 5, String.valueOf(num), wcf1));
		       ws.mergeCells(1, 5, 2, 5);
             
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
								
				File file = new File(path,"J-7-2本科教学信息化.xls");
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
		boolean flag = J72_Excel.export_J72(path);
		if(flag){
		System.out.println("成功");
		}else{
			System.out.println("失败");
		}
	}

}
