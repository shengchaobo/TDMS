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
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<S25_Bean> totalList(String year){
		return s25_dao.totalList(year);
	}
	
	
}
