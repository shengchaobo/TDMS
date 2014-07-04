package cn.nit.service.table2;

import java.util.List;


import cn.nit.bean.table2.T232_Bean;
import cn.nit.dao.table2.T232_Dao;


public class T232_Service {
	
	private T232_Dao infoDao = new T232_Dao();
	
	//根据年参数取得相应年数据
	
	public T232_Bean getYearInfo(String selectYear){
				
		T232_Bean bean = infoDao.getYearInfo(selectYear);
		
		return bean;		
	}
	
	
	//保存
	public Boolean save(T232_Bean bean, String year,	String fields){
		return infoDao.save(bean,year,fields);
	}

}
