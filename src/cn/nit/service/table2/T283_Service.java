package cn.nit.service.table2;

import java.util.List;


import cn.nit.bean.table2.T283_Bean;
import cn.nit.dao.table2.T283_Dao;


public class T283_Service {
	
	private T283_Dao infoDao = new T283_Dao();
	
	//根据年参数取得相应年数据
	
	public T283_Bean getYearInfo(String selectYear){
				
		T283_Bean bean = infoDao.getYearInfo(selectYear);
		
		return bean;		
	}
	
	
	//保存
	public Boolean save(T283_Bean bean, String year,	String fields){
		return infoDao.save(bean,year,fields);
	}

}
