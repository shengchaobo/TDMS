package cn.nit.excel.imports.table5;

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
import cn.nit.bean.table5.T513Bean;
import cn.nit.service.di.DiEvaluTypeService;
import cn.nit.service.table5.T513Service;
import cn.nit.util.TimeUtil;

public class T513Excel {
	
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
//		boolean biOpen=false;
//		boolean buildCondi=false;
		Date Time = new Date();
		List<T513Bean> list = new LinkedList<T513Bean>() ;
//		UserRoleBean userinfo = (UserRoleBean)request.getSession().getAttribute("userinfo") ;
		DiEvaluTypeService  dieSer = new DiEvaluTypeService();
		List<DiEvaluTypeBean> dieList = dieSer.getList();
		for(Cell[] cell : cellList){
			T513Bean t513Bean = new  T513Bean();
			int n=cellList.indexOf(cell);
			if(count<5){
				count++;
				continue;
			}
			else{
				
				
			  try{
				 
				 String Item = cell[0].getContents() ;
				 if(Item == null || Item.equals("")){
					 return "第" + count + "行，项目不能为空" ;
				 }
				 for(DiEvaluTypeBean dieBean :dieList){
					 if(dieBean.getEvaluType().equals(Item)){
						 Item = dieBean.getIndexId();
						 flag = true;
						 break;
					 }
				 }
				 if(!flag){
					 return "第" + count + "行，此项目不存在！" ;
				 }
					String Category = cell[1].getContents() ;
					
					if(Category == null || Category.equals("")){
						return "第" + count + "行，分类不能为空" ;
					}
					if(!Category.equals("理论课")&&!Category.equals("实践教学")){
						return "第" + count + "行，分类只能填写“理论课”或“实践教学”" ;
					}
					
					String ShouldASCSNum = cell[2].getContents();
					if(ShouldASCSNum == null||ShouldASCSNum.equals("")){
						return "第" + count + "行，应评课程总门次数不能为空" ;
					}
					if(!this.isNumeric(ShouldASCSNum)){
						return "第" + count + "行，应评课程总门次数后只能填写数字" ;
					}
					
					String HavedASCSNum = cell[3].getContents();
					if(HavedASCSNum == null||HavedASCSNum.equals("")){
						return "第" + count + "行，已评课程门次数不能为空" ;
					}
					if(!this.isNumeric(HavedASCSNum)){
						return "第" + count + "行，已评课程门次数只能填数字" ;
					}
					
					String CoverRatio = cell[4].getContents();
					if(CoverRatio == null || CoverRatio.equals("")){
						return "第" + count + "行，覆盖比例（%）不能为空" ;
					}
					if(!this.isRatio(CoverRatio)){
						return "第" + count + "行，覆盖比例（%）只能填百分数" ;
					}
					
					String ExcellentNum = cell[5].getContents();
					if(ExcellentNum == null ||ExcellentNum.equals("")){
						return "第" + count + "行，优级门次数不能为空" ;
					}
					if(!this.isNumeric(ExcellentNum)){
						return "第" + count + "行，优级门次数只能填数字" ;
					}
					
					String ExcellentRatio = cell[6].getContents();
					if(ExcellentRatio == null ||ExcellentRatio.equals(ExcellentRatio)){
						return "第" + count + "行，优级比例只能填数字" ;
					}
					if(!this.isRatio(ExcellentRatio)){
						return "第" + count + "行，优级比例只能填百分比" ;
					}
					
					String GoodNum = cell[7].getContents();
					if(GoodNum == null ||GoodNum.equals("")){
						return "第" + count + "行，良级门次数不能为空" ;
					}
					if(!this.isNumeric(GoodNum)){
						return "第" + count + "行，良级门次数只能填数字" ;
					}
					
					String GoodRatio = cell[8].getContents();
					if(GoodRatio == null ||ExcellentRatio.equals(GoodRatio)){
						return "第" + count + "行，良级比例只能填数字" ;
					}
					if(!this.isRatio(GoodRatio)){
						return "第" + count + "行，良级比例只能填百分比" ;
					}
					
					String AvgNum = cell[9].getContents();
					if(AvgNum == null ||AvgNum.equals("")){
						return "第" + count + "行，中级门次数不能为空" ;
					}
					if(!this.isNumeric(AvgNum)){
						return "第" + count + "行，中级门次数只能填数字" ;
					}
					
					String AvgRatio = cell[10].getContents();
					if(ExcellentRatio == null ||ExcellentRatio.equals("")){
						return "第" + count + "行，中级比例只能填数字" ;
					}
					if(!this.isRatio(AvgRatio)){
						return "第" + count + "行，中级比例只能填百分比" ;
					}
					
					String PoorNum = cell[11].getContents();
					if(PoorNum == null ||PoorNum.equals("")){
						return "第" + count + "行，差级门次数不能为空" ;
					}
					if(!this.isNumeric(PoorNum)){
						return "第" + count + "行，差级门次数只能填数字" ;
					}
					
					String PoorRatio = cell[12].getContents();
					if(PoorRatio == null ||PoorRatio.equals("")){
						return "第" + count + "行，差级比例只能填数字" ;
					}
					if(!this.isRatio(ExcellentRatio)){
						return "第" + count + "行，差级比例只能填百分比" ;
					}
			
				count++ ;
//				String fillUnitID="3001";
				t513Bean.setAvgNum(Integer.parseInt(AvgNum));
				t513Bean.setAvgRatio(this.toDouble(AvgRatio));
				t513Bean.setCategory(Category);
				t513Bean.setCoverRatio(this.toDouble(CoverRatio));
				t513Bean.setExcellentNum(Integer.parseInt(ExcellentNum));
				t513Bean.setExcellentRatio(this.toDouble(ExcellentRatio));
				t513Bean.setGoodNum(Integer.parseInt(GoodNum));
				t513Bean.setGoodRatio(this.toDouble(GoodRatio));
				t513Bean.setHavedASCSNum(Integer.parseInt(HavedASCSNum));
				t513Bean.setItem(Item);
				t513Bean.setPoorNum(Integer.parseInt(PoorNum));
				t513Bean.setPoorRatio(this.toDouble(PoorRatio));
				t513Bean.setShouldASCSNum(Integer.parseInt(ShouldASCSNum));
				t513Bean.setTime(selectYear);
				
				list.add(t513Bean);
				
							
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		}
		
		flag = false ;
		T513Service t513Ser = new T513Service() ;
		flag = t513Ser.batchInsert(list,selectYear) ;
		
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
		T533Excel exl= new T533Excel();
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
