package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cn.nit.bean.table4.J413_Bean;
import cn.nit.dao.table4.T411_Dao;
import cn.nit.util.ExcelUtil;

public class J413_Excel {
	
	public static boolean export_J413(String path){
		
		T411_Dao T411_dao = new T411_Dao();
		Calendar a = Calendar.getInstance();
		String year = String.valueOf(a.get(Calendar.YEAR));
		List<J413_Bean> list = T411_dao.getTotalList(year);
				
		String sheetName = "J-4-1-3其他师资信息（时点）";
		
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("姓名");columns.add("工号");columns.add("性别");columns.add("出生年月");
		columns.add("入校时间");columns.add("任职状态");columns.add("师资类别");columns.add("单位号");
		columns.add("单位名称");columns.add("学历");columns.add("最高学位");
		columns.add("专业技术职称");columns.add("学科类别");columns.add("导师类别");

		
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("teaName", 1);maplist.put("teaId", 2);maplist.put("gender", 3);maplist.put("birthday", 4);
		maplist.put("admisTime", 5);maplist.put("teaState", 6);maplist.put("idcode", 7);maplist.put("unitId", 8);
		maplist.put("fromUnit", 9);maplist.put("education", 10);maplist.put("topDegree", 11);maplist.put("majTechTitle", 12);
		maplist.put("subjectClass", 13);maplist.put("tutorType", 14);
		
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
								
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-4-1-3其他师资信息.xls");
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
	
	public static void main(String args[]){
		String path = "E:/test";
		boolean flag = J413_Excel.export_J413(path);
		if(flag){
		System.out.println("成功");
		}else{
			System.out.println("失败");
		}
	}

}
