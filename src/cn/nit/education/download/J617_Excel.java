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
import cn.nit.bean.table1.S17_Bean;
import cn.nit.bean.table1.S18_Bean;
import cn.nit.bean.table6.T641_Bean;
import cn.nit.constants.Constants;
import cn.nit.dao.table6.T641_Dao;

public class J617_Excel {
	
	public static boolean export_J617(String path,String year) {
		
		T641_Dao T641_dao = new T641_Dao();
//		
//		//获取当前年份
//		Date time = new Date();
//		String currentTime = time.toString();
//		String year = currentTime.substring(currentTime.length()-4, currentTime.length());
		
		T641_Bean  bean = T641_dao.getYearInfo(year,Constants.PASS_CHECK);

		
//		T641_Bean bean  = T641_dao.getYearInfo(year);
//		System.out.println(bean.getSumAidFund());
	//	T16POJO pojo = list.get(0);
		
		String sheetName = "J-6-1-7本科生奖贷补（自然年）";
		
		
		
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
	           ws.addCell(new Label(1, 2, "资助金额（万元）", wcf)); 
	           ws.addCell(new Label(2, 2, "资助学生数（人次）", wcf)); 
	           ws.addCell(new Label(0, 3, "总计", wcf)); 
	           ws.addCell(new Label(0, 4, "1.政府奖、助学金", wcf)); 
	           ws.addCell(new Label(0, 5, "2.社会奖助学金", wcf)); 
	           ws.addCell(new Label(0, 6, "3.学校奖学金", wcf)); 
	           ws.addCell(new Label(0, 7, "4.国家助学贷款", wcf)); 
	           ws.addCell(new Label(0, 8, "5.勤工助学金", wcf)); 
	           ws.addCell(new Label(0, 9, "6.减免学费", wcf)); 
	           ws.addCell(new Label(0, 10, "7.临时困难补助", wcf)); 


	           if(bean!=null){

		           ws.addCell(new Label(1, 3, bean.getSumAidFund()+"", wcf1)); 
		           ws.addCell(new Label(1, 4, bean.getGovAidFund()+"", wcf1)); 
		           ws.addCell(new Label(1, 5, bean.getSocialAidFund()+"", wcf1));
		           ws.addCell(new Label(1, 6, bean.getSchAidFund()+"", wcf1));
		           ws.addCell(new Label(1, 7, bean.getNationAidFund()+"", wcf1));
		           ws.addCell(new Label(1, 8, bean.getWorkStudyFund()+"", wcf1));
		           ws.addCell(new Label(1, 9, bean.getTuitionWaiberFund()+"", wcf1));
		           ws.addCell(new Label(1, 10, bean.getTempFund()+"", wcf1));
		           
		           ws.addCell(new Label(2, 3, bean.getSumAidNum()+"", wcf1)); 
		           ws.addCell(new Label(2, 4, bean.getGovAidNum()+"", wcf1)); 
		           ws.addCell(new Label(2, 5, bean.getSocialAidNum()+"", wcf1));
		           ws.addCell(new Label(2, 6, bean.getSchAidNum()+"", wcf1));
		           ws.addCell(new Label(2, 7, bean.getNationAidNum()+"", wcf1));
		           ws.addCell(new Label(2, 8, bean.getWorkStudyNum()+"", wcf1));
		           ws.addCell(new Label(2, 9, bean.getTuitionWaiberNum()+"", wcf1));
		           ws.addCell(new Label(2, 10, bean.getTempNum()+"", wcf1));
	           }
//	           
	           
	          
	           wwb.write();
		       wwb.close();
								
	//		byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);
	
			File file = new File(path,"J-6-1-7本科生奖贷补（自然年）.xls");
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



	
	

}
