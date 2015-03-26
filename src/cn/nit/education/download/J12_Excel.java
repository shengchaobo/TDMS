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
import cn.nit.dao.table1.T11DAO;

import cn.nit.util.ExcelUtil;
import cn.nit.util.TimeUtil;

public class J12_Excel {
	
	public static boolean export_J12(String path) {
		
		T11DAO T11_dao = new T11DAO();
		
		//获取当前年份
		Date time = new Date();
		String currentTime = time.toString();
		String year = currentTime.substring(currentTime.length()-4, currentTime.length());
		
		List<T11_Bean> list = T11_dao.forExcel(year);
		T11_Bean  bean;
		if(list==null){
			System.out.println("无列表");
			bean = new T11_Bean();
		}else if(list.size()==0){
			System.out.println("无列表");
			bean = new T11_Bean();
		}else{
			System.out.println("有列表");
			bean = list.get(0);
		}
		
		
		String sheetName = "J-1-2校区地址（时点）";
	
		
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
	           ws.addCell(new Label(0, 2, "序号", wcf)); 
	           ws.addCell(new Label(1, 2,"校区名称", wcf)); 
	           ws.addCell(new Label(2, 2, "地址", wcf));  
	           
	           //写入数据
	           ws.addCell(new Label(0, 3, "1", wcf1));
	           ws.addCell(new Label(0, 4, "2", wcf1));
	           ws.addCell(new Label(1, 3, "瑶湖校区", wcf1));
	           ws.addCell(new Label(1, 4, "彭桥校区", wcf1));
	           ws.addCell(new Label(2, 3, bean.getYaohuSchAdd(), wcf1));
	           ws.addCell(new Label(2, 4, bean.getPengHuSchAdd(), wcf1));
	           
//	           //写入数据
//	           ws.addCell(new Label(3, 2, bean.getSchAddress().toString(), wcf1)); 
//	           ws.addCell(new Label(3, 3, bean.getSchTel().toString(), wcf1));
//	           ws.addCell(new Label(3, 4, bean.getSchFax().toString(), wcf1));
//	           ws.addCell(new Label(3, 5, bean.getSchFillerName().toString(), wcf1));
//	           ws.addCell(new Label(3, 6, bean.getSchFillerTel().toString(), wcf1));
//	           ws.addCell(new Label(3, 7, bean.getSchFillerEmail().toString(), wcf1)); 
//	           ws.addCell(new Label(3, 8, bean.getSchName().toString(), wcf1));
//	           ws.addCell(new Label(3, 9, bean.getSchID().toString(), wcf1));
//	           ws.addCell(new Label(3, 10, bean.getSchEnName().toString(), wcf1));
//	           ws.addCell(new Label(3, 11, bean.getSchType().toString(), wcf1));
//	           ws.addCell(new Label(3, 12, bean.getSchQuality().toString(), wcf1));
//	           ws.addCell(new Label(3, 13, bean.getSchBuilder().toString(), wcf1));
//	           ws.addCell(new Label(3, 14, bean.getMajDept().toString(), wcf1));
//	           ws.addCell(new Label(3, 15, bean.getSchUrl().toString(), wcf1));
//	           ws.addCell(new Label(3, 16, bean.getAdmissonBatch().toString(), wcf1));
//	           ws.addCell(new Label(3, 17, TimeUtil.changeFormat5(bean.getSch_BeginTime()), wcf1));
//	           ws.addCell(new Label(3, 18, bean.getMediaUrl().toString(), wcf1));
//	           ws.addCell(new Label(3, 19, bean.getYaohuSchAdd().toString(), wcf1));
//	           ws.addCell(new Label(3, 20, bean.getPengHuSchAdd().toString(), wcf1));
	           wwb.write();
		       wwb.close();
								
//			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-1-2校区地址（时点）.xls");
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
		 String path = "C:\\Users\\Fan Shuangyan\\Desktop";
		  J12_Excel excel = new J12_Excel();
		  boolean flag = excel.export_J12(path);
		  if(flag){
			  System.out.println("成功！");
		  }else{
			  System.out.println("不成功！");
		  }
	}
}
