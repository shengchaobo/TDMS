package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.RoleBean;
import cn.nit.dao.RoleDAO;



public class DiRoleService {
	

	private RoleDAO roleDao = new RoleDAO() ;
	/**
	 * 加载所有的角色
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<RoleBean> getList(){
		return roleDao.getList() ;
	}
	
/*	*//**
	 * 新增一个角色
	 * @param researchType
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 *//*
	public boolean insert(RoleBean role){
		return roleDao.insert(role) ;
	}*/
}
