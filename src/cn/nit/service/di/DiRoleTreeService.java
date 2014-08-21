package cn.nit.service.di;

import java.util.List;

import cn.nit.bean.di.DiRoleTreeBean;
import cn.nit.dao.di.DIRoleTreeDAO;


public class DiRoleTreeService {

	private DIRoleTreeDAO diRoleTreeDao = new DIRoleTreeDAO() ;
	
	//获取某个角色所有的treeID,即权限
	public List<Integer> getRoleTreeIDs(String roleId){
		return diRoleTreeDao.getRoleTreeIDs(roleId) ;
	}
	
	//修改某个角色所有的treeID,即权限
	public boolean save(String roleId,List<DiRoleTreeBean> list){
		return diRoleTreeDao.save(roleId, list) ;
	}
}
