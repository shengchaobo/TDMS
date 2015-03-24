package cn.nit.service.table3;

import java.util.List;

import cn.nit.bean.table3.S321_Bean;
import cn.nit.dao.table3.S321_DAO;



public class S321_Service {
	
	private S321_DAO s321_dao= new S321_DAO();
	
	public List<S321_Bean> getYearInfo(String selectYear){
		
		List<S321_Bean> list=s321_dao.getData(selectYear);
	
		//System.out.println(list.toString());
         
		return list;
		
		
	}
	
	  /**
		 * 获取字典表的所有数据
		 * @return
		 *
		 * @time: 2014-5-14/下午02:34:42
		 */
		public List<S321_Bean> totalList(String year){
			return s321_dao.totalList(year);
		}
}
