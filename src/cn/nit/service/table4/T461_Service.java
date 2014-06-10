package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.table4.*;
import cn.nit.dao.table4.T461_Dao;

public class T461_Service {
	
	private T461_Dao honorDao = new T461_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T461_Bean> getPagehonorList(String rows, String page, String param){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T461_Bean> honor = honorDao.queryPageList(pagesize, currentpage, param);
		
		return honor;		
	}
	
	public int getTotal(String param){
		return honorDao.getAllList(param).size();
	}
	
	public Boolean insert(T461_Bean bean){
		return honorDao.insert(bean);
	}
	
	/**
	 * 加载所有的人
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<T461_Bean> getList(String param){
		return honorDao.getAllList(param) ;
	}

}
