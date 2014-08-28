/* 
* @Title: DiCourseAtrriService.java
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

import cn.nit.bean.di.DiCourseAttriBean;
import cn.nit.bean.di.DiCourseAttriBean;
import cn.nit.dao.di.DiCourseAttriDao;

/**
 * 课程性质字典表Service类
 * @author Lei Xia
 * @time: 2014-5-15/上午11:12:32
 */
public class DiCourseAttriService {

	private DiCourseAttriDao DiCourseAttriDao = new DiCourseAttriDao() ;
	
	public List<DiCourseAttriBean> getList(){
		
		return DiCourseAttriDao.getList() ;
	}
	
	public boolean insert(DiCourseAttriBean attribean){
		return DiCourseAttriDao.insert(attribean) ;
	}
	
	/**
	 * 更新
	 * @param userinfo
	 * @return
	 */
	public boolean update(DiCourseAttriBean attribean){
		
		return DiCourseAttriDao.update(attribean) ;
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	public boolean deleteByIds(String ids){
		return DiCourseAttriDao.deleteByIds(ids) ;
	}
	
	
	/**
	 * 判断中是否已包含该数据
	 */
	
	public boolean hasAttri(String attriID){
		return DiCourseAttriDao.hasAttri(attriID);
	}
}
