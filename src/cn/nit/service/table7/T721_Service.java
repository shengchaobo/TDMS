package cn.nit.service.table7;

import cn.nit.bean.table7.T721_Bean;
import cn.nit.dao.table7.T721_DAO;

public class T721_Service {

	T721_DAO teachResItemTeaDAO=new T721_DAO();
	
	public boolean insert(T721_Bean teachResItemTea){
		
		return teachResItemTeaDAO.insert(teachResItemTea);
		
	}
}
