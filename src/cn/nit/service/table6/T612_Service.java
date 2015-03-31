package cn.nit.service.table6;

import java.util.List;


import cn.nit.bean.table6.T612_Bean;
import cn.nit.dao.table6.T612_Dao;


public class T612_Service {
	
	private T612_Dao infoDao = new T612_Dao();
	
	//根据年参数取得相应年数据
	
	public T612_Bean getYearInfo(String selectYear){
				
		T612_Bean bean = infoDao.getYearInfo(selectYear);
		
		return bean;		
	}
	
	public T612_Bean getYearInfo(String selectYear,int checkState){
		
		T612_Bean bean = infoDao.getYearInfo(selectYear,checkState);
		
		return bean;		
	}
	
	//保存
	public Boolean save(T612_Bean bean, String year, String fields){
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
