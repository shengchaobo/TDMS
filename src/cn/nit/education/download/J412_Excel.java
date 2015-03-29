package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.nit.bean.table4.T413_Bean;
import cn.nit.dao.table4.T413_Dao;
import cn.nit.util.ExcelUtil;

public class J412_Excel {
	
	
	public static boolean export_J412(String path){
		
		T413_Dao T413_dao = new T413_Dao();
		List<T413_Bean> list = T413_dao.totalList();
				
		String sheetName = "J-4-1-2外聘教师基本信息";
		
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("姓名");columns.add("工号");columns.add("性别");columns.add("出生年月");
		columns.add("聘任时间");columns.add("任职状态");columns.add("聘期");columns.add("单位号");
		columns.add("单位名称");columns.add("学历");columns.add("最高学位");
		columns.add("专业技术职称");columns.add("学科类别");columns.add("工作单位类别");columns.add("导师类别");
		columns.add("地区");

		
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("name", 1);maplist.put("teaId", 2);maplist.put("gender", 3);maplist.put("birthday", 4);
		maplist.put("hireBeginTime", 5);maplist.put("teaState", 6);maplist.put("hireTimeLen", 7);maplist.put("unitId", 8);
		maplist.put("unitName", 9);maplist.put("education", 10);maplist.put("topDegree", 11);maplist.put("techTitle", 12);
		maplist.put("subjectClass", 13);maplist.put("workUnitType", 14);maplist.put("tutorType", 15);maplist.put("region", 16);
		
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
								
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-4-1-2外聘教师基本信息.xls");
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
