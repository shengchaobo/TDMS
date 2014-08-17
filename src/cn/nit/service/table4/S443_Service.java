package cn.nit.service.table4;

import cn.nit.bean.table4.S443_Bean;
import cn.nit.dao.table4.S443_Dao;



public class S443_Service {
	
	private S443_Dao s443_dao= new S443_Dao();
	
	public S443_Bean getYearInfo(String selectYear){
		
		S443_Bean bean=s443_dao.getYearInfo(selectYear);
	
		//System.out.println(list.toString());
         
		return bean;				
	}
	
	//保存
	public Boolean save(S443_Bean bean, String year){
		return s443_dao.save(bean,year);
	}

}
