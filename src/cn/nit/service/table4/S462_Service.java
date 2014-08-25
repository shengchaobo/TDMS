package cn.nit.service.table4;

import java.sql.SQLException;
import java.util.List;

import cn.nit.bean.table4.S46_Bean;
import cn.nit.dao.table4.S462_Dao;


public class S462_Service {
	
	private S462_Dao s462_dao= new S462_Dao();
	
	public List<S46_Bean> getYearInfo(String selectYear) throws SQLException{
		
		List<S46_Bean> list=s462_dao.getYearInfo(selectYear);
	
		//System.out.println(list.toString());
         
		return list;				
	}

}
