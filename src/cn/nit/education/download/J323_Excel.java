package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.nit.bean.table3.T322_Bean;
import cn.nit.dao.table3.T322_DAO;
import cn.nit.pojo.table3.T322POJO;
import cn.nit.util.ExcelUtil;

public class J323_Excel {
	
	
	
	public static boolean export_J323(String path,String year){
		
		T322_DAO T322_dao = new T322_DAO();
//		Calendar a = Calendar.getInstance();
//		String year = String.valueOf(a.get(Calendar.YEAR));
		List<T322POJO> list = T322_dao.totalList1(year);
				
		String sheetName = "J-3-2-3优势专业情况（时点）";
		
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("校内专业名称");columns.add("校内专业代码");columns.add("类型");columns.add("起始年份");
	
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("SchMajorName", 1);maplist.put("SchMajorID", 2);maplist.put("Type", 3);maplist.put("AppvlYear", 4);
		
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
								
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-3-2-3优势专业情况.xls");
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
