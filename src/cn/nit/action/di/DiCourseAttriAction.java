/* 
* @Title: DICourseCategoriesACtion.java
* @Package cn.bjtu.di.action
* @Description 
* @author Lei Xia
*      Email: xialei199023@163.com
* @copyright BJTU(C)2014
* @date 2014-5-14 下午02:24:41
* @version V1.0 
*/
package cn.nit.action.di;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;
import cn.nit.bean.di.DiCourseAttriBean;
import cn.nit.service.di.DiCourseAttriService;

/**
 * 课程性质action
 * @author Lei Xia
 * @time: 2014-5-14/下午02:24:41
 */
public class DiCourseAttriAction {

	private DiCourseAttriService diCourseAttriSer = new DiCourseAttriService() ;
	
	/**
	 * 初始化加载课程性质字典
	 *
	 * @time: 2014-5-14/下午03:03:37
	 */
	public void loadCourseAttri(){
		
		List<DiCourseAttriBean> list = diCourseAttriSer.getList() ;
		//将数据转换为json格式
		JSON json = JSONSerializer.toJSON(list) ;
		PrintWriter out = null ;
		
		try {
			//设置输出内容的格式为json
			getResponse().setContentType("application/json; Attriset=UTF-8") ;
			out = getResponse().getWriter() ;
			//设置数据的内容的编码格式
			String outPrint = URLDecoder.decode(json.toString(), "UTF-8") ;
			System.out.println(outPrint) ;
			out.print(outPrint) ;
			out.flush() ;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	/**
	 * 获取request
	 * @return
	 *
	 * @time: 2014-5-14/下午03:07:00
	 */
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest() ;
	}
	
	/**
	 * 获取session
	 * @return
	 *
	 * @time: 2014-5-14/下午03:07:14
	 */
	public HttpSession getSession(){
		return getRequest().getSession() ;
	}
	
	/**
	 * 获取response
	 * @return
	 *
	 * @time: 2014-5-14/下午03:07:24
	 */
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse() ;
	}
	
}
