package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiSourceBean;
import cn.nit.dao.di.DiSourceDao;


public class DiSourceService {

	private DiSourceDao sourceDao = new DiSourceDao() ;
	/**
	 * 加载所有的学缘
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<DiSourceBean> getList(){
		return sourceDao.getList() ;
	}
	
	/**
	 * 新增一个学缘
	 * @param source
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 */
	public boolean insert(DiSourceBean identiType){
		return sourceDao.insert(identiType) ;
	}

}
