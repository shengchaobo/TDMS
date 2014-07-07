package cn.nit.service.table2;

import java.util.List;


import cn.nit.bean.table2.T241_Bean;
import cn.nit.dao.table2.T241_Dao;


public class T241_Service {
	
	private T241_Dao infoDao = new T241_Dao();
	
	//根据年参数取得相应年数据
	
	public T241_Bean getYearInfo(String selectYear){
				
		T241_Bean bean = infoDao.getYearInfo(selectYear);
		
		return bean;		
	}
	
	
	//保存
	public Boolean save(T241_Bean bean, String year,	String fields){
		return infoDao.save(bean,year,fields);
	}

}
