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


import cn.nit.pojo.table2.J261_POJO;
import cn.nit.dao.table2.J261_Dao;
import cn.nit.util.ExcelUtil;

public class J261_Excel {
	
	public static boolean export_J261(String path, String year){
		
/*		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		String year = dateFormat.format(new Date());*/
		
		J261_Dao J261_dao = new J261_Dao();		
		List<J261_POJO> list = J261_dao.getYearInfo(year);
				
		String sheetName = "J-2-6-1本科实验、实习、实训场所（时点） ";
		
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("名称");columns.add("院系(单位)号");columns.add("院系（单位）名称");
		columns.add("面积（平方米）");columns.add("实验室性质");columns.add("面向专业");columns.add("学年度承担的实验教学人时数（人时）");
		columns.add("学年度承担的实验教学人次数（人次）");columns.add("本科生实验、实习、实训项目数（个）");
		columns.add("最大可容纳的学生数（人）");
		
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("expCenterName", 1);maplist.put("teaUnitID", 2);maplist.put("teaUnit", 3);
		maplist.put("area", 4);maplist.put("nature", 5);maplist.put("forMajor", 6);
		maplist.put("expHour", 7);maplist.put("expTimes", 8);
		maplist.put("practiseItemNum", 9);maplist.put("stuNum", 10);
		
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
								
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-2-6-1本科实验、实习、实训场所.xls");
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
