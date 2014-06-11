package cn.nit.service.table4;

import java.util.List;


import cn.nit.bean.table4.T442_Bean;
import cn.nit.dao.table4.T442_Dao;

public class T442_Service {
	
	private T442_Dao tutorDao = new T442_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T442_Bean> getPagetutorList(String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T442_Bean> tutor = tutorDao.queryPageList(pagesize, currentpage);
		
		return tutor;		
	}
	
	//取得总数
	public int getTotal(){
		return tutorDao.getAllList().size();
	}
	
	//插入一个bean
	public Boolean insert(T442_Bean bean){
		return tutorDao.insert(bean);
	}

}
