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

public class J512_Excel {
	
	public static boolean export_J512(String path,String year){
	
			T512_DAO T512_dao = new T512_DAO();
			
//			//年份
//			Calendar a = Calendar.getInstance();
//			String year = String.valueOf(a.get(Calendar.YEAR));
			
			List<T512POJO> list = T512_dao.totalList(year);
					
			String sheetName = "J-5-1-2专业教学实施情况表（学年）";
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("校内专业（大类）名称");columns.add("校内专业（大类）代码");columns.add("开课号");columns.add("课程性质");
			columns.add("学分");columns.add("年级");
			
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("CSMajorName", 1);maplist.put("CSMajorID", 2);maplist.put("CSID", 3);maplist.put("CSNature", 4);
			maplist.put("Credit", 5);maplist.put("CSGrade", 6);
			
			ByteArrayOutputStream byteArrayOutputStream = null;		
			try {
									
				byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);
		
				File file = new File(path,"J-5-1-2专业教学实施情况表（学年）.xls");
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
		String path = "C:\\Users\\Fan Shuangyan\\Desktop\\Education";
		  J512_Excel excel = new J512_Excel();
		  boolean flag = excel.export_J512(path,"2002");
		  if(flag){
			  System.out.println("成功！");
		  }else{
			  System.out.println("不成功！");
		  }
	}




}
