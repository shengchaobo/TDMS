package cn.nit.service.table2;

import java.util.List;


import cn.nit.bean.table2.T251_Bean;
import cn.nit.bean.table4.T411_Bean;
import cn.nit.bean.table4.T42_Bean;
import cn.nit.dao.table2.T251_Dao;
import cn.nit.dao.table4.T42_Dao;

public class T251_Service {
	
	private T251_Dao placeDao = new T251_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T251_Bean> getPageMajorTeaList(String conditions, String fillunitID, String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T251_Bean> placeInfo = placeDao.queryPageList(conditions, fillunitID, pagesize, currentpage);
		
		return placeInfo;		
	}
	
	//取得总数
	public int getTotal(String cond, String fillUnitID){
		return placeDao.totalQueryPageList(cond, fillUnitID);
	}
	
	//模板导入
	public Boolean batchInsert(List<T251_Bean> list){
		return placeDao.batchInsert(list);
	}
	
	//插入一个bean
	public Boolean insert(T251_Bean bean){
		return placeDao.insert(bean);
	}
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(T251_Bean bean){
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
	
	/**
	 * 获得面积
	 * @param 
	 * @return
	 */
	
	public double getTrainArea(String year){
		
		return placeDao.getTrainArea(year) ;
	}
	
	/**
	 * 得到该条数据审核状态
	 * @param 
	 * @return
	 */
	public int getCheckState(int seqNumber){
		return placeDao.getCheckState(seqNumber) ;
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(int seqNum, int checkState){
		return placeDao.updateCheck(seqNum,checkState) ;
	}
	
	
	/**
	 * 全部审核通过
	 * @param 
	 * @return
	 */
	public boolean checkAll(){
		return placeDao.checkAll() ;
	}


}
