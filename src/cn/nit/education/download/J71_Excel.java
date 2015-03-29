package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import cn.nit.bean.table7.S71_Bean;
import cn.nit.dao.table7.S71_DAO;
import cn.nit.service.table7.S71_Service;

public class J71_Excel {
	public static boolean export_J71(String path,String year){
		
		S71_Service S71_Ser=new S71_Service();
		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
//		String year = dateFormat.format(new Date());
		
		S71_Bean bean=S71_Ser.getYearIf(year);
		ByteArrayOutputStream fos = null;
		String sheetName = "J-7-1教学管理人员培训及成果（时点）";
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
		       ws.mergeCells(0, 0, 3, 0);
		       
		       ws.addCell(new Label(0, 2, "项目", wcf)); 
			   ws.addCell(new Label(3, 2, "内容", wcf)); 
			   ws.addCell(new Label(0, 3, "1.教学管理人员成果", wcf)); 
			   ws.addCell(new Label(1, 3, "教学成果奖（项）", wcf)); 
			   ws.addCell(new Label(1, 7, "教学论文（篇）", wcf));  
			   ws.addCell(new Label(2, 3, "总数", wcf)); 
			   ws.addCell(new Label(2, 4, "其中：国家级", wcf)); 
			   ws.addCell(new Label(2, 5, "    省部级", wcf)); 
			   ws.addCell(new Label(2, 6, "    校级", wcf)); 
			   ws.addCell(new Label(2, 7, "总数", wcf)); 
			   ws.addCell(new Label(2, 8, "其中：教学研究", wcf)); 
			   ws.addCell(new Label(2, 9, "    教学管理", wcf)); 
			   
			   ws.mergeCells(0, 2, 2, 2);
			   ws.mergeCells(0, 3, 0, 9);
			   ws.mergeCells(1, 3, 1, 6);
			   ws.mergeCells(1, 7, 1, 9);
			   
			   if(bean!=null){
		           ws.addCell(new Label(3, 3, bean.getSumTeaAward().toString(), wcf1)); 
		           ws.addCell(new Label(3, 4, bean.getNationAward().toString(), wcf1));  
		           ws.addCell(new Label(3, 5, bean.getProviAward().toString(), wcf1)); 
		           ws.addCell(new Label(3, 6, bean.getSchAward().toString(), wcf1)); 
		           ws.addCell(new Label(3, 7, bean.getSumTeaPaper().toString(), wcf1)); 
		           ws.addCell(new Label(3, 8, bean.getTeaResPaper().toString(), wcf1)); 
		           ws.addCell(new Label(3, 9, bean.getTeaManagePaper().toString(), wcf1)); 
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
			
			File file = new File(path,"J-7-1教学管理人员培训及成果（时点）.xls");
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
		String path = "E:\\江西项目\\测试表\\一键导出";
		 J71_Excel excel= new J71_Excel();
		 boolean flag = excel.export_J71(path,"2014");
		 if(flag){
			 System.out.println("导出成功");
		 }else{
			 System.out.println("导出不成功");
		 }
	}

}
