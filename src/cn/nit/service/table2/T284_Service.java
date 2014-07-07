package cn.nit.service.table2;

import java.util.List;


import cn.nit.bean.table2.T284_Bean;
import cn.nit.dao.table2.T284_Dao;


public class T284_Service {
	
	private T284_Dao infoDao = new T284_Dao();
	
	//根据年参数取得相应年数据
	
	public T284_Bean getYearInfo(String selectYear){
				
		T284_Bean bean = infoDao.getYearInfo(selectYear);
		
		return bean;		
	}
	
	
	//保存
	public Boolean save(T284_Bean bean, String year,	String fields){
		return infoDao.save(bean,year,fields);
	}

}
