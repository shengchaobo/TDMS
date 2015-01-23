package cn.nit.service.table6;

import java.util.List;


import cn.nit.bean.table6.T614_Bean;
import cn.nit.dao.table6.T614_Dao;


public class T614_Service {
	
	private T614_Dao infoDao = new T614_Dao();
	
	//根据年参数取得相应年数据
	
	public T614_Bean getYearInfo(String selectYear){
				
		T614_Bean bean = infoDao.getYearInfo(selectYear);
		
		return bean;		
	}
	
	
	//保存
	public Boolean save(T614_Bean bean, String year, String fields){
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
