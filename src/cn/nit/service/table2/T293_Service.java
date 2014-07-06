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
	public Boolean save(T293_Bean bean, String year,	String fields){
		return infoDao.save(bean,year,fields);
	}

}
