package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiResearchTypeBean;
import cn.nit.bean.di.DiTutorTypeBean;
import cn.nit.dao.di.DiResearchTypeDao;


public class DiResearchTypeService {
	
	private DiResearchTypeDao researchTypeDao = new DiResearchTypeDao() ;
	/**
	 * 加载所有的科研类型
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<DiResearchTypeBean> getList(){
		return researchTypeDao.getList() ;
	}
	
	/**
	 * 新增一个科研类型
	 * @param researchType
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 */
	public boolean insert(DiResearchTypeBean Type){
		return researchTypeDao.insert(Type) ;
	}
	
	/**
	 * 更新
	 * @param userinfo
	 * @return
	 */
	public boolean update(DiResearchTypeBean typebean){
		
		return researchTypeDao.update(typebean) ;
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	public boolean deleteByIds(String ids){
		return researchTypeDao.deleteByIds(ids) ;
	}
	
	
	/**
	 * 判断中是否已包含该数据
	 */
	
	public boolean hasType(String typeID){
		return researchTypeDao.hasType(typeID);
	}


}
