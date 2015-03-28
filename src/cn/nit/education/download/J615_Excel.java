package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cn.nit.bean.table6.T622_Bean;
import cn.nit.dao.table6.T622_Dao;
import cn.nit.util.ExcelUtil;

public class J615_Excel {
	
	 public static boolean export_J615(String path,String year){
			
			T622_Dao T622_dao = new T622_Dao();
			
//			//获取当前年份
//			Date time = new Date();
//			String currentTime = time.toString();
//			String year = currentTime.substring(currentTime.length()-4, currentTime.length());
		
			List<T622_Bean> list = T622_dao.getAllList(year);
			
			String sheetName = "J-6-1-5近一届本科生录取标准及人数（时点）";
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("省份");columns.add("批次");
			columns.add("文科录取数");columns.add("理科录取数");
			columns.add("文科批次最低控制线");columns.add("理科批次最低控制线");
			columns.add("文科当年录取平均分");columns.add("理科当年录取平均分");
			columns.add("说明");
		
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("Province", 1);maplist.put("Batch", 2);
			maplist.put("LibEnrollNum", 3);maplist.put("SciEnrollNum", 4);
			maplist.put("LibLowestScore", 5);maplist.put("SciLowestScore", 6);
			maplist.put("LibAvgScore", 7);maplist.put("SciAvgScore", 8);
			maplist.put("Note", 9);
			
			ByteArrayOutputStream byteArrayOutputStream = null;		
			try {
									
				byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

				File file = new File(path,"J-6-1-5近一届本科生录取标准及人数（时点）.xls");
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
			  J615_Excel excel = new J615_Excel();
			  boolean flag = excel.export_J615(path,"2014");
			  if(flag){
				  System.out.println("成功！");
			  }else{
				  System.out.println("不成功！");
			  }
			}



}
