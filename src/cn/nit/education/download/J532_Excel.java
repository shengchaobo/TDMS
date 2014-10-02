package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cn.nit.bean.table5.T532Bean;
import cn.nit.dao.table5.T532DAO;
import cn.nit.util.ExcelUtil;

public class J532_Excel {
	
	 public static boolean export_J532(String path){
			
			T532DAO T532_dao = new T532DAO();
			
			//年份
			Calendar a = Calendar.getInstance();
			String year = String.valueOf(a.get(Calendar.YEAR));
			
			List<T532Bean> list = T532_dao.totalList(year);
					
			String sheetName = "J-5-3-2实验教学示范中心（时点）";
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("名称");columns.add("学科名称");columns.add("学科代码");columns.add("级别");
			columns.add("设立时间");

			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("CenterName", 1);maplist.put("FromSubject", 2);maplist.put("SubjectID", 3);maplist.put("CenterLevel", 4);
			maplist.put("BuildTime", 5);
			
			ByteArrayOutputStream byteArrayOutputStream = null;		
			try {
									
				byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

				File file = new File(path,"J-5-3-2实验教学示范中心（时点）.xls");
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
			 String path = "D:\\江西项目\\相关表\\ExcelTest";
			  J532_Excel excel = new J532_Excel();
			  boolean flag = excel.export_J532(path);
			  if(flag){
				  System.out.println("成功！");
			  }else{
				  System.out.println("不成功！");
			  }
		}


}
