package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiAwardTypeBean;
import cn.nit.bean.di.DiTutorTypeBean;
import cn.nit.dao.di.DiAwardTypeDao;

public class DiAwardTypeService {
	
	private DiAwardTypeDao awardTypeDao = new DiAwardTypeDao() ;
	/**
	 * 加载所有的学位
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<DiAwardTypeBean> getList(){
		return awardTypeDao.getList() ;
	}
	
	/**
	 * 根据type选择
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<DiAwardTypeBean> getList(String type){
		return awardTypeDao.getList(type) ;
	}
	
	/**
	 * 新增一个学位
	 * @param degree
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 */
	public boolean insert(DiAwardTypeBean award){
		return awardTypeDao.insert(award) ;
	}
	
	/**
	 * 更新
	 * @param userinfo
	 * @return
	 */
	public boolean update(DiAwardTypeBean typebean){
		
		return awardTypeDao.update(typebean) ;
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	public boolean deleteByIds(String ids){
		return awardTypeDao.deleteByIds(ids) ;
	}
	
	
	/**
	 * 判断中是否已包含该数据
	 */
	
	public boolean hasType(String typeID){
		return awardTypeDao.hasType(typeID);
	}
}
