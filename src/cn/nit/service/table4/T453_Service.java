package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.table4.*;
import cn.nit.dao.table4.T453_Dao;

public class T453_Service {
	
	private T453_Dao talkDao = new T453_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T453_Bean> getPagetalkList(String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T453_Bean> talk = talkDao.queryPageList(pagesize, currentpage);
		
		return talk;		
	}
	
	public int getTotal(){
		return talkDao.getAllList().size();
	}
	
	public Boolean insert(T453_Bean bean){
		return talkDao.insert(bean);
	}
	
	/**
	 * 加载所有的人
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<T453_Bean> getList(){
		return talkDao.getAllList() ;
	}

}
