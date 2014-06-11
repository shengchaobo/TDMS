package cn.nit.service.table7;

import cn.nit.bean.table7.T735_Bean;
import cn.nit.dao.table7.T735_DAO;

public class T735_Service {
	
	T735_DAO teachManageAssessInfoTeaDAO =new T735_DAO();
	
	public boolean insert(T735_Bean teachManageAssessInfoTea){
		return teachManageAssessInfoTeaDAO.insert(teachManageAssessInfoTea);
	}

}
