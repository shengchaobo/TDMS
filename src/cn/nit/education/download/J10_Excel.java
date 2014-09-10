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

import cn.nit.bean.table1.T11Bean;
import cn.nit.bean.table4.T411_Bean;
import cn.nit.dao.table1.T11DAO;
import cn.nit.dao.table4.T411_Dao;
import cn.nit.util.ExcelUtil;

public class J10_Excel {
	
	public static boolean export_J10(String path,String year) throws Exception{
		
		T11DAO T11_dao = new T11DAO();
		
		List<T11Bean> list = T11_dao.forExcel(year);
		T11Bean bean = list.get(0);
		
		String sheetName = "J-1-0联系方式（时点）";
		
		  //    设置单元格的文字格式(标题)
        WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.BOLD,false,
                 UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
        WritableCellFormat wcf = new WritableCellFormat(wf);
        wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
        wcf.setAlignment(Alignment.CENTRE);
        wcf.setBorder(Border.ALL, BorderLineStyle.THIN,
				     jxl.format.Colour.BLACK);
        wcf.setAlignment(jxl.write.Alignment.LEFT);
//        ws.setRowView(1, 500);
        
        //设置表中文字格式
		WritableCellFormat wcf1 = new WritableCellFormat();
		wcf1.setBorder(Border.ALL, BorderLineStyle.THIN,
				     jxl.format.Colour.BLACK); 
       
		ByteArrayOutputStream  byteArrayOutputStream= null;	
		WritableWorkbook wwb;
		
		try {
			
			   byteArrayOutputStream = new ByteArrayOutputStream();
	           wwb = Workbook.createWorkbook(byteArrayOutputStream);
	           WritableSheet ws = wwb.createSheet(sheetName, 0);        // 创建一个工作表
	           
	           ws.setRowView(1, 500);
	           
	           ws.addCell(new Label(0, 0, sheetName, wcf)); 
	           ws.mergeCells(0, 0, 4, 0);
	           
	           //表头
	           ws.addCell(new Label(0, 2, "学校地址", wcf)); 
	           ws.addCell(new Label(0, 3, "学校办公电话", wcf)); 
	           ws.addCell(new Label(0, 4, "学校办公传真电话", wcf)); 
	           ws.addCell(new Label(0, 5, "学校填报负责人", wcf)); 
	           ws.addCell(new Label(1, 5, "姓名", wcf)); 
	           ws.addCell(new Label(1, 6, "联系电话", wcf)); 
	           ws.addCell(new Label(1, 7, "联系电子邮箱", wcf)); 
	           
	           //合并表头
	           ws.mergeCells(0, 5, 0, 7);
	           
	           //写入数据
	           
	           ws.addCell(new Label(1, 2, bean.getSchAddress(), wcf1)); 
	           ws.addCell(new Label(1, 3, bean.getSchTel(), wcf1)); 
	           ws.addCell(new Label(1, 4,bean.getSchFax(), wcf1)); 
	           ws.addCell(new Label(2, 5, bean.getSchFillerName(), wcf1)); 
	           ws.addCell(new Label(2, 6, bean.getSchFillerTel(), wcf1)); 
	           ws.addCell(new Label(2, 7, bean.getSchFillerEmail(), wcf1));
	           
	           //合并表内容
	           ws.mergeCells(1, 2, 2, 2);
	           ws.mergeCells(1, 3, 2, 3);
	           ws.mergeCells(1, 4, 2, 4);
	           wwb.write();
		       wwb.close();
								
//			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-1-0联系方式（时点）.xls");
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
		J10_Excel excel = new J10_Excel();
		
	}
}
