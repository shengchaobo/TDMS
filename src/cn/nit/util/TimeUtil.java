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
	
	public static void main(String arg[])
	{
		String year="2014";
		TimeUtil til=new TimeUtil();
		
		System.out.println(changeDateY(year));
	}
}
