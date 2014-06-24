package cn.nit.excel.imports.table1;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.table1.A15Bean;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class A15Excel {
	
	 /** 
	    *  
	    * 这个是读取模板写入数据 导出
	    * **/  
		public  ByteArrayOutputStream writeExcel(List<A15Bean> list)
		{
			
			 ByteArrayOutputStream fos = null;
			 String path="/cn/nit/excel/downloads/A15.xls";
			 
			 String realpath = this.getClass().getResource("/" + path).getPath();
			 try {
				 realpath = URLDecoder.decode(realpath	, "UTF-8") ;
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try
			{
				
				   fos = new ByteArrayOutputStream();
				   Workbook wb = Workbook.getWorkbook(new File(realpath)); 
				  //第二步：通过模板得到一个可写的Workbook：第一个参数是一个输出流对象,第二个参数代表了要读取的模板
//				   File targetFile = new File("D:\\江西项目\\相关表\\try1.xls");
				   WritableWorkbook wwb = Workbook.createWorkbook(fos, wb); 
				   
				   //设置格式
				   WritableCellFormat normalFormat = new WritableCellFormat();
				   normalFormat.setBorder(Border.ALL, BorderLineStyle.THIN,
						     jxl.format.Colour.BLACK);
				   
				   
				   //第三步：选择模板中的Sheet：
				   WritableSheet wws = wwb.getSheet(0);
				   wws.setName("A-1-5科研机构"); // 给sheet页改名
				   wwb.removeSheet(1); // 移除多余的标签页
				   wwb.removeSheet(2);
//				   WritableSheet wws = wwb.getSheet(0); 
				   

		            int count_02=0;
		            List<String> listStr=this.changeTo(list);
				     
		            for(int j=1;j<6;j++){
//		            	   Label label = (Label)wws.getWritableCell(j,3);
//		            	   label.setString(listStr.get(count_02));
		            	Label label1=new Label(1,j,listStr.get(count_02),normalFormat);
		            	Label label2=new Label(2,j,listStr.get(count_02++),normalFormat);
		            	wws.addCell(label1); 
		            	wws.addCell(label2);
		            	count_02=count_02+2;
		            }
		            wwb.write();
		            wwb.close();
		            wb.close(); 
				   
			}catch(Exception e){
				e.printStackTrace();
			}
			  return fos ;
		}
		
			 /**将list<BEAN>转换成list<String>*/
		   public List<String> changeTo(List<A15Bean> list){
			   
		   	List<String> listStr=new ArrayList<String>();
		   	A15Bean a15=list.get(0);
		   	String  NationResNum=""+a15.getNationResNum();
		   	listStr.add(NationResNum);
		   	String  NationResRatio=""+a15.getNationResRatio();
		 	listStr.add(NationResRatio);
		   	String  ProviResNum=""+a15.getProviResNum();
		 	listStr.add(ProviResNum);
		   	String  ProviResRatio=""+a15.getProviResRatio();
		 	listStr.add(ProviResRatio);
		   	String  CityResNum=""+a15.getCityResNum();
		 	listStr.add(CityResNum);
		   	String  CityResRatio=""+a15.getCityResRatio();
		 	listStr.add(CityResRatio);
		   	String  SchResNum=""+a15.getSchResNum();
		 	listStr.add(SchResNum);
		   	String  SchResRatio=""+a15.getSchResRatio();
		 	listStr.add(SchResRatio);
		 	String  SumResNum=""+a15.getSumResNum();
		 	listStr.add(SumResNum);
		   
		   	return listStr;
		   }

}
