package cn.nit.service.table6;

import java.util.List;


import cn.nit.bean.table6.T641_Bean;
import cn.nit.dao.table6.T641_Dao;


public class T641_Service {
	
	private T641_Dao infoDao = new T641_Dao();
	
	//根据年参数取得相应年数据
	
	public T641_Bean getYearInfo(String selectYear){
				
		T641_Bean bean = infoDao.getYearInfo(selectYear);
		
		return bean;		
	}
	
	
	//保存
	public Boolean save(T641_Bean bean, String year, String fields){
		return infoDao.save(bean,year,fields);
	}

}
