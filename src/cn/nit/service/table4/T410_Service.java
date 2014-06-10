package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.table4.*;
import cn.nit.dao.table4.T410_Dao;

public class T410_Service {
	
	private T410_Dao textDao = new T410_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T410_Bean> getPageResList(String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T410_Bean> text = textDao.queryPageList(pagesize, currentpage);
		
		return text;		
	}
	
	public int getTotal(){
		return textDao.getAllList().size();
	}
	
	public Boolean insert(T410_Bean bean){
		return textDao.insert(bean);
	}
	
	/**
	 * 加载所有的人
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<T410_Bean> getList(){
		return textDao.getAllList() ;
	}

}
