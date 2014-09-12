package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import cn.nit.bean.table6.T659_Bean;
import cn.nit.dao.table6.T659_Dao;

public class J622_Excel {
	
	public static boolean export_J622(String path) {
		
		T659_Dao T659_dao = new T659_Dao();
		
		//获取当前年份
		Date time = new Date();
		String currentTime = time.toString();
		String year = currentTime.substring(currentTime.length()-4, currentTime.length());
		
		 List<T659_Bean> list = T659_dao.getAllList() ;
//		T16POJO pojo = list.get(0);
		
		String sheetName = "J-6-2-2本科生交流情况（学年）";
		
		
		
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
	           ws.addCell(new Label(2, 2, "内容", wcf)); 
	           ws.addCell(new Label(0, 3, "交流学生数（人）", wcf)); 
	           ws.addCell(new Label(1, 3, "总数", wcf1)); 
	           ws.addCell(new Label(1, 4, "其中：本校到境外", wcf1)); 
	           ws.addCell(new Label(1, 5, "本校到境内", wcf1)); 
	           ws.addCell(new Label(1, 6, " 境内到本校", wcf1)); 
	           ws.addCell(new Label(1, 7, " 境外到本校", wcf1)); 
	           
	           //合并表头
	           ws.mergeCells(0, 2, 1, 2);
	           ws.mergeCells(0, 3, 0, 7);
	           
	        //写入数据
	           T659_Bean bean = new T659_Bean();
	          if(list!=null||list.size()>0){
	        	  int ExchangeStuSum = 0;int FromSchToOverseas = 0;int FromSchToDomestic = 0;
	        	  int FromDomesticToSch = 0;int FromOverseasToSch = 0;
	        	  for(int i =0;i<list.size();i++){
	        		  T659_Bean bean1 = list.get(i);
	        		  ExchangeStuSum +=bean1.getExchangeStuSum();
	        		  FromSchToOverseas +=bean1.getFromSchToOverseas();
	        		  FromSchToDomestic +=bean1.getFromSchToDomestic();
	        		  FromDomesticToSch += bean1.getFromDomesticToSch();
	        		  FromOverseasToSch += bean1.getFromOverseasToSch();
	        	  }
	        	  bean.setExchangeStuSum(ExchangeStuSum);
	        	  bean.setFromSchToOverseas(FromSchToOverseas);
	        	  bean.setFromSchToDomestic(FromSchToDomestic);
	        	  bean.setFromDomesticToSch(FromDomesticToSch);
	        	  bean.setFromOverseasToSch(FromOverseasToSch);
	        	  
	        	  ws.addCell(new Label(2, 3, ""+bean.getExchangeStuSum(), wcf1)); 
		           ws.addCell(new Label(2, 4, ""+bean.getFromSchToOverseas(), wcf1)); 
		           ws.addCell(new Label(2, 5, ""+bean.getFromSchToDomestic(), wcf1)); 
		           ws.addCell(new Label(2, 6, ""+bean.getFromDomesticToSch(), wcf1)); 
		           ws.addCell(new Label(2, 7, ""+bean.getFromOverseasToSch(), wcf1)); 
	          }
	        
	           wwb.write();
		       wwb.close();
								
//			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-6-2-2本科生交流情况（学年）.xls");
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
		 String path = "D:\\江西项目\\相关表\\ExcelTest";
		  J622_Excel excel = new J622_Excel();
		  boolean flag = excel.export_J622(path);
		  if(flag){
			  System.out.println("成功！");
		  }else{
			  System.out.println("不成功！");
		  }
	}


}
