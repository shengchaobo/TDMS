package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.di.DiDegreeBean;
import cn.nit.bean.table4.*;
import cn.nit.dao.table4.T413_Dao;

public class T413_Service {
	
	private T413_Dao teaInfoDao = new T413_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T413_Bean> getPageTeaInfoList(String conditions, String fillunitID, String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T413_Bean> teaInfo = teaInfoDao.queryPageList(conditions, fillunitID, pagesize, currentpage);		
		
		return teaInfo;		
	}
	
	public int getTotal(String Conditions, String fillunitID){
		return teaInfoDao.totalQueryPageList(Conditions, fillunitID);
	}
	
	public Boolean insert(T413_Bean bean){
		return teaInfoDao.insert(bean);
	}
	
	//模板导入
	public Boolean batchInsert(List<T413_Bean> list){
		return teaInfoDao.batchInsert(list);
	}
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(T413_Bean bean){
		return teaInfoDao.update(bean) ;
	}

}
