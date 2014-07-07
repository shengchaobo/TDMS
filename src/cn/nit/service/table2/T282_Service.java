package cn.nit.service.table2;

import java.util.List;


import cn.nit.bean.table2.T282_Bean;
import cn.nit.dao.table2.T282_Dao;


public class T282_Service {
	
	private T282_Dao infoDao = new T282_Dao();
	
	//根据年参数取得相应年数据
	
	public T282_Bean getYearInfo(String selectYear){
				
		T282_Bean bean = infoDao.getYearInfo(selectYear);
		
		return bean;		
	}
	
	
	//保存
	public Boolean save(T282_Bean bean, String year,	String fields){
		return infoDao.save(bean,year,fields);
	}

}
