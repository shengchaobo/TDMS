package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiContestLevelBean;
import cn.nit.bean.di.DiTitleLevelBean;
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
	public boolean insert(DiContestLevelBean levelbean){
		return ContestLevelDao.insert(levelbean) ;
	}
	
	/**
	 * 更新
	 * @param userinfo
	 * @return
	 */
	public boolean update(DiContestLevelBean levelbean){
		
		return ContestLevelDao.update(levelbean) ;
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	public boolean deleteByIds(String ids){
		return ContestLevelDao.deleteByIds(ids) ;
	}
	
	
	/**
	 * 判断中是否已包含该数据
	 */
	
	public boolean hasLevel(String levelID){
		return ContestLevelDao.hasLevel(levelID);
	}


}
