package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.table4.S443_Bean;
import cn.nit.dao.table4.S443_Dao;



public class S443_Service {
	
	private S443_Dao s443_dao= new S443_Dao();
	
	public List<S443_Bean> getYearInfo(String selectYear){
		
		List<S443_Bean> list=s443_dao.getYearInfo(selectYear);
	
		//System.out.println(list.toString());
         
		return list;				
	}

}
