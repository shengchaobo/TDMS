package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiEducationBean;
import cn.nit.dao.di.DiEducationDao;


public class DiEducationService {
	
	private DiEducationDao educationDao = new DiEducationDao() ;
	/**
	 * 加载所有的学历
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<DiEducationBean> getList(){
		return educationDao.getList() ;
	}
	
	/**
	 * 新增一个学历
	 * @param education
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 */
	public boolean insert(DiEducationBean identiType){
		return educationDao.insert(identiType) ;
	}


}
