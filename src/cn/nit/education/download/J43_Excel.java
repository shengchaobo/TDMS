package cn.nit.education.download;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table4.T411_Bean;
import cn.nit.dao.table4.T411_Dao;
import cn.nit.util.ExcelUtil;

public class J43_Excel {
	
	public static boolean export_J411(String path){
		
		T411_Dao T411_dao = new T411_Dao();
		
		List<T411_Bean> list = T411_dao.totalList();
				
		String sheetName = "J-4-1-1专任教师基本信息（时点）";
		
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("姓名");columns.add("教工号");columns.add("性别");columns.add("出生年月");
		columns.add("入校时间");columns.add("任职状态");columns.add("参加本科教学工作时间");columns.add("身份代码");
		columns.add("所属部门");columns.add("所属部门单位号");columns.add("所属教学单位");columns.add("所属教学单位号");
		columns.add("所属教研室");columns.add("所属教研室单位号");columns.add("学历");columns.add("最高学位");
		columns.add("毕业学校");columns.add("专业");columns.add("学缘");columns.add("行政职务");
		columns.add("专业技术职称");columns.add("教学系列职称");columns.add("非教学系列职称");columns.add("学科类别");
		columns.add("是否双师型教师");columns.add("是否具有行业背景");columns.add("是否具有工程背景");columns.add("是否列入师资库");

		
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("teaName", 1);maplist.put("teaId", 2);maplist.put("gender", 3);maplist.put("birthday", 4);
		maplist.put("admisTime", 5);maplist.put("teaState", 6);maplist.put("beginWorkTime", 7);maplist.put("idcode", 8);
		maplist.put("fromOffice", 9);maplist.put("officeID", 10);maplist.put("fromUnit", 11);maplist.put("unitId", 12);
		maplist.put("fromTeaResOffice", 13);maplist.put("teaResOfficeID", 14);maplist.put("education", 15);maplist.put("topDegree", 16);
		maplist.put("graSch", 17);maplist.put("major", 18);maplist.put("source", 19);maplist.put("adminLevel", 20);
		maplist.put("majTechTitle", 21);maplist.put("teaTitle", 22);maplist.put("notTeaTitle", 23);maplist.put("subjectClass", 24);
		maplist.put("doubleTea", 25);maplist.put("industry", 26);maplist.put("engineer", 27);maplist.put("teaBase", 28);
		
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
								
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-4-1-1专任教师基本信息.xls");
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
