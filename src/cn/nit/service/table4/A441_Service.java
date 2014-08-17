package cn.nit.service.table4;

import cn.nit.bean.table4.A441_Bean;
import cn.nit.dao.table4.A441_Dao;



public class A441_Service {
	
	private A441_Dao a441_dao= new A441_Dao();
	
	public A441_Bean getYearInfo(String selectYear){
		
		A441_Bean bean=a441_dao.getYearInfo(selectYear);
	
		//System.out.println(list.toString());
         
		return bean;				
	}
	
	//保存
	public Boolean save(A441_Bean bean, String year){
		return a441_dao.save(bean,year);
	}
	
	
	public A441_Bean getData(String year){
		return a441_dao.getData(year);
	}

}
