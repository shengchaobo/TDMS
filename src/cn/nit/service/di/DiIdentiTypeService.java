package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.RoleBean;
import cn.nit.bean.di.DiIdentiTypeBean;
import cn.nit.dao.di.DiIdentiTypeDao;

public class DiIdentiTypeService {
	
	private DiIdentiTypeDao identiTypeDao = new DiIdentiTypeDao() ;
	/**
	 * 加载所有的教职工的身份代码
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<DiIdentiTypeBean> getList(){
		return identiTypeDao.getList() ;
	}
	
	/**
	 * 新增一个教职工身份代码
	 * @param identiType
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 */
	public boolean insert(DiIdentiTypeBean identiType){
		return identiTypeDao.insert(identiType) ;
	}
	
	/**
	 * 更新身份
	 * @param userinfo
	 * @return
	 */
	public boolean update(DiIdentiTypeBean identiType){
		
		return identiTypeDao.update(identiType) ;
	}
	
	/**
	 * 根据身份编号删除身份
	 * @param ids
	 * @return
	 */
	public boolean deleteByIds(String ids){
		return identiTypeDao.deleteByIds(ids) ;
	}
	
	
	/**
	 * 判断中是否已包含该角色
	 */
	
	public boolean hasType(String typeID){
		return identiTypeDao.hasType(typeID);
	}

}
