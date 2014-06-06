/* 
* @Title: DiCourseCharService.java
* @Package cn.bjtu.di.service
* @Description 
* @author Lei Xia
*      Email: xialei199023@163.com
* @copyright BJTU(C)2014
* @date 2014-5-15 上午11:12:32
* @version V1.0 
*/
package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiCourseCharBean;
import cn.nit.dao.di.DiCourseCharDao;

/**
 * 课程性质字典表Service类
 * @author Lei Xia
 * @time: 2014-5-15/上午11:12:32
 */
public class DiCourseCharService {

	private DiCourseCharDao DiCourseCharDao = new DiCourseCharDao() ;
	
	public List<DiCourseCharBean> getList(){
		
		return DiCourseCharDao.getList() ;
	}
}
