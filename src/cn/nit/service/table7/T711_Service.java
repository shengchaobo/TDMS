package cn.nit.service.table7;

import cn.nit.bean.table7.T711_Bean;
import cn.nit.dao.table7.T711_DAO;

public class T711_Service {
	
	private T711_DAO teaManagerAwardInfoTeaTeaDAO=new T711_DAO();
	
	
	public boolean insert(T711_Bean teaManagerAwardInfoTeaTea){
		
		return teaManagerAwardInfoTeaTeaDAO.insert(teaManagerAwardInfoTeaTea);
		
	}

}
