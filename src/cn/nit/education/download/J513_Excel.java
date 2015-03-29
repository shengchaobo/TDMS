package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.nit.dao.table5.T513_DAO;
import cn.nit.pojo.table5.T513POJO;

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


public class J513_Excel {
	
	public static  boolean export_J513(String path,String year){
		
		T513_DAO t513Dao = new T513_DAO();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
//		String year = dateFormat.format(new Date());
		List<T513POJO>  list = t513Dao.totalList(year);
		double CoverRatio1 = 0; double ExcellentRatio1 = 0;double GoodRatio1 = 0;
		double AvgRatio1 = 0; double PoorRatio1 = 0;
		double CoverRatio2 = 0; double ExcellentRatio2 = 0;double GoodRatio2 = 0;
		double AvgRatio2 = 0; double PoorRatio2 = 0;
		double CoverRatio3 = 0; double ExcellentRatio3 = 0;double GoodRatio3 = 0;
		double AvgRatio3 = 0; double PoorRatio3 = 0;
		
		int should1 = 0;int have1=0;int exe1 = 0;int good1=0;int avg1=0; int poor1=0;//学生评教
		int should2 = 0;int have2 = 0;int exe2 = 0;int good2=0;int avg2=0; int poor2=0;//同行评教
		int should3 = 0;int have3 = 0;int exe3 = 0;int good3=0;int avg3=0; int poor3=0;//专家评教
		if(list.size()>0){
			for(T513POJO pojo:list){
				if(pojo.getItemID()=="54000")//学生评教
				{
					have1 +=pojo.getHavedASCSNum();
					should1 +=pojo.getShouldASCSNum();
					exe1 +=pojo.getExcellentNum();
					good1 +=pojo.getGoodNum();
					avg1 +=pojo.getAvgNum();
					poor1 +=pojo.getPoorNum();
				}
				if(pojo.getItemID()=="54002")//同行评教
				{
					have2 +=pojo.getHavedASCSNum();
					should2 +=pojo.getShouldASCSNum();
					exe2 +=pojo.getExcellentNum();
					good2 +=pojo.getGoodNum();
					avg2 +=pojo.getAvgNum();
					poor2 +=pojo.getPoorNum();
				}
				if(pojo.getItemID()=="54003")//专家评教
				{
					have3 +=pojo.getHavedASCSNum();
					should3 +=pojo.getShouldASCSNum();
					exe3 +=pojo.getExcellentNum();
					good3 +=pojo.getGoodNum();
					avg3 +=pojo.getAvgNum();
					poor3 +=pojo.getPoorNum();
				}
			}
		}
		//计算
		//学生评教
		if(should1!=0 && have1!=0){
			CoverRatio1 = J513_Excel.toDouble(have1, should1);
			ExcellentRatio1 = J513_Excel.toDouble(exe1, have1);
			GoodRatio1  = J513_Excel.toDouble(good1, have1);
			AvgRatio1 = J513_Excel.toDouble(avg1, have1);
			PoorRatio1 = J513_Excel.toDouble(poor1, have1);
		}
		//同行评教
		if(should2!=0 && have2!=0){
			CoverRatio2 = J513_Excel.toDouble(have2, should2);
			ExcellentRatio2 = J513_Excel.toDouble(exe2, have2);
			GoodRatio2  = J513_Excel.toDouble(good2, have2);
			AvgRatio2 = J513_Excel.toDouble(avg2, have2);
			PoorRatio2 = J513_Excel.toDouble(poor2, have2);
		}
		//专家评教
		if(should3!=0 && have3!=0){
			CoverRatio3 = J513_Excel.toDouble(have3, should3);
			ExcellentRatio3 = J513_Excel.toDouble(exe3, have3);
			GoodRatio3  = J513_Excel.toDouble(good3, have3);
			AvgRatio3 = J513_Excel.toDouble(avg3, have3);
			PoorRatio3 = J513_Excel.toDouble(poor3, have3);
		}
		
		
		
	    ByteArrayOutputStream fos = null;
		
	    String sheetName = "J-5-1-3课堂教学质量评估统计表（学年）";
					
	    WritableWorkbook wwb;
	    try {    
	           fos = new ByteArrayOutputStream();
	           wwb = Workbook.createWorkbook(fos);
	           WritableSheet ws = wwb.createSheet(sheetName, 0);        // 创建一个工作表
	
	            //    设置单元格的文字格式
	           WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.BOLD,false,
	                    UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
	           WritableCellFormat wcf = new WritableCellFormat(wf);
	           wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
	           wcf.setAlignment(Alignment.CENTRE);
	           wcf.setBorder(Border.ALL, BorderLineStyle.THIN,
	        		     jxl.format.Colour.BLACK);
	           ws.setRowView(1, 500);
	           
	           
	            //    设置内容单无格的文字格式
	           WritableFont wf1 = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false,
	                    UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
	            WritableCellFormat wcf1 = new WritableCellFormat(wf1);        
	            wcf1.setVerticalAlignment(VerticalAlignment.CENTRE);
	            wcf1.setAlignment(Alignment.CENTRE);
	            wcf1.setBorder(Border.ALL, BorderLineStyle.THIN,
		        		     jxl.format.Colour.BLACK);
	 
	
	           ws.addCell(new Label(0, 0, sheetName, wcf)); 
	           ws.mergeCells(0, 0, 1, 0);
	           
	           ws.addCell(new Label(0, 2, "项目", wcf)); 
	           ws.addCell(new Label(1, 2, "覆盖比例%", wcf)); 
	           ws.addCell(new Label(2, 2, "优（90分及以上）", wcf)); 
	           ws.addCell(new Label(3, 2, "良好（89-75分）", wcf));  
	           ws.addCell(new Label(4, 2, "中（74-60分）", wcf)); 
	           ws.addCell(new Label(5, 2, "差（60分以下）", wcf)); 
	           ws.addCell(new Label(0, 3, "学生评教", wcf)); 
	           ws.addCell(new Label(0, 4, "同行评教", wcf)); 
	           ws.addCell(new Label(0, 5, "专家评教", wcf)); 
	           		           

		           ws.addCell(new Label(1, 3, ""+CoverRatio1+"%", wcf1)); 
		           ws.addCell(new Label(2, 3, ""+ExcellentRatio1+"%", wcf1));  
		           ws.addCell(new Label(3, 3, ""+GoodRatio1+"%", wcf1)); 
		           ws.addCell(new Label(4, 3, ""+AvgRatio1+"%", wcf1)); 
		           ws.addCell(new Label(5, 3, ""+PoorRatio1+"%", wcf1)); 
		           
		           ws.addCell(new Label(1, 4, ""+CoverRatio2+"%", wcf1)); 
		           ws.addCell(new Label(2, 4, ""+ExcellentRatio2+"%", wcf1));  
		           ws.addCell(new Label(3, 4, ""+GoodRatio2+"%", wcf1)); 
		           ws.addCell(new Label(4, 4, ""+AvgRatio2+"%", wcf1)); 
		           ws.addCell(new Label(5, 4, ""+PoorRatio2+"%", wcf1)); 
		           
		           ws.addCell(new Label(1, 5, ""+CoverRatio3+"%", wcf1)); 
		           ws.addCell(new Label(2, 5, ""+ExcellentRatio3+"%", wcf1));  
		           ws.addCell(new Label(3, 5, ""+GoodRatio3+"%", wcf1)); 
		           ws.addCell(new Label(4, 5, ""+AvgRatio3+"%", wcf1)); 
		           ws.addCell(new Label(5, 5, ""+PoorRatio3+"%", wcf1)); 
		         
	             
	          wwb.write();
	          wwb.close();
	
	        } catch (IOException e){
	        	return false;
	        } catch (RowsExceededException e){
	        	return false;
	        } catch (WriteException e){
	        	return false;
	        }
	        
			try {
								
				File file = new File(path,"J-5-1-3课堂教学质量评估统计表（学年）.xls");
				FileOutputStream fileOutputStream  = new FileOutputStream(file);
				
				//写到文件中
				fileOutputStream.write(fos.toByteArray());
				
				fos.close();
				fileOutputStream.close();
				
				return true;
			} catch (Exception e) {	
				e.printStackTrace();
				return false;
			}       		
	}
	
	//保留两位小数
	public static double toDouble(int a,int b){
		double n =(double)a/(double)b;
		DecimalFormat df = new DecimalFormat("#.00");
		String dou = df.format(n);
		double m = Double.parseDouble(dou);
		return m;
	}
	
	public static void main(String arg[]){
		 String path = "D:\\江西项目\\相关表\\ExcelTest";
		  J513_Excel excel = new J513_Excel();
		  boolean flag = excel.export_J513(path,"2014");
		  if(flag){
			  System.out.println("成功！");
		  }else{
			  System.out.println("不成功！");
		  }
		
	}

}
