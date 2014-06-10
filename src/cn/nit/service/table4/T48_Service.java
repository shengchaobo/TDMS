package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.table4.*;
import cn.nit.dao.table4.T48_Dao;

public class T48_Service {
	
	private T48_Dao teamDao = new T48_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T48_Bean> getPageteamList(String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T48_Bean> team = teamDao.queryPageList(pagesize, currentpage);
		
		return team;		
	}
	
	public int getTotal(){
		return teamDao.getAllList().size();
	}
	
	public Boolean insert(T48_Bean bean){
		return teamDao.insert(bean);
	}
	
	/**
	 * 加载所有的人
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<T48_Bean> getList(){
		return teamDao.getAllList() ;
	}

}
