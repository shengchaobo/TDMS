package cn.nit.service.table4;

import java.util.List;


import cn.nit.bean.table4.T42_Bean;
import cn.nit.dao.table4.T42_Dao;

public class T42_Service {
	
	private T42_Dao leaderDao = new T42_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T42_Bean> getPageMajorTeaList(String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T42_Bean> majorTea = leaderDao.queryPageList(pagesize, currentpage);
		
		return majorTea;		
	}
	
	//取得总数
	public int getTotal(){
		return leaderDao.getAllList().size();
	}
	
	//插入一个bean
	public Boolean insert(T42_Bean bean){
		return leaderDao.insert(bean);
	}

}
