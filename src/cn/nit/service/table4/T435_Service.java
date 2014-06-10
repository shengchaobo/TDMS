package cn.nit.service.table4;

import java.util.List;


import cn.nit.bean.table4.T435_Bean;
import cn.nit.dao.table4.T435_Dao;

public class T435_Service {
	
	private T435_Dao employDao = new T435_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T435_Bean> getPageEmployList(String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T435_Bean> majorTea = employDao.queryPageList(pagesize, currentpage);
		
		return majorTea;		
	}
	
	//取得总数
	public int getTotal(){
		return employDao.getAllList().size();
	}
	
	//插入一个bean
	public Boolean insert(T435_Bean bean){
		return employDao.insert(bean);
	}

}
