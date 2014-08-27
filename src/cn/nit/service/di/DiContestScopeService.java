package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiContestLevelBean;
import cn.nit.bean.di.DiContestScopeBean;
import cn.nit.dao.di.DiContestScopeDao;

public class DiContestScopeService {
	
	private DiContestScopeDao ContestScopeDao = new DiContestScopeDao() ;
	/**
	 * 加载所有的竞赛范围
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<DiContestScopeBean> getList(){
		return ContestScopeDao.getList() ;
	}
	
	/**
	 * 新增一个竞赛范围
	 * @param degree
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 */
	public boolean insert(DiContestScopeBean scopebean){
		return ContestScopeDao.insert(scopebean) ;
	}
	
	/**
	 * 更新
	 * @param userinfo
	 * @return
	 */
	public boolean update(DiContestScopeBean scopebean){
		
		return ContestScopeDao.update(scopebean) ;
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	public boolean deleteByIds(String ids){
		return ContestScopeDao.deleteByIds(ids) ;
	}
	
	
	/**
	 * 判断中是否已包含该数据
	 */
	
	public boolean hasScope(String scopeID){
		return ContestScopeDao.hasScope(scopeID);
	}


}
