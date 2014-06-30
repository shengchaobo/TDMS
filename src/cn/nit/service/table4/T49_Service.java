package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.table4.*;
import cn.nit.dao.table4.T49_Dao;

public class T49_Service {
	
	private T49_Dao textDao = new T49_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T49_Bean> getPagetextList(String conditions, String fillunitID, String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T49_Bean> text = textDao.queryPageList(conditions, fillunitID, pagesize, currentpage);
		
		return text;		
	}
	
	public int getTotal(String cond, String fillUnitID){
		return textDao.totalQueryPageList(cond, fillUnitID);
	}
	
	public Boolean insert(T49_Bean bean){
		return textDao.insert(bean);
	}
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(T49_Bean bean){
		return textDao.update(bean) ;
	}
	
	
	/**
	 * 删除数据
	 * @param 
	 * @return
	 */
	
	public boolean deleteByIds(String ids){
		
		return textDao.deleteByIds(ids) ;
	}


}
