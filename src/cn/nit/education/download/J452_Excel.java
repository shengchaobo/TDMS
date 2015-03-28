package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.nit.dao.table4.J452_Dao;
import cn.nit.pojo.table4.J452POJO;
import cn.nit.util.ExcelUtil;

public class J452_Excel {
	
	public static boolean export_J452(String path, String year){
		
		J452_Dao J452_dao = new J452_Dao();
		List<J452POJO> list = J452_dao.totalList(year);				

		String sheetName = "J-4-5-2教师培训进修.交流情况（学年）";
		
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("院（系）名称");columns.add("单位号");columns.add("教师培训进修（人次）境内");columns.add("教师培训进修（人次）境外总数");
		columns.add("教师培训进修（人次）境外其中：3个月以上");
		columns.add("教师培训进修（人次）到行业培训");columns.add("教师培训进修（人次）攻读学位总数");columns.add("教师培训进修（人次）攻读学位总数其中：博士");columns.add("教师培训进修（人次）攻读学位总数其中：硕士");
		columns.add("交流教师（3个月及以上）（人次）来访境内");
		columns.add("交流教师（3个月及以上）（人次）来访境外");columns.add("交流教师（3个月及以上）（人次）出访境内");columns.add("交流教师（3个月及以上）（人次）出访境外");
		
		
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("teaUnit", 1);maplist.put("unitID", 2);maplist.put("inPlace", 3);maplist.put("outPlaceTotal", 4);
		maplist.put("outPlaceLong", 5);maplist.put("industryTrain", 6);maplist.put("degreeTrain", 7);maplist.put("doctorTrain", 8);
		maplist.put("masterTrain", 9);maplist.put("comeInPlace", 10);maplist.put("comeOutPlace", 11);maplist.put("goInPlace", 12);
		maplist.put("goOutPlace", 13);
		
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
								
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-4-5-2教师培训进修.交流情况.xls");
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
