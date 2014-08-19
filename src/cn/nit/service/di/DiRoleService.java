package cn.nit.service.di;

import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.RoleBean;
import cn.nit.bean.UserinfoBean;
import cn.nit.bean.UsersBean;
import cn.nit.dao.di.DiRoleDAO;
import cn.nit.util.Pagition;



public class DiRoleService {
	

	private DiRoleDAO roleDao = new DiRoleDAO() ;
	/**
	 * 加载所有的角色
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<RoleBean> getList(){
		return roleDao.getList() ;
	}
	
	/**
	 * 添加角色
	 * @param userinfo
	 * @return
	 */
	public boolean insert(RoleBean roleBean){
		return roleDao.insert(roleBean) ;
	}
	
	/**
	 * 加载所有角色
	 * @param conditions
	 * @param page
	 * @param rows
	 * @return
	 */
	public String loadRoles(String conditions, int page, int rows){
		
		int total = roleDao.totalRoles(conditions) ;
		List<RoleBean> list = roleDao.loadRoles(conditions, page, rows) ;
		Pagition pages = new Pagition(total, list) ;
		JSON json = JSONSerializer.toJSON(pages) ;
		
		return json.toString() ;
	}
	
	/**
	 * 更新角色
	 * @param userinfo
	 * @return
	 */
	public boolean update(RoleBean roleBean){
		
		return roleDao.update(roleBean) ;
	}
	
	/**
	 * 根据角色编号删除角色
	 * @param ids
	 * @return
	 */
	public boolean deleteByIds(String ids){
		return roleDao.deleteByIds(ids) ;
	}
	
	
	/**
	 * 判断中是否已包含该角色
	 */
	
	public boolean hasRole(String roleID){
		return roleDao.hasRole(roleID);
	}
}
