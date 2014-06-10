package cn.nit.service.table7;

import cn.nit.bean.table7.T731_Bean;
import cn.nit.dao.table7.T731_DAO;

public class T731_Service {

	T731_DAO schleadInClassTnfoTeaDAO=new T731_DAO();
	
	public boolean insert(T731_Bean schleadInClassTnfo){
		return schleadInClassTnfoTeaDAO.insert(schleadInClassTnfo);
	}
}
