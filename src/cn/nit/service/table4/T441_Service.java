package cn.nit.service.table4;

import java.util.List;


import cn.nit.bean.table4.T441_Bean;
import cn.nit.dao.table4.T441_Dao;

public class T441_Service {
	
	private T441_Dao majorLeaderDao = new T441_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T441_Bean> getPagemajorLeaderList(String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T441_Bean> majorLeader = majorLeaderDao.queryPageList(pagesize, currentpage);
		
		return majorLeader;		
	}
	
	//取得总数
	public int getTotal(){
		return majorLeaderDao.getAllList().size();
	}
	
	//插入一个bean
	public Boolean insert(T441_Bean bean){
		return majorLeaderDao.insert(bean);
	}

}
