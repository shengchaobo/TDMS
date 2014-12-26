package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.table4.*;
import cn.nit.dao.table4.T4_11_Dao;

public class T4_11_Service {
	
	private T4_11_Dao serDao = new T4_11_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T4_11_Bean> getPagetextList(String conditions, String fillunitID, String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T4_11_Bean> text = serDao.queryPageList(conditions, fillunitID, pagesize, currentpage);
		
		return text;		
	}
	
	public int getTotal(String cond, String fillUnitID){
		return serDao.totalQueryPageList(cond, fillUnitID);
	}
	
	//模板导入
	public Boolean batchInsert(List<T4_11_Bean> list){
		return serDao.batchInsert(list);
	}
	
	public Boolean insert(T4_11_Bean bean){
		return serDao.insert(bean);
	}
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(T4_11_Bean bean){
		return serDao.update(bean) ;
	}
	
	
	/**
	 * 删除数据
	 * @param 
	 * @return
	 */
	
	public boolean deleteByIds(String ids){
		
		return serDao.deleteByIds(ids) ;
	}


}
