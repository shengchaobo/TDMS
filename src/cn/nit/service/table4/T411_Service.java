package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.di.DiDegreeBean;
import cn.nit.bean.table4.*;
import cn.nit.dao.table4.T411_Dao;

public class T411_Service {
	
	private T411_Dao teaInfoDao = new T411_Dao();
	
	//根据第几页获取，每页几行获取数据
	
	public List<T411_Bean> getPageTeaInfoList(String conditions, String fillunitID, String rows, String page){
		
		int currentpage = Integer.parseInt((page == null || page == "0")?"1": page);
		int pagesize = Integer.parseInt((rows == null || rows == "0")?"10":rows);
		
		//这里的fillunitID用于教学单位这一角色，不同学院同属于一个角色，用到相同的报表，但操作的内容不同
		List<T411_Bean> teaInfo = teaInfoDao.queryPageList(conditions, fillunitID, pagesize, currentpage);
		
		return teaInfo;		
	}
	
	public int getTotal(String Conditions, String fillunitID){
		return teaInfoDao.totalQueryPageList(Conditions, fillunitID);
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
	
	
	/**
	 * 更新数据
	 * @param 
	 * @return
	 */
	public boolean update(T411_Bean bean){
		return teaInfoDao.update(bean) ;
	}
	
	
/*	*//**
	 * 删除数据
	 * @param 
	 * @return
	 *//*
	
	public boolean deleteByIds(String ids){
		
		return teaInfoDao.deleteByIds(ids) ;
	}*/
	
	//根据参数加载43系列的表
	public List<T431_Bean> getT43List(int flag){
		return teaInfoDao.getT43List(flag) ;
	}

}
