package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.nit.bean.table3.T312_Bean;
import cn.nit.dao.table3.T312_DAO;
import cn.nit.util.ExcelUtil;

public class J313_Excel {
	
	
	
	public static boolean export_J313(String path,String year){
		
		T312_DAO T312_dao = new T312_DAO();
//		Calendar a = Calendar.getInstance();
//		String year = String.valueOf(a.get(Calendar.YEAR));
		List<T312_Bean> list = T312_dao.totalList(year);
				
		String sheetName = "J-3-1-3博士点、硕士点（时点）";
		
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("名称");columns.add("代码");columns.add("单位名称");columns.add("单位号");
		columns.add("类型");
		
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("StaName", 1);maplist.put("StaID", 2);maplist.put("UnitName", 3);maplist.put("UnitID", 4);
		maplist.put("StaType", 5);
		
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
								
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-3-1-3博士点、硕士点（时点）.xls");
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
