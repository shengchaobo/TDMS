package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cn.nit.bean.table6.T615_Bean;
import cn.nit.dao.table6.T615_Dao;
import cn.nit.util.ExcelUtil;

public class J612_Excel {
	
  public static boolean export_J612(String path){
	
		T615_Dao T615_dao = new T615_Dao();
	
		List<T615_Bean> list = T615_dao.getAllList("", null);
		
		String sheetName = "J-6-1-2普通本科分专业（大类）学生数（时点）";
		
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("校内专业（大类）代码");columns.add("校内专业（大类）名称");columns.add("学制");
		columns.add("在校学生数总计");columns.add("在校一年级学生数");columns.add("在校二年级学生数");
		columns.add("在校三年级学生数");columns.add("在校四年级学生数");columns.add("在校五年级以上学生数");
		columns.add("在校辅修学生数");columns.add("在校双学位学生数");
		columns.add("转专业转入人数");columns.add("转专业转出人数");
	
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("majorId", 1);maplist.put("majorName", 2);maplist.put("SchLen", 3);
		maplist.put("SchStuSumNum", 4);maplist.put("FreshmanNum", 5);maplist.put("SophomoreNum", 6);
		maplist.put("JuniorNum", 7);maplist.put("SeniorNum", 8);maplist.put("OtherGradeNum", 9);
		maplist.put("MinorNum", 10);maplist.put("DualDegreeNum", 11);maplist.put("ChangeInNum", 12);
		maplist.put("ChangeOutNum", 13);
		
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
								
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-6-1-2普通本科分专业（大类）学生数（时点）.xls");
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
		  J612_Excel excel = new J612_Excel();
		  boolean flag = excel.export_J612(path);
		  if(flag){
			  System.out.println("成功！");
		  }else{
			  System.out.println("不成功！");
		  }
		}


}
