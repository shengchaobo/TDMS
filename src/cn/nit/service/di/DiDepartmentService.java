package cn.nit.service.di;

import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import cn.nit.bean.RoleBean;
import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.dao.di.DiDepartmentDao;
import cn.nit.util.Pagition;


public class DiDepartmentService {
	
	private DiDepartmentDao departmentDao = new DiDepartmentDao() ;
	/**
	 * 加载所有的部门以及单位
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<DiDepartmentBean> getList(){
		return departmentDao.getList() ;
	}
	
	/**

	 * 获取科研室�?
	 * */
	public List<DiDepartmentBean> getListSci(){
		return departmentDao.getListSci();
	}
	
	/**

	 * 获取学院�?
	 * */
	public List<DiDepartmentBean> getListAca(){
		return departmentDao.getListAca();
	}
	/**

	 * 新增一个部门或单位
	 * @param department
	 * @return
	 *
	 * @time: 2014-5-14/下午03:05:00
	 */
	public boolean insert(DiDepartmentBean identiType){
		return departmentDao.insert(identiType) ;
	}
	
	/**
	 * 加载所有部门
	 * @param conditions
	 * @param page
	 * @param rows
	 * @return
	 */
	public String loadDes(String conditions, int page, int rows){
		
		int total = departmentDao.totalDes(conditions) ;
		List<DiDepartmentBean> list = departmentDao.loadDes(conditions, page, rows) ;
		Pagition pages = new Pagition(total, list) ;
		JSON json = JSONSerializer.toJSON(pages) ;
		
		return json.toString() ;
	}
	
	/**
	 * 更新角色
	 * @param userinfo
	 * @return
	 */
	public boolean update(DiDepartmentBean deBean){
		
		return departmentDao.update(deBean) ;
	}
	
	/**
	 * 根据角色编号删除角色
	 * @param ids
	 * @return
	 */
	public boolean deleteByIds(String ids){
		return departmentDao.deleteByIds(ids) ;
	}
	
	
	
	/**
	 * 判断中是否已包含该部门
	 */
	
	public boolean hasDe(String unitID){
		return departmentDao.hasDe(unitID);
	}
	
	
	/**
	 * 根据部分ID获得部门名字
	 * @param userinfo
	 * @return
	 */
	public String getName(String unitID){
		
		return departmentDao.getName(unitID) ;
	}
	
	public static void main(String args[]){
		DiDepartmentService d =  new DiDepartmentService() ;
		System.out.println(d.loadDes(null, 10, 3));
	}

}
