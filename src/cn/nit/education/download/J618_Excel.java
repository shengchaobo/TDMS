package cn.nit.education.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import cn.nit.bean.table6.T632_Bean;
import cn.nit.dao.table6.T632_Dao;

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


public class J618_Excel {
	
	public static boolean export_J618(String path,String year) {
		
		T632_Dao T632_dao = new T632_Dao();
		
//		//获取当前年份
//		Date time = new Date();
//		String currentTime = time.toString();
//		String year = currentTime.substring(currentTime.length()-4, currentTime.length());
//		
		List<T632_Bean> list = T632_dao.getAllList("", null);
		
		String sheetName = "J-6-1-8应届本科毕业生就业情况（时点）";
		
		
		try {
			
				  //    设置单元格的文字格式(标题)
		        WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.BOLD,false,
		                 UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
		        WritableCellFormat wcf = new WritableCellFormat(wf);
		        wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
		        wcf.setAlignment(Alignment.CENTRE);
		        wcf.setBorder(Border.ALL, BorderLineStyle.THIN,
						     jxl.format.Colour.BLACK);
		        wcf.setAlignment(jxl.write.Alignment.LEFT);
	//	        ws.setRowView(1, 500);
		        
		        //设置表中文字格式
				WritableCellFormat wcf1 = new WritableCellFormat();
				wcf1.setBorder(Border.ALL, BorderLineStyle.THIN,
						     jxl.format.Colour.BLACK); 
		       
				ByteArrayOutputStream  byteArrayOutputStream= null;	
				WritableWorkbook wwb;
			
			   byteArrayOutputStream = new ByteArrayOutputStream();
	           wwb = Workbook.createWorkbook(byteArrayOutputStream);
	           WritableSheet ws = wwb.createSheet(sheetName, 0);        // 创建一个工作表
	           
	           ws.setRowView(1, 500);
	           
	           ws.addCell(new Label(0, 0, sheetName, wcf)); 
	           ws.mergeCells(0, 0, 2, 0);
	             
	           //写表头
	           ws.addCell(new Label(0, 2, "项目", wcf)); 
	           ws.addCell(new Label(3, 2, "内容", wcf)); 
	           ws.addCell(new Label(0, 3, "1.应届毕业生升学基本情况（人）", wcf)); 
	           ws.addCell(new Label(0, 8, "2.应届毕业生就业基本情况（人）", wcf)); 
	           ws.addCell(new Label(1, 3, "免试推荐研究生", wcf1)); 
	           ws.addCell(new Label(1, 4, "考研录取", wcf1)); 
	           ws.addCell(new Label(1, 7, "出国（境）留学", wcf1));
	           ws.addCell(new Label(2, 4, "总数", wcf1)); 
	           ws.addCell(new Label(2, 5, "考取本校", wcf1)); 
	           ws.addCell(new Label(2, 6, "考取外校", wcf1)); 
	           ws.addCell(new Label(1, 8, "总数", wcf1));
	           ws.addCell(new Label(1, 9, "政府机构", wcf1));
	           ws.addCell(new Label(1, 10, "事业单位数", wcf1));
	           ws.addCell(new Label(1, 11, "企业数", wcf1));
	           ws.addCell(new Label(1, 12, "部队", wcf1));
	           ws.addCell(new Label(1, 13, "灵活就业", wcf1));
	           ws.addCell(new Label(1, 14, "升学", wcf1));
	           ws.addCell(new Label(1, 15, "参加国家地方项目就业", wcf1));
	           ws.addCell(new Label(1, 16, "其他", wcf1));
	           
	           //合并表头
	           ws.mergeCells(0, 2, 2, 2);
	           ws.mergeCells(0, 3, 0, 7);
	           ws.mergeCells(0, 8, 0, 16);
	           ws.mergeCells(1, 3, 2, 3);
	           ws.mergeCells(1, 4, 1, 6);
	           ws.mergeCells(1, 7, 2, 7);
	           ws.mergeCells(1, 8, 2, 8);
	           ws.mergeCells(1, 9, 2, 9);
	           ws.mergeCells(1, 10, 2, 10);
	           ws.mergeCells(1, 11, 2, 11);
	           ws.mergeCells(1, 12, 2, 12);
	           ws.mergeCells(1, 13, 2, 13);
	           ws.mergeCells(1, 14, 2, 14);
	           ws.mergeCells(1, 15, 2, 15);
	           ws.mergeCells(1, 16, 2, 16);
	           
	           //写数据
	           if(list!=null){
	   			int SumEmployNum=0; int GovermentNum=0; int PubInstiNum=0; int EnterpriseNum=0;
	   			int ForceNum = 0; int FlexibleEmploy = 0; int GoOnHighStudy = 0;
	   			int NationItemEmploy = 0;int OtherEmploy = 0; int SumGoOnHighStudyNum = 0;
	   			int RecommendGraNum = 0; int ExamGraApplyNum = 0; int ExamGraEnrollNum = 0;
	   			int ExamGraInSch = 0; int ExamGraOutSch = 0; int AbroadNum = 0;
	   			//统计全校合计
	   			for(T632_Bean bean : list){
	   				SumEmployNum+=bean.getSumEmployNum();
	   				GovermentNum+=bean.getGovermentNum();
	   				PubInstiNum+=bean.getPubInstiNum();
	   				EnterpriseNum+=bean.getEnterpriseNum();
	   				ForceNum+=bean.getForceNum();
	   				FlexibleEmploy+=bean.getFlexibleEmploy();
	   				GoOnHighStudy+=bean.getGoOnHighStudy();
	   				NationItemEmploy+=bean.getNationItemEmploy();
	   				OtherEmploy+=bean.getOtherEmploy();
	   				SumGoOnHighStudyNum+=bean.getSumGoOnHighStudyNum();
	   				RecommendGraNum+=bean.getRecommendGraNum();
	   				ExamGraApplyNum+=bean.getExamGraApplyNum();
	   				ExamGraEnrollNum+=bean.getExamGraEnrollNum();
	   				ExamGraInSch+=bean.getExamGraInSch();
	   				ExamGraOutSch+=bean.getExamGraOutSch();
	   				AbroadNum+=bean.getAbroadNum();
	   			}
	   			
	   			T632_Bean bean = new T632_Bean();
	   			bean.setSumEmployNum(SumEmployNum);
	   			bean.setGovermentNum(GovermentNum);
	   			bean.setPubInstiNum(PubInstiNum);
	   			bean.setEnterpriseNum(EnterpriseNum);
	   			bean.setForceNum(ForceNum);
	   			bean.setFlexibleEmploy(FlexibleEmploy);
	   			
	   			bean.setGoOnHighStudy(GoOnHighStudy);
	   			bean.setNationItemEmploy(NationItemEmploy);
	   			bean.setOtherEmploy(OtherEmploy);
	   			bean.setSumGoOnHighStudyNum(SumGoOnHighStudyNum);
	   			bean.setRecommendGraNum(RecommendGraNum);
	   			bean.setExamGraApplyNum(ExamGraApplyNum);
	   			
	   			bean.setExamGraEnrollNum(ExamGraEnrollNum);
	   			bean.setExamGraInSch(ExamGraInSch);
	   			bean.setExamGraOutSch(ExamGraOutSch);
	   			bean.setAbroadNum(AbroadNum);
	   			bean.setTeaUnit("全校合计：");
	   			
	   		 	//写入数据	           
	   		 ws.addCell(new Label(3, 3, ""+bean.getRecommendGraNum(), wcf1));  
	           ws.addCell(new Label(3, 7, ""+bean.getAbroadNum(), wcf1));
	           ws.addCell(new Label(3, 4, ""+bean.getExamGraEnrollNum(), wcf1)); 
	           ws.addCell(new Label(3, 5, ""+bean.getExamGraInSch(), wcf1)); 
	           ws.addCell(new Label(3, 6, ""+bean.getExamGraOutSch(), wcf1)); 
	           ws.addCell(new Label(3, 8, ""+bean.getSumEmployNum(), wcf1));
	           ws.addCell(new Label(3, 9, ""+bean.getGovermentNum(), wcf1));
	           ws.addCell(new Label(3, 10, ""+bean.getPubInstiNum(), wcf1));
	           ws.addCell(new Label(3, 11, ""+bean.getEnterpriseNum(), wcf1));
	           ws.addCell(new Label(3, 12, ""+bean.getForceNum(), wcf1));
	           ws.addCell(new Label(3, 13, ""+bean.getFlexibleEmploy(), wcf1));
	           ws.addCell(new Label(3, 14, ""+bean.getGoOnHighStudy(), wcf1));
	           ws.addCell(new Label(3, 15, ""+bean.getNationItemEmploy(), wcf1));
	           ws.addCell(new Label(3, 15, ""+bean.getNationItemEmploy(), wcf1));
	           ws.addCell(new Label(3, 16, ""+bean.getOtherEmploy(), wcf1));
	   		}
	          
	           
	          
	           wwb.write();
		       wwb.close();
								
	//		byteArrayOutputStream = ExcelUtil.exportExcel(list, sheetName, maplist,columns);
	
			File file = new File(path,"J-6-1-8应届本科毕业生就业情况（时点）.xls");
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
		 String path = "C:\\Users\\Fan Shuangyan\\Desktop\\Education";
		  J618_Excel excel = new J618_Excel();
		  boolean flag = excel.export_J618(path,"2014");
		  if(flag){
			  System.out.println("成功！");
		  }else{
			  System.out.println("不成功！");
		  }
	}
	
	

}
