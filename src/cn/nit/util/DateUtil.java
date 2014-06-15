package cn.nit.util;

import java.text.DecimalFormat;

public class DateUtil {
	
	/**判断字符串是否为数字*/
	public static boolean isNumeric(String str){
		  for (int i = str.length();--i>=0;){   
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
		 }
	
	/**判断字符串是否为double类型*/
	public static boolean isDouble(String str){
		 try
		 {
			 Double.parseDouble(str);
			 return true;
		 }
		 catch(NumberFormatException ex)
		 {
			 return false;
		 }
	}
	
	/**将字符串转换为精度为两位小数的double类型数*/
	public static double doubleTwo(String str){
		
		double d=Double.valueOf(str);
		DecimalFormat df = new DecimalFormat("0.00");
		String doubleStr=df.format(d);
		double newD=Double.parseDouble(doubleStr);
		return newD;
	}
	
	public static void main(String arg[]){
		DateUtil date=new DateUtil();
		double d=date.doubleTwo("23.4523");
		System.out.println(d);
	}

}
