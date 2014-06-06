package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiContestLevelBean;
import cn.nit.dao.di.DiContestLevelDao;

public class DiContestLevelService {
	
	private DiContestLevelDao ContestLevelDao = new DiContestLevelDao() ;
	/**
	 * 加载所有的竞赛级别
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<DiContestLevelBean> getList(){
		return ContestLevelDao.getList() ;
	}
	
	/**
	 * 新增一个竞赛级别
	 * @param degree
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 */
	public boolean insert(DiContestLevelBean degree){
		return ContestLevelDao.insert(degree) ;
	}


}
