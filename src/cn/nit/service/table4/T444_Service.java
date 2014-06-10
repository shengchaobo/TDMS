package cn.nit.service.table4;

import java.util.List;


import cn.nit.bean.table4.T444_Bean;
import cn.nit.dao.table4.T444_Dao;

public class T444_Service {
	
	private T444_Dao teamDao = new T444_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T444_Bean> getPageteamList(String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T444_Bean> team = teamDao.queryPageList(pagesize, currentpage);
		
		return team;		
	}
	
	//取得总数
	public int getTotal(){
		return teamDao.getAllList().size();
	}
	
	//插入一个bean
	public Boolean insert(T444_Bean bean){
		return teamDao.insert(bean);
	}

}
