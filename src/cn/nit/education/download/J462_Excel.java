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

public class J462_Excel {
	
	public static boolean export_J462(String path){
		
		T410_Dao T410_dao = new T410_Dao();
		Calendar a = Calendar.getInstance();
		String year = String.valueOf(a.get(Calendar.YEAR));
		T410_Bean bean = T410_dao.totalList(year, Constants.PASS_CHECK);
		
	    ByteArrayOutputStream fos = null;
		
	    String sheetName = "J-4-6-2教师科研项目数（自然年）";
					
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
	           ws.addCell(new Label(0, 2, "", wcf)); 
	           ws.addCell(new Label(1, 2, "总计", wcf)); 
	           ws.addCell(new Label(2, 2, "横向", wcf)); 
	           ws.addCell(new Label(4, 2, "纵向", wcf)); 
	           ws.addCell(new Label(2, 3, "横向总数", wcf)); 
	           ws.addCell(new Label(3, 3, "其中：人文社会科学", wcf)); 
	           ws.addCell(new Label(4, 3, "纵向总数", wcf)); 
	           ws.addCell(new Label(5, 3, "其中：人文社会科学", wcf)); 
	           ws.addCell(new Label(0, 4, "项目数（项）", wcf)); 
	           ws.addCell(new Label(0, 5, "经费（万元）", wcf)); 

	           ws.mergeCells(0, 2, 0, 3);
	           ws.mergeCells(1, 2, 1, 3);
	           ws.mergeCells(2, 2, 3, 2);	           
	           ws.mergeCells(4, 2, 5, 2);
	           ws.mergeCells(4, 3, 5, 3);	           
	           if(bean!=null){

		           ws.addCell(new Label(1, 4, bean.getResItemNum().toString(), wcf1)); 
		           ws.addCell(new Label(2, 4, bean.getHresItemNum().toString(), wcf1));
		           ws.addCell(new Label(3, 4, bean.getHhumanItemNum().toString(), wcf1));
		           ws.addCell(new Label(4, 4, bean.getZresItemNum().toString(), wcf1));
		           ws.addCell(new Label(5, 4, bean.getZhumanItemNum().toString(), wcf1));
		           ws.addCell(new Label(1, 5, bean.getResItemFund().toString(), wcf1)); 
		           ws.addCell(new Label(2, 5, bean.getHitemFund().toString(), wcf1));
		           ws.addCell(new Label(3, 5, bean.getHhumanItemFund().toString(), wcf1));
		           ws.addCell(new Label(4, 5, bean.getZitemFund().toString(), wcf1));
		           ws.addCell(new Label(5, 5, bean.getZhumanItemFund().toString(), wcf1));
		           

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
								
				File file = new File(path,"J-4-6-2教师科研项目数.xls");
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
		boolean flag = J462_Excel.export_J462(path);
		if(flag){
		System.out.println("成功");
		}else{
			System.out.println("失败");
		}
	}

}
