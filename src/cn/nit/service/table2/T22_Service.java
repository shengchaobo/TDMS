package cn.nit.service.table2;

import java.util.List;


import cn.nit.bean.table2.T22_Bean;
import cn.nit.dao.table2.T22_Dao;


public class T22_Service {
	
	private T22_Dao infoDao = new T22_Dao();
	
	//根据年参数取得相应年数据
	
	public T22_Bean getYearInfo(String selectYear){
				
		T22_Bean bean = infoDao.getYearInfo(selectYear);
		
		return bean;		
	}
	
	
	//保存
	public Boolean save(T22_Bean bean, String year,	String fields){
		return infoDao.save(bean,year,fields);
	}

}
