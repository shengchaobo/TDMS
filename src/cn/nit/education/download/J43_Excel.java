package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.nit.bean.table4.T411_Bean;
import cn.nit.dao.table4.T411_Dao;
import cn.nit.util.ExcelUtil;

public class J43_Excel {
	
	public static boolean export_J43(String path){
		
		T411_Dao T411_dao = new T411_Dao();
		
		List<T411_Bean> list = T411_dao.getJ43List();
				
		String sheetName = "J-4-3相关管理人员基本信息（时点）";
		
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("姓名");columns.add("工号");columns.add("性别");columns.add("出生年月");
		columns.add("入校时间");columns.add("管理人员类别");columns.add("单位号");columns.add("单位名称");
		columns.add("学历");columns.add("最高学历");columns.add("专业技术职称");
		
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("teaName", 1);maplist.put("teaId", 2);maplist.put("gender", 3);maplist.put("birthday", 4);
		maplist.put("admisTime", 5);maplist.put("idcode", 6);maplist.put("fromUnit", 8);maplist.put("unitId", 7);
        maplist.put("education", 9);maplist.put("topDegree", 10);
		maplist.put("MajTechTitle", 11);
		
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
								
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-4-3相关管理人员基本信息.xls");
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
