package cn.nit.service.table7;

import cn.nit.bean.table7.T743_Bean;
import cn.nit.dao.table7.T743_DAO;

public class T743_Service {
	
	T743_DAO t743_DAO=new T743_DAO();
	public boolean insert(T743_Bean courseBuildAssess){
		
		return t743_DAO.insert(courseBuildAssess);
		
		
	}
	

}
