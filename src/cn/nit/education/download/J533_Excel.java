package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cn.nit.bean.table5.T533_Bean;
import cn.nit.dao.table5.T533DAO;
import cn.nit.excel.imports.table5.T533Excel;
import cn.nit.util.ExcelUtil;

public class J533_Excel {
	
	 public static boolean export_J533(String path,String year){
			
			T533DAO T533_dao = new T533DAO();
			T533Excel t533Excel = new T533Excel();
			
//			//年份
//			Calendar a = Calendar.getInstance();
//			String year = String.valueOf(a.get(Calendar.YEAR));
			
			List<T533_Bean> list = T533_dao.totalListEdu(year);
					
			String sheetName = "J-5-3-3分专业（大类）实验情况（学年）";
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("校内专业（大类）代码");columns.add("校内专业（大类）名称");columns.add("有实验课程（门）");
			columns.add("独立设置的实验课程（门）");
			columns.add("实验开出率（%）");columns.add("综合性、设计性实验教学（门）");

			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("MajorName", 1);maplist.put("MajorID", 2);maplist.put("ExpCSNum", 3);
			maplist.put("IndepentExpCSNum", 4);
			maplist.put("ExpRatio", 5);maplist.put("DesignExpCSNum", 6);
			
			ByteArrayOutputStream byteArrayOutputStream = null;		
			try {
									
				byteArrayOutputStream = t533Excel.exportExcel(list, sheetName, maplist,columns);

				File file = new File(path,"J-5-3-3分专业（大类）实验情况（学年）.xls");
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
