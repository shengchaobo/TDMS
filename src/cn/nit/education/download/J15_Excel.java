package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cn.nit.bean.table1.T151_Bean;
import cn.nit.dao.table1.T151DAO;
import cn.nit.dao.table1.T152DAO;
import cn.nit.util.ExcelUtil;

public class J15_Excel {
	
	public static boolean export_J15(String path,String year){
		
		T151DAO T151dao = new T151DAO();
		T152DAO T152dao = new T152DAO();
		
//		//获取当前年份
//		Date time = new Date();
//		String currentTime = time.toString();
//		String year = currentTime.substring(currentTime.length()-4, currentTime.length());
//		
		List<T151_Bean> list = T151dao.totalList(year);
		List<T151_Bean> list1 = T152dao.totalListAll(year);
		
		list.addAll(list1);

		String sheetName = "J-1-5实验室和科研基地（时点）";
		
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("实验室和科研基地名称");columns.add("类别");columns.add("共建情况");
		columns.add("是否对本科生开放");
		
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("ResInsName", 1);maplist.put("Type", 2);maplist.put("BuildCondition", 3);
		maplist.put("BiOpen", 4);
		
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
								
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-1-5实验室和科研基地（时点）.xls");
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
