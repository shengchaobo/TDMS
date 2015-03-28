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

import cn.nit.bean.table6.T66_Bean;
import cn.nit.dao.table6.T66_Dao;

public class J63_Excel {
	
public static boolean export_J63(String path,String year) {
		
		T66_Dao T66_dao = new T66_Dao();
//		
//		//获取当前年份
//		Date time = new Date();
//		String currentTime = time.toString();
//		String year = currentTime.substring(currentTime.length()-4, currentTime.length());
//		
		T66_Bean bean = T66_dao.getYearInfo(year);
		if(bean==null){
			bean = new T66_Bean();
		}
		
		String sheetName = "J-6-3学生社团（时点）";
		
		
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
	           ws.addCell(new Label(0, 3, "1.学生社团（个）", wcf)); 
	           ws.addCell(new Label(0, 9, "2.参与人次数（人次）", wcf)); 
	           ws.addCell(new Label(1, 3, "总数", wcf));
	           ws.addCell(new Label(1, 4, "其中：科技类", wcf));
	           ws.addCell(new Label(1, 5, "人文社会类", wcf));
	           ws.addCell(new Label(1, 6, "体育类", wcf));
	           ws.addCell(new Label(1, 7, "	文艺类", wcf));
	           ws.addCell(new Label(1, 8, " 其他", wcf));
	           ws.addCell(new Label(1, 9, " 总数", wcf));
	           ws.addCell(new Label(1, 10, "其中：科技类", wcf));
	           ws.addCell(new Label(1, 11, "人文社会类", wcf));
	           ws.addCell(new Label(1, 12, "体育类", wcf));
	           ws.addCell(new Label(1, 13, "文艺类", wcf));
	           ws.addCell(new Label(1, 14, "其他", wcf)); 
	           
	           //合并表头
	           ws.mergeCells(0, 2, 1, 2);
	           ws.mergeCells(0, 3, 0, 8);
	           ws.mergeCells(0, 9, 0, 14);
	           		           
	           //写入数据
	           ws.addCell(new Label(2, 3, bean.getStuClubSum()+"", wcf1));
	           ws.addCell(new Label(2, 4,bean.getStuClubSciNum()+"", wcf1));
	           ws.addCell(new Label(2, 5,bean.getStuClubHumanNum()+"", wcf1));
	           ws.addCell(new Label(2, 6, bean.getStuClubSportNum()+"", wcf1));
	           ws.addCell(new Label(2, 7, bean.getStuClubArtNum()+"", wcf1));
	           ws.addCell(new Label(2, 8,bean.getOtherStuClub()+"", wcf1));
	           ws.addCell(new Label(2, 9, bean.getJoinStuSum()+"", wcf1));
	           ws.addCell(new Label(2, 10, bean.getJoinClubSciNum()+"", wcf1));
	           ws.addCell(new Label(2, 11, bean.getJoinClubHumanNum()+"", wcf1));
	           ws.addCell(new Label(2, 12, bean.getJoinClubSportNum()+"", wcf1));
	           ws.addCell(new Label(2, 13, bean.getJoinClubArtNum()+"", wcf1));
	           ws.addCell(new Label(2, 14, bean.getJoinOtherClub()+"", wcf1)); 
	          
	           wwb.write();
		       wwb.close();
								
	//		byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);
	
			File file = new File(path,"J-6-3学生社团（时点）.xls");
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
	 String path = "E:\\江西项目\\测试表\\一键导出";
	  J63_Excel excel = new J63_Excel();
	  boolean flag = excel.export_J63(path,"2014");
	  if(flag){
		  System.out.println("成功！");
	  }else{
		  System.out.println("不成功！");
	  }
}
	
	
	
	

}
