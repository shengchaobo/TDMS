package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.table4.S452_Bean;
import cn.nit.dao.table4.S452_Dao;



public class S452_Service {
	
	private S452_Dao s452_dao= new S452_Dao();
	
	public List<S452_Bean> getYearInfo(String selectYear){
		
		List<S452_Bean> list=s452_dao.getYearInfo(selectYear);
	
		//System.out.println(list.toString());
         
		return list;				
	}

}
