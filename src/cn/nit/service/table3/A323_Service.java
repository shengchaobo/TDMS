package cn.nit.service.table3;

import java.util.List;

import cn.nit.bean.table3.A323_Bean;
import cn.nit.dao.table3.A323_DAO;


public class A323_Service {
	
	private A323_DAO a323_dao= new A323_DAO();
	
	public List<A323_Bean> getYearInfo(String selectYear){
		
	List<A323_Bean> list = a323_dao.getData(selectYear);

         
		return list;
		
		
	}
	
	  /**
		 * 获取字典表的所有数据
		 * @return
		 *
		 * @time: 2014-5-14/下午02:34:42
		 */
		public List<A323_Bean> totalList(String year){
			return a323_dao.totalList(year);
		}

}
