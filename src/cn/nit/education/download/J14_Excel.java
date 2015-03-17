package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.nit.bean.table1.T12_Bean;
import cn.nit.bean.table1.T13_Bean;
import cn.nit.bean.table1.T14_Bean;
import cn.nit.dao.table1.T12DAO;
import cn.nit.dao.table1.T13DAO;
import cn.nit.dao.table1.T14DAO;
import cn.nit.util.ExcelUtil;

public class J14_Excel {
	
	public static boolean export_J14(String path){
		
		T13DAO T13dao = new T13DAO();
		
		List<T12_Bean> list = T13dao.totalListAll();

		String sheetName = "J-1-4学校教学科研单位（时点）";
		
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("教学科研单位名称");columns.add("单位号");columns.add("单位负责人");
		
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("UnitName", 1);maplist.put("UnitID", 2);maplist.put("Leader", 3);
		
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
								
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-1-4学校教学科研单位（时点）.xls");
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
		  J14_Excel excel = new J14_Excel();
		  boolean flag = excel.export_J14(path);
		  if(flag){
			  System.out.println("成功！");
		  }else{
			  System.out.println("不成功！");
		  }
	}

}
