package cn.nit.excel.imports.table6;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import jxl.Cell;
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



import cn.nit.bean.di.DiEvaluTypeBean;
import cn.nit.bean.table6.T616_Bean;
import cn.nit.service.di.DiEvaluTypeService;
import cn.nit.service.table6.T616_Service;
import cn.nit.util.TimeUtil;

public class T616_Excel {
	
	/**
	 * 批量导入
	 * @param cellList {@link java.util.List<{@link jxl.Cell}>}  Cell是数组
	 * @param request  {@link javax.servlet.http.HttpServletRequest}
	 * @return
	 */
	public String batchInsert(List<Cell[]> cellList, HttpServletRequest request,String selectYear){
		
		if((cellList == null) || (cellList.size() < 2)){
			return "数据不标准，请重新提交" ;
		}
		
		int count = 1 ;
		
		boolean flag = false ;

		Date Time = new Date();
		List<T616_Bean> list = new LinkedList<T616_Bean>() ;
		
		int[] sum = new int[20]; 
		for(int i = 0; i < 20; i++)sum[i] = 0;
		

		for(Cell[] cell : cellList){
			T616_Bean t616Bean = new  T616_Bean();
			int n=cellList.indexOf(cell);
			if(count<6){
				count++;
				continue;
			}
			else{
				
				
			  try{
				 
				 
				 
					String Category = cell[0].getContents() ;
					
					if(Category == null || Category.equals("")){
						return "第" + count + "行，分类不能为空" ;
					}
					if(!Category.equals("博士")&&!Category.equals("硕士")&&!Category.equals("本科生")&&!Category.equals("专科生")&&!Category.equals("培训生")){
						return "第" + count + "行，学生类别只能填写“博士”、“硕士”、“本科生”、“专科生”、“培训生”中的一种！" ;
					}
					
					String sumGraNum = cell[1].getContents();
					if(sumGraNum == null||sumGraNum.equals("")){
						sumGraNum = "0";
					}
					if(!this.isNumeric(sumGraNum)){
						return "第" + count + "行，毕业生人数小计只能填写数字" ;
					}
					
					String graOutNum = cell[2].getContents();
					if(graOutNum == null||graOutNum.equals("")){
						graOutNum = "0";
					}
					if(!this.isNumeric(graOutNum)){
						return "第" + count + "行，国外毕业生人数只能填写数字" ;
					}
					
					String graHongNum = cell[3].getContents();
					if(graHongNum == null||graHongNum.equals("")){
						graHongNum = "0";
					}
					if(!this.isNumeric(graHongNum)){
						return "第" + count + "行，香港毕业生人数只能填写数字" ;
					}
					
					String graAoNum = cell[4].getContents();
					if(graAoNum == null||graAoNum.equals("")){
						graAoNum = "0";
					}
					if(!this.isNumeric(graAoNum)){
						return "第" + count + "行，澳门毕业生人数只能填写数字" ;
					}
					
					String graTaiNum = cell[5].getContents();
					if(graTaiNum == null||graTaiNum.equals("")){
						graTaiNum = "0";
					}
					if(!this.isNumeric(graTaiNum)){
						return "第" + count + "行，台湾毕业生人数只能填写数字" ;
					}
					
					String sumDegreeNum = cell[6].getContents();
					if(sumDegreeNum == null||sumDegreeNum.equals("")){
						sumDegreeNum = "0";
					}
					if(!this.isNumeric(sumDegreeNum)){
						return "第" + count + "行，获得学位人数小计只能填写数字" ;
					}
					
					String degreeOutNum = cell[7].getContents();
					if(degreeOutNum == null||degreeOutNum.equals("")){
						degreeOutNum = "0";
					}
					if(!this.isNumeric(degreeOutNum)){
						return "第" + count + "行，国外获得学位人数只能填写数字" ;
					}
					
					String degreeHongNum = cell[8].getContents();
					if(degreeHongNum == null||degreeHongNum.equals("")){
						degreeHongNum = "0";
					}
					if(!this.isNumeric(degreeHongNum)){
						return "第" + count + "行，香港获得学位人数只能填写数字" ;
					}
					
					String degreeAoNum = cell[9].getContents();
					if(degreeAoNum == null||degreeAoNum.equals("")){
						degreeAoNum = "0";
					}
					if(!this.isNumeric(degreeAoNum)){
						return "第" + count + "行，澳门获得学位人数只能填写数字" ;
					}
					
					String degreeTaiNum = cell[10].getContents();
					if(degreeTaiNum == null||degreeTaiNum.equals("")){
						degreeTaiNum = "0";
					}
					if(!this.isNumeric(degreeTaiNum)){
						return "第" + count + "行，台湾获得学位人数只能填写数字" ;
					}
					
					String sumAdmisNum = cell[11].getContents();
					if(sumAdmisNum == null||sumAdmisNum.equals("")){
						sumAdmisNum = "0";
					}
					if(!this.isNumeric(sumAdmisNum)){
						return "第" + count + "行，招生人数小计只能填写数字" ;
					}
					
					String admisOutNum = cell[12].getContents();
					if(admisOutNum == null||admisOutNum.equals("")){
						admisOutNum = "0";
					}
					if(!this.isNumeric(admisOutNum)){
						return "第" + count + "行，国外招生人数只能填写数字" ;
					}
					
					String admisHongNum = cell[13].getContents();
					if(admisHongNum == null||admisHongNum.equals("")){
						admisHongNum = "0";
					}
					if(!this.isNumeric(admisHongNum)){
						return "第" + count + "行，香港招生人数只能填写数字" ;
					}
					
					String admisAoNum = cell[14].getContents();
					if(admisAoNum == null||admisAoNum.equals("")){
						admisAoNum = "0";
					}
					if(!this.isNumeric(admisAoNum)){
						return "第" + count + "行，澳门招生人数只能填写数字" ;
					}
					
					String admisTaiNum = cell[15].getContents();
					if(admisTaiNum == null||admisTaiNum.equals("")){
						admisTaiNum = "0";
					}
					if(!this.isNumeric(admisTaiNum)){
						return "第" + count + "行，台湾招生人数只能填写数字" ;
					}
					
					String sumInSchNum = cell[16].getContents();
					if(sumInSchNum == null||sumInSchNum.equals("")){
						sumInSchNum = "0";
					}
					if(!this.isNumeric(sumInSchNum)){
						return "第" + count + "行，在校生人数小计只能填写数字" ;
					}
					
					String inSchOutNum = cell[17].getContents();
					if(inSchOutNum == null||inSchOutNum.equals("")){
						inSchOutNum = "0";
					}
					if(!this.isNumeric(inSchOutNum)){
						return "第" + count + "行，国外在校生人数只能填写数字" ;
					}
					
					String inSchHongNum = cell[18].getContents();
					if(inSchHongNum == null||inSchHongNum.equals("")){
						inSchHongNum = "0";
					}
					if(!this.isNumeric(inSchHongNum)){
						return "第" + count + "行，香港在校生人数只能填写数字" ;
					}
					
					String inSchAoNum = cell[19].getContents();
					if(inSchAoNum == null||inSchAoNum.equals("")){
						inSchAoNum = "0";
					}
					if(!this.isNumeric(inSchAoNum)){
						return "第" + count + "行，澳门在校生人数只能填写数字" ;
					}
					
					String inSchTaiNum = cell[20].getContents();
					if(inSchTaiNum == null||inSchTaiNum.equals("")){
						inSchTaiNum = "0";
					}
					if(!this.isNumeric(inSchTaiNum)){
						return "第" + count + "行，台湾在校生人数只能填写数字" ;
					}
					
							
					
				sum[0] += Integer.parseInt(sumGraNum);
				sum[1] += Integer.parseInt(graOutNum);
				sum[2] += Integer.parseInt(graHongNum);
				sum[3] += Integer.parseInt(graAoNum);
			    sum[4] += Integer.parseInt(graTaiNum);
				sum[5] += Integer.parseInt(sumDegreeNum);
				sum[6] += Integer.parseInt(degreeOutNum);
				sum[7] += Integer.parseInt(degreeHongNum);
				sum[8] += Integer.parseInt(degreeAoNum);
				sum[9] += Integer.parseInt(degreeTaiNum);
				sum[10] += Integer.parseInt(sumAdmisNum);
				sum[11] += Integer.parseInt(admisOutNum);
				sum[12] += Integer.parseInt(admisHongNum);
				sum[13] += Integer.parseInt(admisAoNum);
				sum[14] += Integer.parseInt(admisTaiNum);
				sum[15] += Integer.parseInt(sumInSchNum);
				sum[16] += Integer.parseInt(inSchOutNum);
				sum[17] += Integer.parseInt(inSchHongNum);
				sum[18] += Integer.parseInt(inSchAoNum);
				sum[19] += Integer.parseInt(inSchTaiNum);
				
				count++ ;

				t616Bean.setStuType(Category);
				t616Bean.setSumGraNum(Integer.parseInt(sumGraNum));
				t616Bean.setGraOutNum(Integer.parseInt(graOutNum));
				t616Bean.setGraHongNum(Integer.parseInt(graHongNum));
				t616Bean.setGraAoNum(Integer.parseInt(graAoNum));
				t616Bean.setGraTaiNum(Integer.parseInt(graTaiNum));
				t616Bean.setSumDegreeNum(Integer.parseInt(sumDegreeNum));
				t616Bean.setDegreeOutNum(Integer.parseInt(degreeOutNum));
				t616Bean.setDegreeHongNum(Integer.parseInt(degreeHongNum));
				t616Bean.setDegreeAoNum(Integer.parseInt(degreeAoNum));
				t616Bean.setDegreeTaiNum(Integer.parseInt(degreeTaiNum));
				t616Bean.setSumAdmisNum(Integer.parseInt(sumAdmisNum));
				t616Bean.setAdmisOutNum(Integer.parseInt(admisOutNum));
				t616Bean.setAdmisHongNum(Integer.parseInt(admisHongNum));
				t616Bean.setAdmisAoNum(Integer.parseInt(admisAoNum));
				t616Bean.setAdmisTaiNum(Integer.parseInt(admisTaiNum));
				t616Bean.setSumInSchNum(Integer.parseInt(sumInSchNum));
				t616Bean.setInSchOutNum(Integer.parseInt(inSchOutNum));
				t616Bean.setInSchHongNum(Integer.parseInt(inSchHongNum));
				t616Bean.setInSchAoNum(Integer.parseInt(inSchAoNum));
				t616Bean.setInSchTaiNum(Integer.parseInt(inSchTaiNum));
				
				
				
				list.add(t616Bean);
				
							
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		}
		
		flag = false ;
		
		T616_Bean sumBean = new  T616_Bean();
		sumBean.setStuType("总计");
		sumBean.setSumGraNum(sum[0]);
		sumBean.setGraOutNum(sum[1]);
		sumBean.setGraHongNum(sum[2]);
		sumBean.setGraAoNum(sum[3]);
		sumBean.setGraTaiNum(sum[4]);
		sumBean.setSumDegreeNum(sum[5]);
		sumBean.setDegreeOutNum(sum[6]);
		sumBean.setDegreeHongNum(sum[7]);
		sumBean.setDegreeAoNum(sum[8]);
		sumBean.setDegreeTaiNum(sum[9]);
		sumBean.setSumAdmisNum(sum[10]);
		sumBean.setAdmisOutNum(sum[11]);
		sumBean.setAdmisHongNum(sum[12]);
		sumBean.setAdmisAoNum(sum[13]);
		sumBean.setAdmisTaiNum(sum[14]);
		sumBean.setSumInSchNum(sum[15]);
		sumBean.setInSchOutNum(sum[16]);
		sumBean.setInSchHongNum(sum[17]);
		sumBean.setInSchAoNum(sum[18]);
		sumBean.setInSchTaiNum(sum[19]);
		
		list.add(0, sumBean);
		
		T616_Service t616Ser = new T616_Service() ;
		flag = t616Ser.batchInsert(list,selectYear) ;
		
		
		if(flag){
			return null ;
		}else{
			return "数据存储失败，请联系管理员" ;
		}
		
	}
	
	/**判断字符串是否是数字*/
	public boolean isNumeric(String str){
		  for (int i = str.length();--i>=0;){   
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
		 }
	
	
	/**将带%的字符串转换成double类型*/
	public double toDouble(String str){
		String dou=str.substring(0,str.length()-1);
	    double num1=Double.parseDouble(dou);
	    double num2=num1/100;
	    double num =(double)Math.round(num1)/100;
		return num;
	}
	
//	public boolean isDouble
	
	/**判断字符串是否为百分数*/
	public boolean isRatio(String str){
		boolean flag = false;
		if(str.indexOf("%")!=-1){
			String s = str.substring(0, str.indexOf("%"));
			try{
				double d=Double.parseDouble(s);
				if(d<=100&&d>=0){
					flag = true;
				}
			}catch(NumberFormatException ex){
				flag = false;
			}
		}
		return flag;
		
	}
	
//	public static boolean isCorrectVersion(String odivalue) {  
//	    // TODO Auto-generated method stub  
//	    Pattern pattern = Pattern.compile("d{2}" + "%");  
//	    Matcher isNum = pattern.matcher(odivalue);  
//	    if( !isNum.matches() )  
//	    {  
//	        return false;  
//	    }  
//	        return true;  
//	    }   
	public static void main(String arg[]){
		T616_Excel exl= new T616_Excel();
		String a = "20.3%";
		String b="20";
		boolean flag=exl.isRatio(a);
		if(flag){
			System.out.println("是");
		}else{
			System.out.println("否");
		}
	}

}