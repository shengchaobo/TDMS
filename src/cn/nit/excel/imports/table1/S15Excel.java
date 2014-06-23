package cn.nit.excel.imports.table1;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import cn.nit.bean.table1.S15Bean;
import cn.nit.bean.table1.T11Bean;
import cn.nit.util.TimeUtil;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class S15Excel {

	
	 /** 
     *  
     * 这个是读取模板写入数据 导出
     * **/  
	public  ByteArrayOutputStream writeExcel(List<S15Bean> list)
	{
		
		 ByteArrayOutputStream fos = null;
		 String path="/cn/nit/excel/downloads/S15.xls";
		 
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
			   wws.setName("S-1-5科研机构"); // 给sheet页改名
			   wwb.removeSheet(1); // 移除多余的标签页
			   wwb.removeSheet(2);
//			   WritableSheet wws = wwb.getSheet(0); 
			   

	            int count_02=0;
	            List<String> listStr=this.changeTo(list);
			     
	            for(int j=2;j<16;j++){
//	            	   Label label = (Label)wws.getWritableCell(j,3);
//	            	   label.setString(listStr.get(count_02));
	            	Label label1=new Label(2,j,listStr.get(count_02),normalFormat);
	            	Label label2=new Label(3,j,listStr.get(count_02+1),normalFormat);
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
    public List<String> changeTo(List<S15Bean> list){
    	List<String> listStr=new ArrayList<String>();
    	S15Bean s15Bean=list.get(0);
    	//总计
    	String totalNum=""+s15Bean.getSumResNum();
    	listStr.add(totalNum);
    	String totalArea=""+s15Bean.getTeaUnitResArea();
    	listStr.add(totalArea);
    	//国家实验室
    	String nationRes=""+s15Bean.getNationResNum();
    	listStr.add(nationRes);
    	String nationResArea=""+s15Bean.getNationResArea();
    	listStr.add(nationResArea);
    	//国家重点实验室
    	String nationKey=""+s15Bean.getNationKeyResNum();
    	listStr.add(nationKey);
    	String nationKeyArea=""+s15Bean.getNationKeyResArea();
    	listStr.add(nationKeyArea);
    	//国家工程研究中心
    	String nationEn=""+s15Bean.getNationEnginResNum();
    	listStr.add(nationEn);
    	String nationEnArea=""+s15Bean.getNationEnginResArea();
    	listStr.add(nationEnArea);
    	//其他国家研究室
    	String nationOther=""+s15Bean.getOtherNationResNum();
    	listStr.add(nationOther);
    	String nationOtherArea=""+s15Bean.getOtherNationResArea();
    	listStr.add(nationOtherArea);
    	//省部级研究室
    	String proRes=""+s15Bean.getProviResNum();
    	listStr.add(proRes);
    	String proResArea=""+s15Bean.getProviResArea();
    	listStr.add(proResArea);
    	//省部级实验室
    	String proLab=""+s15Bean.getProviLabNum();
    	listStr.add(proLab);
    	String proLabArea=""+s15Bean.getProviLabArea();
    	listStr.add(proLabArea);
    	//其他省级实验室
    	String otherPro=""+s15Bean.getOtherProviResNum();
    	listStr.add(otherPro);
    	String otherProArea=""+s15Bean.getOtherProviResArea();
    	listStr.add(otherProArea);
    	//人文总
    	String humanSum=""+s15Bean.getHumanResSumNum();
    	listStr.add(humanSum);
    	String humanSumAre=""+s15Bean.getHumanResSumArea();
    	listStr.add(humanSumAre);
    	//人文国家
    	String humanNat=""+s15Bean.getHumanNationResNum();
    	listStr.add(humanNat);
    	String humanNatAre=""+s15Bean.getHumanNationResArea();
    	listStr.add(humanNatAre);
    	//人文省部级
    	String humanPro=""+s15Bean.getHumanProviResNum();
    	listStr.add(humanPro);
    	String humanProAre=""+s15Bean.getHumanProviResArea();
    	listStr.add(humanProAre);
    	//市级科研
    	String cityRes=""+s15Bean.getCityResNum();
    	listStr.add(cityRes);
    	String cityResAre=""+s15Bean.getCityResArea();
    	listStr.add(cityResAre);
    	//教学科研
    	String teaRes=""+s15Bean.getTeaUnitResNum();
    	listStr.add(teaRes);
    	String teaResAre=""+s15Bean.getTeaUnitResArea();
    	listStr.add(teaResAre);
    	//其他
    	String otherSch=""+s15Bean.getOtherSchResNum();
    	listStr.add(otherSch);
    	String otherSchAre=""+s15Bean.getOtherSchResArea();
    	listStr.add(otherSchAre);
    	
    	return listStr;
    }
}
