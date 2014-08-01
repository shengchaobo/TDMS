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

}
