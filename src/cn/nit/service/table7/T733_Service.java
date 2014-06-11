package cn.nit.service.table7;

import cn.nit.bean.table7.T733_Bean;
import cn.nit.dao.table7.T733_DAO;

public class T733_Service {
	
	T733_DAO t733_DAO=new T733_DAO();
	
	public boolean insert(T733_Bean eachUnit){
		
		return t733_DAO.insert(eachUnit);
	}

}
