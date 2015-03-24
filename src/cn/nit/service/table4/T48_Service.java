package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.table4.*;
import cn.nit.dao.table4.T48_Dao;

public class T48_Service {
	
	private T48_Dao teamDao = new T48_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T48_Bean> getPageteamList(String conditions, String fillunitID, String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T48_Bean> team = teamDao.queryPageList(conditions, fillunitID, pagesize, currentpage);
		
		return team;		
	}
	
	public int getTotal(String cond, String fillUnitID){
		return teamDao.totalQueryPageList(cond, fillUnitID);
	}
	
	public Boolean insert(T48_Bean bean){
		return teamDao.insert(bean);
	}
	
	//模板导入
	public Boolean batchInsert(List<T48_Bean> list){
		return teamDao.batchInsert(list);
	}
	
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(T48_Bean bean){
		return teamDao.update(bean) ;
	}
	
	
	/**
	 * 删除数据
	 * @param 
	 * @return
	 */
	
	public boolean deleteByIds(String ids){
		
		return teamDao.deleteByIds(ids) ;
	}
	
	/**
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return teamDao.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return teamDao.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return teamDao.checkAll() ;
	}
	
	/**
	 * 审核数据导出
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T48_Bean> totalList(String fillUnitID, String year, int checkState){
		return teamDao.totalList(fillUnitID, year, checkState);
	}


}
