/* 
* @Title: Log4jUtil.java
* @Package cn.bjtu.util
* @Description 
* @author Lei Xia
*      Email: xialei199023@163.com
* @copyright BJTU(C)2014
* @date 2014-4-19 上午10:05:58
* @version V1.0 
*/
package cn.nit.util;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

/**
 * 
 * @author Lei Xia
 * @time: 2014-4-19/上午10:05:58
 */
public class Log4jUtil extends HttpServlet{

	public void service(ServletRequest req, ServletResponse res){
		
	}
	
	public void init(){
		PropertyConfigurator.configure(getServletContext().getRealPath("/") + getInitParameter("configfile")) ;
	}
}
