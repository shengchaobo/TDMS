package cn.nit.service.table7;

import cn.nit.bean.table7.T745_Bean;
import cn.nit.dao.table7.T745_DAO;

public class T745_Service {
	
	T745_DAO teachWorkAssessACDAO=new T745_DAO();
	
	public boolean insert(T745_Bean teachWorkAssessAC){
			return teachWorkAssessACDAO.insert(teachWorkAssessAC); 
	}
			

}
