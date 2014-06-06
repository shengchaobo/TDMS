package cn.nit.service.table4;

import java.util.List;


import cn.nit.bean.table4.T412_Bean;
import cn.nit.dao.table4.T412_Dao;

public class T412_Service {
	
	private T412_Dao majorTeaDao = new T412_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T412_Bean> getPageMajorTeaList(String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T412_Bean> majorTea = majorTeaDao.queryPageList(pagesize, currentpage);
		
		return majorTea;		
	}
	
	//取得总数
	public int getTotal(){
		return majorTeaDao.getAllList().size();
	}
	
	//插入一个bean
	public Boolean insert(T412_Bean bean){
		return majorTeaDao.insert(bean);
	}

}
