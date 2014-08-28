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
	HttpServletResponse response = ServletActionContext.getResponse();
	private DiCourseAttriBean attri_bean = new DiCourseAttriBean();
	private String ids ;
	
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
	
	public void edit(){
		
		boolean flag = diCourseAttriSer.update(attri_bean) ;
		
		PrintWriter out = null ;
		
		try{
			
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			if(flag){
				out.print("{\"state\":true,data:\"更新成功!!!\"}") ;
			}else{
				out.print("{\"state\":true,data:\"更新失败!!!\"}") ;
			}
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"系统错误，请联系管理员!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	/**
	 * 添加用户
	 */
	public void insert(){
		
		//首先该用 户是否已存数据库
		Boolean flag0 = diCourseAttriSer.hasAttri(attri_bean.getIndexId());
		
		boolean flag = false;
		//如果该用户不存数据库，可以添加
		if(flag0 == false){			
			flag = diCourseAttriSer.insert(attri_bean) ;
		}
		
		PrintWriter out = null ;
		
		try{
			
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			
			if(flag0){
				out.print("{\"state\":true,data:\"该课程属性已存在!!!\"}") ;
			}else{
				if(flag){
					out.print("{\"state\":true,data:\"添加成功!!!\"}") ;
				}else{
					out.print("{\"state\":false,data:\"添加失败!!!\"}") ;
				}
			}

			
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"添加失败!!!\"}") ;
		}finally{
			out.flush() ;
			
			if(out != null){
				out.close() ;
			}
		}
	}
	
	/**
	 * 删除用户
	 */
	public void deleteByIds(){
		
		boolean flag = diCourseAttriSer.deleteByIds(ids) ;
		PrintWriter out = null ;
		
		try{
			getResponse().setContentType("text/html; charset=UTF-8") ;
			out = getResponse().getWriter() ;
			
			if(flag){
				out.print("{\"state\":true,data:\"删除成功!!!\"}") ;
			}else{
				out.print("{\"state\":false,data:\"删除失败!!!\"}") ;
			}
			
			out.flush() ;
		}catch(Exception e){
			e.printStackTrace() ;
			out.print("{\"state\":false,data:\"系统错误，请联系管理员!!!\"}") ;
		}finally{
			if(out != null){
				out.close() ;
			}
		}
	}
	
	public void setAttri_bean(DiCourseAttriBean attri_bean) {
		this.attri_bean = attri_bean;
	}

	public DiCourseAttriBean getAttri_bean() {
		return attri_bean;
	}
	
	
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
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
