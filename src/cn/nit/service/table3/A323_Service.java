package cn.nit.service.table3;

import java.util.List;

import cn.nit.bean.table3.A323_Bean;
import cn.nit.dao.table3.A323_DAO;


public class A323_Service {
	
	private A323_DAO a323_dao= new A323_DAO();
	
	public List<A323_Bean> getYearInfo(String selectYear){
		
	List<A323_Bean> list = a323_dao.getData(selectYear);

         
		return list;
		
		
	}

}
