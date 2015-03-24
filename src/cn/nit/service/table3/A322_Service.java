package cn.nit.service.table3;

import java.util.Collection;
import java.util.List;

import cn.nit.bean.table3.A322_Bean;
import cn.nit.dao.table3.A322_DAO;



public class A322_Service {
	
	
	private A322_DAO a322_dao= new A322_DAO();
	
	public List<A322_Bean> getYearInfo(String selectYear){
		
	List<A322_Bean> list = a322_dao.getData(selectYear);

         
		return list;
		
		
	}
	
	 /**
		 * 获取字典表的所有数据
		 * @return
		 *
		 * @time: 2014-5-14/下午02:34:42
		 */
		public List<A322_Bean> totalList(String year){
			return a322_dao.totalList(year);
		}

}
