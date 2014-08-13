package cn.nit.service.table2;

import java.util.List;

import cn.nit.bean.table2.S25_Bean;
import cn.nit.dao.table2.S25_Dao;

public class S25_Service {
	
	private S25_Dao s25_dao= new S25_Dao();
	
	public List<S25_Bean> getYearInfo(String selectYear){
		
		List<S25_Bean> list=s25_dao.getYearInfo(selectYear);
	
		//System.out.println(list.toString());
         
		return list;				
	}
}
