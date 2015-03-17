package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.nit.bean.table1.T12_Bean;
import cn.nit.bean.table6.T631_Bean;
import cn.nit.dao.table1.T13DAO;
import cn.nit.dao.table6.T631_Dao;
import cn.nit.pojo.table6.T631POJO;
import cn.nit.util.ExcelUtil;

public class J619_Excel {
	
	
public static boolean export_J619(String path){
		
		T631_Dao T631_dao = new T631_Dao();
		
		//获取当前年份
		Date time = new Date();
		String currentTime = time.toString();
		String year = currentTime.substring(currentTime.length()-4, currentTime.length());
		
		List<T631POJO> list = T631_dao.getAllList(year);

		String sheetName = "J-6-1-9应届本科毕业生分专业毕业就业情况（时点）    ";
		
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("校内专业（大类）代码");columns.add("校内专业（大类）名称");columns.add("应届毕业生数");
		columns.add("应届生中未按时毕业数");columns.add("授予学位数");columns.add("应届就业人数");
//		columns.add("应届就业人数");
		
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("MajorID", 1);maplist.put("MajorName", 2);maplist.put("ThisYearGraduNum", 3);
		maplist.put("ThisYearNotGraduNum", 4);maplist.put("AwardDegreeNum", 5);maplist.put("SumEmployNum", 6);
//		maplist.put("SumEmployNum", 6);
		
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
								
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-6-1-9应届本科毕业生分专业毕业就业情况（时点）    .xls");
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
		  J619_Excel excel = new J619_Excel();
		  boolean flag = excel.export_J619(path);
		  if(flag){
			  System.out.println("成功！");
		  }else{
			  System.out.println("不成功！");
		  }
	}

	

}
