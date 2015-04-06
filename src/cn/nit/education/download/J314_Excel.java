package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.nit.dao.table3.T313_DAO;
import cn.nit.pojo.table3.T313POJO;
import cn.nit.util.ExcelUtil;

public class J314_Excel {
	
	
	public static boolean export_J314(String path,String year){
		
		T313_DAO T313_dao = new T313_DAO();
//		Calendar a = Calendar.getInstance();
//		String year = String.valueOf(a.get(Calendar.YEAR));
		List<T313POJO> list = T313_dao.totalList(year);

		String sheetName = "J-3-1-4重点学科（时点）";
		
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("重点学科名称");columns.add("学科代码");columns.add("单位名称");columns.add("单位号");
		columns.add("学科门类");columns.add("级别");
		
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("DiscipName", 1);maplist.put("DiscipID", 2);maplist.put("UnitName", 3);maplist.put("UnitID", 4);
		maplist.put("DiscipType", 5);maplist.put("Level", 6);
		
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
								
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-3-1-4重点学科（时点）.xls");
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
		export_J314("E:","2015");
	}
	
	
	


}
