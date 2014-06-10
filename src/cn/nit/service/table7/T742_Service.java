package cn.nit.service.table7;

import cn.nit.bean.table7.T742_Bean;
import cn.nit.dao.table7.T742_DAO;

public class T742_Service {
	
	T742_DAO t742_DAO=new T742_DAO();
	
	public boolean insert(T742_Bean teachLevelAssess){
		return t742_DAO.insert(teachLevelAssess);
		
	}

}
