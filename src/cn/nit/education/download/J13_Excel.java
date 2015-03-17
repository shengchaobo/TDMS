package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

	
import cn.nit.bean.table1.T12_Bean;
import cn.nit.dao.table1.T12DAO;
import cn.nit.util.ExcelUtil;

public class J13_Excel {
	
	public static boolean export_J13(String path){
		
		T12DAO T12dao = new T12DAO();
		
		List<T12_Bean> list = T12dao.totalList();
				
		String sheetName = "J-1-3学校相关行政单位（时点）";
		
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("行政单位名称");columns.add("单位号");columns.add("单位职能");columns.add("单位负责人");
		
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("UnitName", 1);maplist.put("UnitID", 2);maplist.put("Functions", 3);maplist.put("Leader", 4);
		
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
								
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-1-3学校相关行政单位（时点）.xls");
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
	
	public static void main(String arg[]){
		 String path = "D:\\江西项目\\相关表\\ExcelTest";
		  J13_Excel excel = new J13_Excel();
		  boolean flag = excel.export_J13(path);
		  if(flag){
			  System.out.println("成功！");
		  }else{
			  System.out.println("不成功！");
		  }
	}

}
