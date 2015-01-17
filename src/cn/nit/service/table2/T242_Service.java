package cn.nit.service.table2;

import java.util.List;


import cn.nit.bean.table2.T242_Bean;
import cn.nit.dao.table2.T242_Dao;


public class T242_Service {
	
	private T242_Dao infoDao = new T242_Dao();
	
	//根据年参数取得相应年数据
	
	public T242_Bean getYearInfo(String selectYear){
				
		T242_Bean bean = infoDao.getYearInfo(selectYear);
		
		return bean;		
	}
	
	
	//保存
	public Boolean save(T242_Bean bean, String year,	String fields){
		return infoDao.save(bean,year,fields);
	}
	
	/**
	 * 更新该条数据审核状态
	 * @param 
	 * @return
	 */
	public boolean updateCheck(String selectYear, int checkState){
		return infoDao.updateCheck(selectYear,checkState) ;
	}

}
