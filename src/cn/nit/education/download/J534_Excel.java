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
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import cn.nit.bean.table5.T534_Bean;
import cn.nit.dao.table5.T534DAO;
import cn.nit.util.ExcelUtil;

public class J534_Excel {
	
	public static boolean export_J534(String path,String year){
		
		T534DAO T534_dao = new T534DAO();
//		Calendar a = Calendar.getInstance();
//		String year = String.valueOf(a.get(Calendar.YEAR));		
		List<T534_Bean> list = T534_dao.totalListY(year);
				
		String sheetName = "J-5-3-4分专业毕业综合训练情况（学年）";
		
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("校内专业代码");columns.add("教学单位");columns.add("专任教师");columns.add("外聘教师");
		columns.add("毕业综合训练课题总数");columns.add("在实验、实习、工程实践和社会调查等社会实践中完成数");
		
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("MajorID", 1);maplist.put("TeaUnit", 2);maplist.put("TeaName", 3);maplist.put("IsOutEmploy", 4);
		maplist.put("TrainIssueNum", 5);maplist.put("SocialNum", 6);
		
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
								
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-5-3-4分专业毕业综合训练情况.xls");
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
