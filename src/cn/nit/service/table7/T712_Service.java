package cn.nit.service.table7;

import cn.nit.bean.table7.T712_Bean;

import cn.nit.dao.table7.T712_DAO;

public class T712_Service {
	
	T712_DAO  teaManagerPaperInfoTeaDAO=new T712_DAO();
	
	public boolean insert(T712_Bean teaManagerPaperInfoTeaTea){
		
		return teaManagerPaperInfoTeaDAO.insert(teaManagerPaperInfoTeaTea);
	}

}
