package cn.nit.service.table2;

import java.util.List;


import cn.nit.bean.table2.T281_Bean;
import cn.nit.dao.table2.T281_Dao;


public class T281_Service {
	
	private T281_Dao infoDao = new T281_Dao();
	
	//根据年参数取得相应年数据
	
	public T281_Bean getYearInfo(String selectYear){
				
		T281_Bean bean = infoDao.getYearInfo(selectYear);
		
		return bean;		
	}
	
	
	//保存
	public Boolean save(T281_Bean bean, String year,	String fields){
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
