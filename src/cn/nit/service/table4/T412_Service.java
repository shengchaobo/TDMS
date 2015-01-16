package cn.nit.service.table4;

import java.util.List;


import cn.nit.bean.table4.T412_Bean;
import cn.nit.bean.table4.T42_Bean;
import cn.nit.dao.table4.T412_Dao;

public class T412_Service {
	
	private T412_Dao majorTeaDao = new T412_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T412_Bean> getPageMajorTeaList(String conditions, String fillunitID, String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T412_Bean> majorTea = majorTeaDao.queryPageList(conditions, fillunitID, pagesize, currentpage);
		
		return majorTea;		
	}
	
	//取得总数
	public int getTotal(String cond, String fillUnitID){
		return majorTeaDao.totalQueryPageList(cond, fillUnitID);
	}
	
	//插入一个bean
	public Boolean insert(T412_Bean bean){
		return majorTeaDao.insert(bean);
	}
	
	//模板导入
	public Boolean batchInsert(List<T412_Bean> list){
		return majorTeaDao.batchInsert(list);
	}
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(T412_Bean bean){
		return majorTeaDao.update(bean) ;
	}
	
	
	/**
	 * 删除数据
	 * @param 
	 * @return
	 */
	
	public boolean deleteByIds(String ids){
		
		return majorTeaDao.deleteByIds(ids) ;
	}
	
	/**
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return majorTeaDao.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return majorTeaDao.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return majorTeaDao.checkAll() ;
	}


}
