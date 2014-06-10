package cn.nit.service.table4;

import java.util.List;


import cn.nit.bean.table4.T443_Bean;
import cn.nit.dao.table4.T443_Dao;

public class T443_Service {
	
	private T443_Dao talentDao = new T443_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T443_Bean> getPagetalentList(String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T443_Bean> talent = talentDao.queryPageList(pagesize, currentpage);
		
		return talent;		
	}
	
	//取得总数
	public int getTotal(){
		return talentDao.getAllList().size();
	}
	
	//插入一个bean
	public Boolean insert(T443_Bean bean){
		return talentDao.insert(bean);
	}

}
