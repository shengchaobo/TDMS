package cn.nit.service.table2;

import java.util.List;


import cn.nit.bean.table2.T21_Bean;
import cn.nit.dao.table2.T21_Dao;


public class T21_Service {
	
	private T21_Dao infoDao = new T21_Dao();
	
	//根据年参数取得相应年数据
	
	public T21_Bean getYearInfo(String selectYear){
				
		T21_Bean bean = infoDao.getYearInfo(selectYear);
		return bean;		
	}
	
	
	//保存
	public Boolean save(T21_Bean bean, String year,	String fields){
		return infoDao.save(bean,year,fields);
	}

}
