package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cn.nit.bean.table5.T521_Bean;
import cn.nit.dao.table5.T521DAO;
import cn.nit.util.ExcelUtil;

public class J52_Excel {
	
	 public static boolean export_J52(String path,String year){
			
			T521DAO T521_dao = new T521DAO();
			
//			//年份
//			Calendar a = Calendar.getInstance();
//			String year = String.valueOf(a.get(Calendar.YEAR));
			
			List<T521_Bean> list = T521_dao.totalList(year);
					
			String sheetName = "J-5-2课程建设情况（时点）";
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("项目名称");columns.add("类型");columns.add("级别");columns.add("获准时间");

			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("CSName", 1);maplist.put("CSType", 2);maplist.put("CSLevel", 3);maplist.put("AppvlTime", 4);
			
			ByteArrayOutputStream byteArrayOutputStream = null;		
			try {
									
				byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

				File file = new File(path,"J-5-2课程建设情况（时点）.xls");
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
