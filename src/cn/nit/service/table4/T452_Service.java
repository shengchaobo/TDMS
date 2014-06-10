package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.table4.*;
import cn.nit.dao.table4.T452_Dao;

public class T452_Service {
	
	private T452_Dao trainDao = new T452_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T452_Bean> getPagetrainList(String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T452_Bean> train = trainDao.queryPageList(pagesize, currentpage);
		
		return train;		
	}
	
	public int getTotal(){
		return trainDao.getAllList().size();
	}
	
	public Boolean insert(T452_Bean bean){
		return trainDao.insert(bean);
	}
	
	/**
	 * 加载所有的人
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<T452_Bean> getList(){
		return trainDao.getAllList() ;
	}

}
