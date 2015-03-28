package cn.nit.education.download;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cn.nit.bean.table2.T26_Bean;
import cn.nit.dao.table2.T26_Dao;
import cn.nit.util.ExcelUtil;

public class J262_Excel {

	public static boolean export_J262(String path, String year){
		
/*		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		String year = dateFormat.format(new Date());*/
		
		T26_Dao T26_dao = new T26_Dao();		
		List<T26_Bean> list = T26_dao.getYearInfo(year);
				
		String sheetName = "J-2-6-2校外实习、实训基地（时点） ";
		
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("名称");columns.add("院系（单位）号");columns.add("院系（单位）名称");columns.add("地址");
		columns.add("面向专业");columns.add("每次可接纳学生数（个）");columns.add("当年接纳学生总数（人）");
		
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("practiseBase", 1);maplist.put("teaUnitID", 2);maplist.put("teaUnit", 3);maplist.put("address", 4);
		maplist.put("forMajor", 5);maplist.put("stuNumEachTime", 6);maplist.put("stuNumEachYear", 7);
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
								
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-2-6-2校外实习、实训基地 .xls");
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
