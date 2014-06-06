package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.di.DiDegreeBean;
import cn.nit.bean.table4.*;
import cn.nit.dao.table4.T411_Dao;

public class T411_Service {
	
	private T411_Dao teaInfoDao = new T411_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T411_Bean> getPageTeaInfoList(String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T411_Bean> teaInfo = teaInfoDao.queryPageList(pagesize, currentpage);
		
		return teaInfo;		
	}
	
	public int getTotal(){
		return teaInfoDao.getAllList().size();
	}
	
	public Boolean insert(T411_Bean bean){
		return teaInfoDao.insert(bean);
	}
	
	/**
	 * 加载所有的人
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<T411_Bean> getList(){
		return teaInfoDao.getAllList() ;
	}

}
