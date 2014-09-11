package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cn.nit.bean.table1.T151Bean;
import cn.nit.dao.table1.T151DAO;
import cn.nit.dao.table1.T152DAO;
import cn.nit.util.ExcelUtil;

public class J15_Excel {
	
	public static boolean export_J15(String path){
		
		T151DAO T151dao = new T151DAO();
		T152DAO T152dao = new T152DAO();
		
		List<T151Bean> list = T151dao.totalList();
		List<T151Bean> list1 = T152dao.totalListAll();
		
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
	
	public static void main(String arg[]){
		List<String> list = new ArrayList<String>();
		list.add("a");list.add("b");list.add("c");
		List<String> list1 = new ArrayList<String>();
		list1.add("d");list1.add("e");list1.add("f");
		list.addAll(list1);
		for(int i = 0;i<list.size();i++){
			System.out.println(list.get(i));
		}
	}

	

}
