package cn.nit.service.table2;

import java.util.List;


import cn.nit.bean.table2.T22_Bean;
import cn.nit.dao.table2.T22_Dao;


public class T22_Service {
	
	private T22_Dao infoDao = new T22_Dao();
	
	//根据年参数取得相应年数据
	
	public T22_Bean getYearInfo(String selectYear){
				
		T22_Bean list = infoDao.getYearInfo(selectYear);
		
		return list;		
	}
	
	
	//保存
	public Boolean save(T22_Bean bean, String year,	String fields){
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
