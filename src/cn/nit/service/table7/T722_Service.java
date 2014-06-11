package cn.nit.service.table7;

import cn.nit.bean.table7.T722_Bean;
import cn.nit.dao.table7.T722_DAO;

public class T722_Service {
	
	T722_DAO teachAchieveAwardTeaDAO=new T722_DAO();
	
	public boolean insert(T722_Bean teachAchieveAwardTea){
		return teachAchieveAwardTeaDAO.insert(teachAchieveAwardTea);
		
	}

}
