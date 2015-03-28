package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.nit.bean.table4.T451_Bean;
import cn.nit.dao.table4.T451_Dao;
import cn.nit.util.ExcelUtil;

public class J451_Excel {
	
	
	
	public static boolean export_J451(String path,String year){
		
		T451_Dao T451_dao = new T451_Dao();
//		Calendar a = Calendar.getInstance();
//		String year = String.valueOf(a.get(Calendar.YEAR));
		List<T451_Bean> list = T451_dao.totalList(year);
		for(int i=0;i<list.size();i++){
			list.get(i).setOrgType(null);
		}
				
		String sheetName = "J-4-5-1教师教学发展机构（学年）";
		
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("机构名称");columns.add("单位号");columns.add("工作计划");columns.add("培训次数");
		columns.add("培训人次");
		
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("orgName", 1);maplist.put("unitId", 2);maplist.put("orgType", 3);maplist.put("trainTimes", 4);
		maplist.put("trainPerTimes", 5);
		
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
								
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-4-5-1教师教学发展机构.xls");
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
