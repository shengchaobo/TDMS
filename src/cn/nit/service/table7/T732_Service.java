package cn.nit.service.table7;

import cn.nit.bean.table7.T732_Bean;
import cn.nit.dao.table7.T732_DAO;

public class T732_Service {
	
	T732_DAO t732_DAO=new T732_DAO();
	
	public boolean insert(T732_Bean teaLeadInClass){
		return t732_DAO.insert(teaLeadInClass);
	}

}
