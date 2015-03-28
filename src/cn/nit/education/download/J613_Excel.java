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
import cn.nit.bean.table6.T621_Bean;
import cn.nit.dao.table6.T621_Dao;


public class J613_Excel {
	
	public static boolean export_J613(String path,String year) {
		
		T621_Dao T621_dao = new T621_Dao();
		
//		//获取当前年份
//		Date time = new Date();
//		String currentTime = time.toString();
//		String year = currentTime.substring(currentTime.length()-4, currentTime.length());
		
		//从数据库读取数据
		List<T621_Bean> list = T621_dao.getAllList(year);
		
		T621_Bean bean = new T621_Bean();	;
		
		if(list!=null){
			int AmisPlanNum=0; int ActulEnrollNum=0; int ActulRegisterNum=0; int AutoEnrollNum=0;
			int SpecialtyEnrollNum = 0; int InProviEnrollNum = 0; int NewMajEnrollNum = 0;
			//统计全校合计
			for(T621_Bean bean1 : list){
				AmisPlanNum+=bean1.getAmisPlanNum();
				ActulEnrollNum+=bean1.getActulEnrollNum();
				ActulRegisterNum+=bean1.getActulRegisterNum();
				AutoEnrollNum+=bean1.getActulEnrollNum();
				SpecialtyEnrollNum+=bean1.getSpecialtyEnrollNum();
				InProviEnrollNum+=bean1.getInProviEnrollNum();
				NewMajEnrollNum+=bean1.getNewMajEnrollNum();
			}
			
			bean.setActulEnrollNum(ActulEnrollNum);
			bean.setActulRegisterNum(ActulRegisterNum);
			bean.setAmisPlanNum(AmisPlanNum);
			bean.setAutoEnrollNum(AutoEnrollNum);
			bean.setInProviEnrollNum(InProviEnrollNum);
			bean.setNewMajEnrollNum(NewMajEnrollNum);
			bean.setSpecialtyEnrollNum(SpecialtyEnrollNum);
			bean.setFromTeaUnit("全校合计：");
		}
		
		String sheetName = "J-6-1-3近一届本科生招生类别情况（时点）";
	
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
	           ws.addCell(new Label(0, 3, "1.招生计划数", wcf)); 
	           ws.addCell(new Label(0, 4, "2.实际录取数", wcf)); 
	           ws.addCell(new Label(0, 5, "3.实际报到数", wcf)); 
	           ws.addCell(new Label(0, 6, "4.自主招生数", wcf)); 
	           ws.addCell(new Label(0, 7, "5.招收特长生数", wcf)); 
	           ws.addCell(new Label(0, 8, "6.招收本省学生数", wcf));
	           ws.addCell(new Label(0, 8, "7.新办专业招生数", wcf));
	   
	           
		        //写入数据
	           ws.addCell(new Label(1, 3, ""+bean.getAmisPlanNum(), wcf1)); 
	           ws.addCell(new Label(1, 4, ""+bean.getActulEnrollNum(), wcf1)); 
	           ws.addCell(new Label(1, 5, ""+bean.getActulRegisterNum(), wcf1)); 
	           ws.addCell(new Label(1, 6, ""+bean.getAutoEnrollNum(), wcf1)); 
	           ws.addCell(new Label(1, 7, ""+bean.getSpecialtyEnrollNum(), wcf1)); 
	           ws.addCell(new Label(1, 8, ""+bean.getInProviEnrollNum(), wcf));
	           ws.addCell(new Label(1, 8, ""+bean.getNewMajEnrollNum(), wcf1));
	   
	        
	           wwb.write();
		       wwb.close();
								
//			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-6-1-3近一届本科生招生类别情况（时点）.xls");
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
		  J613_Excel excel = new J613_Excel();
		  boolean flag = excel.export_J613(path,"2015");
		  if(flag){
			  System.out.println("成功！");
		  }else{
			  System.out.println("不成功！");
		  }
	}

	

}
