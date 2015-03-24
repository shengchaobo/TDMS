package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.table4.S444_Bean;
import cn.nit.dao.table4.S444_Dao;



public class S444_Service {
	
	
	private S444_Dao s444_dao= new S444_Dao();
	
	public List<S444_Bean> getYearInfo(String selectYear){
		
		List<S444_Bean> list=s444_dao.getYearInfo(selectYear);
	
		//System.out.println(list.toString());
         
		return list;				
	}
	
	  /**
		 * 获取字典表的所有数据
		 * @return
		 *
		 * @time: 2014-5-14/下午02:34:42
		 */
		public List<S444_Bean> totalList(String year){
			return s444_dao.totalList(year);
		}

}
