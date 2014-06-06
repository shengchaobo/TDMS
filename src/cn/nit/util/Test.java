/* 
* @Title: Test.java
* @Package cn.bjtu.util
* @Description 
* @author Lei Xia
*      Email: xialei199023@163.com
* @copyright BJTU(C)2014
* @date 2014-5-14 下午03:25:14
* @version V1.0 
*/
package cn.nit.util;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.dao.di.DiCourseCategoriesDao;

/**
 * 
 * @author Lei Xia
 * @time: 2014-5-14/下午03:25:14
 */
public class Test {

	private int total ;
	
	private List rows ;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	public static void main(String args[]) throws Exception{
//		DICourseCategoriesDAO dicourseDao = new DICourseCategoriesDAO() ;
//		Test test = new Test() ;
//		test.setRows(dicourseDao.getList()) ;
//		test.setTotal(dicourseDao.getList().size()) ;
//		JSON json = JSONSerializer.toJSON(test) ;
//		
//		System.out.println(json.toString()) ;
		Class clazz = Class.forName("cn.nit.excel.imports.table5.UndergraCSBaseTeaExcel") ;
		Method method = clazz.getDeclaredMethod("batchInsert", List.class, HttpServletRequest.class) ;
		String errorMsg = (String)method.invoke(clazz.newInstance(), null, null) ;
		System.out.println(errorMsg) ;
	}
}
