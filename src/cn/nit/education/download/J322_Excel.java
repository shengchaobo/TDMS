package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.nit.bean.table3.T321_Bean;
import cn.nit.dao.table3.T321_DAO;
import cn.nit.util.ExcelUtil;

public class J322_Excel {
	
	
	public static boolean export_J322(String path,String year){
		
		T321_DAO T321_dao = new T321_DAO();
//		Calendar a = Calendar.getInstance();
//		String year = String.valueOf(a.get(Calendar.YEAR));
		List<T321_Bean> list = T321_dao.totalList(year);
				
		String sheetName = "J-3-2-2大类培养基本情况表（时点）";
		
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("大类名称");columns.add("大类代码");columns.add("分流时间");columns.add("所属单位号");
		columns.add("校内专业代码");columns.add("校内专业名称");

		
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("MainClassName", 1);maplist.put("MainClassID", 2);maplist.put("ByPassTime", 3);maplist.put("UnitID", 4);
		maplist.put("MajorID", 5);maplist.put("MajorNameInSch", 6);
		
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
								
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-3-2-2大类培养基本情况表（时点）.xls");
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
