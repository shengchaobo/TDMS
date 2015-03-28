package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.nit.bean.table4.T443_Bean;
import cn.nit.dao.table4.T443_Dao;
import cn.nit.util.ExcelUtil;

public class J441_Excel {
	
	
	
	public static boolean export_J441(String path, String year){
		
		T443_Dao T443_dao = new T443_Dao();
		List<T443_Bean> list = T443_dao.totalList(year);
				
		String sheetName = "J-4-4-1高层次人才（时点）";
		
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("姓名");columns.add("工号");columns.add("类型");columns.add("研究方向");
		columns.add("获得时间");
		
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("name", 1);maplist.put("teaId", 2);maplist.put("type", 3);maplist.put("resField", 4);
		maplist.put("gainTime", 5);
		
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
								
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-4-4-1高层次人才.xls");
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
