package cn.nit.service.table4;

import java.util.List;

import cn.nit.bean.table4.A412_Bean;
import cn.nit.dao.table4.A412_Dao;



public class A412_Service {
	private A412_Dao a412_dao= new A412_Dao();
	
	public A412_Bean getYearInfo(int year){
		
		A412_Bean bean=a412_dao.getYearInfo(year);
         
		return bean;				
	}
	
	//保存
	public Boolean save(A412_Bean bean){
		return a412_dao.save(bean);
	}
	
	public A412_Bean getData(){
		return a412_dao.getData();
	}

}
