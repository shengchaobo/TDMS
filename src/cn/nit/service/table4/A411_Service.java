package cn.nit.service.table4;

import cn.nit.bean.table4.A411_Bean;
import cn.nit.dao.table4.A411_Dao;



public class A411_Service {
	
	private A411_Dao a411_Dao = new A411_Dao();
	
	//根据年参数取得相应年数据
	
	public A411_Bean getYearInfo(String selectYear){
				
		A411_Bean bean = a411_Dao.getYearInfo(selectYear);
		
		return bean;		
	}
	
	
	//保存
	public Boolean save(A411_Bean bean, String year){
		return a411_Dao.save(bean,year);
	}




}
