package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.table4.*;
import cn.nit.dao.table4.T451_Dao;

public class T451_Service {
	
	private T451_Dao orgDao = new T451_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T451_Bean> getPageorgList(String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T451_Bean> org = orgDao.queryPageList(pagesize, currentpage);
		
		return org;		
	}
	
	public int getTotal(){
		return orgDao.getAllList().size();
	}
	
	public Boolean insert(T451_Bean bean){
		return orgDao.insert(bean);
	}
	
	/**
	 * 加载所有的人
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<T451_Bean> getList(){
		return orgDao.getAllList() ;
	}

}
