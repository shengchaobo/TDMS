package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.nit.dao.table7.J732_DAO;
import cn.nit.pojo.table7.J732POJO;
import cn.nit.util.ExcelUtil;

public class J732_Excel {
	public static boolean export_J732(String path){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		String year = dateFormat.format(new Date());
		J732_DAO J732_Dao=new J732_DAO();
		List<J732POJO> list=J732_Dao.getYearInfo(year);
		
		String sheetName = "J-7-3-2教学成果奖（时点）";
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("奖励项目名称");columns.add("主持人");columns.add("主持人工号");columns.add("级别");columns.add("获奖时间");columns.add("授予单位");
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("AwardName", 1);maplist.put("Leader", 2);maplist.put("TeaID", 3);maplist.put("AwardLevel", 4);maplist.put("AwardTime", 5);maplist.put("AwardFromUnit", 6);
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
			
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-7-3-2教学成果奖（时点）.xls");
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
		
		String path = "E:\\江西项目\\测试表\\一键导出";
		 J732_Excel excel= new J732_Excel();
		 boolean flag = excel.export_J732(path);
		 if(flag){
			 System.out.println("导出成功");
		 }else{
			 System.out.println("导出不成功");
		 }
		
	}
}
