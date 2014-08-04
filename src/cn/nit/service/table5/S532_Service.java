package cn.nit.service.table5;

import java.util.List;

import cn.nit.bean.table5.S532_Bean;
import cn.nit.dao.table5.S532_DAO;

public class S532_Service {
	
	S532_DAO s532_Dao=new S532_DAO();
	
	public List<S532_Bean> getYearInfo(String year){
		
		List<S532_Bean> list=s532_Dao.getData(year);
		
		return list;
		
	}

}
