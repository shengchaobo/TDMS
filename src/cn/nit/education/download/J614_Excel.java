package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import cn.nit.bean.table6.T616_Bean;
import cn.nit.dao.table6.T616_Dao;

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


public class J614_Excel {
	
public static boolean export_J614(String path) {
		
		T616_Dao T616_dao = new T616_Dao();
		
		//获取当前年份
		Date time = new Date();
		String currentTime = time.toString();
		String year = currentTime.substring(currentTime.length()-4, currentTime.length());
		
		List<T616_Bean> list = T616_dao.totalList(year);
		
		String sheetName = "J-6-1-4国外及港澳台学生情况（时点）";
		
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
	           ws.addCell(new Label(0, 2, "", wcf)); 
	           ws.addCell(new Label(2, 2, "毕（结）业生数（人）", wcf)); 
	           ws.addCell(new Label(3, 2, "授予学位数（人）", wcf)); 
	           ws.addCell(new Label(4, 2, "招生数（人）", wcf)); 
	           ws.addCell(new Label(5, 2, "在校生数（人）", wcf)); 
	           ws.addCell(new Label(0, 3, "本科生（境外）", wcf)); 
	           ws.addCell(new Label(0, 4, "按地域分", wcf)); 
	           ws.addCell(new Label(1, 4, "国外", wcf1)); 
	           ws.addCell(new Label(1, 5, "香港", wcf1)); 
	           ws.addCell(new Label(1, 6, "澳门", wcf1));
	           ws.addCell(new Label(1, 7, "台湾", wcf1));
	           
	           //合并表头
	           ws.mergeCells(0, 2, 1, 2);
	           ws.mergeCells(0, 3, 1, 3);
	           ws.mergeCells(0, 4, 0, 7);
	           
	          T616_Bean bean ;
	           if(list!=null && list.size()>0){
	        	   bean = list.get(0);
	           }else{
	        	   bean = new T616_Bean();
	           }
	           
		        		   //写入数据
	           ws.addCell(new Label(2, 3, ""+bean.getSumGraNum(), wcf)); 
	           ws.addCell(new Label(3, 3, ""+bean.getSumDegreeNum(), wcf)); 
	           ws.addCell(new Label(4, 3, ""+bean.getSumAdmisNum(), wcf)); 
	           ws.addCell(new Label(5, 3, ""+bean.getSumInSchNum(), wcf));
	           //国外
	           ws.addCell(new Label(2, 4, ""+bean.getGraOutNum(), wcf)); 
	           ws.addCell(new Label(3, 4, ""+bean.getDegreeOutNum(), wcf)); 
	           ws.addCell(new Label(4, 4, ""+bean.getAdmisOutNum(), wcf)); 
	           ws.addCell(new Label(5, 4, ""+bean.getInSchOutNum(), wcf)); 
	           //香港
	           ws.addCell(new Label(2, 5, ""+bean.getGraHongNum(), wcf)); 
	           ws.addCell(new Label(3, 5, ""+bean.getDegreeHongNum(), wcf)); 
	           ws.addCell(new Label(4, 5, ""+bean.getAdmisHongNum(), wcf)); 
	           ws.addCell(new Label(5, 5, ""+bean.getInSchHongNum(), wcf)); 
	           //澳门
	           ws.addCell(new Label(2, 6, ""+bean.getGraAoNum(), wcf)); 
	           ws.addCell(new Label(3, 6, ""+bean.getDegreeAoNum(), wcf)); 
	           ws.addCell(new Label(4, 6, ""+bean.getAdmisAoNum(), wcf)); 
	           ws.addCell(new Label(5, 6, ""+bean.getInSchAoNum(), wcf)); 
	           //台湾
	           ws.addCell(new Label(2, 7, ""+bean.getGraTaiNum(), wcf)); 
	           ws.addCell(new Label(3, 7, ""+bean.getDegreeTaiNum(), wcf)); 
	           ws.addCell(new Label(4, 7, ""+bean.getAdmisTaiNum(), wcf)); 
	           ws.addCell(new Label(5, 7, ""+bean.getInSchTaiNum(), wcf)); 
	        
	           wwb.write();
		       wwb.close();
								
//			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-6-1-4国外及港澳台学生情况（时点）.xls");
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
		  J614_Excel excel = new J614_Excel();
		  boolean flag = excel.export_J614(path);
		  if(flag){
			  System.out.println("成功！");
		  }else{
			  System.out.println("不成功！");
		  }
	}


}
