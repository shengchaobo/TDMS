package cn.nit.service.table2;

import java.util.List;


import cn.nit.bean.table2.T291_Bean;
import cn.nit.dao.table2.T291_Dao;


public class T291_Service {
	
	private T291_Dao infoDao = new T291_Dao();
	
	//根据年参数取得相应年数据
	
	public T291_Bean getYearInfo(String selectYear){
				
		T291_Bean bean = infoDao.getYearInfo(selectYear);
		
		return bean;		
	}
	
	
	//保存
	public Boolean save(T291_Bean bean, String year,	String fields){
		return infoDao.save(bean,year,fields);
	}

}
