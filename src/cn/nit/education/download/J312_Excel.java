package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.nit.bean.table3.T311_Bean;
import cn.nit.dao.table3.T311_DAO;
import cn.nit.util.ExcelUtil;

public class J312_Excel {
	
	
	public static boolean export_J312(String path,String year){
		
		T311_DAO T311_dao = new T311_DAO();
//		Calendar a=Calendar.getInstance();
//		String year = String.valueOf(a.get(Calendar.YEAR));
		List<T311_Bean> list = T311_dao.totalList(year);
				
		String sheetName = "J-3-1-2博士后流动站（时点）";
		
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("单位号");columns.add("单位名称");columns.add("博士后流动站名称");


		
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("UnitID", 1);maplist.put("UnitName", 2);maplist.put("PostDocStaName", 3);
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
								
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-3-1-2博士后流动站(时点).xls");
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
	
	public static void main (String args[]){
		export_J312("E:","2014");
	}
	

	

}
