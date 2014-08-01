package cn.nit.excel.imports.table3;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.table3.S31_Bean;



import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class S31Excel {
	
	


	public ByteArrayOutputStream exportExcel(List<S31_Bean> list){
		ByteArrayOutputStream fos=null;
		String path="/cn/nit/excel/downloads/table3/S31.xls";
		String rPath=this.getClass().getResource("/"+path).getPath();
		try{
			rPath = URLDecoder.decode(rPath , "UTF-8");
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();		
		}
		
		try{
			System.out.println(rPath);
			fos = new ByteArrayOutputStream();
			Workbook wb = Workbook.getWorkbook(new File(rPath));
			
			
			WritableWorkbook wwb = Workbook.createWorkbook(fos,wb);
			WritableCellFormat normalFormat = new WritableCellFormat();
			normalFormat.setBorder(Border.ALL, BorderLineStyle.THIN,
					     jxl.format.Colour.BLACK);
			
			
			WritableSheet wws = wwb.getSheet(0);
			wws.setName("S-3-1学科建设");
			wwb.removeSheet(1);
			wwb.removeSheet(2);
			List<String> listStr = this.changeToString (list);
			int count=0;
			for(int i=3 ; i<=8; i++){
				Label label = new Label(2,i,listStr.get(count),normalFormat);
				wws.addCell(label);
				count++;
			}

			wwb.write();
			wwb.close();
			wb.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return fos;
		
	}

	
	public List<String> changeToString (List<S31_Bean> list){
		List<String> listStr = new ArrayList<String> ();
		S31_Bean s31_Bean = list.get(0);
		listStr.add(""+s31_Bean.getPostdocStation());
		listStr.add(""+s31_Bean.getDocStation());
		listStr.add(""+s31_Bean.getMasterStation());
		listStr.add(""+s31_Bean.getSumMajor());
		listStr.add(""+s31_Bean.getNewMajor());
		listStr.add(""+s31_Bean.getJuniorMajor());
		return listStr;

	}

}
