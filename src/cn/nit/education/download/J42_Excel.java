package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.nit.bean.table4.T42_Bean;
import cn.nit.dao.table4.T42_Dao;
import cn.nit.util.ExcelUtil;

public class J42_Excel {
	
	public static boolean export_J42(String path, String year){
		
		T42_Dao T42_dao = new T42_Dao();
/*		Calendar a = Calendar.getInstance();
		String year = String.valueOf(a.get(Calendar.YEAR));*/
		List<T42_Bean> list = T42_dao.totalList(year);
				
		String sheetName = "J-4-2校领导基本信息（时点）";
		
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("姓名");columns.add("工号");columns.add("职务");columns.add("性别");columns.add("出生年月");
		columns.add("入校时间");columns.add("学历");columns.add("最高学位");
		columns.add("专业技术职称");columns.add("校内分管工作");columns.add("学习和工作简历");
		
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("name", 1);maplist.put("teaId", 2);maplist.put("duty", 3);maplist.put("gender", 4);
		maplist.put("birthday", 5);maplist.put("joinSchTime", 6);maplist.put("education", 7);maplist.put("topDegree", 8);
		maplist.put("majTechTitle", 9);maplist.put("forCharge", 10);maplist.put("resume", 11);
		
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
								
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-4-2校领导基本信息.xls");
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
<<<<<<< HEAD
=======
	

>>>>>>> 3c642a643366a0b258dfc711e79b81f39eb32ffa

}
