package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.table4.*;
import cn.nit.dao.table4.T49_Dao;

public class T49_Service {
	
	private T49_Dao textDao = new T49_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T49_Bean> getPagetextList(String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T49_Bean> text = textDao.queryPageList(pagesize, currentpage);
		
		return text;		
	}
	
	public int getTotal(){
		return textDao.getAllList().size();
	}
	
	public Boolean insert(T49_Bean bean){
		return textDao.insert(bean);
	}
	
	/**
	 * 加载所有的人
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<T49_Bean> getList(){
		return textDao.getAllList() ;
	}

}
