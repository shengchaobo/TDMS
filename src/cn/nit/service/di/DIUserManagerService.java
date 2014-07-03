package cn.nit.service.di;

import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.UserinfoBean;
import cn.nit.dao.di.DIUserManagerDAO;
import cn.nit.util.Pagition;

/**
 * 用户管理Service类
 * @author lenovo
 *
 */
public class DIUserManagerService {

	private DIUserManagerDAO userManagerDao = new DIUserManagerDAO() ;
	
	/**
	 * 添加用户
	 * @param userinfo
	 * @return
	 */
	public boolean insert(UserinfoBean userinfo){
		return userManagerDao.insert(userinfo) ;
	}
	
	/**
	 * 加载所有用户
	 * @param conditions
	 * @param page
	 * @param rows
	 * @return
	 */
	public String loadUsers(String conditions, int page, int rows){
		
		int total = userManagerDao.totalUsers(conditions) ;
		List<UserinfoBean> list = userManagerDao.loadUsers(conditions, page, rows) ;
		Pagition pages = new Pagition(total, list) ;
		JSON json = JSONSerializer.toJSON(pages) ;
		
		return json.toString() ;
	}
	
	/**
	 * 更新用户
	 * @param userinfo
	 * @return
	 */
	public boolean update(UserinfoBean userinfo){
		
		return userManagerDao.update(userinfo) ;
	}
	
	/**
	 * 根据用户SeqNumber删除用户
	 * @param ids
	 * @return
	 */
	public boolean deleteByIds(String ids){
		return userManagerDao.deleteByIds(ids) ;
	}
	
	/**
	 * 重置密码
	 * @param ids
	 * @return
	 */
	public boolean resetPassword(String ids){
		return userManagerDao.resetPassword(ids) ;
	}
}
