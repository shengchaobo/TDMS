package cn.nit.service.table3;

import java.util.List;

import cn.nit.bean.table3.S322_Bean;
import cn.nit.dao.table3.S322_DAO;



public class S322_Service {
	
	private S322_DAO s322_dao= new S322_DAO();
	
	public List<S322_Bean> getYearInfo(String selectYear){
		
		List<S322_Bean> list=s322_dao.getData(selectYear);
	
		//System.out.println(list.toString());
         
		return list;
		
		
	}
	
	/**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<S322_Bean> totalList(String year){
		return s322_dao.totalList(year);
	}
}
