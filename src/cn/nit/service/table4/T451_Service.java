package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.table4.*;
import cn.nit.dao.table4.T451_Dao;

public class T451_Service {
	
	private T451_Dao orgDao = new T451_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T451_Bean> getPageorgList(String conditions, String fillunitID, String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T451_Bean> org = orgDao.queryPageList(conditions, fillunitID, pagesize, currentpage);
		
		return org;		
	}
	
	public int getTotal(String cond, String fillUnitID){
		return orgDao.totalQueryPageList(cond, fillUnitID);
	}
	
	public Boolean insert(T451_Bean bean){
		return orgDao.insert(bean);
	}
	
	
	//模板导入
	public Boolean batchInsert(List<T451_Bean> list){
		return orgDao.batchInsert(list);
	}
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(T451_Bean bean){
		return orgDao.update(bean) ;
	}
	
	
	/**
	 * 删除数据
	 * @param 
	 * @return
	 */
	
	public boolean deleteByIds(String ids){
		
		return orgDao.deleteByIds(ids) ;
	}


}
