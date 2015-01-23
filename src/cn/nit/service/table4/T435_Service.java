package cn.nit.service.table4;

import java.util.List;


import cn.nit.bean.table4.T42_Bean;
import cn.nit.bean.table4.T435_Bean;
import cn.nit.dao.table4.T435_Dao;

public class T435_Service {
	
	private T435_Dao employDao = new T435_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T435_Bean> getPageEmployList(String conditions, String fillunitID, String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T435_Bean> majorTea = employDao.queryPageList(conditions, fillunitID, pagesize, currentpage);
		
		return majorTea;		
	}
	
	//取得总数
	public int getTotal(String cond, String fillUnitID){
		return employDao.totalQueryPageList(cond, fillUnitID);
	}
	
	//插入一个bean
	public Boolean insert(T435_Bean bean){
		return employDao.insert(bean);
	}
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(T435_Bean bean){
		return employDao.update(bean) ;
	}
	
	
	/**
	 * 删除数据
	 * @param 
	 * @return
	 */
	
	public boolean deleteByIds(String ids){
		
		return employDao.deleteByIds(ids) ;
	}
	
	/**
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return employDao.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return employDao.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return employDao.checkAll() ;
	}

}
