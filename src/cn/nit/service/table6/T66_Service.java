package cn.nit.service.table6;

import java.util.List;


import cn.nit.bean.table6.T66_Bean;
import cn.nit.dao.table6.T66_Dao;


public class T66_Service {
	
	private T66_Dao infoDao = new T66_Dao();
	
	//根据年参数取得相应年数据
	
	public T66_Bean getYearInfo(String selectYear){
				
		T66_Bean bean = infoDao.getYearInfo(selectYear);
		
		return bean;		
	}
	
	
	//保存
	public Boolean save(T66_Bean bean, String year, String fields){
		return infoDao.save(bean,year,fields);
	}

}
