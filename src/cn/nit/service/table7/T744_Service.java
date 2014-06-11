package cn.nit.service.table7;

import cn.nit.bean.table7.T744_Bean;
import cn.nit.dao.table7.T744_DAO;

public class T744_Service {

	T744_DAO t744_DAO=new T744_DAO();
	
	public boolean insert(T744_Bean t744_B){
		return t744_DAO.insert(t744_B);
		
	}
}
