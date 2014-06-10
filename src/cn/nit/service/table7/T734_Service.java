package cn.nit.service.table7;

import cn.nit.bean.table7.T734_Bean;
import cn.nit.dao.table7.T734_DAO;

public class T734_Service {
	
	T734_DAO t734_DAO=new T734_DAO();
	
	public boolean insert(T734_Bean teachAccident){
		
		return t734_DAO.insert(teachAccident);
	}

}
