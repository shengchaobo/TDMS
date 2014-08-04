package cn.nit.service.table3;

import java.util.List;

import cn.nit.bean.table3.S322_Bean;
import cn.nit.dao.table3.S322_DAO;



public class S322_Service {
	
	private S322_DAO s322_dao= new S322_DAO();
	
	public List<S322_Bean> getYearInfo(String selectYear){
		
		List<S322_Bean> list=s322_dao.getData(selectYear);
	
		//System.out.println(list.toString());
         
		return list;
		
		
	}
}
