package cn.nit.education.download;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table1.T11_Bean;
import cn.nit.bean.table4.T411_Bean;
import cn.nit.dao.table1.T11DAO;
import cn.nit.dao.table4.T411_Dao;
import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

public class J11_Excel {
	
	public static boolean export_J11(String path,String year) {
		
		T11DAO T11_dao = new T11DAO();
		
		//获取当前年份
//		Date time = new Date();
//		String currentTime = time.toString();
//		String year = currentTime.substring(currentTime.length()-4, currentTime.length());
		
		List<T11_Bean> list = T11_dao.forExcel(year);
//		T11_Bean  bean;
//		if(list==null){
//			System.out.println("无列表");
//			bean = new T11_Bean();
//		}else if(list.size()==0){
//			System.out.println("无列表");
//			bean = new T11_Bean();
//		}else{
//			System.out.println("有列表");
//			bean = list.get(0);
//		}
		
		
		String sheetName = "J-1-1学校概况（时点）";
	
		
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
	           ws.mergeCells(0, 0, 4, 0);
	             
	           //写入表头
	           ws.addCell(new Label(0, 2, "项目", wcf)); 
	           ws.addCell(new Label(1, 2, "内容", wcf)); 
	           
	           ws.addCell(new Label(0, 3, "1.学校名称", wcf)); 
	           ws.addCell(new Label(0, 4, "2.代码", wcf));  
	           ws.addCell(new Label(0, 5, "3.英文名称", wcf)); 
	           ws.addCell(new Label(0, 6, "4.办学类型", wcf)); 
	           ws.addCell(new Label(0, 7, "5.办学性质", wcf)); 
	           ws.addCell(new Label(0, 8, "6.举办者", wcf)); 
	           ws.addCell(new Label(0, 9, "7.主管部门", wcf)); 
	           ws.addCell(new Label(0, 10, "8.学校网址", wcf)); 
	           ws.addCell(new Label(0, 11, "9.招生批次", wcf)); 
	           ws.addCell(new Label(0, 12, "10.开办本科教育年份", wcf)); 
	          
	           if(list.size()!=0){
	        	   T11_Bean bean = list.get(0);
	        	   ws.addCell(new Label(1, 3, bean.getSchName().toString(), wcf1)); 
		           ws.addCell(new Label(1, 4, bean.getSchID().toString(), wcf1));  
		           ws.addCell(new Label(1, 5,  bean.getSchEnName().toString(), wcf1)); 
		           ws.addCell(new Label(1, 6, bean.getSchType().toString(), wcf1)); 
		           ws.addCell(new Label(1, 7, bean.getSchQuality().toString(), wcf1)); 
		           ws.addCell(new Label(1, 8, bean.getSchBuilder().toString(), wcf1)); 
		           ws.addCell(new Label(1, 9, bean.getMajDept().toString(), wcf1)); 
		           ws.addCell(new Label(1, 10, bean.getSchUrl().toString(), wcf1)); 
		           ws.addCell(new Label(1, 11, bean.getAdmissonBatch().toString(), wcf1)); 
		           ws.addCell(new Label(1, 12, bean.getSch_BeginTime().toString(), wcf1));
	           }
	         
	          
	           
//	           //写入数据
//	           ws.addCell(new Label(3, 2, bean.getSchAddress(), wcf1)); 
//	           ws.addCell(new Label(3, 3, bean.getSchTel(), wcf1));
//	           ws.addCell(new Label(3, 4, bean.getSchFax(), wcf1));
//	           ws.addCell(new Label(3, 5, bean.getSchFillerName(), wcf1));
//	           ws.addCell(new Label(3, 6, bean.getSchFillerTel(), wcf1));
//	           ws.addCell(new Label(3, 7, bean.getSchFillerEmail(), wcf1)); 
//	           ws.addCell(new Label(3, 8, bean.getSchName(), wcf1));
//	           ws.addCell(new Label(3, 9, bean.getSchID(), wcf1));
//	           ws.addCell(new Label(3, 10, bean.getSchEnName(), wcf1));
//	           ws.addCell(new Label(3, 11, bean.getSchType(), wcf1));
//	           ws.addCell(new Label(3, 12, bean.getSchQuality(), wcf1));
//	           ws.addCell(new Label(3, 13, bean.getSchBuilder(), wcf1));
//	           ws.addCell(new Label(3, 14, bean.getMajDept(), wcf1));
//	           ws.addCell(new Label(3, 15, bean.getSchUrl(), wcf1));
//	           ws.addCell(new Label(3, 16, bean.getAdmissonBatch(), wcf1));
//	           ws.addCell(new Label(3, 17, bean.getSch_BeginTime(),wcf1));
////	           if(bean.getSch_BeginTime()==null){
////	        	   ws.addCell(new Label(3, 17,"", wcf1));
////	           }else{
////	        	   ws.addCell(new Label(3, 17, TimeUtil.changeFormat5(bean.getSch_BeginTime()), wcf1));  
////	           }
//	           ws.addCell(new Label(3, 18, bean.getMediaUrl(), wcf1));
//	           ws.addCell(new Label(3, 19, bean.getYaohuSchAdd(), wcf1));
//	           ws.addCell(new Label(3, 20, bean.getPengHuSchAdd(), wcf1));
	           
	           wwb.write();
		       wwb.close();
								
//			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-1-1 学校概况（时点）.xls");
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
