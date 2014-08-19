package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.table4.S453_Bean;
import cn.nit.dao.table4.S453_Dao;



public class S453_Service {
	
	private S453_Dao s453_dao= new S453_Dao();
	
	public List<S453_Bean> getYearInfo(String selectYear){
		
		List<S453_Bean> list=s453_dao.getYearInfo(selectYear);
	
		//System.out.println(list.toString());
         
		return list;				
	}

}
