package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cn.nit.bean.table5.T531_Bean;
import cn.nit.dao.table5.T531DAO;
import cn.nit.util.ExcelUtil;

public class J531_Excel {
	
	 public static boolean export_J531(String path,String year){
			
			T531DAO T531_dao = new T531DAO();
			
//			//年份
//			Calendar a = Calendar.getInstance();
//			String year = String.valueOf(a.get(Calendar.YEAR));
//			
			List<T531_Bean> list = T531_dao.totalList(year);
					
			String sheetName = "J-5-3-1人才培养模式创新实验项目（学年）";
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("名称");columns.add("类型");columns.add("设立时间");columns.add("参与学生数（人）");

			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("Name", 1);maplist.put("Type", 2);maplist.put("BuildTime", 3);maplist.put("JoinStuNum", 4);
			
			ByteArrayOutputStream byteArrayOutputStream = null;		
			try {
									
				byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

				File file = new File(path,"J-5-3-1人才培养模式创新实验项目（学年）.xls");
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
