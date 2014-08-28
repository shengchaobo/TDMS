package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiTalentTypeBean;
import cn.nit.bean.di.DiTutorTypeBean;
import cn.nit.dao.di.DiTalentTypeDao;

public class DiTalentTypeService {
	
	private DiTalentTypeDao talentTypeDao = new DiTalentTypeDao() ;
	/**
	 * 加载所有的学位
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<DiTalentTypeBean> getList(){
		return talentTypeDao.getList() ;
	}
	
	/**
	 * 新增一个学位
	 * @param degree
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 */
	public boolean insert(DiTalentTypeBean typebean){
		return talentTypeDao.insert(typebean) ;
	}
	
	/**
	 * 更新
	 * @param userinfo
	 * @return
	 */
	public boolean update(DiTalentTypeBean typebean){
		
		return talentTypeDao.update(typebean) ;
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	public boolean deleteByIds(String ids){
		return talentTypeDao.deleteByIds(ids) ;
	}
	
	
	/**
	 * 判断中是否已包含该数据
	 */
	
	public boolean hasType(String typeID){
		return talentTypeDao.hasType(typeID);
	}


}
