package cn.nit.excel.imports.table3;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import cn.nit.pojo.table3.A321POJO;

public class A321Excel {
	


	public ByteArrayOutputStream exportExcel(List<A321POJO> list){
		ByteArrayOutputStream fos=null;
		String path="/cn/nit/excel/downloads/table3/A321.xls";
		String rPath=this.getClass().getResource("/"+path).getPath();
		try{
			rPath = URLDecoder.decode(rPath , "UTF-8");
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();		
		}
		
		try{
			System.out.println(rPath);
			fos = new ByteArrayOutputStream();
			Workbook wb = Workbook.getWorkbook(new File(rPath));
			
			
			WritableWorkbook wwb = Workbook.createWorkbook(fos,wb);
			WritableCellFormat normalFormat = new WritableCellFormat();
			normalFormat.setBorder(Border.ALL, BorderLineStyle.THIN,
					     jxl.format.Colour.BLACK);
			
			
			WritableSheet wws = wwb.getSheet(0);
			wws.setName("A-3-2-1本科专业所属学科情况分析");
			wwb.removeSheet(1);
			wwb.removeSheet(2);
			List<String> listStr = this.changeToString (list);
			Label label = new Label(2,4,listStr.get(0),normalFormat);
			wws.addCell(label);
			int count=1;
			for(int i=5;i<=11;i++){
				for(int j=2;j<=3;j++){
					label = new Label(j,i,listStr.get(count),normalFormat);
					wws.addCell(label);
					count++;
				}
			}
			wwb.write();
			wwb.close();
			wb.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return fos;
		
	}
	
//	 /**将list<BEAN>转换成list<String>*/
//	   public List<String> changeTo(List<S18Bean> list){
//		   
//	   	List<String> listStr=new ArrayList<String>();
//	   	S18Bean s18Bean=list.get(0);
//	    String total=""+s18Bean.getSumAgreeNum();
//	    listStr.add(total);
//	    String academic=""+s18Bean.getAcademicNum();
//	    listStr.add(academic);
//	    String indus=""+s18Bean.getIndustryNum();
//	    listStr.add(indus);
//	    String gover=""+s18Bean.getLocalGoverNum();
//	    listStr.add(gover);
//	   	return listStr;
//	   }
	
	public List<String> changeToString (List<A321POJO> list){
		List<String> listStr = new ArrayList<String> ();
		A321POJO a321POJO = list.get(0);

	
//		String EconomicNum="a321POJO.getEconomicNum()";
//	
//		String EconomicRatio="a321POJO.getEconomicRatio()";
//	
//		String LiteratureNum="a321POJO.getLiteratureNum()";
//	
//		String LiteratureRatio="a321POJO.getLiteratureRatio()";
//	
//		String ScienceNum="a321POJO.getScienceNum()";
//
//		String ScienceRatio="a321POJO.getScienceRatio()";
//
//		String EngineerNum="a321POJO.getEngineerNum()";
//
//		String EngineerRatio="a321POJO.getEngineerRatio()";
//
//		String AgronomyNum="a321POJO.getAgronomyNum()";
//
//		String AgronomyRatio="a321POJO.getAgronomyRatio()";
//
//	
//		String ManageNum="a321POJO.getManageNum()";
//		
//		String ManageRatio="a321POJO.getManageRatio()";
//	
//		String ArtNum="a321POJO.getArtNum()";
//		String ArtRatio="a321POJO.getArtRatio()";
		int total=a321POJO.getEconomicNum()+a321POJO.getLiteratureNum()+a321POJO.getScienceNum()
		+a321POJO.getEngineerNum()+a321POJO.getAgronomyNum()+a321POJO.getManageNum();
		listStr.add(""+total);
		listStr.add(""+a321POJO.getEconomicNum());
		listStr.add(""+a321POJO.getEconomicRatio());
		System.out.println("怎么显示呢");
		System.out.println(a321POJO.getEconomicRatio());
		listStr.add(""+a321POJO.getLiteratureNum());
		listStr.add(""+a321POJO.getLiteratureRatio());
		listStr.add(""+a321POJO.getScienceNum());
		listStr.add(""+a321POJO.getScienceRatio());
		listStr.add(""+a321POJO.getEngineerNum());
		listStr.add(""+a321POJO.getEngineerRatio());
		listStr.add(""+a321POJO.getAgronomyNum());
		listStr.add(""+a321POJO.getAgronomyRatio());
		listStr.add(""+a321POJO.getManageNum());
		listStr.add(""+a321POJO.getManageRatio());
		listStr.add(""+a321POJO.getArtNum());
		listStr.add(""+a321POJO.getArtRatio());
		return listStr;

	}
	
	

}
