package cn.nit.service.table5;

import java.util.List;


import cn.nit.bean.table5.T54_Bean;

import cn.nit.dao.table5.T54_Dao;


public class T54_Service {
	
	private T54_Dao infoDao = new T54_Dao();
	
	//根据年参数取得相应年数据
	
	public T54_Bean getYearInfo(String selectYear){
				
		T54_Bean bean = infoDao.getYearInfo(selectYear);
		
		return bean;		
	}
	
	//保存
	public Boolean save(T54_Bean bean, String year,	String fields){
		return infoDao.save(bean,year,fields);
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(String selectYear, int checkState){
		return infoDao.updateCheck(selectYear,checkState) ;
	}

}
