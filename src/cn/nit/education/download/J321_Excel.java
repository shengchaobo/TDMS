package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.nit.bean.table3.T322_Bean;
import cn.nit.dao.table3.T322_DAO;
import cn.nit.util.ExcelUtil;

public class J321_Excel {
	
	
	
	public static boolean export_J321(String path){
		
		T322_DAO T322_dao = new T322_DAO();
		Calendar a = Calendar.getInstance();
		String year = String.valueOf(a.get(Calendar.YEAR));
		List<T322_Bean> list = T322_dao.totalList1(year);
				
		String sheetName = "J-3-2-1专业基本情况（时点）";
		
		List<String> columns = new ArrayList<String>();
		columns.add("序号");
		columns.add("校内专业代码");columns.add("校内名称");columns.add("专业名称");columns.add("专业代码");
		columns.add("代码版本");columns.add("专业方向号");columns.add("专业方向名称");columns.add("所属单位号");
		columns.add("专业带头人工号");columns.add("招生状态");columns.add("专业特色");columns.add("专业培养目标");
		columns.add("学制");columns.add("专业设置年份");columns.add("是否新专业");columns.add("学时总数");
		columns.add("必修课学时数");columns.add("选修课学时数");columns.add("课内教学学时数");columns.add("实验教学学时数");
		columns.add("学分总数");columns.add("必修课学分数");columns.add("选修课学分数");columns.add("集中性实践教学环节学分数");
		columns.add("课内教学学分数");columns.add("实验教学学分数");columns.add("课外科技活动学分数");
		
		Map<String,Integer> maplist = new HashMap<String,Integer>();
		maplist.put("SeqNum", 0);
		maplist.put("SchMajorID", 1);maplist.put("SchMajorName", 2);maplist.put("MajorName", 3);maplist.put("MajorID", 4);
		maplist.put("MajorVersion", 5);maplist.put("MajorFieldID", 6);maplist.put("MajorField", 7);maplist.put("FillUnitID", 8);
		maplist.put("TeaID", 9);maplist.put("MajorState", 10);maplist.put("MajorFeature", 11);maplist.put("MajorPurpose", 12);
		maplist.put("MajorDurition", 13);maplist.put("MajorSetTime", 14);maplist.put("IsNewMajor", 15);maplist.put("TotalCSHour", 16);
		maplist.put("RequireCShour", 17);maplist.put("OptionCSHour", 18);maplist.put("InClassCSHour", 19);maplist.put("ExpCSHour", 20);
		maplist.put("TotalCredit", 21);maplist.put("RequireCredit", 22);maplist.put("OptionCredit", 23);maplist.put("PraCredit", 24);
		maplist.put("InClassCredit", 25);maplist.put("ExpCredit", 26);maplist.put("OutClassCredit", 27);
		
		ByteArrayOutputStream byteArrayOutputStream = null;		
		try {
								
			byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);

			File file = new File(path,"J-3-2-1专业基本情况.xls");
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
	
	public static void main(String args[]){
		String path = "E:/test";
		boolean flag = J321_Excel.export_J321(path);
		if(flag){
		System.out.println("成功");
		}else{
			System.out.println("失败");
		}
	}

}
