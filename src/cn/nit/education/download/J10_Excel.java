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
import cn.nit.util.TimeUtil;

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
	             
	           //写入表头
	           ws.addCell(new Label(0, 2, "联系方式", wcf)); 
	           ws.addCell(new Label(0, 8, "学校概况", wcf)); 
	           ws.addCell(new Label(0, 19, "校区地址", wcf));  
	           ws.addCell(new Label(1, 2, "1.学校地址", wcf)); 
	           ws.addCell(new Label(1, 3, "2.学校办公电话号码", wcf)); 
	           ws.addCell(new Label(1, 4, "3.学校办公传真号码", wcf)); 
	           ws.addCell(new Label(1, 5, "4.学校填报负责人", wcf)); 
	           ws.addCell(new Label(1, 8, "5.学校名称", wcf)); 
	           ws.addCell(new Label(1, 9, "6.代码", wcf)); 
	           ws.addCell(new Label(1, 10, "7.英文名称", wcf)); 
	           ws.addCell(new Label(1, 11, "8.办学类型", wcf)); 
	           ws.addCell(new Label(1, 12, "9.学校性质", wcf)); 
	           ws.addCell(new Label(1, 13, "10.举办者", wcf)); 
	           ws.addCell(new Label(1, 14, "11.主管部门", wcf)); 
	           ws.addCell(new Label(1, 15, "12.学校网址", wcf)); 
	           ws.addCell(new Label(1, 16, "13.招生批次", wcf)); 
	           ws.addCell(new Label(1, 17, "14.开办本科教育年份", wcf));
	           ws.addCell(new Label(1, 18, "15.多媒体反映", wcf)); 
	           ws.addCell(new Label(1, 19, "16.校区名称", wcf));
	           ws.addCell(new Label(2,5,"姓名",wcf));
	           ws.addCell(new Label(2,6,"联系电话",wcf));
	           ws.addCell(new Label(2,7,"联系电子邮箱",wcf));
	           ws.addCell(new Label(2,19,"瑶湖校区",wcf));
	           ws.addCell(new Label(2,20,"彭桥校区",wcf));
	           
	           //合并表头
	           ws.mergeCells(0, 2, 0, 7);
	           ws.mergeCells(0, 8, 0, 18);
	           ws.mergeCells(0, 19, 0, 20);
	           ws.mergeCells(1, 2, 2, 2);
	           ws.mergeCells(1, 3, 2, 3);
	           ws.mergeCells(1, 4, 2, 4);
	           ws.mergeCells(1, 5, 1, 7);
	           ws.mergeCells(1, 19, 1, 20);
	           ws.mergeCells(1, 8, 2, 8);
	           ws.mergeCells(1, 9, 2, 9);
	           ws.mergeCells(1, 10, 2, 10);
	           ws.mergeCells(1, 11, 2, 11);
	           ws.mergeCells(1, 12, 2, 12);
	           ws.mergeCells(1, 13, 2, 13);
	           ws.mergeCells(1, 14, 2, 14);
	           ws.mergeCells(1, 15, 2, 15);
	           ws.mergeCells(1, 16, 2, 16);
	           ws.mergeCells(1, 17, 2, 17);
	           ws.mergeCells(1, 18, 2, 18);
	           
	           //写入数据
	           ws.addCell(new Label(3, 2, bean.getSchAddress().toString(), wcf1)); 
	           ws.addCell(new Label(3, 3, bean.getSchTel().toString(), wcf1));
	           ws.addCell(new Label(3, 4, bean.getSchFax().toString(), wcf1));
	           ws.addCell(new Label(3, 5, bean.getSchFillerName().toString(), wcf1));
	           ws.addCell(new Label(3, 6, bean.getSchFillerTel().toString(), wcf1));
	           ws.addCell(new Label(3, 7, bean.getSchFillerEmail().toString(), wcf1)); 
	           ws.addCell(new Label(3, 8, bean.getSchName().toString(), wcf1));
	           ws.addCell(new Label(3, 9, bean.getSchID().toString(), wcf1));
	           ws.addCell(new Label(3, 10, bean.getSchEnName().toString(), wcf1));
	           ws.addCell(new Label(3, 11, bean.getSchType().toString(), wcf1));
	           ws.addCell(new Label(3, 12, bean.getSchQuality().toString(), wcf1));
	           ws.addCell(new Label(3, 13, bean.getSchBuilder().toString(), wcf1));
	           ws.addCell(new Label(3, 14, bean.getMajDept().toString(), wcf1));
	           ws.addCell(new Label(3, 15, bean.getSchUrl().toString(), wcf1));
	           ws.addCell(new Label(3, 16, bean.getAdmissonBatch().toString(), wcf1));
	           ws.addCell(new Label(3, 17, TimeUtil.changeFormat5(bean.getSch_BeginTime()), wcf1));
	           ws.addCell(new Label(3, 18, bean.getMediaUrl().toString(), wcf1));
	           ws.addCell(new Label(3, 19, bean.getYaohuSchAdd().toString(), wcf1));
	           ws.addCell(new Label(3, 20, bean.getPengHuSchAdd().toString(), wcf1));
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
