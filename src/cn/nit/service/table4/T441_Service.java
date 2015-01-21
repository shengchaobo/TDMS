package cn.nit.service.table4;

import java.util.List;


import cn.nit.bean.table4.T412_Bean;
import cn.nit.bean.table4.T441_Bean;
import cn.nit.dao.table4.T441_Dao;

public class T441_Service {
	
	private T441_Dao majorLeaderDao = new T441_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T441_Bean> getPagemajorLeaderList(String conditions, String fillunitID, String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T441_Bean> majorLeader = majorLeaderDao.queryPageList(conditions, fillunitID, pagesize, currentpage);
		
		return majorLeader;		
	}
	
	//取得总数
	public int getTotal(String cond, String fillUnitID){
		return majorLeaderDao.totalQueryPageList(cond, fillUnitID);
	}
	
	//插入一个bean
	public Boolean insert(T441_Bean bean){
		return majorLeaderDao.insert(bean);
	}
	
	//模板导入
	public Boolean batchInsert(List<T441_Bean> list){
		return majorLeaderDao.batchInsert(list);
	}
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(T441_Bean bean){
		return majorLeaderDao.update(bean) ;
	}
	
	
	/**
	 * 删除数据
	 * @param 
	 * @return
	 */
	
	public boolean deleteByIds(String ids){
		
		return majorLeaderDao.deleteByIds(ids) ;
	}
	
	/**
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return majorLeaderDao.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return majorLeaderDao.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return majorLeaderDao.checkAll() ;
	}



}
