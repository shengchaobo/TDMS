package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import cn.nit.bean.table6.T611_Bean;
import cn.nit.bean.table6.T612_Bean;
import cn.nit.bean.table6.T613_Bean;
import cn.nit.bean.table6.T614_Bean;
import cn.nit.dao.table6.T611_Dao;
import cn.nit.dao.table6.T612_Dao;
import cn.nit.dao.table6.T613_Dao;
import cn.nit.dao.table6.T614_Dao;

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


public class J611_Excel {
	
	
   public static boolean export_J611(String path,String year) {
		
		T611_Dao T611_dao = new T611_Dao();
		T612_Dao T612_dao = new T612_Dao();
		T613_Dao T613_dao = new T613_Dao();
		T614_Dao T614_dao = new T614_Dao();
		
//		
//		//获取当前年份
//		Date time = new Date();
//		String currentTime = time.toString();
//		String year = currentTime.substring(currentTime.length()-4, currentTime.length());
		
		//从数据库中获得值
        T611_Bean t611bean = T611_dao.getYearInfo(year);
        if(t611bean==null){
        	t611bean = new T611_Bean();
        }
        T612_Bean t612bean = T612_dao.getYearInfo(year);
        if(t612bean==null){
        	t612bean = new T612_Bean();
        }
        T613_Bean t613bean = T613_dao.getYearInfo(year);
        if(t613bean==null){
        	t613bean = new T613_Bean();
        }
        T614_Bean t614bean = T614_dao.getYearInfo(year);
        if(t614bean==null){
        	t614bean = new T614_Bean();
        }
		
		String sheetName = "J-6-1-1学生数量基本情况（时点）";
		
		
		
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
	            
	           //写入表头
	           ws.addCell(new Label(0, 2, "分类", wcf)); 
	           ws.addCell(new Label(1, 2, "本学年人数（个）", wcf)); 
	           ws.addCell(new Label(0, 3, "1.普通本科学生数", wcf));   ws.addCell(new Label(0, 4, "  其中：与国（境）外大学联合培养的学生数", wcf1));
	           ws.addCell(new Label(0, 5, "2.普通高职（含专科）学生数", wcf));   ws.addCell(new Label(0, 6, "3.硕士研究生数", wcf)); 
	           ws.addCell(new Label(0, 7, " 其中：全日制研究生", wcf1));   ws.addCell(new Label(0, 8, " 非全日制研究生", wcf1)); 
	           ws.addCell(new Label(0, 9, "4.博士研究生数", wcf));   ws.addCell(new Label(0, 10, "  其中：全日制", wcf1)); 
	           ws.addCell(new Label(0, 11, " 非全日制", wcf1));   ws.addCell(new Label(0, 12, "5.外国留学生数", wcf)); 
	           ws.addCell(new Label(0, 13, "6.普通预科生数", wcf));   ws.addCell(new Label(0, 14, "7.进修生数（一年以上）", wcf)); 
	           ws.addCell(new Label(0, 15, "8.成人脱产学生数", wcf));   ws.addCell(new Label(0, 16, "9.夜大（业余）学生数", wcf)); 
	           ws.addCell(new Label(0, 17, "10.函授学生数", wcf));   ws.addCell(new Label(0, 18, "11.网络学生数", wcf));  
	           ws.addCell(new Label(0, 19, "12.自考学生数", wcf));  
	        
	           //写入数据
	           if(t611bean!=null && t612bean!=null && t614bean!=null && t613bean!=null){
	        	   ws.addCell(new Label(1, 3, ""+t611bean.getUndergraThisYearNum(), wcf));            ws.addCell(new Label(1, 4, ""+t613bean.getCoTrainStuThisYearNum(), wcf1));
		           ws.addCell(new Label(1, 5, ""+t611bean.getJuniorThisYearNum(), wcf));  ws.addCell(new Label(1, 6, ""+t612bean.getMasterThisYearNum(), wcf)); 
		           ws.addCell(new Label(1, 7, ""+t612bean.getFullTimeMasterThisYearNum(), wcf1));        ws.addCell(new Label(1, 8, ""+t612bean.getPartTimeMasterThisYearNum(), wcf1)); 
		           ws.addCell(new Label(1, 9, ""+t612bean.getDoctorThisYearNum(), wcf));              ws.addCell(new Label(1, 10, ""+t612bean.getFullTimeDoctorThisYearNum(), wcf1)); 
		           ws.addCell(new Label(1, 11, ""+t612bean.getPartTimeDoctorThisYearNum(), wcf1));                 ws.addCell(new Label(1, 12, ""+t613bean.getForeignStuThisYearNum(), wcf)); 
		           ws.addCell(new Label(1, 13, ""+t614bean.getPreppyThisYearNum(), wcf));             ws.addCell(new Label(1, 14, ""+t614bean.getAdvStuThisYearNum(), wcf)); 
		           ws.addCell(new Label(1, 15,""+t614bean.getAdultThisYearNum(), wcf));  		   ws.addCell(new Label(1, 16, ""+t614bean.getNightUniThisYearNum(), wcf)); 
		           ws.addCell(new Label(1, 17, ""+t614bean.getCorrespdThisYearNum(), wcf));             ws.addCell(new Label(1, 18, ""+t614bean.getNetStuThisYearNum(), wcf));  
		           ws.addCell(new Label(1, 19, ""+t614bean.getSelfStudyThisYearNum(), wcf));  
	           }
	          
	           
	           wwb.write();
		       wwb.close();
								
//			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-6-1-1学生数量基本情况（时点）.xls");
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
