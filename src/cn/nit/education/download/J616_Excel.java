package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.nit.bean.table6.T621_Bean;
import cn.nit.dao.table6.T621_Dao;
import cn.nit.util.ExcelUtil;

public class J616_Excel {
	
	 public static boolean export_J616(String path,String year){
			
			T621_Dao T621_dao = new T621_Dao();
			
//			//获取当前年份
//			Date time = new Date();
//			String currentTime = time.toString();
//			String year = currentTime.substring(currentTime.length()-4, currentTime.length());
		
			List<T621_Bean> list = T621_dao.getAllList(year);
			
			String sheetName = "J-6-1-6各专业招生报到情况（时点）";
			
			List<String> columns = new ArrayList<String>();
			columns.add("序号");
			columns.add("校内专业（大类）名称");columns.add("校内专业（大类）代码");
			columns.add("招生计划数（人）");columns.add("实际录取数（人）");columns.add("实际报到数（人）");
		
			Map<String,Integer> maplist = new HashMap<String,Integer>();
			maplist.put("SeqNum", 0);
			maplist.put("majorName", 1);maplist.put("majorId", 2);
			maplist.put("AmisPlanNum", 3);maplist.put("ActulEnrollNum", 4);maplist.put("ActulRegisterNum", 5);
			
			ByteArrayOutputStream byteArrayOutputStream = null;		
			try {
									
				byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

				File file = new File(path,"J-6-1-6各专业招生报到情况（时点）.xls");
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
			  J616_Excel excel = new J616_Excel();
			  boolean flag = excel.export_J616(path,"2014");
			  if(flag){
				  System.out.println("成功！");
			  }else{
				  System.out.println("不成功！");
			  }
			}


}
