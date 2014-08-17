package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.table4.A412_Bean;
import cn.nit.dao.table4.A412_Dao;



public class A412_Service {
	private A412_Dao a412_dao= new A412_Dao();
	
	public A412_Bean getYearInfo(String selectYear){
		
		A412_Bean bean=a412_dao.getYearInfo(selectYear);
	
		//System.out.println(list.toString());
         
		return bean;				
	}
	
	//保存
	public Boolean save(A412_Bean bean, String year){
		return a412_dao.save(bean,year);
	}
	
	public A412_Bean getData(String year){
		return a412_dao.getData(year);
	}

}
