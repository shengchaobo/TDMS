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

import cn.nit.dao.table7.J731_DAO;
import cn.nit.pojo.table7.J731POJO;
import cn.nit.util.ExcelUtil;

public class J731_Excel {
	public static boolean export_J731(String path,String year){
		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
//		String year = dateFormat.format(new Date());
		
		J731_DAO J731_Dao=new J731_DAO();
		List<J731POJO> list = J731_Dao.getYearInfo(year);
		String sheetName = "J-7-3-1教育教学研究与改革项目（自然年）";
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("项目名称");columns.add("主持人");columns.add("主持人工号");columns.add("级别");columns.add("立项时间");columns.add("验收时间");columns.add("经费（万元）");columns.add("参与教师人数");		
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("ItemName", 1);maplist.put("Leader", 2);maplist.put("TeaID", 3);maplist.put("ItemLevel", 4);maplist.put("ItemSetUpTime", 5);maplist.put("ReceptTime", 6);maplist.put("ApplvExp", 7);maplist.put("OtherTeaNum", 8);
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
			
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-7-3-1教育教学研究与改革项目（自然年）.xls");
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
		 J731_Excel excel= new J731_Excel();
		 boolean flag = excel.export_J731(path,"2015");
		 if(flag){
			 System.out.println("导出成功");
		 }else{
			 System.out.println("导出不成功");
		 }
		
	}
	
	
}
