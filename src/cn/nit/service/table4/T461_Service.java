package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.table4.*;
import cn.nit.dao.table4.T461_Dao;

public class T461_Service {
	
	private T461_Dao honorDao = new T461_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T461_Bean> getPagehonorList(String conditions, String fillunitID, String rows, String page, String param){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T461_Bean> honor = honorDao.queryPageList(conditions, fillunitID, pagesize, currentpage, param);
		
		return honor;		
	}
	
	public int getTotal(String cond, String fillUnitID, String param){
		return honorDao.totalQueryPageList(cond, fillUnitID, param);
	}
	
	public Boolean insert(T461_Bean bean){
		return honorDao.insert(bean);
	}
	
	//模板导入
	public Boolean batchInsert(List<T461_Bean> list){
		return honorDao.batchInsert(list);
	}
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(T461_Bean bean){
		return honorDao.update(bean) ;
	}
	
	
	/**
	 * 删除数据
	 * @param 
	 * @return
	 */
	
	public boolean deleteByIds(String ids){
		
		return honorDao.deleteByIds(ids) ;
	}
	
	/**
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return honorDao.getCheckState(seqNumber) ;
	}
	
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return honorDao.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(String param){
		return honorDao.checkAll(param) ;
	}

}
