package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiDepartmentBean;
import cn.nit.dao.di.DiDepartmentDao;


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
	 * 获取科研室值
	 * */
	public List<DiDepartmentBean> getListSci(){
		return departmentDao.getListSci();
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


}
