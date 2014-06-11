package cn.nit.service.table7;

import cn.nit.bean.table7.T741_Bean;
import cn.nit.dao.table7.T741_DAO;

public class T741_Service {
	
	T741_DAO t741_DAO=new T741_DAO();
	
	public boolean insert(T741_Bean teachAbility){
		return t741_DAO.insert(teachAbility);
		
	}

}
