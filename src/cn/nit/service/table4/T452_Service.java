package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.table4.*;
import cn.nit.dao.table4.T452_Dao;

public class T452_Service {
	
	private T452_Dao trainDao = new T452_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T452_Bean> getPagetrainList(String conditions, String fillunitID, String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T452_Bean> train = trainDao.queryPageList(conditions, fillunitID, pagesize, currentpage);
		
		return train;		
	}
	
	public int getTotal(String cond, String fillUnitID){
		return trainDao.totalQueryPageList(cond, fillUnitID);
	}
	
	public Boolean insert(T452_Bean bean){
		return trainDao.insert(bean);
	}
	
	//模板导入
	public Boolean batchInsert(List<T452_Bean> list){
		return trainDao.batchInsert(list);
	}
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(T452_Bean bean){
		return trainDao.update(bean) ;
	}
	
	
	/**
	 * 删除数据
	 * @param 
	 * @return
	 */
	
	public boolean deleteByIds(String ids){
		
		return trainDao.deleteByIds(ids) ;
	}
	
	/**
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return trainDao.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return trainDao.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return trainDao.checkAll() ;
	}
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T452_Bean> totalList(String fillUnitID, String year, int checkState){
		return trainDao.totalList(fillUnitID, year, checkState);
	}



}
