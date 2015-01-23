package cn.nit.service.table6;

import java.util.List;


import cn.nit.bean.table6.T613_Bean;
import cn.nit.dao.table6.T613_Dao;


public class T613_Service {
	
	private T613_Dao infoDao = new T613_Dao();
	
	//根据年参数取得相应年数据
	
	public T613_Bean getYearInfo(String selectYear){
				
		T613_Bean bean = infoDao.getYearInfo(selectYear);
		
		return bean;		
	}
	
	
	//保存
	public Boolean save(T613_Bean bean, String year, String fields){
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
