package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
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

import cn.nit.dao.table1.T16DAO;
import cn.nit.pojo.table1.T16POJO;

public class J16_Excel {
	
	public static boolean export_J16(String path,String year) {
		
		T16DAO T16_dao = new T16DAO();
//		
//		//获取当前年份
//		Date time = new Date();
//		String currentTime = time.toString();
//		String year = currentTime.substring(currentTime.length()-4, currentTime.length());
		
		List<T16POJO> list = T16_dao.forExcel(year);
//		T16POJO pojo = list.get(0);
		
		String sheetName = "J-1-6办学指导思想（时点）";
		
		
		
		try {
			
				  //    设置单元格的文字格式(标题)
		        WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.BOLD,false,
		                 UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
		        WritableCellFormat wcf = new WritableCellFormat(wf);
		        wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
		        wcf.setAlignment(Alignment.CENTRE);
		        wcf.setBorder(Border.ALL, BorderLineStyle.THIN,
						     jxl.format.Colour.BLACK);
		        wcf.setAlignment(jxl.write.Alignment.LEFT);
	//	        ws.setRowView(1, 500);
		        
		        //设置表中文字格式
				WritableCellFormat wcf1 = new WritableCellFormat();
				wcf1.setBorder(Border.ALL, BorderLineStyle.THIN,
						     jxl.format.Colour.BLACK); 
		       
				ByteArrayOutputStream  byteArrayOutputStream= null;	
				WritableWorkbook wwb;
			
			   byteArrayOutputStream = new ByteArrayOutputStream();
	           wwb = Workbook.createWorkbook(byteArrayOutputStream);
	           WritableSheet ws = wwb.createSheet(sheetName, 0);        // 创建一个工作表
	           
	           ws.setRowView(1, 500);
	           
	           ws.addCell(new Label(0, 0, sheetName, wcf)); 
	           ws.mergeCells(0, 0, 2, 0);
	             
	           //写表头
	           ws.addCell(new Label(0, 2, "项目", wcf)); 
	           ws.addCell(new Label(1, 2, "内容", wcf)); 
	           ws.addCell(new Label(2, 2, "备注", wcf)); 
	           ws.addCell(new Label(0, 3, "1.校训", wcf)); 
	           ws.addCell(new Label(0, 4, "2.定位与发展目标", wcf)); 
	           
	           //合并表头
	           ws.mergeCells(1, 2, 3, 2);
	           
	           T16POJO pojo ;
	           if(list!=null && list.size()>0){
	        	   pojo = list.get(0);
	           }else{
	        	   pojo = new T16POJO();
	           }
	           
    		   //写入数据
    		   ws.addCell(new Label(1, 3, pojo.getContents1(), wcf1)); 
    		   ws.mergeCells(1, 3, 3, 3);
    		   ws.addCell(new Label(1, 4, pojo.getContents2(), wcf1)); 
    		   ws.mergeCells(1, 4, 3, 4);
	        
	           wwb.write();
		       wwb.close();
								
//			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-1-6办学指导思想（时点）.xls");
			FileOutputStream fileOutputStream  = new FileOutputStream(file);
			
			//写到文件中
			fileOutputStream.write(byteArrayOutputStream.toByteArray());
			
			byteArrayOutputStream.close();
			fileOutputStream.close();
			return true;
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String arg[]){
		String path = "C:\\Users\\Fan Shuangyan\\Desktop\\Education";
		  J16_Excel excel = new J16_Excel();
		  boolean flag = excel.export_J16(path,"2002");
		  if(flag){
			  System.out.println("成功！");
		  }else{
			  System.out.println("不成功！");
		  }
	}

}
