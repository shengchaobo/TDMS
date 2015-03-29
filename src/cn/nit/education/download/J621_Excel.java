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
import cn.nit.bean.table6.S65_Bean;
import cn.nit.bean.table6.T659_Bean;
import cn.nit.dao.table6.S65_Dao;
import cn.nit.dao.table6.T659_Dao;

public class J621_Excel {
	
public static boolean export_J621(String path,String year) {
		
		S65_Dao S65_dao = new S65_Dao();
		
//		//获取当前年份
//		Date time = new Date();
//		String currentTime = time.toString();
//		String year = currentTime.substring(currentTime.length()-4, currentTime.length());
		
		S65_Bean bean = S65_dao.getYearInfo(year);
		
		String sheetName = "J-6-2-1本科生学习成果（学年）";
		
		
		
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
	           ws.addCell(new Label(0, 3, "1.学科竞赛获奖（项）", wcf)); 
	           ws.addCell(new Label(1, 3, "总数", wcf1)); 
	           ws.addCell(new Label(1, 4, "其中：国际级", wcf1)); 
	           ws.addCell(new Label(1, 5, "      国家级 ", wcf1)); 
	           ws.addCell(new Label(1, 6, "      省部级", wcf1)); 
	           ws.addCell(new Label(0, 7, "2.本科生创新活动、技能竞赛获奖（项）", wcf)); 
	           ws.addCell(new Label(1, 7, "总数", wcf1)); 
	           ws.addCell(new Label(1, 8, "其中：国际级", wcf1)); 
	           ws.addCell(new Label(1, 9, "      国家级 ", wcf1)); 
	           ws.addCell(new Label(1, 10, "      省部级", wcf1));
	           ws.addCell(new Label(0, 11, "3.文艺、体育竞赛获奖（项）", wcf)); 
	           ws.addCell(new Label(1, 11, "总数", wcf1)); 
	           ws.addCell(new Label(1, 12, "其中：国际级", wcf1)); 
	           ws.addCell(new Label(1, 13, "      国家级 ", wcf1)); 
	           ws.addCell(new Label(1, 14, "      省部级", wcf1));
	           ws.addCell(new Label(0, 15, "4.学生发表学术论文（篇）", wcf)); 
	           ws.addCell(new Label(0, 16, " 5.学生发表作品数（篇、册）", wcf)); 
	           ws.addCell(new Label(0, 17, " 6.学生获准专利数（项）", wcf)); 
	           ws.addCell(new Label(0, 18, " 7.英语等级考试", wcf)); 
	           ws.addCell(new Label(1, 18, " 英语四级考试累计通过率（%）", wcf)); 
	           ws.addCell(new Label(1, 19, " 英语六级考试累计通过率（%）", wcf));
	           ws.addCell(new Label(0, 20, " 8.体质合格率（%）", wcf)); 
	           ws.addCell(new Label(0, 21, " 9.参加国际会议（人次）", wcf)); 
	           
	           //合并表头
	           ws.mergeCells(0, 2, 1, 2);
	           ws.mergeCells(0, 3, 0, 6);
	           ws.mergeCells(0, 7, 0, 10);
	           ws.mergeCells(0, 11, 0, 14);
	           ws.mergeCells(0, 15, 1, 15);
	           ws.mergeCells(0, 16, 1, 16);
	           ws.mergeCells(0, 17, 1, 17);
	           ws.mergeCells(0, 18, 0, 19);
	           ws.mergeCells(0, 20, 1, 20);
	           ws.mergeCells(0, 21, 1, 21);
	           
	        //写入数据
	           
	           if(bean!=null){
	        	   
	        	   ws.addCell(new Label(2, 3, ""+bean.getSumDiscipAward(), wcf1)); 
		           ws.addCell(new Label(2, 4, ""+bean.getInterD(), wcf1));
		           ws.addCell(new Label(2, 5, ""+bean.getNationD(), wcf1)); 
		           ws.addCell(new Label(2, 6, ""+bean.getProviD(), wcf1));
		       
		           ws.addCell(new Label(2, 7, ""+bean.getSumActAward(), wcf1)); 
		           ws.addCell(new Label(2, 8, ""+bean.getInterA(), wcf1)); 
		           ws.addCell(new Label(2, 9, ""+bean.getNationA(), wcf1)); 
		           ws.addCell(new Label(2, 10, ""+bean.getProviA(), wcf1)); 
		           
		           ws.addCell(new Label(2, 11, ""+bean.getSumLiterSportAward(), wcf1)); 
		           ws.addCell(new Label(2, 12, ""+bean.getInterLS(), wcf1));
		           ws.addCell(new Label(2, 13, ""+bean.getNationLS(), wcf1));
		           ws.addCell(new Label(2, 14, ""+bean.getProviLS(), wcf1)); 
		           
		           ws.addCell(new Label(2, 15, ""+bean.getPaperNum(), wcf1)); 
		           ws.addCell(new Label(2, 16, ""+bean.getWorkNum(), wcf1));  
		           ws.addCell(new Label(2, 17, ""+bean.getPatentNum(), wcf1)); 
		           
		           ws.addCell(new Label(2, 18, ""+bean.getCET4()+"%", wcf1)); 
		           ws.addCell(new Label(2, 19, ""+bean.getCET6()+"%", wcf1)); 
		           
		           ws.addCell(new Label(2, 20, ""+bean.getConQualify()+"", wcf1));
		           ws.addCell(new Label(2, 21, ""+bean.getInterConference(), wcf1));   
	           }
	           
	          
	        
	           wwb.write();
		       wwb.close();
								
//			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-6-2-1本科生学习成果（学年）.xls");
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
		  J621_Excel excel = new J621_Excel();
		  boolean flag = excel.export_J621(path,"2014");
		  if(flag){
			  System.out.println("成功！");
		  }else{
			  System.out.println("不成功！");
		  }
	}

	
	

}
