package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.table4.*;
import cn.nit.dao.table4.T453_Dao;

public class T453_Service {
	
	private T453_Dao talkDao = new T453_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T453_Bean> getPagetalkList(String conditions, String fillunitID, String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T453_Bean> talk = talkDao.queryPageList(conditions, fillunitID, pagesize, currentpage);
		
		return talk;		
	}
	
	public int getTotal(String cond, String fillUnitID){
		return talkDao.totalQueryPageList(cond, fillUnitID);
	}
	
	public Boolean insert(T453_Bean bean){
		return talkDao.insert(bean);
	}
	
	//模板导入
	public Boolean batchInsert(List<T453_Bean> list){
		return talkDao.batchInsert(list);
	}
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(T453_Bean bean){
		return talkDao.update(bean) ;
	}
	
	
	/**
	 * 删除数据
	 * @param 
	 * @return
	 */
	
	public boolean deleteByIds(String ids){
		
		return talkDao.deleteByIds(ids) ;
	}


}
