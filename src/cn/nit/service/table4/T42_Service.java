package cn.nit.service.table4;

import java.util.List;


import cn.nit.bean.table4.T411_Bean;
import cn.nit.bean.table4.T42_Bean;
import cn.nit.dao.table4.T42_Dao;

public class T42_Service {
	
	private T42_Dao leaderDao = new T42_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T42_Bean> getPageMajorTeaList(String conditions, String fillunitID, String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T42_Bean> majorTea = leaderDao.queryPageList(conditions, fillunitID, pagesize, currentpage);
		
		return majorTea;		
	}
	
	//取得总数
	public int getTotal(String cond, String fillUnitID){
		return leaderDao.totalQueryPageList(cond, fillUnitID);
	}
	
	//模板导入
	public Boolean batchInsert(List<T42_Bean> list){
		return leaderDao.batchInsert(list);
	}
	
	//插入一个bean
	public Boolean insert(T42_Bean bean){
		return leaderDao.insert(bean);
	}
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(T42_Bean bean){
		return leaderDao.update(bean) ;
	}
	
	
	/**
	 * 删除数据
	 * @param 
	 * @return
	 */
	
	public boolean deleteByIds(String ids){
		
		return leaderDao.deleteByIds(ids) ;
	}

	
	/**
	 *数据导出
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<T42_Bean> totalList(){
		return leaderDao.totalList();
	}
}
