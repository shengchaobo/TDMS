package cn.nit.service.table6;

import java.util.List;


import cn.nit.bean.table6.T611_Bean;
import cn.nit.dao.table6.T611_Dao;


public class T611_Service {
	
	private T611_Dao infoDao = new T611_Dao();
	
	//根据年参数取得相应年数据
	
	public T611_Bean getYearInfo(String selectYear){
				
		T611_Bean bean = infoDao.getYearInfo(selectYear);
		
		return bean;		
	}
	
	
	//保存
	public Boolean save(T611_Bean bean, String year, String fields){
		return infoDao.save(bean,year,fields);
	}

}
