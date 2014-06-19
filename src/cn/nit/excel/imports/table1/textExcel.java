package cn.nit.excel.imports.table1;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.nit.bean.table1.T11Bean;
import cn.nit.dao.table1.T11DAO;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class textExcel {
    
	
	public  void writeExcel()
	{
		String realpath="D:\\江西项目\\相关表\\T11.xls";//选择模板文件
		try
		{
			 Workbook wb = Workbook.getWorkbook(new File(realpath)); 
			  //第二步：通过模板得到一个可写的Workbook：第一个参数是一个输出流对象,第二个参数代表了要读取的模板
			   File targetFile = new File("D:\\江西项目\\相关表\\try1.xls");
			   WritableWorkbook wwb = Workbook.createWorkbook(targetFile, wb); 
			   
			   //设置格式
			   WritableCellFormat normalFormat = new WritableCellFormat();
			   normalFormat.setBorder(Border.ALL, BorderLineStyle.THIN,
					     jxl.format.Colour.BLACK);
			   
			   //第三步：选择模板中的Sheet：
//			   String[] sheetName=wwb.getSheetNames();
			   WritableSheet wws = wwb.getSheet(0);
			   wws.setName("表1-1学校基本信息（党院办）"); // 给sheet页改名
			   wwb.removeSheet(1); // 移除多余的标签页
			   wwb.removeSheet(2);
			   
			   
			   List<T11Bean> list=new ArrayList<T11Bean>(); 
	            //需要操作的数据库dao
	            T11DAO t11dao=new T11DAO();
	            Date time=new Date();
	            String time1=time.toString();
	            String year=time1.substring(time1.length()-4, time1.length());
	            //将数据存放在list中
	            list=t11dao.forExcel(year);
	            int count_02=0;
	            List<String> listStr=this.changeTo(list);
			     
	            for(int j=1;j<20;j++){
//	            	   Label label = (Label)wws.getWritableCell(j,3);
//	            	   label.setString(listStr.get(count_02));
	            	Label label=new Label(3,j,listStr.get(count_02),normalFormat);
	            	wws.addCell(label);   
	            	count_02++;
	            }
	            wwb.write();
	            wwb.close();
	            wb.close(); 
			   
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	 public List<String> changeTo(List<T11Bean> list){
	    	List<String> listStr=new ArrayList<String>();
	    	T11Bean t11Bean=list.get(0);
	    	listStr.add(t11Bean.getSchAddress());
	    	listStr.add(t11Bean.getSchTel());
	    	listStr.add(t11Bean.getSchFax());
	    	listStr.add(t11Bean.getSchFillerName());listStr.add(t11Bean.getSchFillerTel());listStr.add(t11Bean.getSchFillerEmail());
	    	listStr.add(t11Bean.getSchName());
	    	listStr.add(t11Bean.getSchID());listStr.add(t11Bean.getSchEnName());listStr.add(t11Bean.getSchType());
	    	listStr.add(t11Bean.getSchQuality());listStr.add(t11Bean.getSchBuilder());listStr.add(t11Bean.getMajDept());
	    	listStr.add(t11Bean.getSchUrl());listStr.add(t11Bean.getAdmissonBatch());
	    	String beginYear=t11Bean.getSchFillerTel();
	    	String year=beginYear.substring(0, 4);
	    	listStr.add(year);listStr.add(t11Bean.getMediaUrl());listStr.add(t11Bean.getYaohuSchAdd());
	    	listStr.add(t11Bean.getPengHuSchAdd());
	    	return listStr;
	    }
	 
	 public static void main(String arg[]){
		 textExcel ex=new textExcel();
		 ex.writeExcel();
		 System.out.println("运行完毕！");
	 }
	
	   
}
