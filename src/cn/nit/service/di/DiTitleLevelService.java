package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiTitleLevelBean;
import cn.nit.dao.di.DiTitleLevelDao;

public class DiTitleLevelService {

	private DiTitleLevelDao titleLevelDao = new DiTitleLevelDao() ;
	/**
	 * 加载所有的专业技术职称
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<DiTitleLevelBean> getList(){
		return titleLevelDao.getList() ;
	}
	
	/**
	 * 新增一个专业技术职称
	 * @param titleLevel
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 */
	public boolean insert(DiTitleLevelBean identiType){
		return titleLevelDao.insert(identiType) ;
	}

}
