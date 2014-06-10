package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.table4.*;
import cn.nit.dao.table4.T47_Dao;

public class T47_Service {
	
	private T47_Dao honorDao = new T47_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T47_Bean> getPagehonorList(String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T47_Bean> honor = honorDao.queryPageList(pagesize, currentpage);
		
		return honor;		
	}
	
	public int getTotal(){
		return honorDao.getAllList().size();
	}
	
	public Boolean insert(T47_Bean bean){
		return honorDao.insert(bean);
	}
	
	/**
	 * 加载所有的人
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<T47_Bean> getList(){
		return honorDao.getAllList() ;
	}

}
