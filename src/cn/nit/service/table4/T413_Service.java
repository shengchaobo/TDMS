package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.di.DiDegreeBean;
import cn.nit.bean.table4.*;
import cn.nit.dao.table4.T413_Dao;

public class T413_Service {
	
	private T413_Dao teaInfoDao = new T413_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T413_Bean> getPageTeaInfoList(String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		List<T413_Bean> teaInfo = teaInfoDao.queryPageList(pagesize, currentpage);
		
		return teaInfo;		
	}
	
	public int getTotal(){
		return teaInfoDao.getAllList().size();
	}
	
	public Boolean insert(T413_Bean bean){
		return teaInfoDao.insert(bean);
	}
	
	/**
	 * 加载所有的人
	 * @return
	 *
	 * @time: 2014-5-14/下午03:04:36
	 */
	public List<T413_Bean> getList(){
		return teaInfoDao.getAllList() ;
	}

}
