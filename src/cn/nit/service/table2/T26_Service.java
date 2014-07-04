package cn.nit.service.table2;

import java.util.List;


import cn.nit.bean.table2.T26_Bean;
import cn.nit.dao.table2.T26_Dao;

public class T26_Service {
	
	private T26_Dao placeDao = new T26_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T26_Bean> getPageMajorTeaList(String conditions, String fillunitID, String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T26_Bean> placeInfo = placeDao.queryPageList(conditions, fillunitID, pagesize, currentpage);
		
		return placeInfo;		
	}
	
	//取得总数
	public int getTotal(String cond, String fillUnitID){
		return placeDao.totalQueryPageList(cond, fillUnitID);
	}
	
	//模板导入
	public Boolean batchInsert(List<T26_Bean> list){
		return placeDao.batchInsert(list);
	}
	
	//插入一个bean
	public Boolean insert(T26_Bean bean){
		return placeDao.insert(bean);
	}
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(T26_Bean bean){
		return placeDao.update(bean) ;
	}
	
	
	/**
	 * 删除数据
	 * @param 
	 * @return
	 */
	
	public boolean deleteByIds(String ids){
		
		return placeDao.deleteByIds(ids) ;
	}

}
