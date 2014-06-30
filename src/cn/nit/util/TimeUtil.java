package cn.nit.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式转换工具类
 * @author lenovo
 *
 */
public class TimeUtil {

	

	/**
	 * 将{@link java.util.Date}日期格式转换为yyyy/MM/dd
	 * @param date
	 * @return
	 */
	public static String changeFormat1(Date date){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd") ;
		return sf.format(date) ;
	}
	
	/**
	 * 将{@link java.util.Date}的格式转换为yyyy/MM
	 * @param date
	 * @return
	 */
	public static String changeFormat2(Date date){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM") ;
		return sf.format(date) ;
	}
	
	/**
	 * 将{@link java.util.Date}的格式转换为yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String changeFormat4(Date date){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd") ;
		return sf.format(date) ;
	}
	
	/**
	 * 将{@link java.util.Date}日期格式转换为yyyy
	 * @param date
	 * @return
	 */
	public static String changeFormat5(Date date){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy") ;
		return sf.format(date) ;
	}
	
	/**
	 * 将{@link java.lang.String}类型转换为{@link java.util.Date}类型
	 * @param dateString
	 * @return
	 */
	public static Date changeDateY(String dateString){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy") ;
		Date date = null ;
		try {
			date = sf.parse(dateString) ;
		} catch (Exception e) {
			e.printStackTrace();
			return date ;
		}
		
		return date ;
	}
	
	/**
	 * 将{@link java.lang.String}类型转换为{@link java.util.Date}类型
	 * @param dateString
	 * @return
	 */
	public static Date changeDateYM(String dateString){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM") ;
		Date date = null ;
		try {
			date = sf.parse(dateString) ;
		} catch (Exception e) {
			e.printStackTrace();
			return date ;
		}
		
		return date ;
	}
	
	/**
	 * 将{@link java.lang.String}类型转换为{@link java.util.Date}类型
	 * @param dateString
	 * @return
	 */
	public static Date changeDateYMD(String dateString){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd") ;
		Date date = null ;
		try {
			date = sf.parse(dateString) ;
		} catch (Exception e) {
			e.printStackTrace();
			return date ;
		}
		
		return date ;
	}
	
	
	
	/**
	 * 将{@link java.lang.String}类型转换为{@link java.util.Date}类型
	 * @param dateString
	 * @return
	 */
	public static Date changeDate3(String dateString){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy\\MM\\dd") ;
		Date date = null ;
		try {
			date = sf.parse(dateString) ;
		} catch (ParseException e) {
			e.printStackTrace();
			return date ;
		}
		
		return date ;
	}
	
	/**判断字符串格式是否为2013/02*/
	public static boolean judgeFormat1(String dataString){
		boolean flag=false;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM") ;
		Date date=null;
		try{
			date = sf.parse(dataString) ;
			flag = true;
		}catch(ParseException e){
			flag=false;
		}
		return flag;
	}
	
	/**判断字符串格式是否为2013/02/01*/
	public static boolean judgeFormat2(String dataString){
		boolean flag=false;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd") ;
		Date date=null;
		try{
			date = sf.parse(dataString) ;
			flag = true;
		}catch(ParseException e){
			flag=false;
		}
		return flag;
	}
	
	/**判断字符串格式是否为2013*/
	public static boolean judgeFormat3(String dataString){
		boolean flag=false;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy") ;
		Date date=null;
		try{
			date = sf.parse(dataString);
			flag=true;
		}catch(ParseException e){
			flag=false;
		}
		return flag;
	}
	
	public static void main(String arg[])
	{
		String year="2013";
		TimeUtil til=new TimeUtil();
//		boolean flag=til.judgeFormat3(year);
//		if(flag){
//			System.out.println("格式正确！");
//		}else{
//			System.out.println("格式错误！");
//		}
//		
//		String test = "1973/02/02";
//		
//		System.out.println(changeDateY(test));
//		System.out.println(changeDateYM(test));
//		System.out.println(changeDateYMD(test));
	
		System.out.println(til.changeDateY(year));
		

	}
}
