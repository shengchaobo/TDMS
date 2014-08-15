package cn.nit.service.table4;

import cn.nit.bean.table4.A413_Bean;
import cn.nit.dao.table4.A413_Dao;



public class A413_Service {
	
	private A413_Dao a413_dao= new A413_Dao();
	
	public A413_Bean getYearInfo(String selectYear){
		
		A413_Bean bean=a413_dao.getYearInfo(selectYear);
	
		//System.out.println(list.toString());
         
		return bean;				
	}
	
	//保存
	public Boolean save(A413_Bean bean, String year){
		return a413_dao.save(bean,year);
	}

}
