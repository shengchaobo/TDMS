package cn.nit.excel.imports.table1;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.springframework.beans.BeanWrapperImpl;
import cn.nit.bean.table1.T16_Bean;
import cn.nit.pojo.table1.T16POJO;
import cn.nit.util.TimeUtil;

public class T16Excel {
	
	 /** 
     *  
     * 这个是读取模板写入数据 导出
     * **/  
	public  ByteArrayOutputStream writeExcel(List<T16POJO> list)
	{
		
		 ByteArrayOutputStream fos = null;
		 String path="/cn/nit/excel/downloads/T16.xls";
		 
		 String realpath = this.getClass().getResource("/" + path).getPath();
		 try {
			 realpath = URLDecoder.decode(realpath	, "UTF-8") ;
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//String realpath="E:\\Workspaces\\MyEclipse 8.5\\TDMS\\src\\cn\\nit\\excel\\downloads\\T11.xls";//选择模板文件
		try
		{
			
			   fos = new ByteArrayOutputStream();
			   Workbook wb = Workbook.getWorkbook(new File(realpath)); 
			  //第二步：通过模板得到一个可写的Workbook：第一个参数是一个输出流对象,第二个参数代表了要读取的模板
//			   File targetFile = new File("D:\\江西项目\\相关表\\try1.xls");
			   WritableWorkbook wwb = Workbook.createWorkbook(fos, wb); 
			   
			   //设置格式
			   WritableCellFormat normalFormat = new WritableCellFormat();
			   normalFormat.setBorder(Border.ALL, BorderLineStyle.THIN,
					     jxl.format.Colour.BLACK);
			   
			   
			   //第三步：选择模板中的Sheet：
			   WritableSheet wws = wwb.getSheet(0);
			   wws.setName("表1-6办学指导思想（党院办）"); // 给sheet页改名
			   wwb.removeSheet(1); // 移除多余的标签页
			   wwb.removeSheet(2);
//			   WritableSheet wws = wwb.getSheet(0); 
			   

	            int count_02=0;
	            List<String> listStr=this.changeTo(list);
			     
	            for(int j=2;j<4;j++){
//	            	   Label label = (Label)wws.getWritableCell(j,3);
//	            	   label.setString(listStr.get(count_02));
	            	Label label1=new Label(0,j,listStr.get(count_02),normalFormat);
	            	Label label2=new Label(1,j,listStr.get(count_02+1),normalFormat);
	            	Label label3=new Label(2,j,listStr.get(count_02+2),normalFormat);
	            	wws.addCell(label1); 
	            	wws.addCell(label2); 
	            	wws.addCell(label3); 
	            	count_02=+3;
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
    public List<String> changeTo(List<T16POJO> list){
    	List<String> listStr=new ArrayList<String>();
    	T16POJO t16Pojo=list.get(0);
    	listStr.add(t16Pojo.getItem1());
//    	System.out.println(t16Pojo.getItem1());
    	listStr.add(t16Pojo.getContents1());
    	listStr.add(t16Pojo.getNote1());
    	listStr.add(t16Pojo.getItem2());
    	listStr.add(t16Pojo.getContents2());
    	listStr.add(t16Pojo.getNote2());
    	return listStr;
    }


}
