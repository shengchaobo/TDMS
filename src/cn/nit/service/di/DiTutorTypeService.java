package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiTutorTypeBean;
import cn.nit.bean.di.DiTutorTypeBean;
import cn.nit.dao.di.DiTutorTypeDao;

public class DiTutorTypeService {
	
	private DiTutorTypeDao tutorTypeDao = new DiTutorTypeDao() ;
	/**
	 * 加载所有的学位
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<DiTutorTypeBean> getList(){
		return tutorTypeDao.getList() ;
	}
	
	/**
	 * 新增一个导师类别
	 * @param 
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 */
	public boolean insert(DiTutorTypeBean typebean){
		return tutorTypeDao.insert(typebean) ;
	}
	
	/**
	 * 更新
	 * @param userinfo
	 * @return
	 */
	public boolean update(DiTutorTypeBean typebean){
		
		return tutorTypeDao.update(typebean) ;
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	public boolean deleteByIds(String ids){
		return tutorTypeDao.deleteByIds(ids) ;
	}
	
	
	/**
	 * 判断中是否已包含该数据
	 */
	
	public boolean hasType(String typeID){
		return tutorTypeDao.hasType(typeID);
	}


}
