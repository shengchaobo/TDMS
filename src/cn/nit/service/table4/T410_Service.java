package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.table4.*;
import cn.nit.dao.table4.T410_Dao;

public class T410_Service {
	
	private T410_Dao textDao = new T410_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T410_Bean> getPageResList(String conditions, String fillunitID, String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T410_Bean> text = textDao.queryPageList(conditions, fillunitID, pagesize, currentpage);
		
		return text;		
	}
	
	public int getTotal(String cond, String fillUnitID){
		return textDao.totalQueryPageList(cond, fillUnitID);
	}
	
	public Boolean insert(T410_Bean bean){
		return textDao.insert(bean);
	}
	
	
	/** 检查是否存在该年数据*/
	public boolean check(String year){
		return textDao.check(year);
	}
	
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(T410_Bean bean){
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
	
	/**
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return textDao.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return textDao.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return textDao.checkAll() ;
	}
	
	/**
	 * 审核数据导出
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public T410_Bean totalList(String year, int checkState){
		return textDao.totalList(year, checkState);
	}

}
