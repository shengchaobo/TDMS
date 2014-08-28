/* 
* @Title: DICourseCategoriesService.java
* @Package cn.bjtu.di.service
* @Description 
* @author Lei Xia
*      Email: xialei199023@163.com
* @copyright BJTU(C)2014
* @date 2014-5-14 下午02:25:30
* @version V1.0 
*/
package cn.nit.service.di;

import java.util.List;


import cn.nit.bean.di.DiCourseCategoriesBean;
import cn.nit.dao.di.DiCourseCategoriesDao;


/**
 * 课程类别的service类
 * @author Lei Xia
 * @time: 2014-5-14/下午02:25:30
 */
public class DiCourseCategoriesService {

	private DiCourseCategoriesDao diCourseCategoriesDao = new DiCourseCategoriesDao() ;
	
	/**
	 * 加载所有的课程类别数据字典表的内容
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<DiCourseCategoriesBean> getList(){
		return diCourseCategoriesDao.getList() ;
	}
	
	/**
	 * 新增课程类别字典表的数据
	 * @param diCourseCategories
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 */
	public boolean insert(DiCourseCategoriesBean diCourseCategories){
		return diCourseCategoriesDao.insert(diCourseCategories) ;
	}
	
	/**
	 * 更新
	 * @param userinfo
	 * @return
	 */
	public boolean update(DiCourseCategoriesBean catebean){
		
		return diCourseCategoriesDao.update(catebean) ;
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	public boolean deleteByIds(String ids){
		return diCourseCategoriesDao.deleteByIds(ids) ;
	}
	
	
	/**
	 * 判断中是否已包含该数据
	 */
	
	public boolean hasCate(String cateID){
		return diCourseCategoriesDao.hasCate(cateID);
	}
}
