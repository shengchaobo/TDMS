package cn.nit.service.table2;

import java.util.List;


import cn.nit.bean.table2.T293_Bean;
import cn.nit.dao.table2.T293_Dao;


public class T293_Service {
	
	private T293_Dao infoDao = new T293_Dao();
	
	//根据年参数取得相应年数据
	
	public T293_Bean getYearInfo(String selectYear){
				
		T293_Bean bean = infoDao.getYearInfo(selectYear);
		
		return bean;		
	}
	
	
	//保存
	public Boolean save(T293_Bean bean, String year, String fields){
		return infoDao.save(bean, year, fields);
	}

	//更新捐赠收入
	public Boolean update(T293_Bean bean,String year){
		return infoDao.update(bean,year);
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
