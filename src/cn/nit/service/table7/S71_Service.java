package cn.nit.service.table7;

import java.util.List;

import cn.nit.bean.table7.S71_Bean;
import cn.nit.dao.table7.S71_DAO;

public class S71_Service {
	
	private S71_DAO s71_dao= new S71_DAO();
	
	public List<S71_Bean> getYearInfo(String selectYear){
		
		List<S71_Bean> list=s71_dao.getData(selectYear);
		return list;		
	}
	public S71_Bean getYearIf(String selectYear){
		S71_Bean bean= s71_dao.getYearInfo(selectYear);
		return bean;
	}
	
	 /**
		 * 获取字典表的所有数据
		 * @return
		 *
		 * @time: 2014-5-14/下午02:34:42
		 */
		public List<S71_Bean> totalList(String year){
			return s71_dao.totalList(year);
		}
}
