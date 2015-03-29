package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cn.nit.dao.table5.T512_DAO;
import cn.nit.pojo.table5.T512POJO;
import cn.nit.util.ExcelUtil;

public class J511_Excel {
	
  public static boolean export_J511(String path,String year){
		
		T512_DAO T512_dao = new T512_DAO();
		
//		//年份
//		Calendar a = Calendar.getInstance();
//		String year = String.valueOf(a.get(Calendar.YEAR));
		
		List<T512POJO> list = T512_dao.totalList(year);
				
		String sheetName = "J-5-1-1开课情况（学年）";
		
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("开课号");columns.add("课程名称");columns.add("课程号");columns.add("课程类别");
		columns.add("是否双语授课");columns.add("考核方式");columns.add("学时");columns.add("开课单位");
		columns.add("单位号");columns.add("授课教师");columns.add("授课教师工号");columns.add("本科学生数");
		columns.add("教材使用情况");columns.add("是否规划教材");

		
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("CSMajorID", 1);maplist.put("CSName", 2);maplist.put("CSID", 3);maplist.put("CSType", 4);
		maplist.put("IsDoubleCS", 5);maplist.put("ExamWay", 6);maplist.put("SumCSHour", 7);maplist.put("CSUnit", 8);
		maplist.put("UnitID", 9);maplist.put("CSTea", 10);maplist.put("TeaID", 11);maplist.put("StuNum", 12);
		maplist.put("BookUseInfo", 13);maplist.put("IsPlanbook", 14);
		
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
								
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-5-1-1开课情况（学年）.xls");
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
		  J511_Excel excel = new J511_Excel();
		  boolean flag = excel.export_J511(path,"2015");
		  if(flag){
			  System.out.println("成功！");
		  }else{
			  System.out.println("不成功！");
		  }
	}
	


}
