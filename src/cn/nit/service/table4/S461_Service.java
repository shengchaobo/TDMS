package cn.nit.service.table4;

import java.sql.SQLException;
import java.util.List;

import cn.nit.bean.table4.S46_Bean;
import cn.nit.dao.table4.S461_Dao;


public class S461_Service {
	
	
	private S461_Dao s461_dao= new S461_Dao();
	
	public List<S46_Bean> getYearInfo(String selectYear) throws SQLException{
		
		List<S46_Bean> list=s461_dao.getYearInfo(selectYear);
	
		//System.out.println(list.toString());
         
		return list;				
	}
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<S46_Bean> totalList(String year){
		return s461_dao.totalList(year);
	}

}
