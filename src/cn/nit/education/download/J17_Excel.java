package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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

import cn.nit.bean.table1.S17Bean;
import cn.nit.bean.table1.S18Bean;
import cn.nit.dao.table1.T17DAO;
import cn.nit.service.table1.S17Service;
import cn.nit.service.table1.S18Service;

public class J17_Excel {
	
public static boolean export_J17(String path,String year) throws Exception{
		
		S17Service S17_Ser = new S17Service();
		S18Service S18_Ser = new S18Service();
		
		S17Bean  s17Bean = S17_Ser.loadData(year);//校友会
		S18Bean  s18Bean = S18_Ser.loadData(year);//合作协议
		
		String sheetName = "J-1-7校友会与社会合作（时点）";
		
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
	           
	           //表头
	           ws.addCell(new Label(0, 2, "项目", wcf)); 
	           ws.addCell(new Label(2, 2, "内容", wcf)); 
	           ws.addCell(new Label(0, 3, "1.校友会（个）", wcf)); 
	           ws.addCell(new Label(0, 6, "2.签订合作协议机构（个）", wcf)); 
	           ws.addCell(new Label(1, 3, "总数", wcf)); 
	           ws.addCell(new Label(1, 4, "其中：境外", wcf)); 
	           ws.addCell(new Label(1, 5, "     境内", wcf)); 
	           ws.addCell(new Label(1, 6, "机构总数", wcf)); 
	           ws.addCell(new Label(1, 7, "其中：学术机构", wcf)); 
	           ws.addCell(new Label(1, 8, "     行业机构和企业", wcf)); 
	           ws.addCell(new Label(1, 9, "     地方政府", wcf)); 
	        
	           //合并表头
	           ws.mergeCells(0, 2, 1, 2);
	           ws.mergeCells(0, 3, 0, 5);
	           ws.mergeCells(0, 6, 0, 9);
	           
	           
	       
	           //写入数据
	           
	           ws.addCell(new Label(2, 3, ""+s17Bean.getSumSchFriNum(), wcf)); 
	           ws.addCell(new Label(2, 4, ""+s17Bean.getOutlandNum(), wcf)); 
	           ws.addCell(new Label(2, 5, ""+s17Bean.getInlandNum(), wcf)); 
	           ws.addCell(new Label(2, 6, ""+s18Bean.getSumAgreeNum(), wcf)); 
	           ws.addCell(new Label(2, 7, ""+s18Bean.getAcademicNum(), wcf)); 
	           ws.addCell(new Label(2, 8, ""+s18Bean.getIndustryNum(), wcf)); 
	           ws.addCell(new Label(2, 9, ""+s18Bean.getLocalGoverNum(), wcf)); 
	        
								
//			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-1-7校友会与社会合作（时点）.xls");
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
