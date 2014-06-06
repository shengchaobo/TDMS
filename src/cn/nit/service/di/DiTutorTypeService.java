package cn.nit.service.di;

import java.util.List;

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
	 * 新增一个学位
	 * @param degree
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 */
	public boolean insert(DiTutorTypeBean degree){
		return tutorTypeDao.insert(degree) ;
	}


}
