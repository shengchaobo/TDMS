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

import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.bean.di.DiMajorTwoBean;
import cn.nit.bean.table5.T533Bean;
import cn.nit.service.di.DiDepartmentService;
import cn.nit.service.di.DiMajorTwoService;
import cn.nit.service.table5.T533Service;
import cn.nit.util.TimeUtil;

public class T533Excel {
	
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
		List<T533Bean> list = new LinkedList<T533Bean>() ;
//		UserRoleBean userinfo = (UserRoleBean)request.getSession().getAttribute("userinfo") ;
		DiDepartmentService diDepartSer = new DiDepartmentService() ;
		List<DiDepartmentBean> diDepartBeanList = diDepartSer.getList() ;
		DiMajorTwoService diMajorTwoSer=new DiMajorTwoService();
		List<DiMajorTwoBean> diMajorTwoBeanList=diMajorTwoSer.getList();
		
		for(Cell[] cell : cellList){
			T533Bean t533Bean = new  T533Bean();
			int n=cellList.indexOf(cell);
			if(count<4){
				count++;
				continue;
			}
			else{
				
				
			  try{
				 
				 String TeaUnit = cell[1].getContents() ;
					String UnitID = cell[2].getContents() ;
					
					if(TeaUnit == null || TeaUnit.equals("")){
						return "第" + count + "行，教学单位不能为空" ;
					}
					
					if(UnitID == null || UnitID.equals("")){
						return "第" + count + "行，单位编号不能为空" ;
					}
					
					if(UnitID.length() > 50){
						return "第" + count + "行，单位编号字数不超过50个数字或字母" ;
					}
					
					for(DiDepartmentBean diDepartBean : diDepartBeanList){
						if(diDepartBean.getUnitId().equals(UnitID)){
							if(diDepartBean.getUnitName().equals(TeaUnit)){
								flag = true ;
								break ;
							}else{
								return "第" + count + "行，教学单位与单位编号不对应" ;
							}
						}//if
					}//for
					
					if(!flag){
						return "第" + count + "行，没有与之相匹配的单位编号" ;
					}else{
						flag = false ;
					}
					
					String MajorName = cell[3].getContents() ;
					String MajorID = cell[4].getContents() ;
					
					if(MajorName == null || MajorName.equals("")){
						return "第" + count + "行，专业名称不能为空" ;
					}
					
					if(MajorID == null || MajorID.equals("")){
						return "第" + count + "行，专业代码不能为空" ;
					}
					
					if(MajorID.length() > 50){
						return "第" + count + "行，专业代码字数不超过50个数字或字母" ;
					}
					
					for(DiMajorTwoBean diDMajorTwoBean : diMajorTwoBeanList){
						if(diDMajorTwoBean.getMajorNum().equals(MajorID)){
							if(diDMajorTwoBean.getMajorName().equals(MajorName)){
								flag = true ;
								break ;
							}else{
								return "第" + count + "行，专业名称与专业代码不对应" ;
							}
						}//if
					}//for
					
					if(!flag){
						return "第" + count + "行，没有与之相匹配的专业代码" ;
					}else{
						flag = false ;
					}
					
					String ExpCSNum = cell[5].getContents() ;
					
					if(ExpCSNum == null || ExpCSNum.equals("")){
						ExpCSNum="0";
					}
					if(!this.isNumeric(ExpCSNum)){
						return "第" + count + "行，实验课程门数应为数字" ;
					}
					
					
					String IndepentExpCSNum=cell[6].getContents();
//					System.out.println("BuildCon:"+BuildCon);
					
					if(IndepentExpCSNum == null || IndepentExpCSNum.equals("")){
						IndepentExpCSNum ="0" ;
					}
					
					if (!this.isNumeric(IndepentExpCSNum)){
						return "第" + count + "行，独立实验课程只能填数字" ;
					}
					
					String DesignExpCSNum=cell[7].getContents();
//					System.out.println("BuildCon:"+BuildCon);
					
					if(DesignExpCSNum == null || DesignExpCSNum.equals("")){
						DesignExpCSNum ="0"; ;
					}
					
					if (!this.isNumeric(DesignExpCSNum)){
						return "第" + count + "行，综合型实验教学门数只能填数字" ;
					}
					
					String ExpRatio=cell[8].getContents();
					
					if(ExpRatio == null || ExpRatio.equals("")){
						return "第" + count + "行，实验开出率（%）不能为空" ;
					}
					if(!this.isRatio(ExpRatio)){
						return "第" + count + "行，实验开出率（%）格式有误，请填写百分数" ;
					}
					
				
				count++ ;
				String fillUnitID="3001";
				t533Bean.setDesignExpCSNum(Integer.parseInt(DesignExpCSNum));
				t533Bean.setExpCSNum(Integer.parseInt(ExpCSNum));
				t533Bean.setExpRatio(this.toDouble(ExpRatio));
				t533Bean.setIndepentExpCSNum(Integer.parseInt(IndepentExpCSNum));
				t533Bean.setMajorID(MajorID);
				t533Bean.setMajorName(MajorName);
				t533Bean.setTeaUnit(TeaUnit);
				t533Bean.setUnitID(UnitID);
				t533Bean.setFillUnitID(fillUnitID);
				t533Bean.setTime(Time);
				list.add(t533Bean);
				
							
			}
			catch(Exception e){
				e.printStackTrace() ;
				return "上传文件不合法！！！" ;
			}
	     }
		}
		
		flag = false ;
		T533Service t533Ser = new T533Service() ;
		flag = t533Ser.batchInsert(list) ;
		
		if(flag){
			return "数据导入成功" ;
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
