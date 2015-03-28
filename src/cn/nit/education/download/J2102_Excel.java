package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.nit.bean.table2.T292_Bean;
import cn.nit.bean.table2.T293_Bean;
import cn.nit.service.table2.T292_Service;
import cn.nit.service.table2.T293_Service;

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


public class J2102_Excel {
	
	public static boolean export_J2102(String path,String year){
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
//		String year = dateFormat.format(new Date());
		
		T292_Service T292_services = new T292_Service();
		T292_Bean bean1 = T292_services.getYearInfo(year);
		
		T293_Service T293_services = new T293_Service();
		T293_Bean bean2 = T293_services.getYearInfo(year);
		
	    ByteArrayOutputStream fos = null;
		
	    String sheetName = "J-2-10-2本科教育经费收支情况（自然年）  ";
					
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
	           ws.addCell(new Label(3, 2, "内容", wcf)); 
	           ws.addCell(new Label(0, 3, "1.本科教育经费支出（万元）", wcf)); 
	           ws.addCell(new Label(1, 3, "支出总计", wcf)); 
	           ws.addCell(new Label(1, 4, "教学日常运行支出", wcf));  
	           ws.addCell(new Label(1, 5, "教学改革支出", wcf));  
	           ws.addCell(new Label(1, 6, "专业建设支出", wcf)); 
	           ws.addCell(new Label(1, 7, "实践教学支出", wcf)); 
	           ws.addCell(new Label(1, 8, "其中：实验经费支出", wcf));
	           ws.addCell(new Label(1, 9, "其中：实习经费支出", wcf));
	           ws.addCell(new Label(1, 10, "其他教学专项", wcf)); 
	           ws.addCell(new Label(1, 11, "学生活动经费支出", wcf)); 
	           ws.addCell(new Label(1, 12, "教师培训进修专项经费支出", wcf)); 
	           
	           ws.mergeCells(0, 2, 2, 2);
	           ws.mergeCells(0, 3, 0, 12);
	           ws.mergeCells(1, 3, 2, 3);
	           ws.mergeCells(1, 4, 2, 4);
	           ws.mergeCells(1, 5, 2, 5);
	           ws.mergeCells(1, 6, 2, 6);
	           ws.mergeCells(1, 7, 2, 7);
	           ws.mergeCells(1, 8, 2, 8);
	           ws.mergeCells(1, 9, 2, 9);
	           ws.mergeCells(1, 10, 2, 10);
	           ws.mergeCells(1, 11, 2, 11);
	           ws.mergeCells(1, 12, 2, 12);
	           
	           ws.addCell(new Label(0, 13, "2.本科教育事业收入（万元）", wcf)); 
	           ws.addCell(new Label(1, 13, "本科生生均拨款总额", wcf)); 
	           ws.addCell(new Label(2, 13, "国家", wcf));
	           ws.addCell(new Label(2, 14, "地方", wcf));
	           ws.addCell(new Label(1, 15, "专科生生均拨款总额", wcf)); 
	           ws.addCell(new Label(1, 16, "本科生学费收入", wcf)); 
	           ws.addCell(new Label(1, 17, "高职高专学费收入", wcf)); 
	           ws.addCell(new Label(1, 18, "教改专项拨款", wcf)); 
	           ws.addCell(new Label(1, 19, "社会捐赠金额", wcf)); 
	           ws.mergeCells(0, 13, 0, 19);
	           ws.mergeCells(1, 13, 1, 14);
	           ws.mergeCells(1, 15, 2, 15);
	           ws.mergeCells(1, 16, 2, 16);
	           ws.mergeCells(1, 17, 2, 17);
	           ws.mergeCells(1, 18, 2, 18);
	           ws.mergeCells(1, 19, 2, 19);
	           		           
	           if(bean1!=null){
		           ws.addCell(new Label(3, 3, bean1.getUndergraTeaExpTotal().toString(), wcf1)); 
		           ws.addCell(new Label(3, 4, bean1.getDayTeaExp().toString(), wcf1));  
		           ws.addCell(new Label(3, 5, bean1.getTeaReformExp().toString(), wcf1)); 
		           ws.addCell(new Label(3, 6, bean1.getMajorExp().toString(), wcf1)); 
		           ws.addCell(new Label(3, 7, bean1.getPraTeaExpTotal().toString(), wcf1));
		           ws.addCell(new Label(3, 8, bean1.getExpTeaExp().toString(), wcf1));
		           ws.addCell(new Label(3, 9, bean1.getPraTeaExp().toString(), wcf1));
		           ws.addCell(new Label(3, 10, bean1.getOtherTeaExp().toString(), wcf1)); 
		           ws.addCell(new Label(3, 11, bean1.getStuActExp().toString(), wcf1)); 
		           ws.addCell(new Label(3, 12, bean1.getTeaTrainExp().toString(), wcf1)); 		           		              
	           }
	           
	           if(bean2!=null){	        	   
		           ws.addCell(new Label(3, 13, bean2.getNationFund().toString(), wcf1)); 
		           ws.addCell(new Label(3, 14, bean2.getLocalFund().toString(), wcf1));  
		           ws.addCell(new Label(3, 15, bean2.getJuniorAllocateFund().toString(), wcf1)); 
		           ws.addCell(new Label(3, 16, bean2.getUndergraTuition().toString().toString(), wcf1)); 
		           ws.addCell(new Label(3, 17, bean2.getJuniorTuition().toString(), wcf1));
		           ws.addCell(new Label(3, 18, bean2.getEduReformFund().toString(), wcf1));
		           ws.addCell(new Label(3, 19, bean2.getDonation().toString(), wcf1));		           		              
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
								
				File file = new File(path,"J-2-10-2本科教育经费收支情况.xls");
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
