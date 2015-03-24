package cn.nit.service.table5;

import java.util.List;

import cn.nit.bean.table5.S533_Bean;
import cn.nit.dao.table5.S533_DAO;

public class S533_Service {
	
	S533_DAO s533_Dao=new S533_DAO();
	
	public List<S533_Bean> getYearInfo(String year){
		
		List<S533_Bean> list=s533_Dao.getData(year);
		
		return list;
		
	}
	
	 /**
	 * 获取字典表的所有数据
	 * @return
	 *
	 * @time: 2014-5-14/下午02:34:42
	 */
	public List<S533_Bean> totalList(String year){
		return s533_Dao.totalList(year);
	}

}
