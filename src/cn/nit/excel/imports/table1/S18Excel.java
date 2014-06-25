package cn.nit.excel.imports.table1;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.table1.S18Bean;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class S18Excel {
	

	 /** 
    *  
    * 这个是读取模板写入数据 导出
    * **/  
	public  ByteArrayOutputStream writeExcel(List<S18Bean> list)
	{
		
		 ByteArrayOutputStream fos = null;
		 String path="/cn/nit/excel/downloads/S18.xls";
		 
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
//			   File targetFile = new File("D:\\江西项目\\相关表\\try1.xls");
			   WritableWorkbook wwb = Workbook.createWorkbook(fos, wb); 
			   
			   //设置格式
			   WritableCellFormat normalFormat = new WritableCellFormat();
			   normalFormat.setBorder(Border.ALL, BorderLineStyle.THIN,
					     jxl.format.Colour.BLACK);
			   
			   
			   //第三步：选择模板中的Sheet：
			   WritableSheet wws = wwb.getSheet(0);
			   wws.setName("S-1-8签订合作协议机构的协议个数"); // 给sheet页改名
			   wwb.removeSheet(1); // 移除多余的标签页
			   wwb.removeSheet(2);
//			   WritableSheet wws = wwb.getSheet(0); 
			   

	            int count_02=0;
	            List<String> listStr=this.changeTo(list);
			     
	            for(int j=2;j<6;j++){
//	            	   Label label = (Label)wws.getWritableCell(j,3);
//	            	   label.setString(listStr.get(count_02));
	            	Label label=new Label(2,j,listStr.get(count_02),normalFormat);
	            	wws.addCell(label);   
	            	count_02++;
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
	   public List<String> changeTo(List<S18Bean> list){
		   
	   	List<String> listStr=new ArrayList<String>();
	   	S18Bean s18Bean=list.get(0);
	    String total=""+s18Bean.getSumAgreeNum();
	    listStr.add(total);
	    String academic=""+s18Bean.getAcademicNum();
	    listStr.add(academic);
	    String indus=""+s18Bean.getIndustryNum();
	    listStr.add(indus);
	    String gover=""+s18Bean.getLocalGoverNum();
	    listStr.add(gover);
	   	return listStr;
	   }

}
