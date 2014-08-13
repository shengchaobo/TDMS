package cn.nit.service.di;

import cn.nit.bean.di.DiUserRoleBean;
import cn.nit.dao.di.DIUserRoleDAO;

public class DiUserRoleService {
	
	private DIUserRoleDAO userUserRoleDao = new DIUserRoleDAO() ;
	
	/**
	 * 添加用户角色对
	 * @param userinfo
	 * @return
	 */
	public boolean insert(DiUserRoleBean useRoleBean){
		return userUserRoleDao.insert(useRoleBean) ;
	}
	
	/**
	 * 修改用户角色
	 * @param userinfo
	 * @return
	 */
	public boolean update(DiUserRoleBean useRoleBean){
		return userUserRoleDao.update(useRoleBean) ;
	}
	
	/**
	 * 删除用户角色
	 * @param userinfo
	 * @return
	 */
	public boolean deleteByIds(String ids){
		return userUserRoleDao.deleteByIds(ids) ;
	}
}
